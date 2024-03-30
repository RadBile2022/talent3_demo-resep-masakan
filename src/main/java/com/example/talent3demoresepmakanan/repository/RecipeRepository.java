package com.example.talent3demoresepmakanan.repository;

import com.example.talent3demoresepmakanan.model.Levels;
import com.example.talent3demoresepmakanan.model.Recipes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipes,Long> {
    List<Recipes> findByIsDeleted(Boolean isDeleted);
    @Query(value = "select * from recipes r where LOWER(r.recipe_name) LIKE%:name%",nativeQuery = true)
    List<Recipes> findByRecipesName(String name);
}
