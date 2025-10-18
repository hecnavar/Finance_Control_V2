// MonthlySummaryDTO.java
package Bedu.Project.Finance_Control.models;

import java.math.BigDecimal;

public class MonthlySummaryDTO {

    private final Integer totalYear; 
    private final Integer totalMonth; 
    private final BigDecimal totalIncome;
    private final BigDecimal totalExpense;


    public MonthlySummaryDTO(Integer totalYear, Integer totalMonth, BigDecimal totalIncome, BigDecimal totalExpense) {
        this.totalYear = totalYear;
        this.totalMonth = totalMonth;
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
  }

  public Integer getTotalYear() {
    return totalYear;
  }

  public Integer getTotalMonth() {
    return totalMonth;
  }

  public BigDecimal getTotalIncome() {
    return totalIncome;
  }

  public BigDecimal getTotalExpense() {
    return totalExpense;
  }
}