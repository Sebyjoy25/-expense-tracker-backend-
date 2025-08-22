package com.expensetracker.demo.controller;


import com.expensetracker.demo.dtos.ExpenseRequest;
import com.expensetracker.demo.dtos.ExpenseResponse;
import com.expensetracker.demo.entities.Category;
import com.expensetracker.demo.entities.Expense;
import com.expensetracker.demo.services.CategoryService;
import com.expensetracker.demo.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;


    @Autowired
    private CategoryService categoryService;





    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{name}")
    public ResponseEntity<List<Expense>> getExpensesByCategory(@PathVariable String name) {
        List<Expense> expenses = expenseService.getExpensesByCategory(name);
        return ResponseEntity.ok(expenses);
    }
    @PostMapping
    public ResponseEntity<ExpenseResponse> createExpense(@RequestBody ExpenseRequest request) {
        Category category = categoryService.getCategoryById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Expense expense = new Expense();
        expense.setAmount(request.getAmount());
        expense.setDescription(request.getDescription());
        expense.setDate(request.getDate());
        expense.setCategory(category);

        Expense saved = expenseService.createExpense(expense);

        ExpenseResponse response = new ExpenseResponse(
                saved.getId(),
                saved.getAmount(),
                saved.getDescription(),
                saved.getDate(),
                saved.getCategory().getName()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponse> updateExpense(@PathVariable Long id, @RequestBody ExpenseRequest request) {
        Category category = categoryService.getCategoryById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Expense updatedExpense = new Expense();
        updatedExpense.setAmount(request.getAmount());
        updatedExpense.setDescription(request.getDescription());
        updatedExpense.setDate(request.getDate());
        updatedExpense.setCategory(category); // 👈 This is key

        Optional<Expense> updated = expenseService.updateExpense(id, updatedExpense);

        return updated.map(expense -> {
            ExpenseResponse response = new ExpenseResponse(
                    expense.getId(),
                    expense.getAmount(),
                    expense.getDescription(),
                    expense.getDate(),
                    expense.getCategory().getName()
            );
            return ResponseEntity.ok(response);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }





}
