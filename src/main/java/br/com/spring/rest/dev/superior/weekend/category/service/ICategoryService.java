package br.com.spring.rest.dev.superior.weekend.category.service;

import br.com.spring.rest.dev.superior.weekend.category.dto.CategoryModel;
import br.com.spring.rest.dev.superior.weekend.category.model.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> findAll();

    List<CategoryModel> findAllCategoryModel();

    CategoryModel toCategoryModel(Category category);
}
