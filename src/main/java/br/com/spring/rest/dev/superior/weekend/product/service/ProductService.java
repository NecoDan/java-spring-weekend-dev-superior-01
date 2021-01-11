package br.com.spring.rest.dev.superior.weekend.product.service;

import br.com.spring.rest.dev.superior.weekend.product.dto.ProductModel;
import br.com.spring.rest.dev.superior.weekend.product.dto.ProductSummaryModel;
import br.com.spring.rest.dev.superior.weekend.product.model.Product;
import br.com.spring.rest.dev.superior.weekend.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public ProductModel findByIdProductModel(Long id) {
        return null;
    }

    @Override
    public List<ProductSummaryModel> findAllProductSummaryModel() {
        return findAll().stream().filter(Objects::nonNull).map(this::toProductSummaryModel).collect(Collectors.toList());
    }

    @Override
    public ProductSummaryModel toProductSummaryModel(Product product) {
        return this.modelMapper.map(product, ProductSummaryModel.class);
    }

    @Override
    public ProductModel toProductModel(Product product) {
        return this.modelMapper.map(product, ProductModel.class);
    }
}
