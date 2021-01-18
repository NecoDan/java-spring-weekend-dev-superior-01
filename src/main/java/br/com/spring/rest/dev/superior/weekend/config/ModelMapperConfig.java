package br.com.spring.rest.dev.superior.weekend.config;

import br.com.spring.rest.dev.superior.weekend.product.dto.ProductSummaryModel;
import br.com.spring.rest.dev.superior.weekend.product.model.Product;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper
                .createTypeMap(Product.class, ProductSummaryModel.class)
                .<String>addMapping(product -> product.getCategory().getName(),
                        ProductSummaryModel::setNameCategory
                );

        return new ModelMapper();
    }
}
