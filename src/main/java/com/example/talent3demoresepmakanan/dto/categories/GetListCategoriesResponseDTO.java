package com.example.talent3demoresepmakanan.dto.categories;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetListCategoriesResponseDTO {
    private List<GetCategoriesResponseDTO> categories;
}
