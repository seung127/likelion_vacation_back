package com.example.demo.service;

import com.example.demo.dto.ResponseDto;
import com.example.demo.entity.Evaluation;
import com.example.demo.entity.Likey;
import com.example.demo.entity.User;
import com.example.demo.repository.EvaluationRepository;
import com.example.demo.repository.LikeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeyService {

    private final EvaluationService evaluationService;
    private final EvaluationRepository evaluationRepository;

    private final LikeyRepository likeyRepository;

    public ResponseDto addLike(int evaId, User user){
        Evaluation evaluation=evaluationRepository.findById(evaId).get();
        if(!likeyRepository.existsByUserAndEvaluation(user,evaluation)){
            evaluation.setLikeCount(evaluation.getLikeCount()+1);
            likeyRepository.save(new Likey(user,evaluation));
            return ResponseDto.setSuccess("like success");
        }

        else{
            evaluation.setLikeCount(evaluation.getLikeCount()-1);
            likeyRepository.deleteByUserAndEvaluation(user,evaluation);
            return ResponseDto.setSuccess("like delete");
        }
    }
}
