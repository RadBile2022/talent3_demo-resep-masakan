package com.example.talent3demoresepmakanan.controller;

import com.example.talent3demoresepmakanan.dto.categories.GeneralResponseDTO;
import com.example.talent3demoresepmakanan.dto.levels.*;
import com.example.talent3demoresepmakanan.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/levels")
public class LevelController {
    @Autowired
    private LevelService levelService;

    @GetMapping("/all")
    public GetListLevelsResponseDTO getAllLevels(){
        return levelService.getAllLevels();
    }

    @GetMapping("/get/{id}")//
    public GetLevelsResponseDTO getLevel(@PathVariable Long id){
        GetLevelsRequestDTO req = new GetLevelsRequestDTO();
        req.setId(id);
        return levelService.getLevel(req);
    }

    @GetMapping("/getByName")
    public GetListLevelsResponseDTO getLevelByName(@RequestBody GetLevelsRequestDTO req){
        req.setLevelsName(req.getLevelsName());
        return levelService.getLevelByName(req);
    }

    @PostMapping("/create")
    public GeneralResponseDTO createLevel(@RequestBody StoreLevelsRequestDTO req){
        return levelService.createLevel(req);
    }

    @PutMapping("/update/{id}")
    public GeneralResponseDTO updateLevel(@PathVariable Long id, @RequestBody StoreLevelsRequestDTO req){
        return levelService.updateLevel(id,req);
    }

    @DeleteMapping("/delete")
    public GeneralResponseDTO deleteLevel(@RequestBody DeleteLevelRequestDTO req){
        return levelService.deleteLevel(req);

    }

}
