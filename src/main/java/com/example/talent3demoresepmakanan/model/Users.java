package com.example.talent3demoresepmakanan.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    private String username;

    @Column(name = "fullname")
    private String fullName;

    private String password;

    private String role;

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


    @PrePersist
    public void setPrePersist() {
        this.isDeleted = false;
        this.createdTime = new Date();
        this.createdBy = "ADMIN";
    }

    @PreUpdate
    public void setPreUpdate(){
        this.modifiedTime = new Date();
        this.modifiedBy = "ADMIN";
    }

}
