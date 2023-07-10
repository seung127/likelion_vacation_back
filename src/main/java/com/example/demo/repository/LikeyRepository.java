package com.example.demo.repository;

import com.example.demo.entity.Evaluation;
import com.example.demo.entity.Likey;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeyRepository extends JpaRepository<Likey, Integer> {

    //존재하는지 검토
    boolean existsByUserAndEvaluation(User user, Evaluation evaluation);

    //삭제
    void deleteByUserAndEvaluation(User user,Evaluation evaluation);
}
