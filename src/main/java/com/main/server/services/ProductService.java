package com.main.server.services;

import com.main.server.mapping.ProductColorMapping;
import com.main.server.mapping.ProductMapping;

import com.main.server.mapping.ProductSizeMapping;
import com.main.server.model.dto.ProductDTO;
import com.main.server.model.entities.*;
import com.main.server.model.enumerations.ETag;
import com.main.server.model.enumerations.EType;
import com.main.server.model.request.NewProductRequest;
import com.main.server.repository.*;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCategoryRepository productGroupRepository;

    @Autowired
    ProductModelRepository productModelRepository;

    @Autowired
    ProductSizeRepository productSizeRepository;

    @Autowired
    ProductColorRepository productColorRepository;

    public Map<String, Object> getAllProducts(int page, int limit, String group, String category, @Nullable String color, @Nullable String size, @Nullable String tag, @Nullable String minPrice, @Nullable String maxPrice) {
        Map<String, Object> data = new HashMap<>();
        Pageable pageable = PageRequest.of(page - 1, limit);

        Page<Product> products = buildDynamicQuery(group, category, color, size, tag, minPrice, maxPrice, pageable);

        data.put("products", products.stream().map(ProductMapping::bind));
        data.put("page", page);
        data.put("totalPages", (int) Math.ceil((double) products.getTotalElements() / limit));

        return data;
    }

    private Page<Product> buildDynamicQuery(String group, String category, String color, String size, String tag, String minPrice, String maxPrice, Pageable pageable) {
        if (color != null) {
            if (size != null) {
                if (tag != null) {
                    if (minPrice != null && maxPrice != null) {
                        return productRepository.findByColorAndSizeTagAndPrice(
                                EType.valueOf(group), category, color, size, ETag.valueOf(tag),
                                Double.parseDouble(minPrice), Double.parseDouble(maxPrice), pageable
                        );
                    } else {
                        return productRepository.findByColorAndSizeAndTag(
                                EType.valueOf(group), category, color, size, ETag.valueOf(tag), pageable
                        );
                    }
                } else if (minPrice != null && maxPrice != null) {
                    return productRepository.findByColorAndSizeAndPrice(
                            EType.valueOf(group), category, color, size,
                            Double.parseDouble(minPrice), Double.parseDouble(maxPrice), pageable
                    );
                } else {
                    return productRepository.findByColorAndSize(
                            EType.valueOf(group), category, color, size, pageable
                    );
                }
            } else if (tag != null) {
                if (minPrice != null && maxPrice != null) {
                    return productRepository.findByColorAndTagAndPrice(
                            EType.valueOf(group), category, color, ETag.valueOf(tag),
                            Double.parseDouble(minPrice), Double.parseDouble(maxPrice), pageable
                    );
                } else {
                    return productRepository.findByColorAndTag(
                            EType.valueOf(group), category, color, ETag.valueOf(tag), pageable
                    );
                }
            } else if (minPrice != null && maxPrice != null) {
                return productRepository.findByColorAndRangePrice(
                        EType.valueOf(group), category, color,
                        Double.parseDouble(minPrice), Double.parseDouble(maxPrice), pageable
                );
            } else {
                return productRepository.findByColor(EType.valueOf(group), category, color, pageable);
            }
        } else if (size != null) {
            if (tag != null) {
                if (minPrice != null && maxPrice != null) {
                    return productRepository.findBySizeAndTagAndPrice(
                            EType.valueOf(group), category, size, ETag.valueOf(tag),
                            Double.parseDouble(minPrice), Double.parseDouble(maxPrice), pageable
                    );
                } else {
                    return productRepository.findBySizeAndTag(
                            EType.valueOf(group), category, size, ETag.valueOf(tag), pageable
                    );
                }
            } else if (minPrice != null && maxPrice != null) {
                return productRepository.findBySizeAndRangePrice(
                        EType.valueOf(group), category, size,
                        Double.parseDouble(minPrice), Double.parseDouble(maxPrice), pageable
                );
            } else {
                return productRepository.findBySize(EType.valueOf(group), category, size, pageable);
            }
        } else if (tag != null) {
            if (minPrice != null && maxPrice != null) {
                return productRepository.findByTagAndRangePrice(
                        EType.valueOf(group), category, ETag.valueOf(tag),
                        Double.parseDouble(minPrice), Double.parseDouble(maxPrice), pageable
                );
            } else {
                return productRepository.findByTag(EType.valueOf(group), category, ETag.valueOf(tag), pageable);
            }
        } else if (minPrice != null && maxPrice != null) {
            return productRepository.findByRangePrice(
                    EType.valueOf(group), category, Double.parseDouble(minPrice), Double.parseDouble(maxPrice), pageable
            );
        } else {
            return productRepository.findByCategory(EType.valueOf(group), category, pageable);
        }
    }


    public ProductDTO getProductById(int id) {
        Product product = productRepository.findById(id).orElse(null);
        return ProductMapping.bind(product);
    }

    public boolean addProduct(NewProductRequest newProduct) {
        try {
            Product product = new Product();
            product.setName(newProduct.getName());
            product.setDescription(newProduct.getDescription());
            product.setBasePrice(newProduct.getBasePrice());
            product.setSalePrice(newProduct.getSalePrice());
//            product.setTag(ETag.NEW);
            List<ProductSize> productSizes = new ArrayList<>();
            for (ProductSize size : newProduct.getSizes()) {
                ProductSize productSize = new ProductSize();
                productSize.setName(size.getName());
                productSize.setExtraCoefficient(size.getExtraCoefficient());
                productSize.setProduct(product); // Set the relationship
                productSizes.add(productSize);
            }
            List<ProductColor> productColors = new ArrayList<>();
            for (ProductColor color : newProduct.getColors()) {
                ProductColor productColor = new ProductColor();
                productColor.setName(color.getName());
                productColor.setSrc(color.getSrc());
                productColor.setExtraCoefficient(color.getExtraCoefficient());
                productColor.setProduct(product); // Set the relationship
                productColors.add(productColor);
            }
            List<ProductImage> productImages = new ArrayList<>();
            for(ProductImage image : newProduct.getImages()) {
                ProductImage productImage = new ProductImage();
                productImage.setName(image.getName());
                productImage.setSrc(image.getSrc());
                productImage.setProduct(product);
                productImages.add(productImage);
            }
            product.setProductSizes(productSizes);
            product.setProductColors(productColors);
            product.setProductImages(productImages);

            product.setCategory(productGroupRepository.findById(newProduct.getCategory()).orElse(null));
            product.setModel(productModelRepository.findById(newProduct.getCategory()).orElse(null));

            List<ProductUpdateRecord> productUpdateRecords = new ArrayList<>();
            productUpdateRecords.add(
                    new ProductUpdateRecord(
                            new Date(),
                            newProduct.getUpdateDescription(),
                            product
                    )
            );
            product.setUpdateRecords(productUpdateRecords);
            productRepository.save(product);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Map<String, Object> getFilterOptions(String category) {
        Map<String, Object> data = new HashMap<>();
        data.put("sizes", productSizeRepository.findSizeOptions(category).stream().map(ProductSizeMapping::bind));
        data.put("colors", productColorRepository.findColorOptions(category).stream().map(ProductColorMapping::bind));
        return data;
    }
}
