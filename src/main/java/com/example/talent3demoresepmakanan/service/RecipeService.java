package com.example.talent3demoresepmakanan.service;

import com.example.talent3demoresepmakanan.common.Common;
import com.example.talent3demoresepmakanan.dto.categories.GeneralResponseDTO;
import com.example.talent3demoresepmakanan.dto.levels.StoreLevelsRequestDTO;
import com.example.talent3demoresepmakanan.dto.recipes.GetListRecipesResponseDTO;
import com.example.talent3demoresepmakanan.dto.recipes.GetRecipesRequestDTO;
import com.example.talent3demoresepmakanan.dto.recipes.GetRecipesResponseDTO;
import com.example.talent3demoresepmakanan.dto.recipes.StoreRecipeRequestDTO;
import com.example.talent3demoresepmakanan.dto.users.DeleteUserRequestDTO;
import com.example.talent3demoresepmakanan.model.Categories;
import com.example.talent3demoresepmakanan.model.Levels;
import com.example.talent3demoresepmakanan.model.Recipes;
import com.example.talent3demoresepmakanan.model.Users;
import com.example.talent3demoresepmakanan.repository.CategoriesRepository;
import com.example.talent3demoresepmakanan.repository.LevelsRepository;
import com.example.talent3demoresepmakanan.repository.RecipeRepository;
import com.example.talent3demoresepmakanan.repository.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RecipeService {
    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    UsersRepository usersRepository;
    @Autowired
    CategoriesRepository categoriesRepository;

    @Autowired
    LevelsRepository levelsRepository;
    @Autowired
    ObjectMapper objectMapper;

    public GetListRecipesResponseDTO getAllRecipes() {
        log.info("Get all recipes started");
        List<Recipes> getAllRecipe = recipeRepository.findByIsDeleted(false);
        log.info(Common.CONST_LOG_DB,getAllRecipe);

        List<GetRecipesResponseDTO> recipesDTO = new ArrayList<>();

        for(Recipes recipe : getAllRecipe){
            GetRecipesResponseDTO recipeDTO = objectMapper.convertValue(recipe,GetRecipesResponseDTO.class);
            recipesDTO.add(recipeDTO);
        }

        return GetListRecipesResponseDTO.builder()
                .recipes(recipesDTO)
                .build();
    }

    public GetRecipesResponseDTO getRecipe(GetRecipesRequestDTO req) {
        log.info("get recipe started");
        Optional<Recipes> recipeOptional = recipeRepository.findById(req.getId());
        log.info(Common.CONST_LOG_DB,recipeOptional.get());
        if(recipeOptional.isPresent()){
            GetRecipesResponseDTO recipesDTO = objectMapper.convertValue(recipeOptional.get(),GetRecipesResponseDTO.class);
            return recipesDTO;
        }else{
            return GetRecipesResponseDTO.builder().build();
        }
    }


    public GetListRecipesResponseDTO getRecipeByName(GetRecipesRequestDTO req) {
        List<Recipes> recipe = recipeRepository.findByRecipesName(req.getRecipeName());
        List<GetRecipesResponseDTO> recipesDTO = recipe.stream()
                .map(r->objectMapper.convertValue(r,GetRecipesResponseDTO.class))
                .collect(Collectors.toList());

        return GetListRecipesResponseDTO.builder()
                .recipes(recipesDTO)
                .build();
    }

    public GeneralResponseDTO createRecipe(StoreRecipeRequestDTO req) {
        log.info("create recipe started");
        Optional<Users> userOptional = usersRepository.findById(req.getUserId());
        Optional<Categories> categoryOptional = categoriesRepository.findById(req.getCategoriesId());
        Optional<Levels> levelsOptional = levelsRepository.findById(req.getLevelsId());

        Recipes recipes = Recipes.builder()
                .user(userOptional.get())
                .categories(categoryOptional.get())
                .levels(levelsOptional.get())
                .recipeName(req.getRecipeName())
                .imageFilename(req.getImageFilename())
                .timeCook(req.getTimeCook())
                .ingredient(req.getIngredient())
                .howToCook(req.getHowToCook())
                .build();

        log.info(Common.CONST_LOG_DB,recipes);
        recipeRepository.saveAndFlush(recipes);

        return GeneralResponseDTO.builder()
                .isSuccess(true)
                .build();
    }

    public GeneralResponseDTO updateRecipe(Long id, StoreRecipeRequestDTO req) {
        log.info("update recipe started");
        Optional<Recipes> recipeOptional = recipeRepository.findById(id);
        if(recipeOptional.isPresent()){
            Optional<Users> userOptional = usersRepository.findById(req.getUserId());
            Optional<Categories> categoryOptional = categoriesRepository.findById(req.getCategoriesId());
            Optional<Levels> levelsOptional = levelsRepository.findById(req.getLevelsId());

            Recipes recipe = recipeOptional.get();
            recipe.setUser(userOptional.get());
            recipe.setCategories(categoryOptional.get());
            recipe.setLevels(levelsOptional.get());
            recipe.setRecipeName(req.getRecipeName());
            recipe.setImageFilename(req.getImageFilename());
            recipe.setTimeCook(req.getTimeCook());
            recipe.setIngredient(req.getIngredient());
            recipe.setHowToCook(req.getHowToCook());

            log.info(Common.CONST_LOG_DB,recipe);
            recipeRepository.saveAndFlush(recipe);

            return GeneralResponseDTO.builder()
                    .isSuccess(true)
                    .build();
        } else {
            return GeneralResponseDTO.builder().build();
        }
    }

    public GeneralResponseDTO deleteRecipe(DeleteUserRequestDTO req) {
        log.info("delete recipe started");
        Optional<Recipes> recipesOptional = recipeRepository.findById(req.getId());

        if(recipesOptional.isPresent()){
            Recipes recipe = recipesOptional.get();
            recipe.setIsDeleted(true);

            log.info(Common.CONST_LOG_DB,recipe);
            recipeRepository.saveAndFlush(recipe);

            return GeneralResponseDTO.builder()
                    .isSuccess(true)
                    .build();
        } else {
            return GeneralResponseDTO.builder().build();
        }
    }
}
