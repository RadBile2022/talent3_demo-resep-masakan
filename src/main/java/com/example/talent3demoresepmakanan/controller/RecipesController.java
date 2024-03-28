package com.example.talent3demoresepmakanan.controller;

import com.example.talent3demoresepmakanan.dto.categories.GeneralResponseDTO;
import com.example.talent3demoresepmakanan.dto.recipes.GetListRecipesResponseDTO;
import com.example.talent3demoresepmakanan.dto.recipes.GetRecipesRequestDTO;
import com.example.talent3demoresepmakanan.dto.recipes.GetRecipesResponseDTO;
import com.example.talent3demoresepmakanan.dto.recipes.StoreRecipeRequestDTO;
import com.example.talent3demoresepmakanan.dto.users.DeleteUserRequestDTO;
import com.example.talent3demoresepmakanan.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipes")
public class RecipesController {
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/all")
    public GetListRecipesResponseDTO getAllRecipes(){
        return recipeService.getAllRecipes();
    }

    @GetMapping("/get/{id}")
    public GetRecipesResponseDTO getRecipe(@PathVariable Long id){
        GetRecipesRequestDTO req = new GetRecipesRequestDTO();
        req.setId(id);
        return recipeService.getRecipe(req);
    }

    @GetMapping("/getByName")
    public GetListRecipesResponseDTO getRecipeByName(@RequestBody GetRecipesRequestDTO req){
        req.setRecipeName(req.getRecipeName());
        return recipeService.getRecipeByName(req);
    }

    @PostMapping("/create")
    public GeneralResponseDTO createRecipe(@RequestBody StoreRecipeRequestDTO req){
        return recipeService.createRecipe(req);
    }

    @PutMapping("/update/{id}")
    public GeneralResponseDTO updateRecipe(@PathVariable Long id, @RequestBody StoreRecipeRequestDTO req){
        return recipeService.updateRecipe(id,req);
    }

    @DeleteMapping("/delete")
    public GeneralResponseDTO deleteRecipe(@RequestBody DeleteUserRequestDTO req){
        return recipeService.deleteRecipe(req);
    }
}
