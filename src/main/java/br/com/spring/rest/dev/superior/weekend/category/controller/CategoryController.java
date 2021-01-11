package br.com.spring.rest.dev.superior.weekend.category.controller;

import br.com.spring.rest.dev.superior.weekend.category.dto.CategoryModel;
import br.com.spring.rest.dev.superior.weekend.category.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categorys")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;

    @GetMapping
    public List<CategoryModel> listAll() {
        return categoryService.findAllCategoryModel();
    }
}
