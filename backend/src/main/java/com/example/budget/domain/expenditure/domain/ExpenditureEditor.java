package com.example.budget.domain.expenditure.domain;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ExpenditureEditor {
    private String category;
    private Long expenditureAmount;
    private LocalDateTime expenditureAt;
    private String memo;
    private Boolean isReadable;

    public ExpenditureEditor(
            String category, Long expenditureAmount, LocalDateTime expenditureAt, String memo, Boolean isReadable) {
        this.category = category;
        this.expenditureAmount = expenditureAmount;
        this.expenditureAt = expenditureAt;
        this.memo = memo;
        this.isReadable = isReadable;
    }

    public static ExpenditureEditor.ExpenditureEditorBuilder builder() {
        return new ExpenditureEditorBuilder();
    }

    public static class ExpenditureEditorBuilder {
        private String category;
        private Long expenditureAmount;
        private LocalDateTime expenditureAt;
        private String memo;
        private Boolean isReadable;


        ExpenditureEditorBuilder() {
        }

        public ExpenditureEditor.ExpenditureEditorBuilder category(final String category) {
            if (category != null && !category.isEmpty()) {
                this.category = category;
            }
            return this;
        }

        public ExpenditureEditor.ExpenditureEditorBuilder expenditureAmount(final Long expenditureAmount) {
            if (expenditureAmount != null) {
                this.expenditureAmount = expenditureAmount;
            }
            return this;
        }

        public ExpenditureEditor.ExpenditureEditorBuilder expenditureAt(final LocalDateTime expenditureAt) {
            if (expenditureAt != null) {
                this.expenditureAt = expenditureAt;
            }
            return this;
        }

        public ExpenditureEditor.ExpenditureEditorBuilder memo(final String memo) {
            if (memo != null && !memo.isEmpty()) {
                this.memo = memo;
            }
            return this;
        }

        public ExpenditureEditor.ExpenditureEditorBuilder isReadable(final Boolean isReadable) {
            if (isReadable != null) {
                this.isReadable = isReadable;
            }
            return this;
        }

        public ExpenditureEditor build() {
            return new ExpenditureEditor(category, expenditureAmount, expenditureAt, memo, isReadable);
        }
    }
}
