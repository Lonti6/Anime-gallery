package com.example.MyTestMVC.Repositories;

import com.example.MyTestMVC.Models.Evaluations;
import com.example.MyTestMVC.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeRepository extends JpaRepository<Evaluations, Long> {

    Evaluations findByUserId(long userId);
    Evaluations findByPostId(long postId);

    @Query(value = "SELECT * FROM evaluations WHERE post_id = :postID AND is_good = 1", nativeQuery = true)
    List<Evaluations> findAllByPostIdWithoutNull(@Param("postID") long postId);

    @Query(value = "SELECT * FROM evaluations WHERE post_id = :postID AND user_id= :userID", nativeQuery = true)
    Evaluations findLikeByPostAndUser(@Param("postID") long postId, @Param("userID") long userId);
}
