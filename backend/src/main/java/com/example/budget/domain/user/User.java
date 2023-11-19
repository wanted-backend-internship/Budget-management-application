package com.example.budget.domain.user;

import com.example.budget.domain.budget.entity.Budget;
import com.example.budget.domain.expenditure.Expenditure;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    private String email;
    private String username;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Budget> budgets = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Expenditure> expenditures = new ArrayList<>();

    // 연관 관계 편의 메서드
    public void addBudgets(Budget budget) {
        this.budgets.add(budget);
        budget.addUser(this);
    }

    public void addExpenditure(Expenditure expenditure) {
        this.expenditures.add(expenditure);
        expenditure.addUser(this);
    }
}
