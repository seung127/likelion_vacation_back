package com.example.demo.service;

import com.example.demo.dto.DeleteDto;
import com.example.demo.dto.EvaluationDto;
import com.example.demo.dto.ModifyDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.entity.Evaluation;
import com.example.demo.entity.User;
import com.example.demo.repository.EvaluationRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationService {

    @Autowired
    EvaluationRepository evaluationRepository;

    @Autowired
    UserRepository userRepository;

    public EvaluationDto write(EvaluationDto evaluationDto) {
        Evaluation evaluation = Evaluation.builder()
                .user_id(evaluationDto.getUserId())
                .lectureName(evaluationDto.getLectureName())
                .professorName(evaluationDto.getProfessorName())
                .lectureYear(evaluationDto.getLectureYear())
                .semesterDivide(evaluationDto.getSemesterDivide())
                .lectureDivide(evaluationDto.getLectureDivide())
                .evaluationTitle(evaluationDto.getEvaluationTitle())
                .evaluationContent(evaluationDto.getEvaluationContent())
                .creditScore(evaluationDto.getCreditScore())
                .lectureScore(evaluationDto.getLectureScore())
                .build();

        evaluationRepository.save(evaluation);
        return evaluationDto;
    }

    public List<Evaluation> getList() {
        return evaluationRepository.findAll();
    }

    public Evaluation get(int evaluationId) {
        return evaluationRepository.findById(evaluationId).get();
    }

    public ResponseDto modify(int evaluationID, ModifyDto modifyDto) {

        Evaluation evaluation = evaluationRepository.findById(evaluationID)
                .orElseThrow(() -> new NullPointerException("post does not exist"));

        if (evaluation.getUser_id().equals(modifyDto.getUserId()) && userRepository.existsByUserIdAndUserPwAndUserEmail(modifyDto.getUserId(), modifyDto.getUserPw(), modifyDto.getUserEmail())) {
            // 요청된 필드가 존재하는 경우에만 해당 필드를 수정합니다.
            if (modifyDto.getLectureName() != null) {
                evaluation.setLectureName(modifyDto.getLectureName());
            }
            if (modifyDto.getProfessorName() != null) {
                evaluation.setProfessorName(modifyDto.getProfessorName());
            }
            if (modifyDto.getLectureYear() != 0) {
                evaluation.setLectureYear(modifyDto.getLectureYear());
            }
            if (modifyDto.getSemesterDivide() != null) {
                evaluation.setSemesterDivide(modifyDto.getSemesterDivide());
            }
            if (modifyDto.getLectureDivide() != null) {
                evaluation.setLectureDivide(modifyDto.getLectureDivide());
            }
            if (modifyDto.getEvaluationTitle() != null) {
                evaluation.setEvaluationTitle(modifyDto.getEvaluationTitle());
            }
            if (modifyDto.getEvaluationContent() != null) {
                evaluation.setEvaluationContent(modifyDto.getEvaluationContent());
            }
            if (modifyDto.getCreditScore() != null) {
                evaluation.setCreditScore(modifyDto.getCreditScore());
            }
            if (modifyDto.getLectureScore() != null) {
                evaluation.setLectureScore(modifyDto.getLectureScore());
            }
            evaluationRepository.save(evaluation);
        } else {
            return ResponseDto.setFailed("update fail");
        }

        return ResponseDto.setSuccess("update success");
    }

    public ResponseDto delete(int evaluationId, DeleteDto deleteDto) {
        Evaluation evaluation = evaluationRepository.findById(evaluationId)
                .orElseThrow(() -> new NullPointerException("post does not exist"));

        if (evaluation.getUser_id().equals(deleteDto.getUserId())
                && userRepository.existsByUserIdAndUserPwAndUserEmail(
                        deleteDto.getUserId(), deleteDto.getUserPw(), deleteDto.getUserEmail())) {
            evaluationRepository.delete(evaluation);
            }
        else{return ResponseDto.setFailed("delete failed");}
        return ResponseDto.setSuccess("delete success");
    }

    public List<Evaluation> search(String LectureDivide,String search){
        return evaluationRepository.findByLectureDivideAndLectureNameContaining(LectureDivide,search);
    }
}
