package br.com.spring.rest.dev.superior.weekend.product.controller;

import br.com.spring.rest.dev.superior.weekend.product.dto.ProductModel;
import br.com.spring.rest.dev.superior.weekend.product.dto.ProductSummaryModel;
import br.com.spring.rest.dev.superior.weekend.product.model.Product;
import br.com.spring.rest.dev.superior.weekend.product.service.IProductService;
import br.com.spring.rest.dev.superior.weekend.util.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @GetMapping
    public List<ProductSummaryModel> listAll() {
        return productService.findAllProductSummaryModel();
    }

    @GetMapping("/id")
    public ProductModel findById(@PathVariable("id") Long id) {
        Product product = this.productService.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        return this.productService.toProductModel(product);
    }
}
