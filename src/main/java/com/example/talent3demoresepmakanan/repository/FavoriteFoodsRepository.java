package com.example.talent3demoresepmakanan.repository;

import com.example.talent3demoresepmakanan.model.FavoriteFoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteFoodsRepository extends JpaRepository<FavoriteFoods,Long> {
    List<FavoriteFoods> findAll();

    List<FavoriteFoods> findByIsFavorite(Boolean isFavorite);

}
