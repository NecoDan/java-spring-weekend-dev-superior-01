package br.com.spring.rest.dev.superior.weekend.product.model;

import br.com.spring.rest.dev.superior.weekend.category.model.Category;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_product", schema = "dev_pedidos")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @Column(name = "imageUri")
    private String imageUri;

    @ManyToOne
    @JoinColumn(columnDefinition = "category_id")
    private Category category;

    public String toStringProduct() {
        return this.id + " - " + this.name;
    }
}
