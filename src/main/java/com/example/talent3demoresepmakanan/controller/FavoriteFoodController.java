package com.example.talent3demoresepmakanan.controller;

import com.example.talent3demoresepmakanan.dto.categories.GeneralResponseDTO;
import com.example.talent3demoresepmakanan.dto.favorite_foods.DeleteFavoriteFoodRequestDTO;
import com.example.talent3demoresepmakanan.dto.favorite_foods.GetFavoriteFoodsResponseDTO;
import com.example.talent3demoresepmakanan.dto.favorite_foods.GetListFavoriteFoodsResponseDTO;
import com.example.talent3demoresepmakanan.dto.favorite_foods.StoreFavoriteFoodsRequestDTO;
import com.example.talent3demoresepmakanan.model.*;
import com.example.talent3demoresepmakanan.service.FavoriteFoodService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/favorite-foods")
public class FavoriteFoodController {
    @Autowired
    private FavoriteFoodService favoriteFoodService;

    @GetMapping("/all")
    public GetListFavoriteFoodsResponseDTO getAllFavoriteFoods(){
        return favoriteFoodService.geAllFavoriteFoods();
    }

    @GetMapping("/get/{id}")
    public GetFavoriteFoodsResponseDTO getFavoriteFood (@PathVariable Long id){
        return favoriteFoodService.getFavoriteFood(id);
    }

    @PostMapping("/create")
    public GeneralResponseDTO createFavoriteFood(@RequestBody StoreFavoriteFoodsRequestDTO req){
        return favoriteFoodService.createFavoriteFood(req);
    }

    @PutMapping("/update/{id}")
    public GeneralResponseDTO updateFavoriteFood(@PathVariable Long id, @RequestBody StoreFavoriteFoodsRequestDTO req){
        return favoriteFoodService.updateFavoriteFood(id,req);
    }

    @DeleteMapping("/delete")
    public GeneralResponseDTO deleteFavoriteFood(@RequestBody DeleteFavoriteFoodRequestDTO req){
        return favoriteFoodService.deleteFavoriteFood(req);
    }
}
