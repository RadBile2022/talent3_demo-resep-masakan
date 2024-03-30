package com.example.talent3demoresepmakanan.service;

import com.example.talent3demoresepmakanan.dto.categories.*;
import com.example.talent3demoresepmakanan.model.Categories;
import com.example.talent3demoresepmakanan.repository.CategoriesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
// simple logging facade for java atau tatapan loging sederhana untuk java
public class CategoryService {
    @Autowired
    CategoriesRepository categoryRepository;
    @Autowired
    ObjectMapper objectMapper;


    // Read / Get All
    public GetListCategoriesResponseDTO getAllCategories(){
        log.info("Get Started");
        List<Categories> getAllCategory = categoryRepository.findByIsDeleted(false);
        List<GetCategoriesResponseDTO> categoriesDTO = new ArrayList<>();

        for (Categories category : getAllCategory){
            GetCategoriesResponseDTO categoryDTO = objectMapper.convertValue(category,GetCategoriesResponseDTO.class);
            categoriesDTO.add(categoryDTO);
        }

        return GetListCategoriesResponseDTO.builder()
                .categories(categoriesDTO)
                .build();
    }

    // Create
    public GeneralResponseDTO createCategory(StoreCategoriesRequestDTO req){
        Categories category = Categories.builder()
                .categoryName(req.getCategoryName())
                .isDeleted(false)
                .createdBy("SYSTEM")
                .createdTime(new Date())
                .modifiedBy("SYSTEM")
                .modifiedTime(new Date())
                .build();

        categoryRepository.saveAndFlush(category);

        return GeneralResponseDTO.builder()
                .isSuccess(true)
                .build();
    }
    // SoftDelete
    public GeneralResponseDTO deleteCategory(DeleteCategoriesRequestDTO req){
        Optional<Categories> categoriesOptional = categoryRepository.findById(req.getId());
        if(categoriesOptional.isPresent()){
            Categories category = categoriesOptional.get();
            category.setIsDeleted(true);
            category.setModifiedTime(new Date());
            category.setModifiedBy("SYSTEM");

            categoryRepository.saveAndFlush(category);
            return GeneralResponseDTO.builder()
                    .isSuccess(true)
                    .build();
        }else {
            return GeneralResponseDTO.builder().build();
        }
    }

    // Update
    public GeneralResponseDTO updateCategory (Long id, StoreCategoriesRequestDTO req){
        Optional<Categories> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()){
            Categories category = categoryOptional.get();
            category.setCategoryName(req.getCategoryName());
            category.setModifiedTime(new Date());
            category.setModifiedBy("SYSTEM");
            categoryRepository.saveAndFlush(category);

            return GeneralResponseDTO.builder()
                    .isSuccess(true)
                    .build();
        } else {
            return GeneralResponseDTO.builder().build();
        }
    }

    // find by id
    public GetCategoriesResponseDTO getCategory(GetCategoriesRequestDTO req){
        log.info("Hallo Dek");
        Optional<Categories> categoryOptional = categoryRepository.findById(req.getId());
        if (categoryOptional.isPresent()){
            GetCategoriesResponseDTO categoriesDTO = objectMapper.convertValue(categoryOptional.get(),GetCategoriesResponseDTO.class);
            return categoriesDTO;
        }else {
            return GetCategoriesResponseDTO.builder().build();
        }
    }

    // find by name
    public GetListCategoriesResponseDTO getCategoryByName(GetCategoriesRequestDTO req){
        List<Categories> category = categoryRepository.findByCategoriesName(req.getCategoriesName());
        List<GetCategoriesResponseDTO> categoriesDTO = category.stream()
                .map(c -> objectMapper.convertValue(c,GetCategoriesResponseDTO.class))
                .collect(Collectors.toList());

        return GetListCategoriesResponseDTO.builder()
                .categories(categoriesDTO)
                .build();
    }

}
