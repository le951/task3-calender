package org.example.task3calender.develop.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import org.hibernate.annotations.Comment;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.cglib.core.Local;

@Entity
@Table(name="users", indexes = {
        @Index(name = "idx_account", columnList = "account")
})
@NoArgsConstructor
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String account;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false)
    @Comment("")
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private LocalDateTime deletedAt;

    public User(String account, String password){
        this.account = account;
        this.password = password;
    }

    public void remove(){
        if(deletedAt == null)
            deletedAt = LocalDateTime.now();
    }


    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){

    }



}
