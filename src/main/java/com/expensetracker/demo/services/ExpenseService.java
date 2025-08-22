package com.expensetracker.demo.services;

import com.expensetracker.demo.entities.Expense;
import com.expensetracker.demo.repo.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {



    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public  Optional<Expense> getExpenseById(Long id){
        return expenseRepository.findById(id);
    }

    public Expense createExpense(Expense expense){
        return expenseRepository.save(expense);
    }


    public Optional<Expense> updateExpense(Long id, Expense updatedExpense) {
        if (updatedExpense.getAmount() == null || updatedExpense.getDate() == null) {
            throw new IllegalArgumentException("Amount and Date must not be null");
        }

        return expenseRepository.findById(id)
                .map(expense -> {
                    expense.setAmount(updatedExpense.getAmount());
                    expense.setDescription(updatedExpense.getDescription());
                    expense.setDate(updatedExpense.getDate());
                    expense.setCategory(updatedExpense.getCategory());
                    return expenseRepository.save(expense);
                });
    }


    public void deleteById(Long id){
        expenseRepository.deleteById(id);
    }





    public List<Expense> getExpensesByCategory(String categoryName){
        return expenseRepository.findByCategory_Name(categoryName);
    }


    public List<Expense> getExpenseByDateRange(LocalDate start, LocalDate end){
        return expenseRepository.findByDateBetween(start,end);
    }





}
