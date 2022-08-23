package com.example.MyTestMVC.Repositories;

import com.example.MyTestMVC.Models.Evaluations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Evaluations, Long> {
}
