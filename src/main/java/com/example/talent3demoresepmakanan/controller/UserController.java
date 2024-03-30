package com.example.talent3demoresepmakanan.controller;

import com.example.talent3demoresepmakanan.dto.LoginRequest;
import com.example.talent3demoresepmakanan.dto.LoginResponse;
import com.example.talent3demoresepmakanan.dto.RegisterRequest;
import com.example.talent3demoresepmakanan.dto.RegisterResponse;
import com.example.talent3demoresepmakanan.dto.categories.GeneralResponseDTO;
import com.example.talent3demoresepmakanan.dto.levels.GetLevelsRequestDTO;
import com.example.talent3demoresepmakanan.dto.users.*;
import com.example.talent3demoresepmakanan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public GetListUsersResponseDTO getAllUsers(){
        return userService.getAllUser();
    }

    @GetMapping("/getByName")
    public GetListUsersResponseDTO getUserByName(@RequestBody GetLevelsRequestDTO req) {
        req.setLevelsName(req.getLevelsName());
        return userService.getUserByName(req);
    }

    @GetMapping("/get/{id}")
    public GetUsersResponseDTO getUser (@PathVariable Long id){
        GetUsersRequestDTO req = new GetUsersRequestDTO();
        req.setId(id);
        return userService.getUser(req);
    }
    @PostMapping("/create")
    public GeneralResponseDTO createUser(@RequestBody StoreUsersRequestDTO req){
        return userService.createUser(req);
    }


    @PostMapping("/register")
    public RegisterResponse registerUser(@RequestBody RegisterRequest req){
        return userService.register(req);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        LoginResponse loginResponse = userService.login(request);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",loginResponse.getToken());

        return ResponseEntity.ok()
                .headers(headers)
                .body(loginResponse);
    }


    @PutMapping("/update/{id}")
    public GeneralResponseDTO updateUser(@PathVariable Long id, @RequestBody StoreUsersRequestDTO req){
        return userService.updateUser(id,req);
    }


    @DeleteMapping("/delete")
    public GeneralResponseDTO deleteUser (@RequestBody DeleteUserRequestDTO req){
        return userService.deleteUser(req);
    }
}
