package org.example.Controller;

import org.example.Dto.ExpenseDto;
import org.example.Service.IExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/LastWeek")
    public ResponseEntity<List<ExpenseDto>> getAllForLastWeek() {
        List<ExpenseDto> expenseDtos = iExpenseService.getAllForLastWeek();
        return ResponseEntity.ok(expenseDtos);
    }

    @GetMapping("/LastMonth")
    public ResponseEntity<List<ExpenseDto>> getAllForLastMonth() {
        List<ExpenseDto> expenseDtos = iExpenseService.getAllForLastMonth();
        return ResponseEntity.ok(expenseDtos);
    }

    @GetMapping("/LastThreeMonths")
    public ResponseEntity<List<ExpenseDto>> getAllForLastThreeMonths() {
        List<ExpenseDto> expenseDtos = iExpenseService.getAllForLastThreeMonths();
        return ResponseEntity.ok(expenseDtos);
    }
}
