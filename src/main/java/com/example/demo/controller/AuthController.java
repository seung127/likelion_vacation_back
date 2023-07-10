package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @Autowired
    AuthService authService;
    @PostMapping("/join")
    public ResponseDto join(@RequestBody JoinDto joinDto){
        System.out.println(joinDto.toString());
        ResponseDto result=authService.join((joinDto));
        return result;
    }

    @PostMapping("/login")
    public ResponseDto login(@RequestBody LoginDto logindto){
        System.out.println(logindto.toString());
        ResponseDto result=authService.login((logindto));
        return result;
    }


    @PatchMapping("/logout")
    public ResponseDto modify(@RequestBody LogoutDto logoutdto) {
        ResponseDto result=authService.logout((logoutdto));
        return result;
    }

    @DeleteMapping("/deleteuser")
    public ResponseDto modify(@RequestBody DeleteDto deleteDto) {
        ResponseDto result=authService.delete(deleteDto);
        return result;
    }
}
