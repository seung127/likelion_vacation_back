package com.example.demo.controller;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;
import com.example.demo.service.LikeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likey")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class LikeyController {

    private final LikeyService likeyService;
    private final UserRepository userRepository;

    @PostMapping("/{evaluationId}")
    public ResponseDto addLike(@PathVariable int evaluationId, @RequestBody LoginDto loginDto){
        User user= userRepository.findByUserId(loginDto.getUserId());
        return likeyService.addLike(evaluationId,user);
    }
}
