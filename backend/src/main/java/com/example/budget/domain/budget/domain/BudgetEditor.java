package com.example.budget.domain.budget.domain;

import lombok.Getter;

@Getter
public class BudgetEditor {
    private String category;
    private Long percentage;

    public BudgetEditor(String category, Long percentage) {
        this.category = category;
        this.percentage = percentage;
    }

    public static BudgetEditorBuilder builder() {
        return new BudgetEditorBuilder();
    }

    public static class BudgetEditorBuilder {
        private String category;
        private Long percentage;

        BudgetEditorBuilder() {
        }

        public BudgetEditorBuilder category(final String category) {
            if (category != null && !category.isEmpty()) {
                this.category = category;
            }
            return this;
        }

        public BudgetEditorBuilder percentage(final Long percentage) {
            if (percentage != null) {
                this.percentage = percentage;
            }
            return this;
        }

        public BudgetEditor build() {
            return new BudgetEditor(category, percentage);
        }
    }
}
