package com.example.demo.entity;

import com.example.demo.dto.EvaluationDto;
import com.example.demo.dto.ModifyDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Data
@Entity
@Builder
@Table(name = "Evaluation")
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eva_id;

    private String user_id;

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
    @Builder.Default
    private int likeCount=0;


}
