package com.example.talent3demoresepmakanan.dto.favorite_foods;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreFavoriteFoodsRequestDTO {
    private Long userId;
    private Long recipeId;
    private Boolean isFavorite;


}
