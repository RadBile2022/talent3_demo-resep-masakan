package com.example.talent3demoresepmakanan.dto.recipes;

import com.example.talent3demoresepmakanan.model.Categories;
import com.example.talent3demoresepmakanan.model.Levels;
import com.example.talent3demoresepmakanan.model.Users;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreRecipeRequestDTO {
    private Long userId;
    private Long categoriesId;
    private Long levelsId;
    private String recipeName;

    private String imageFilename;

    private Integer timeCook;

    private String ingredient;

    private String howToCook;

    private Boolean isDeleted;

}
