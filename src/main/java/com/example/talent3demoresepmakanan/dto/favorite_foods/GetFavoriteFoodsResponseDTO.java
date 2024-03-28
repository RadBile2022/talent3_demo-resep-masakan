package com.example.talent3demoresepmakanan.dto.favorite_foods;

import com.example.talent3demoresepmakanan.dto.users.GetUsersResponseDTO;
import com.example.talent3demoresepmakanan.model.Recipes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetFavoriteFoodsResponseDTO {
    private Long favoriteId;
    private GetUsersResponseDTO users;
    private Recipes recipes;
    private Boolean isFavorite;

}
