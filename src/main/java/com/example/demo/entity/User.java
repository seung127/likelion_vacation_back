package com.example.demo.entity;

import com.example.demo.dto.JoinDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "User")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30)
    private String userId;
    @Column(length = 30)
    private String userPw;
    @Column(length = 50)
    private String userEmail;

    private int loginStatus=0;


}
