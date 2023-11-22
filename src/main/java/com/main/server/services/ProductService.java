package com.main.server.services;

import com.main.server.mapping.ProductColorMapping;
import com.main.server.mapping.ProductMapping;

import com.main.server.mapping.ProductSizeMapping;
import com.main.server.models.dto.ProductColorDTO;
import com.main.server.models.dto.ProductDTO;
import com.main.server.models.dto.ProductImageDTO;
import com.main.server.models.dto.ProductSizeDTO;
import com.main.server.models.entities.*;
import com.main.server.models.enumerations.ETag;
import com.main.server.models.enumerations.EType;
import com.main.server.models.request.NewProductRequest;
import com.main.server.models.request.UpdateProductRequest;
import com.main.server.models.response.Response;
import com.main.server.repositories.*;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    ProductImageRepository productImageRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    public Map<String, Object> getAll(int page,
                                      int limit,
                                      @Nullable String group,
                                      @Nullable String category,
                                      @Nullable String color,
                                      @Nullable String size,
                                      @Nullable String tag,
                                      @Nullable String minPrice,
                                      @Nullable String maxPrice) {

        Map<String, Object> data = new HashMap<>();
        Pageable pageable = PageRequest.of(page - 1, limit);

        Page<Product> products;

        if(group == null || group.isEmpty() || category == null || category.isEmpty()) {
            products = productRepository.findByCategory(pageable);
        }
        else {
            products = buildDynamicQuery(group, category, color, size, tag, minPrice, maxPrice, pageable);
        }

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

    public Object getSpecialProductsByTag(String tag) {
        try {
            List<Object> data = new ArrayList<>();
            Arrays.stream(EType.values()).toList().forEach(type -> {
                Map<String, Object> dataByType = new HashMap<>();
                dataByType.put("type", type.toString());
                dataByType.put("products", productRepository.findByTag(ETag.valueOf(tag), type, PageRequest.of(1, 10)).stream().map(ProductMapping::bind));
                data.add(dataByType);
            });

            return ResponseEntity.ok(
                    new Response(
                            "Lấy dữ liệu thành công",
                            data
                    )
            );
        }
        catch (Exception e) {
            return ResponseEntity.ok(
                    new Response(
                            "Lấy dữ liệu thất bại: " + e.getMessage(),
                            null
                    )
            );
        }
    }
    public ProductDTO getProductById(int id) {
        Product product = productRepository.findById(id).orElse(null);
        return ProductMapping.bind(product);
    }

    public ResponseEntity<Response> addProduct(NewProductRequest newProduct) {
        try {
            Product productEntity = generateEntity(newProduct);
            Product savedProduct = productRepository.save(productEntity);
            return ResponseEntity.ok(
                    new Response(
                            "thêm sản phẩm thành công",
                            savedProduct
                    )
            );
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new Response(
                            "thêm sản phẩm thất bại: " + e.getMessage(),
                            null
                    )
            );
        }
    }

    public ResponseEntity<Response> addProducts(List<NewProductRequest> newProducts) {
        List<Product> products = new ArrayList<>();
        for (NewProductRequest newProduct : newProducts ){
            try {
                Product productEntity = generateEntity(newProduct);
                products.add(productEntity);
            }
            catch (Exception e) {

            }
        }
        try {
            List<Product> savedProducts = productRepository.saveAll(products);
            return ResponseEntity.ok(
                    new Response(
                            "thêm sản phẩm thành công",
                            savedProducts
                    )
            );
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new Response(
                            "thêm sản phẩm thất bại: " + e.getMessage(),
                            null
                    )
            );
        }
    }

    private Product generateEntity(NewProductRequest newProduct) throws Exception{
        try {
            Product product = new Product();
            product.setName(newProduct.getName());
            product.setDescription(newProduct.getDescription());
            product.setBasePrice(newProduct.getBasePrice());
            product.setSalePrice(newProduct.getSalePrice());
            product.setTag(ETag.valueOf("NEW"));
            List<ProductSize> productSizes = new ArrayList<>();
            for (ProductSizeDTO size : newProduct.getSizes()) {
                productSizes.add(new ProductSize(null, size.getName(), size.getExtraCoefficient(), product));
            }
            List<ProductColor> productColors = new ArrayList<>();
            for (ProductColorDTO color : newProduct.getColors()) {
                productColors.add(new ProductColor(null, color.getName(), color.getSrc(), color.getExtraCoefficient(), product));
            }

            List<ProductImage> productImages = new ArrayList<>();
            for(ProductImageDTO image : newProduct.getImages()) {
                productImages.add(new ProductImage(null, image.getName(), image.getSrc(), product));
            }

            product.setProductSizes(productSizes);
            product.setProductColors(productColors);
            product.setProductImages(productImages);

            product.setValid(true);

            product.setCategory(productGroupRepository.findById(newProduct.getCategory()).orElse(null));
            product.setModel(productModelRepository.findById(newProduct.getCategory()).orElse(null));

            List<ProductUpdateRecord> productUpdateRecords = new ArrayList<>();
            productUpdateRecords.add(
                    new ProductUpdateRecord(
                            new Date(),
                            "thêm mới",
                            product
                    )
            );
            product.setUpdateRecords(productUpdateRecords);
            return product;

        }
        catch (Exception e) {
            throw new Exception(e);
        }
    }

    public Map<String, Object> getFilterOptions(@Nullable String category, @Nullable String group) {
        Map<String, Object> data = new HashMap<>();
        if(category != null && !category.isEmpty() && group != null && !group.isEmpty()) {
            data.put("sizes", productSizeRepository.findSizesByCategory(category, EType.valueOf(group)).stream().map(ProductSizeMapping::bind));
            data.put("colors", productColorRepository.findColorOptions(category, EType.valueOf(group)).stream().map(ProductColorMapping::bind));
        }
        else {
            data.put("sizes", productSizeRepository.findSizesByCategory().stream().map(ProductSizeMapping::bind));
            data.put("colors", productColorRepository.findColorOptions().stream().map(ProductColorMapping::bind));
        }
        return data;
    }

    public Object updateProduct(UpdateProductRequest updateProduct) {
        Product currentProduct = productRepository.findById(updateProduct.getId()).orElse(null);

        if(currentProduct != null) {
            currentProduct.setName(updateProduct.getName());
            currentProduct.setBasePrice(updateProduct.getBasePrice());
            currentProduct.setDescription(updateProduct.getDescription());
            currentProduct.setTag(ETag.valueOf(updateProduct.getTag()));
            currentProduct.setSalePrice(updateProduct.getSalePrice());

            List<ProductColor> colors = new ArrayList<>();
            for(ProductColorDTO DTOColor : updateProduct.getColors()) {
                if(DTOColor.getId() != null) {
                    ProductColor color = productColorRepository.findById(DTOColor.getId()).orElse(null);

                    assert color != null;
                    color.setName(DTOColor.getName());
                    color.setSrc(DTOColor.getSrc());
                    color.setExtraCoefficient(DTOColor.getExtraCoefficient());
                    productColorRepository.save(color);
                }
                else {
                    colors.add(new ProductColor(null, DTOColor.getName(), DTOColor.getSrc(), DTOColor.getExtraCoefficient(), currentProduct));
                }
            }
            currentProduct.setProductColors(colors);

            List<ProductSize> sizes = new ArrayList<>();
            for(ProductSizeDTO DTOSize : updateProduct.getSizes()) {
               if(DTOSize.getId() != null) {
                   ProductSize size = productSizeRepository.findById(DTOSize.getId()).orElse(null);

                   assert size != null;
                   size.setName(DTOSize.getName());
                   size.setExtraCoefficient(DTOSize.getExtraCoefficient());
                   productSizeRepository.save(size);
               }
               else {
                   sizes.add(new ProductSize(null, DTOSize.getName(), DTOSize.getExtraCoefficient(), currentProduct));
               }
            }
            currentProduct.setProductSizes(sizes);

            List<ProductImage> images = new ArrayList<>();
            for(ProductImageDTO DTOImage : updateProduct.getImages()) {
                if(DTOImage.getId() != null) {
                    ProductImage image = productImageRepository.findById(DTOImage.getId()).orElse(null);

                    assert image != null;
                    image.setName(DTOImage.getName());
                    image.setSrc(DTOImage.getSrc());
                    productImageRepository.save(image);
                }
                else {
                    images.add(new ProductImage(null, DTOImage.getName(), DTOImage.getSrc(), currentProduct));
                }
            }
            currentProduct.setProductImages(images);

            if(currentProduct.getModel().getId() != updateProduct.getModel()) {
                ProductModel model = productModelRepository.findById(updateProduct.getModel()).orElse(null);
                currentProduct.setModel(model);
            }
            if(currentProduct.getCategory().getId() != updateProduct.getCategory()){
                ProductCategory category = productCategoryRepository.findById(updateProduct.getCategory()).orElse(null);
                currentProduct.setCategory(category);
            }

            List<ProductUpdateRecord> records = new ArrayList<>(currentProduct.getUpdateRecords().stream().toList());
            records.add(new ProductUpdateRecord(null, new Date(), updateProduct.getUpdateDescription(), currentProduct));

            currentProduct.setUpdateRecords(records);

            try {
                productRepository.save(currentProduct);
                return ResponseEntity.ok(
                        new Response(
                                "Cập nhật sản phẩm thành công",
                                ProductMapping.bind(currentProduct)
                        )
                );
            }
            catch (Exception e) {
                return ResponseEntity.badRequest().body(
                        new Response(
                                e.getMessage(),
                                null
                        )
                );
            }
        }
        else {
            return ResponseEntity.badRequest().body(
                    new Response(
                            "Không thể cập nhật sản phẩm",
                            null
                    )
            );
        }
    }

    public Object deleteProduct(int id) {
        try {
            productRepository.deleteById(id);
            return ResponseEntity.ok(
                    new Response(
                            "Xóa sản phẩm thành công!",
                            true
                    )
            );
        }
        catch (Exception e) {
            return ResponseEntity.ok(
                    new Response(
                            "Xóa sản phẩm thất bại!",
                            e.getMessage()
                    )
            );
        }

    }

}

