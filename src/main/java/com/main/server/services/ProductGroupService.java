package com.main.server.services;

import com.main.server.mapping.ProductGroupMapping;
import com.main.server.model.enumerations.EType;
import com.main.server.repository.ProductGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductGroupService {
    @Autowired
    ProductGroupRepository productGroupRepository;
    public List<Object> getAllGroups() {
        List<Object> data = new ArrayList<>();
        for (EType i : EType.values()) {
            Map<String, Object> dataType = new HashMap<>();
            dataType.put("name", i.toString().toLowerCase());
            dataType.put("groups", productGroupRepository.findProductGroupByType(i).stream().map(ProductGroupMapping::bind));
            data.add(dataType);
        }
        return data;
    }
}
