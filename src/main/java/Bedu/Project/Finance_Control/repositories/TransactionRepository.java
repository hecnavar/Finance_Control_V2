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
            new Bedu.Project.Finance_Control.models.MonthlySummaryDTO(
                EXTRACT(YEAR FROM t.date),
                EXTRACT(MONTH FROM t.date),
                SUM(CASE WHEN t.type = 'INCOME' THEN t.amount ELSE 0 END),
                SUM(CASE WHEN t.type = 'EXPENSE' THEN t.amount ELSE 0 END)
            )
        FROM Transaction t
        GROUP BY EXTRACT(YEAR FROM t.date), EXTRACT(MONTH FROM t.date)
        ORDER BY 1 DESC, 2 DESC
    """)
    List<MonthlySummaryDTO> getMonthlySummaryData();
}