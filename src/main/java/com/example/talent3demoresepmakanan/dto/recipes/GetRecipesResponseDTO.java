package com.example.talent3demoresepmakanan.dto.recipes;

import com.example.talent3demoresepmakanan.dto.categories.GetCategoriesResponseDTO;
import com.example.talent3demoresepmakanan.dto.levels.GetLevelsResponseDTO;
import com.example.talent3demoresepmakanan.dto.users.GetUsersResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetRecipesResponseDTO {
    private Long recipeId;
    private GetUsersResponseDTO user;
    private GetCategoriesResponseDTO categories;
    private GetLevelsResponseDTO levels;
    private String recipeName;
    private String imageFilename;
    private Integer timeCook;
    private String ingredient;
    private String howToCook;
    private Boolean isDeleted;


}
