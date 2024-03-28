package com.example.talent3demoresepmakanan.service;

import com.example.talent3demoresepmakanan.dto.categories.GeneralResponseDTO;
import com.example.talent3demoresepmakanan.dto.levels.GetLevelsRequestDTO;
import com.example.talent3demoresepmakanan.dto.levels.StoreLevelsRequestDTO;
import com.example.talent3demoresepmakanan.dto.users.*;
import com.example.talent3demoresepmakanan.model.Levels;
import com.example.talent3demoresepmakanan.model.Users;
import com.example.talent3demoresepmakanan.repository.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ObjectMapper objectMapper;
    public GetListUsersResponseDTO getAllUser() {
        List<Users> getAllUsers = usersRepository.findByIsDeleted(false);
        List<GetUsersResponseDTO> usersDTO = new ArrayList<>();

        for(Users user : getAllUsers) {
            GetUsersResponseDTO userDTO = objectMapper.convertValue(user, GetUsersResponseDTO.class);
            usersDTO.add(userDTO);
        }
        return GetListUsersResponseDTO.builder()
                .users(usersDTO)
                .build();
    }

    public GetListUsersResponseDTO getUserByName(GetLevelsRequestDTO req) {
        List<Users> user = usersRepository.findByUsersName(req.getLevelsName());
        List<GetUsersResponseDTO> usersDTO = user.stream()
                .map(u-> objectMapper.convertValue(u,GetUsersResponseDTO.class))
                .collect(Collectors.toList());

        return GetListUsersResponseDTO.builder()
                .users(usersDTO)
                .build();
    }
    public GetUsersResponseDTO getUser(GetUsersRequestDTO req) {
        Optional<Users> userOptional = usersRepository.findById(req.getId());
        if(userOptional.isPresent()){
            GetUsersResponseDTO usersDTO = objectMapper.convertValue(userOptional.get(),GetUsersResponseDTO.class);
            return usersDTO;
        } else {
            return  GetUsersResponseDTO.builder().build();
        }
    }


    public GeneralResponseDTO createUser(StoreUsersRequestDTO req) {
        Users user = Users.builder()
                .username(req.getUsername())
                .fullName(req.getFullName())
                .password(req.getPassword())
                .role(req.getRole())
                .isDeleted(false)
                .createdBy("SYSTEM")
                .createdTime(new Date())
                .build();

        usersRepository.saveAndFlush(user);

        return GeneralResponseDTO.builder()
                .isSuccess(true)
                .build();
    }

    public GeneralResponseDTO updateUser(Long id, StoreUsersRequestDTO req) {
        Optional<Users> userOptional = usersRepository.findById(id);
        if(userOptional.isPresent()){
            Users user = userOptional.get();
            user.setUsername(req.getUsername());
            user.setFullName(req.getFullName());
            user.setPassword(req.getPassword());
            user.setRole(req.getRole());
            user.setModifiedBy("SYSTEM");
            user.setModifiedTime(new Date());

            usersRepository.saveAndFlush(user);

            return GeneralResponseDTO.builder()
                    .isSuccess(true)
                    .build();
        } else {
            return GeneralResponseDTO.builder().build();
        }
    }


    public GeneralResponseDTO deleteUser(DeleteUserRequestDTO req) {
        Optional<Users> usersOptional = usersRepository.findById(req.getId());
        if(usersOptional.isPresent()){
            Users user = usersOptional.get();
            user.setIsDeleted(true);
            user.setModifiedTime(new Date());
            user.setModifiedBy("SYSTEM");

            usersRepository.saveAndFlush(user);

            return GeneralResponseDTO.builder()
                    .isSuccess(true)
                    .build();
        }else {
            return GeneralResponseDTO.builder().build();
        }

    }
}
