package com.example.talent3demoresepmakanan.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.talent3demoresepmakanan.dto.categories.GeneralResponseDTO;
import com.example.talent3demoresepmakanan.dto.categories.GetCategoriesResponseDTO;
import com.example.talent3demoresepmakanan.dto.categories.GetListCategoriesResponseDTO;
import com.example.talent3demoresepmakanan.dto.categories.StoreCategoriesRequestDTO;
import com.example.talent3demoresepmakanan.model.Categories;
import com.example.talent3demoresepmakanan.repository.CategoriesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;


@WebMvcTest(controllers = CategoryService.class)
@ContextConfiguration(classes = CategoryService.class)
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoriesRepository categoriesRepository;

    private ObjectMapper objectMapper;

    @Test
    public void testGetAllCategories() {
        List<Categories> categoriesList = new ArrayList<>();
        categoriesList.add(new Categories(1L, "Category 1", false, "SYSTEM", new Date(), "SYSTEM", new Date()));
        categoriesList.add(new Categories(2L, "Category 2", false, "SYSTEM", new Date(), "SYSTEM", new Date()));

        when(categoriesRepository.findByIsDeleted(false)).thenReturn(categoriesList);

        List<GetCategoriesResponseDTO> expectedResponse = new ArrayList<>();
        for (Categories category : categoriesList) {
            expectedResponse.add(new GetCategoriesResponseDTO(category.getCategoryId(), category.getCategoryName(), false));
        }

        GetListCategoriesResponseDTO expected = GetListCategoriesResponseDTO.builder()
                .categories(expectedResponse)
                .build();

        GetListCategoriesResponseDTO actual = categoryService.getAllCategories();

        assertEquals(expected.getCategories().size(), actual.getCategories().size());
    }

    @Test
    public void testCreateCategory() {
        StoreCategoriesRequestDTO requestDTO = new StoreCategoriesRequestDTO();
        requestDTO.setCategoryName("New Category");

        GeneralResponseDTO expectedResponse = new GeneralResponseDTO(true,null);

        GeneralResponseDTO actualResponse = categoryService.createCategory(requestDTO);

        assertEquals(expectedResponse.getIsSuccess(), actualResponse.getIsSuccess());
    }
}

