package com.example.budget.domain.budget.entity;

import com.example.budget.domain.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;
    private Long percentage;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void addUser(User user) {
        this.user = user;
        if (!user.getBudgets().contains(this)) {
            user.addBudgets(this);
        }
    }

    public BudgetEditor.BudgetEditorBuilder toUpdate() {
        return BudgetEditor.builder()
                .category(category)
                .percentage(percentage);
    }

    public void update(BudgetEditor budgetEditor){
        category = budgetEditor.getCategory();
        percentage = budgetEditor.getPercentage();
    }
}
