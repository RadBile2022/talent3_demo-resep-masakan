package com.example.talent3demoresepmakanan.dto.levels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetListLevelsResponseDTO {
    List<GetLevelsResponseDTO> levels;
}
