package com.example.talent3demoresepmakanan.controller;

import com.example.talent3demoresepmakanan.dto.categories.*;
import com.example.talent3demoresepmakanan.dto.users.GetListUsersResponseDTO;
import com.example.talent3demoresepmakanan.dto.users.GetUsersResponseDTO;
import com.example.talent3demoresepmakanan.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public GeneralResponseDTO createCategory(@RequestBody StoreCategoriesRequestDTO req){
        return categoryService.createCategory(req);
    }

    @PutMapping("/update/{id}")
    public GeneralResponseDTO updateCategory(@PathVariable Long id, @RequestBody StoreCategoriesRequestDTO req)  {
        return categoryService.updateCategory(id,req);
    }

    @GetMapping("/get/{id}")
    public GetCategoriesResponseDTO getCategory(@PathVariable Long id){
        GetCategoriesRequestDTO req = new GetCategoriesRequestDTO();
        req.setId(id);

        return categoryService.getCategory(req);
    }

    @GetMapping("/all")
    public GetListCategoriesResponseDTO getAllCategories(){
        return categoryService.getAllCategories();
    }
    @GetMapping("/getByName")
    public GetListCategoriesResponseDTO getListCategoryByName (@RequestBody GetCategoriesRequestDTO req){
        req.setCategoriesName(req.getCategoriesName());
        return categoryService.getCategoryByName(req);
    }

    @DeleteMapping("/delete")
    public GeneralResponseDTO deleteCategory (@RequestBody DeleteCategoriesRequestDTO req){
        return categoryService.deleteCategory(req);
    }
}
