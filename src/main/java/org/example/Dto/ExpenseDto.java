package org.example.Dto;

import org.example.Model.Category;
import org.example.Model.User;

public class ExpenseDto {

    private Long id ;
    private String name;
    private String time ;
    private Long categoryId;
    private Long userId;


    public ExpenseDto() {
    }

    public ExpenseDto(Long id, String name, String time, Long categoryId, Long userId) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }




}
