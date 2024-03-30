package com.example.talent3demoresepmakanan.service;


import com.example.talent3demoresepmakanan.common.Common;
import com.example.talent3demoresepmakanan.dto.categories.GeneralResponseDTO;
import com.example.talent3demoresepmakanan.dto.favorite_foods.DeleteFavoriteFoodRequestDTO;
import com.example.talent3demoresepmakanan.dto.favorite_foods.GetFavoriteFoodsResponseDTO;
import com.example.talent3demoresepmakanan.dto.favorite_foods.GetListFavoriteFoodsResponseDTO;
import com.example.talent3demoresepmakanan.dto.favorite_foods.StoreFavoriteFoodsRequestDTO;
import com.example.talent3demoresepmakanan.model.FavoriteFoods;
import com.example.talent3demoresepmakanan.model.Recipes;
import com.example.talent3demoresepmakanan.model.Users;
import com.example.talent3demoresepmakanan.repository.FavoriteFoodsRepository;
import com.example.talent3demoresepmakanan.repository.RecipeRepository;
import com.example.talent3demoresepmakanan.repository.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@Service
@Slf4j
public class FavoriteFoodService {
    @Autowired
    FavoriteFoodsRepository favoriteFoodsRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    ObjectMapper objectMapper;


    public GetListFavoriteFoodsResponseDTO geAllFavoriteFoods() {
        log.info("get all favorite food started");
        List<FavoriteFoods> getAllFavoriteFood = favoriteFoodsRepository.findByIsFavorite(true);
        List<GetFavoriteFoodsResponseDTO> favoriteFoodsDTO = new ArrayList<>();
        log.info(Common.CONST_LOG_DB,getAllFavoriteFood);
        for(FavoriteFoods favoriteFood : getAllFavoriteFood){
            GetFavoriteFoodsResponseDTO favoriteFoodDTO = objectMapper.convertValue(favoriteFood,GetFavoriteFoodsResponseDTO.class);
            favoriteFoodsDTO.add(favoriteFoodDTO);
        }

        return GetListFavoriteFoodsResponseDTO.builder()
                .favoriteFoods(favoriteFoodsDTO)
                .build();
    }

    public GetFavoriteFoodsResponseDTO getFavoriteFood(Long id) {
        log.info("get favorite food started");
        Optional<FavoriteFoods> favoriteFoodOptional = favoriteFoodsRepository.findById(id);
        log.info(Common.CONST_LOG_DB,favoriteFoodOptional.get());

        if(favoriteFoodOptional.isPresent()){
            GetFavoriteFoodsResponseDTO favoriteFoodsDTO = objectMapper.convertValue(favoriteFoodOptional.get(),GetFavoriteFoodsResponseDTO.class);
            return favoriteFoodsDTO;
        } else {
            return GetFavoriteFoodsResponseDTO.builder().build();
        }
    }

    public GeneralResponseDTO createFavoriteFood(StoreFavoriteFoodsRequestDTO req) {
        log.info("create favorite food started");
        Optional<Users> userOptional = usersRepository.findById(req.getUserId());
        Optional<Recipes> recipesOptional = recipeRepository.findById(req.getRecipeId());
        FavoriteFoods favoriteFood = FavoriteFoods.builder()
                .users(userOptional.get())
                .recipes(recipesOptional.get())
                .isFavorite(req.getIsFavorite())
                .build();
        log.info(Common.CONST_LOG_DB,favoriteFood);

        favoriteFoodsRepository.saveAndFlush(favoriteFood);

        return GeneralResponseDTO.builder()
                .isSuccess(true)
                .build();
    }

    public GeneralResponseDTO updateFavoriteFood(Long id,StoreFavoriteFoodsRequestDTO req) {
        log.info("update favorite food started");
        Optional<FavoriteFoods> favoriteFoodOptional = favoriteFoodsRepository.findById(id);

        if (favoriteFoodOptional.isPresent()){
            Optional<Users> userOptional = usersRepository.findById(req.getUserId());
            Optional<Recipes> recipesOptional = recipeRepository.findById(req.getRecipeId());
            log.info(userOptional.get().toString());
            FavoriteFoods favorite = favoriteFoodOptional.get();
            favorite.setUsers(userOptional.get());
            favorite.setRecipes(recipesOptional.get());
            favorite.setIsFavorite(req.getIsFavorite());
            log.info(Common.CONST_LOG_DB,favorite);

            favoriteFoodsRepository.saveAndFlush(favorite);
            return GeneralResponseDTO.builder()
                    .isSuccess(true)
                    .build();
        } else{
            return GeneralResponseDTO.builder().build();
        }
    }

    public GeneralResponseDTO deleteFavoriteFood(DeleteFavoriteFoodRequestDTO req) {
        log.info("delete favorite food started");
        Optional<FavoriteFoods> favoriteFoodOptional = favoriteFoodsRepository.findById(req.getId());

        if (favoriteFoodOptional.isPresent()){
//            FavoriteFoods favorite = favoriteFoodOptional.get();
//            favorite.set(userOptional.get());
//            favorite.setRecipes(recipesOptional.get());
//            favorite.setIsFavorite(req.getIsFavorite());


            return GeneralResponseDTO.builder()
                    .isSuccess(true)
                    .build();
        } else{
            return GeneralResponseDTO.builder().build();
        }
    }
}
