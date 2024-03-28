package com.example.talent3demoresepmakanan.repository;

import com.example.talent3demoresepmakanan.model.Levels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LevelsRepository extends JpaRepository<Levels,Long> {

    List<Levels> findAll();

    List<Levels> findByIsDeleted(Boolean isDeleted);
    @Query(value = "select * from levels l where LOWER(l.level_name) LIKE%:name%",nativeQuery = true)
    List<Levels> findByLevelsName(String name);
}
