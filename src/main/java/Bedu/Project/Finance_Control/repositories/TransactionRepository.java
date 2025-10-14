package Bedu.Project.Finance_Control.repositories;

import Bedu.Project.Finance_Control.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    
}