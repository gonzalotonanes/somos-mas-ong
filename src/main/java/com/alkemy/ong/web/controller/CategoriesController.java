package com.alkemy.ong.web.controller;

import com.alkemy.ong.domain.categories.CategoriesModel;
import com.alkemy.ong.domain.categories.CategoriesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoriesController {

    private CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping
    public List<CategoriesModel> findAll() {
        return categoriesService.getCategories();
    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<CategoriesModel> createCategory(@RequestBody CategoriesModel category){
        categoriesService.createCategory(category);
        return new ResponseEntity<CategoriesModel>(category, HttpStatus.CREATED);
    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriesModel> updateCategories(@RequestBody CategoriesModel categoriesModel, @PathVariable(value = "id") Long categoriesId) {
        return ResponseEntity.ok(categoriesService.updateCategories(categoriesModel, categoriesId));
    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategories(@PathVariable (value = "id") Long categoriesId) {
        categoriesService.deleteCategories(categoriesId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}