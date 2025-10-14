package Bedu.Project.Finance_Control.repositories;

import Bedu.Project.Finance_Control.models.Transaction;
import Bedu.Project.Finance_Control.models.MonthlySummaryProjection; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    @Query("""
        SELECT SUM(CASE 
                        WHEN t.type = 'INCOME' THEN t.amount 
                        ELSE -t.amount 
                    END) 
        FROM Transaction t
    """)
    BigDecimal calculateTotalBalance();
    
    @Query(value = """
        SELECT 
            YEAR(t.date) AS year, 
            MONTH(t.date) AS month,
            SUM(CASE WHEN t.type = 'INCOME' THEN t.amount ELSE 0 END) AS totalIncome,
            SUM(CASE WHEN t.type = 'EXPENSE' THEN t.amount ELSE 0 END) AS totalExpense
        FROM transactions t
        GROUP BY year, month
        ORDER BY year DESC, month DESC
    """, nativeQuery = true)
    List<MonthlySummaryProjection> getMonthlySummaryData();
}