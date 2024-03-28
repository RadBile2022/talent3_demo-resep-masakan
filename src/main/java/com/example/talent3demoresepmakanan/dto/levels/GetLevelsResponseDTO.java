package com.example.talent3demoresepmakanan.dto.levels;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetLevelsResponseDTO {
    private Long levelId;
    private String levelName;
}
