package org.itstep.msk.app.controller;

import lombok.RequiredArgsConstructor;
import org.itstep.msk.app.entity.model.CategoryDTO;
import org.itstep.msk.app.service.CategoryServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/all")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE
            , value = "/{name}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name) {
        return ResponseEntity.ok(categoryService.findCategoryByName(name));
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{name}")
    public ResponseEntity<CategoryDTO> getUpdatedCategory(@PathVariable String name) {
        return ResponseEntity.accepted().body(categoryService.getUpdatedCategory(name));

    }
}
