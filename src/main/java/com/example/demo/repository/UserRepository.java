package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUserEmail(String userEmail);

    boolean existsByUserIdAndUserPw(String userId, String userPw);

    boolean existsByUserIdAndUserPwAndUserEmail(String userId, String userPw,String userEmail);

    User findByUserEmail(String userEmail);

    User findByUserId(String userId);

}
