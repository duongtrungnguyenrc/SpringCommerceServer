package com.main.server.models.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    private List<Integer> productIds;
}
