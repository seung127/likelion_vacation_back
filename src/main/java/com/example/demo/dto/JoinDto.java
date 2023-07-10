package com.example.demo.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinDto {
    @Column(length = 30)
    private String userId;
    @Column(length = 30)
    private String userPw;

    @NonNull
    @Column(length = 30)
    private String checkuserPw;

    @Column(length = 50)
    private String userEmail;
}
