package com.example.MyTestMVC.Models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Evaluations {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private long userId;

    @NotNull
    private long postId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public boolean isGood() {
        return isGood;
    }

    public void setGood(boolean good) {
        isGood = good;
    }

    @NotNull
    private boolean isGood;

    public Evaluations() {
    }

    public Evaluations(@NotNull long userId, @NotNull long postId, @NotNull boolean isGood) {
        this.postId = postId;
        this.userId = userId;
        this.isGood = isGood;
    }
}
