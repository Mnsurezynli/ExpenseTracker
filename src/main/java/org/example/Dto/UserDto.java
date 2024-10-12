package org.example.Dto;

import org.example.Model.Expense;

import java.util.List;

public class UserDto {

    private Long id;
    private String userName;
    private String password;
    private List<Expense> expense;

    public UserDto(Long id, String userName, String password, List<Expense> expense) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.expense = expense;
    }

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Expense> getExpense() {
        return expense;
    }

    public void setExpense(List<Expense> expense) {
        this.expense = expense;
    }
}
