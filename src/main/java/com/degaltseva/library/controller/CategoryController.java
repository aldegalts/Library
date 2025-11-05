package com.degaltseva.library.controller;

import com.degaltseva.library.entity.CategoryEntity;
import com.degaltseva.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для управления категориями книг.
 * Отображает список категорий, позволяет добавлять, редактировать и удалять категории.
 */
@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listCategories(Model model) {
        List<CategoryEntity> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "category/categories";
    }

    @GetMapping("/{id}")
    public String viewCategory(@PathVariable Long id, Model model) {
        CategoryEntity category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "category/category-details";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("category", new CategoryEntity());
        return "category/category-form";
    }

    @PostMapping
    public String createCategory(@ModelAttribute("category") CategoryEntity category) {
        categoryService.createCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        CategoryEntity category = categoryService.getCategoryById(id);
        if (category == null) {
            return "redirect:/categories";
        }
        model.addAttribute("category", category);
        return "category/category-form";
    }

    @PostMapping("/{id}/update")
    public String updateCategory(@PathVariable Long id, @ModelAttribute("category") CategoryEntity category) {
        categoryService.updateCategory(id, category);
        return "redirect:/categories";
    }

    @PostMapping("/{id}/delete")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}