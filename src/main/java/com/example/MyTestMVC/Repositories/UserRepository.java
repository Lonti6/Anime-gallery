package com.example.MyTestMVC.Repositories;

import com.example.MyTestMVC.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByMail(String mail);
}