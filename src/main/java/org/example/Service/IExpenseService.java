package org.example.Service;

import org.example.Dto.ExpenseDto;

import java.util.List;

public interface IExpenseService {

    ExpenseDto add(ExpenseDto expenseDto);

    ExpenseDto update(Long id ,ExpenseDto expenseDto);

    void deleteById(Long expenseId);

    ExpenseDto getExpense(Long expenseId);


    List<ExpenseDto> getAll();


    List<ExpenseDto> getAllForLastWeek();


    List<ExpenseDto> getAllForLastMonth();

    List<ExpenseDto> getAllForLastThreeMonths();

}
