package com.example.talent3demoresepmakanan.controller;


import com.example.talent3demoresepmakanan.dto.categories.GeneralResponseDTO;
import com.example.talent3demoresepmakanan.dto.categories.GetCategoriesResponseDTO;
import com.example.talent3demoresepmakanan.dto.categories.GetListCategoriesResponseDTO;
import com.example.talent3demoresepmakanan.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CategoryController.class)
@ContextConfiguration(classes = CategoryController.class)
public class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGetAllCategories() throws Exception {
        List<GetCategoriesResponseDTO> categories = new ArrayList<>();
        categories.add(new GetCategoriesResponseDTO(1L, "string", false));
        categories.add(new GetCategoriesResponseDTO(2L, "string", false));

        when(categoryService.getAllCategories()).thenReturn(new GetListCategoriesResponseDTO(categories));

        mockMvc.perform(get("/categories/all")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(GetListCategoriesResponseDTO.builder().build())))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void testCreateCategory() throws Exception {
        when(categoryService.createCategory(any()))
                .thenReturn(GeneralResponseDTO.builder().build());

        mockMvc.perform(post("/categories/create")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(GetListCategoriesResponseDTO.builder().build())))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
