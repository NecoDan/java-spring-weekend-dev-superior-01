package br.com.spring.rest.dev.superior.weekend.product.dto;

import br.com.spring.rest.dev.superior.weekend.category.dto.CategoryModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductModel {
    private Long id;
    private String name;
    private CategoryModel category;
}
