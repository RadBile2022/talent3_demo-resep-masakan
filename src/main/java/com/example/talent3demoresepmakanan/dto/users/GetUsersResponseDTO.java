package com.example.talent3demoresepmakanan.dto.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUsersResponseDTO {
    private Long userId;
    private String username;
    private String fullName;
    private String role;
    private Boolean isDeleted;
}
