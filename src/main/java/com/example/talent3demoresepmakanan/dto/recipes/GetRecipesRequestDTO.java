package com.example.talent3demoresepmakanan.dto.recipes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetRecipesRequestDTO {
    private Long id;
    private String recipeName;

}
