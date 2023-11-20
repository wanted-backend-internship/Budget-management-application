package com.example.budget.domain.user;

import lombok.Getter;

@Getter
public class UserEditor {
    private Long budgetTotal;

    public UserEditor(Long budgetTotal) {
        this.budgetTotal = budgetTotal;
    }

    public static UserEditorBuilder builder() {
        return new UserEditorBuilder();
    }

    public static class UserEditorBuilder {
        private Long budgetTotal;

        UserEditorBuilder() {
        }

        public UserEditorBuilder budgetTotal(final Long budgetTotal) {
            if (budgetTotal != null) {
                this.budgetTotal = budgetTotal;
            }
            return this;
        }

        public UserEditor build() {
            return new UserEditor(budgetTotal);
        }
    }
}
