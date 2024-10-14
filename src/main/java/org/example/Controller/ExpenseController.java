package org.example.Controller;

import org.example.Dto.ExpenseDto;
import org.example.Service.IExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

    private IExpenseService iExpenseService;

    @Autowired
    public ExpenseController(IExpenseService iExpenseService) {
        this.iExpenseService = iExpenseService;
    }

    @PostMapping("/add")
    public ResponseEntity<ExpenseDto> add(@RequestBody ExpenseDto expenseDto) {
        ExpenseDto addExpense = iExpenseService.add(expenseDto);
        return new ResponseEntity<>(addExpense, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDto> update(@PathVariable Long id, @RequestBody ExpenseDto expenseDto) {
        ExpenseDto updateExpense = iExpenseService.update(id, expenseDto);
        return ResponseEntity.ok(updateExpense);
    }

   @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        iExpenseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDto> getExpense(@PathVariable Long id) {
        ExpenseDto getExpense = iExpenseService.getExpense(id);
        return ResponseEntity.ok(getExpense);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ExpenseDto>> getAll() {
        List<ExpenseDto> expenseDtos = iExpenseService.getAll();
        return ResponseEntity.ok(expenseDtos);
    }
    @GetMapping("/custom")
    public List<ExpenseDto> getExpensesBetweenDates(@RequestParam Long userId, @RequestParam Date createdAt, @RequestParam  Date endDate) {
        return iExpenseService.getExpensesBetweenDates(userId, createdAt, endDate);
    }
    @GetMapping("/LastWeek")
    public ResponseEntity<List<ExpenseDto>> getAllForLastWeek(@PathVariable Long userId) {
        List<ExpenseDto> expenseDtos = iExpenseService.getAllForLastWeek(userId);
        return ResponseEntity.ok(expenseDtos);
    }

    @GetMapping("/LastMonth")
    public ResponseEntity<List<ExpenseDto>> getAllForLastMonth(@PathVariable Long userId) {
        List<ExpenseDto> expenseDtos = iExpenseService.getAllForLastMonth(userId);
        return ResponseEntity.ok(expenseDtos);
    }

    @GetMapping("/LastThreeMonths")
    public ResponseEntity<List<ExpenseDto>> getAllForLastThreeMonths(@PathVariable Long userId) {
        List<ExpenseDto> expenseDtos = iExpenseService.getAllForLastThreeMonths(userId);
        return ResponseEntity.ok(expenseDtos);
    }


}
