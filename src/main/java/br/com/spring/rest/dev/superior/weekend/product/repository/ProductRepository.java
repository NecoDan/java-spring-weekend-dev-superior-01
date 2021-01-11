package br.com.spring.rest.dev.superior.weekend.product.repository;

import br.com.spring.rest.dev.superior.weekend.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
