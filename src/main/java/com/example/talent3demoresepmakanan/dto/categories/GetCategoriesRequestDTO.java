package com.example.talent3demoresepmakanan.dto.categories;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCategoriesRequestDTO {
    private Long id;
    private String categoriesName;
}
