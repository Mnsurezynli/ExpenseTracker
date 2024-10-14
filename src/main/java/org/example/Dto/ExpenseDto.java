package org.example.Dto;

import org.example.Model.Category;
import org.example.Model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class ExpenseDto {
    private Long id;
    private String name;
    private Date createdAt;
    private Date endDate;
    private Long categoryId;
    private Long userId;

    public ExpenseDto() {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getEndDate() {

        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


}
