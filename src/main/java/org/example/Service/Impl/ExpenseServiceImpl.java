package org.example.Service.Impl;

import org.example.Dto.ExpenseDto;
import org.example.Exception.ResourceAlreadyExistsException;
import org.example.Exception.ResourceNotFoundException;
import org.example.Model.Category;
import org.example.Model.Expense;
import org.example.Model.User;
import org.example.Repository.CategoryRepository;
import org.example.Repository.ExpenseRepository;
import org.example.Repository.UserRepository;
import org.example.Service.IExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Calendar;
import java.util.Date;


@Service
public class ExpenseServiceImpl implements IExpenseService {

    private ExpenseRepository expenseRepository;

    private CategoryRepository categoryRepository;

    private UserRepository userRepository;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public ExpenseDto add(ExpenseDto expenseDto) {
        System.out.println("Category ID: " + expenseDto.getCategoryId());
        System.out.println("User ID: " + expenseDto.getUserId());
        if (expenseDto.getId() != null) {
            Optional<Expense> expense = expenseRepository.findById(expenseDto.getId());
            if (expense.isPresent()) {
                throw new ResourceAlreadyExistsException("this expense is already exists");
            }
        }
        Expense expense1 = ConvertToEntity(expenseDto);
        expense1 = expenseRepository.saveAndFlush(expense1);
        return ConvertToDto(expense1);
    }

    @Transactional
    @Override
    public ExpenseDto update(Long id, ExpenseDto expenseDto) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("expense with ID " + id + " not found."));

        expense.setName(expenseDto.getName());
        expense.setCreatedAt(expenseDto.getCreatedAt());
        expense.setEndDate(expenseDto.getEndDate());
        if (expenseDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(expenseDto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            expense.setCategory(category);
        }

        if (expenseDto.getUserId() != null) {
            User user = userRepository.findById(expenseDto.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            expense.setUser(user);
        }

        Expense expense1 = expenseRepository.saveAndFlush(expense);
        return ConvertToDto(expense1);

    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        System.out.println("Attempting to delete expense with ID: " + id);
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense with ID " + id + " not found."));
        expenseRepository.deleteById(id);
        System.out.println("Expense with ID: " + id + " deleted successfully");
    }

    @Override
    public ExpenseDto getExpense(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("expense with ID " + expenseId + " not found."));
        return ConvertToDto(expense);

    }

    @Override
    public List<ExpenseDto> getAll() {
        List<Expense> expenses = expenseRepository.findAll();
        return expenses.stream().map(this::ConvertToDto).collect(Collectors.toList());
    }

    @Override
    public List<ExpenseDto> getExpensesBetweenDates(Long userId, Date createdAt, Date endDate) {
        List<Expense> expenses = expenseRepository.findByUserIdAndCreatedAtBetween(userId, createdAt, endDate);
        return expenses.stream().map(this::ConvertToDto).collect(Collectors.toList());
    }

    @Override
    public List<ExpenseDto> getAllForLastWeek(Long userId) {
        Date oneWeekAgo = getDateWeeksAgo(1);
        List<Expense> expenses = expenseRepository.findByUserIdAndCreatedAtAfter(userId, oneWeekAgo);
        return expenses.stream().map(this::ConvertToDto).collect(Collectors.toList());
    }

    @Override
    public List<ExpenseDto> getAllForLastMonth(Long userId) {
        Date oneMonthAgo = getDateMonthsAgo(1);
        List<Expense> expenses = expenseRepository.findByUserIdAndCreatedAtAfter(userId, oneMonthAgo);
        return expenses.stream().map(this::ConvertToDto).collect(Collectors.toList());
    }

    @Override
    public List<ExpenseDto> getAllForLastThreeMonths(Long userId) {
        Date threeMonthsAgo = getDateMonthsAgo(3);
        List<Expense> expenses = expenseRepository.findByUserIdAndCreatedAtAfter(userId, threeMonthsAgo);
        return expenses.stream().map(this::ConvertToDto).collect(Collectors.toList());
    }


    public ExpenseDto ConvertToDto(Expense expense) {
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setId(expense.getId());
        expenseDto.setName(expense.getName());
        expenseDto.setCreatedAt(expense.getCreatedAt());
        expenseDto.setEndDate(expense.getEndDate());

        if (expense.getCategory() != null) {
            expenseDto.setCategoryId(expense.getCategory().getId());
        } else {
            expenseDto.setCategoryId(null);
        }

        if (expense.getUser() != null) {
            expenseDto.setUserId(expense.getUser().getId());
        } else {
            expenseDto.setUserId(null);
        }
        return expenseDto;
    }

    public Expense ConvertToEntity(ExpenseDto expenseDto) {
        Expense expense = new Expense();
        expense.setId(expenseDto.getId());
        expense.setName(expenseDto.getName());
        expense.setCreatedAt(expenseDto.getCreatedAt());
        expense.setEndDate(expenseDto.getEndDate());
        if (expenseDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(expenseDto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            expense.setCategory(category);
        }
        if (expenseDto.getUserId() != null) {
            User user = userRepository.findById(expenseDto.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            expense.setUser(user);
        }
        return expense;
    }

    private Date getDateWeeksAgo(int weeks) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_YEAR, -weeks);
        return calendar.getTime();
    }

    private Date getDateMonthsAgo(int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -months);
        return calendar.getTime();
    }
}