package org.example.Repository;

import org.example.Model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    void deleteById(Long expenseId);
    Optional<Expense> findById(Long id);

    List<Expense> findAll();
}
