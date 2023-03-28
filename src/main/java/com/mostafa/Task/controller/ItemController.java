package com.mostafa.Task.controller;

import com.mostafa.Task.model.Category;
import com.mostafa.Task.model.Item;
import com.mostafa.Task.service.CategoryService;
import com.mostafa.Task.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addItem/{categoryId}")
    public ResponseEntity<?> addItem(@Valid @RequestBody Item item, @PathVariable int categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        if(category==null)
            return new ResponseEntity<>("Not Found Category With id ="+categoryId , HttpStatus.BAD_REQUEST);
        item.setCategory(category);
        itemService.addItem(item);
        return new ResponseEntity<>( item , HttpStatus.CREATED);

    }


    @GetMapping("/getAllItems")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }


}
