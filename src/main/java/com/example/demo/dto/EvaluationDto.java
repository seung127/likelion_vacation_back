package com.example.demo.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluationDto {

    private String userId;

    @Column(length = 50)
    private String lectureName;

    @Column(length = 20)
    private String professorName;
    private int lectureYear ;
    @Column(length = 10)
    private String semesterDivide ;
    @Column(length = 20)
    private String lectureDivide;
    @Column(length = 50)
    private String evaluationTitle;
    @Column(length = 300)
    private String evaluationContent;
    @Column(length = 2)
    private String creditScore;
    @Column(length = 2)
    private String lectureScore;
}
