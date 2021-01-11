package br.com.spring.rest.dev.superior.weekend.category.repository;

import br.com.spring.rest.dev.superior.weekend.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
