package br.com.spring.rest.dev.superior.weekend.product.service;

import br.com.spring.rest.dev.superior.weekend.product.dto.ProductModel;
import br.com.spring.rest.dev.superior.weekend.product.dto.ProductSummaryModel;
import br.com.spring.rest.dev.superior.weekend.product.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    List<ProductSummaryModel> findAllProductSummaryModel();

    List<Product> findAll();

    Optional<Product> findById(Long id);

    ProductModel findByIdProductModel(Long id);

    ProductSummaryModel toProductSummaryModel(Product product);

    ProductModel toProductModel(Product product);
}
