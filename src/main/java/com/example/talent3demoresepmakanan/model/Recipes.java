package com.example.talent3demoresepmakanan.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "recipes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recipes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Long recipeId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories categories;
    @ManyToOne
    @JoinColumn(name = "level_id")
    private Levels levels;
    @Column(name = "recipe_name")
    private String recipeName;

    @Column(name = "image_filename")
    private String imageFilename;

    @Column(name = "time_cook")
    private Integer timeCook;

    @Column(name = "ingredient")
    private String ingredient;

    @Column(name = "how_to_cook")
    private String howToCook;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_time")
    private Date modifiedTime ;



}
