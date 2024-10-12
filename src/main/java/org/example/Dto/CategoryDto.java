package org.example.Dto;

import org.example.Model.Expense;

import java.util.List;

public class CategoryDto {

    private Long id;
    private String name;
    private List<Expense> expense;

    public CategoryDto(Long id, String name, List<Expense> expense) {
        this.id = id;
        this.name = name;
        this.expense = expense;
    }

    public CategoryDto() {
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

    public List<Expense> getExpense() {
        return expense;
    }

    public void setExpense(List<Expense> expense) {
        this.expense = expense;
    }
}
