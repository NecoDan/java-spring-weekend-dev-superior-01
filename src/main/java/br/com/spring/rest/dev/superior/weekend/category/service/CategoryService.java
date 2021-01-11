package br.com.spring.rest.dev.superior.weekend.category.service;

import br.com.spring.rest.dev.superior.weekend.category.dto.CategoryModel;
import br.com.spring.rest.dev.superior.weekend.category.model.Category;
import br.com.spring.rest.dev.superior.weekend.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public List<CategoryModel> findAllCategoryModel() {
        return findAll().stream().filter(Objects::nonNull).map(this::toCategoryModel).collect(Collectors.toList());
    }

    @Override
    public CategoryModel toCategoryModel(Category category) {
        return this.modelMapper.map(category, CategoryModel.class);
    }
}
