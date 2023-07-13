package com.example.demo.controller;

import com.example.demo.dto.DeleteDto;
import com.example.demo.dto.EvaluationDto;
import com.example.demo.dto.ModifyDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.entity.Evaluation;
import com.example.demo.service.EvaluationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/evaluation")
public class EvaluationController {

    @Autowired
    EvaluationService evaluationService;

    @PostMapping("/write")
    public EvaluationDto salePost(@RequestBody @Valid EvaluationDto evaluationDto) throws IOException {
        evaluationService.write(evaluationDto);
        return evaluationDto;
    }

    @GetMapping("/list")
    public List<Evaluation> list(){
        return evaluationService.getList();
    }

    @GetMapping("/{evaluationID}")
    public Evaluation getPost(@PathVariable int evaluationID) throws IOException {
        return evaluationService.get(evaluationID);

    }
    @PatchMapping("/modify/{evaluationID}")
    public ResponseDto modify(@PathVariable int evaluationID, @RequestBody ModifyDto modifyDto) {
        return evaluationService.modify(evaluationID,modifyDto);
    }

    @DeleteMapping("/delete/{evaluationID}")
    public ResponseDto delete(@PathVariable int evaluationID, @RequestBody DeleteDto deleteDto) {
        return evaluationService.delete(evaluationID,deleteDto);
    }


    @GetMapping("/search")
    public List<Evaluation> search(@RequestParam String lecturedivide,@RequestParam String search){
        return evaluationService.search(lecturedivide,search);
    }



}
