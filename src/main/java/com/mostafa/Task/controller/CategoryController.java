package com.mostafa.Task.controller;

import com.mostafa.Task.model.Category;
import com.mostafa.Task.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping("/addCategory")
    public Category addCategory(@Valid @RequestBody Category category) {
        category.setParent(true);
        return categoryService.addCategory(category);
    }

    @PostMapping("/addSubCategoryForParent/{parentCategoryId}")
    public ResponseEntity<?> addSubCategoryForParentCategory(@RequestBody Category subCategory,
                                                             @PathVariable int parentCategoryId) {
        // get parent category and put it for sub category.
        Category parentCategory = categoryService.getCategoryById(parentCategoryId);
        if (parentCategory == null)
            return new ResponseEntity<>("No Parent .....", HttpStatus.NOT_FOUND);
//        subCategory.setParentCategory(parentCategory);
//        categoryService.addCategory(subCategory);
//
//        parentCategory.getSubCategory().add(subCategory);
//        parentCategory.getSubCategory().add(subCategory);
        subCategory.setParentCategory(parentCategory);
        parentCategory.getSubCategory().add(subCategory);
        return new ResponseEntity<>(categoryService.updateCategory(parentCategory), HttpStatus.OK);

    }

    @GetMapping("/getTree")
    public List<Category> getTree() {
        return categoryService.getTree();
    }

    @GetMapping("/getCategoryById/{id}")
    public Category getCategoryById(@PathVariable int id) {
        return categoryService.getCategoryById(id);
    }


    // give me id then get category by id.
    // return sub category for given category.
    @GetMapping("/getSubCategoryForThisCategory/{categoryId}")
    public ResponseEntity<?> getSubCategory(@PathVariable int categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        if (category == null) {
            return new ResponseEntity<>("There Are No Cate....", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(category.getSubCategory(), HttpStatus.OK);
    }

    @GetMapping("/getAllCategories")
    public List<Category>getAllCategories(){
        return categoryService.getAllCategories();
    }

}
