package com.example.talent3demoresepmakanan.service;

import com.example.talent3demoresepmakanan.common.Common;
import com.example.talent3demoresepmakanan.dto.LoginRequest;
import com.example.talent3demoresepmakanan.dto.LoginResponse;
import com.example.talent3demoresepmakanan.dto.RegisterRequest;
import com.example.talent3demoresepmakanan.dto.RegisterResponse;
import com.example.talent3demoresepmakanan.dto.categories.GeneralResponseDTO;
import com.example.talent3demoresepmakanan.dto.levels.GetLevelsRequestDTO;
import com.example.talent3demoresepmakanan.dto.levels.StoreLevelsRequestDTO;
import com.example.talent3demoresepmakanan.dto.users.*;
import com.example.talent3demoresepmakanan.model.Levels;
import com.example.talent3demoresepmakanan.model.Users;
import com.example.talent3demoresepmakanan.repository.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
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

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtEncoder jwtEncoder;
    public RegisterResponse register(RegisterRequest req){
        log.info("Register User Started");
        String encodedPassword = bCryptPasswordEncoder.encode(req.getPassword());
        req.setPassword(encodedPassword);

        Users existingUser = usersRepository.findByUsername(req.getUsername());
        log.info(Common.CONST_LOG_DB, existingUser);
        if(existingUser != null){
              throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Username already registered");
        }

        Users users = Users.builder()
                .username(req.getUsername())
                .fullName(req.getFullname())
                .password(req.getPassword())
                .role(req.getRole())
                .createdBy("SYSTEM")
                .build();

        Users newUser = usersRepository.saveAndFlush(users);

        return RegisterResponse.builder()
                .id(newUser.getUserId())
                .username(newUser.getUsername())
                .fullname(newUser.getFullName())
                .build();

    }

    public LoginResponse login(LoginRequest request){
        try {
            log.info("Login User Started");
            Users users = usersRepository.findByUsername(request.getUsername());
            log.info(Common.CONST_LOG_DB, users);
            if(users == null || !bCryptPasswordEncoder.matches(request.getPassword(),users.getPassword())){
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"invalid username or password");
            }
            Instant now = Instant.now();
            long expired =  36000L;

            JwtClaimsSet token = JwtClaimsSet.builder()
                    .issuer("self")
                    .issuedAt(now)
                    .expiresAt(now.plusSeconds(expired))
                    .subject(LoginResponse.builder()
                            .username(users.getUsername())
                            .role(users.getRole())
                            .id(users.getUserId())
                            .build().toString())
                    .build();

            var encodedToken = jwtEncoder.encode(JwtEncoderParameters.from(token)).getTokenValue();
            return LoginResponse.builder()
                    .token(encodedToken)
                    .id(users.getUserId())
                    .role(users.getRole())
                    .username(users.getUsername())
                    .build();
        }catch (Exception e){
            throw e;
        }
    }
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
