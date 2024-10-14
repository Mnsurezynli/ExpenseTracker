package org.example.Service;

import org.example.Dto.ExpenseDto;
import org.example.Model.Expense;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface IExpenseService {

    ExpenseDto add(ExpenseDto expenseDto);

    ExpenseDto update(Long id ,ExpenseDto expenseDto);

    void deleteById(Long expenseId);

    ExpenseDto getExpense(Long expenseId);

    List<ExpenseDto> getAll();

    List<ExpenseDto> getExpensesBetweenDates(Long userId,Date createdAt, Date endDate);

    List<ExpenseDto> getAllForLastWeek(Long userId);

    List<ExpenseDto> getAllForLastMonth(Long userId);

    List<ExpenseDto> getAllForLastThreeMonths(Long userId);




}
