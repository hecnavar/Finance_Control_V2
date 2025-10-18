package Bedu.Project.Finance_Control.repositories;

import Bedu.Project.Finance_Control.models.Transaction;
import Bedu.Project.Finance_Control.repositories.TransactionRepository;
import Bedu.Project.Finance_Control.models.MonthlySummaryDTO;
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
             EXTRACT(YEAR FROM t.date) AS totalYear,
            EXTRACT(MONTH FROM t.date) AS totalMonth,
            SUM(CASE WHEN t.type = 'INCOME' THEN t.amount ELSE 0 END) AS totalIncome,
            SUM(CASE WHEN t.type = 'EXPENSE' THEN t.amount ELSE 0 END) AS totalExpense
        FROM transactions t
        GROUP BY 1, 2 
        ORDER BY 1 DESC, 2 DESC
    """, nativeQuery = true)
    List<MonthlySummaryDTO> getMonthlySummaryData();
}