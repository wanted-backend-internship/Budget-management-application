package com.example.budget.domain.expenditure.domain;

import com.example.budget.domain.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
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
public class Expenditure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;
    private Long expenditureAmount;
    private LocalDateTime expenditureAt;
    private String memo;
    private Boolean isReadable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void addUser(User user) {
        this.user = user;
        if (!user.getExpenditures().contains(this)) {
            user.addExpenditure(this);
        }
    }

    public ExpenditureEditor.ExpenditureEditorBuilder toUpdate() {
        return ExpenditureEditor.builder()
                .category(category)
                .expenditureAmount(expenditureAmount)
                .expenditureAt(expenditureAt)
                .memo(memo)
                .isReadable(isReadable);
    }

    public void update(ExpenditureEditor expenditureEditor){
        category = expenditureEditor.getCategory();
        expenditureAmount = expenditureEditor.getExpenditureAmount();
        expenditureAt = expenditureEditor.getExpenditureAt();
        memo = expenditureEditor.getMemo();
        isReadable = expenditureEditor.getIsReadable();
    }
}
