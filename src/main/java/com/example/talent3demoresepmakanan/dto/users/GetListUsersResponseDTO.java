package com.example.talent3demoresepmakanan.dto.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetListUsersResponseDTO {
    private List<GetUsersResponseDTO> users;
}
