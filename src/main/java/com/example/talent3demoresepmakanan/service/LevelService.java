package com.example.talent3demoresepmakanan.service;

import com.example.talent3demoresepmakanan.common.Common;
import com.example.talent3demoresepmakanan.dto.categories.GeneralResponseDTO;
import com.example.talent3demoresepmakanan.dto.categories.GetCategoriesResponseDTO;
import com.example.talent3demoresepmakanan.dto.categories.GetListCategoriesResponseDTO;
import com.example.talent3demoresepmakanan.dto.levels.*;
import com.example.talent3demoresepmakanan.model.Levels;
import com.example.talent3demoresepmakanan.repository.LevelsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LevelService {
    @Autowired
    LevelsRepository levelsRepository;
    @Autowired
    ObjectMapper objectMapper;

    // Read / Get ALl
    public GetListLevelsResponseDTO getAllLevels(){
        log.info("get all level started");
        List<Levels> getAllLevel = levelsRepository.findByIsDeleted(false);
        List<GetLevelsResponseDTO> levelsDTO = new ArrayList<>();
        log.info(Common.CONST_LOG_DB,getAllLevel);

        for(Levels level: getAllLevel){
            GetLevelsResponseDTO levelDTO = objectMapper.convertValue(level,GetLevelsResponseDTO.class);

            levelsDTO.add(levelDTO);
        }
        log.info(Common.CONST_LOG_DTO,levelsDTO);

        return GetListLevelsResponseDTO.builder()
                .levels(levelsDTO)
                .build();
    }

    public GetLevelsResponseDTO getLevel(GetLevelsRequestDTO req) {
        log.info("get level started");
        Optional<Levels> levelOptional = levelsRepository.findById(req.getId());
        log.info(Common.CONST_LOG_DB,levelOptional.get());
        if(levelOptional.isPresent()){
            GetLevelsResponseDTO levelsDTO = objectMapper.convertValue(levelOptional.get(),GetLevelsResponseDTO.class);
            return levelsDTO;
        }else{
            return GetLevelsResponseDTO.builder().build();
        }
    }

    public GetListLevelsResponseDTO getLevelByName(GetLevelsRequestDTO req) {
        log.info("get level by name started");
        List<Levels> level = levelsRepository.findByLevelsName(req.getLevelsName());
        log.info(Common.CONST_LOG_DB,level);

        List<GetLevelsResponseDTO> levelsDTO = level.stream()
                .map(l-> objectMapper.convertValue(l,GetLevelsResponseDTO.class))
                .collect(Collectors.toList());

        return GetListLevelsResponseDTO.builder()
                .levels(levelsDTO)
                .build();
    }

    public GeneralResponseDTO createLevel(StoreLevelsRequestDTO req) {
        log.info("create level started");
        Levels level = Levels.builder()
                .levelName(req.getLevelName())
                .build();
        log.info(Common.CONST_LOG_DB,level);
        levelsRepository.saveAndFlush(level);

        return GeneralResponseDTO.builder()
                .isSuccess(true)
                .build();
    }

    public GeneralResponseDTO updateLevel(Long id, StoreLevelsRequestDTO req) {
        log.info("update level started");
        Optional<Levels> levelOptional = levelsRepository.findById(id);
        if(levelOptional.isPresent()){
            Levels level = levelOptional.get();
            level.setLevelName(req.getLevelName());

            log.info(Common.CONST_LOG_DB,level);
            levelsRepository.saveAndFlush(level);

            return GeneralResponseDTO.builder()
                    .isSuccess(true)
                    .build();
        } else {
            return GeneralResponseDTO.builder().build();
        }
    }

    public GeneralResponseDTO deleteLevel(DeleteLevelRequestDTO req) {
        log.info("delete level started");
        Optional<Levels> levelOptional = levelsRepository.findById(req.getId());
        if(levelOptional.isPresent()){
            Levels level = levelOptional.get();
            level.setIsDeleted(true);

            log.info(Common.CONST_LOG_DB,level);
            levelsRepository.saveAndFlush(level);

            return GeneralResponseDTO.builder()
                    .isSuccess(true)
                    .build();
        }else {
            return GeneralResponseDTO.builder().build();
        }
    }
}
