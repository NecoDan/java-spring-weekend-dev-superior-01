package br.com.spring.rest.dev.superior.weekend.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductSummaryModel {
    private Long id;
    private String name;
    private String nameCategory;
}
