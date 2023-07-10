package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Data
@Entity
@Builder
@Table(name = "Likey")
@NoArgsConstructor
@AllArgsConstructor
public class Likey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "evaluation_id")
    private Evaluation evaluation;

    public Likey(User user,Evaluation evaluation){
        this.user=user;
        this.evaluation=evaluation;
    }
}
