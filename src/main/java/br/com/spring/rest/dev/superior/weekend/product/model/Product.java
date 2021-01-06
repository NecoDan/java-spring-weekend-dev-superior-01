package br.com.spring.rest.dev.superior.weekend.product.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String imageUri;
}
