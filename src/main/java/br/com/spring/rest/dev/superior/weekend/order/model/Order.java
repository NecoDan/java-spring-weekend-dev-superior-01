package br.com.spring.rest.dev.superior.weekend.order.model;

import br.com.spring.rest.dev.superior.weekend.product.model.Product;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Entity
@Table(name = "tb_order", schema = "dev_pedidos")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "longitude")
    private BigDecimal longitude;

    @Column(name = "adress")
    private String adress;

    @Column(name = "moment")
    private Instant moment;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private OrderStatus orderStatus;

    @ManyToMany
    @JoinTable(name = "tb_order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products;

    @Transient
    private BigDecimal valorTotal;

    public void inicializeProducts() {
        if (Objects.isNull(this.products))
            this.products = new HashSet<>();
    }
}
