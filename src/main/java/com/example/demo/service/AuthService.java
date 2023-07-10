package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.Evaluation;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;
    public ResponseDto join(JoinDto joinDto){
        String userEmail= joinDto.getUserEmail();
        String userPassword= joinDto.getUserPw();

        //이메일 중복 확인
        try {
            if (userRepository.existsByUserEmail(userEmail))
                return ResponseDto.setFailed("existed email");
        }catch(Exception e){
            return ResponseDto.setFailed("Data Base Error");
        }

        if(!joinDto.getUserPw().equals(joinDto.getCheckuserPw()))
            return ResponseDto.setFailed("check password");

        //이메일 형식 확인
        boolean validation = false;

        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(joinDto.getUserEmail());
        if(m.matches()) {
            validation = true;
        }
        try {
            if(!validation)
                return ResponseDto.setFailed("Invalid email format");
        } catch (Exception e){
            return ResponseDto.setFailed("Data Base Error");
        }


        User user=User.builder()
                .userId(joinDto.getUserId())
                .userPw(joinDto.getUserPw())
                .userEmail(joinDto.getUserEmail())
                .build();

        //repository에 저장
        try {
            userRepository.save(user);
        }catch(Exception e){
            return ResponseDto.setFailed("Data Base Error");
        }

        return ResponseDto.setSuccess("join success");
    }


    public ResponseDto login(LoginDto loginDto){
        boolean existed=userRepository.existsByUserIdAndUserPw(loginDto.getUserId(), loginDto.getUserPw());
        if(!existed) return ResponseDto.setFailed("login information does not match");

        //이메일 형식 확인
        boolean validation = false;

        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(loginDto.getUserEmail());
        if(m.matches()) {
            validation = true;
        }
        try {
            if(!validation)
                return ResponseDto.setFailed("Invalid email format");
        } catch (Exception e){
            return ResponseDto.setFailed("Data Base Error");
        }

        User user=userRepository.findByUserId(loginDto.getUserId());
        user.setLoginStatus(1);
        userRepository.save(user);
        return ResponseDto.setSuccess("login success");
    }

    public ResponseDto logout(LogoutDto logoutDto){
        if(userRepository.existsByUserIdAndUserPwAndUserEmail(logoutDto.getUserId(), logoutDto.getUserPw(), logoutDto.getUserEmail())){
            User user=userRepository.findByUserId(logoutDto.getUserId());
            user.setLoginStatus(0);
            userRepository.save(user);
            return ResponseDto.setSuccess("logout success");}
        else{
            return ResponseDto.setFailed("logout failed");
        }
    }

    public ResponseDto delete(DeleteDto deleteDto){
        if(userRepository.existsByUserIdAndUserPwAndUserEmail(deleteDto.getUserId(), deleteDto.getUserPw(), deleteDto.getUserEmail())){
            userRepository.delete(userRepository.findByUserId(deleteDto.getUserId()));
            return  ResponseDto.setSuccess("delete success");
        }
        else {return ResponseDto.setFailed("delete failed");}
    }
}
