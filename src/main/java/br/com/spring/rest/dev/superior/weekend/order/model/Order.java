package br.com.spring.rest.dev.superior.weekend.order.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class Order {
    private Long id;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String adress;
    private Instant moment;
    private OrderStatus orderStatus;
    private BigDecimal valorTotal;
}
