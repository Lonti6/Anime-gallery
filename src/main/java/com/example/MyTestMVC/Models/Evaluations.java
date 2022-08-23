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

    @NotNull
    private boolean isGood;

    public Evaluations() {
    }

    public Evaluations(@NotNull long userId, @NotNull boolean isGood) {
        this.userId = userId;
        this.isGood = isGood;
    }
}
