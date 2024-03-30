package com.example.talent3demoresepmakanan.repository;

import com.example.talent3demoresepmakanan.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriesRepository extends JpaRepository<Categories,Long> {
    List<Categories> findByIsDeleted(Boolean isDeleted);
    @Query(value = "select * from categories c where LOWER(c.category_name) LIKE:%name%", nativeQuery = true)
    List<Categories> findByCategoriesName(String name);
}
