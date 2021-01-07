package br.com.spring.rest.dev.superior.weekend.order.model;

import br.com.spring.rest.dev.superior.weekend.product.model.Product;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
public class Order {
    private Long id;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Instant moment;
    private OrderStatus orderStatus;
    private BigDecimal valorTotal;
    private List<Product> products;

    public void instanceListProducts() {
        if (Objects.isNull(this.products))
            this.products = new ArrayList<>();
    }
}
