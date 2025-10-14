package Bedu.Project.Finance_Control.models;

import java.math.BigDecimal;

public interface MonthlySummaryProjection {
    Integer getYear();
    Integer getMonth();
    BigDecimal getTotalIncome();
    BigDecimal getTotalExpense();
}