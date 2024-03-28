package com.example.talent3demoresepmakanan.dto.favorite_foods;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetListFavoriteFoodsResponseDTO {
    List<GetFavoriteFoodsResponseDTO> favoriteFoods;
}
