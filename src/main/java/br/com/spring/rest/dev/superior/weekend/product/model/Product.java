package br.com.spring.rest.dev.superior.weekend.product.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
@Builder
public class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String imageUri;

    public String toStringProduct() {
        return this.id + " - " + this.name;
    }
}
