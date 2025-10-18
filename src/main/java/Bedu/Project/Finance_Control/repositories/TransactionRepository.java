// Archivo: TransactionRepository.java (SOLUCIÓN FINAL DEL ERROR 500)

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
            CAST(FORMATDATETIME(t.date, 'yyyy') AS INT) AS year, 
            CAST(FORMATDATETIME(t.date, 'MM') AS INT) AS month,
            SUM(CASE WHEN t.type = 'INCOME' THEN t.amount ELSE 0 END) AS totalIncome,
            SUM(CASE WHEN t.type = 'EXPENSE' THEN t.amount ELSE 0 END) AS totalExpense
        FROM transactions t
        -- CORRECCIÓN FINAL: Repetir las funciones completas en GROUP BY
        GROUP BY CAST(FORMATDATETIME(t.date, 'yyyy') AS INT), CAST(FORMATDATETIME(t.date, 'MM') AS INT)
        ORDER BY year DESC, month DESC
    """, nativeQuery = true)
    List<MonthlySummaryProjection> getMonthlySummaryData();
}