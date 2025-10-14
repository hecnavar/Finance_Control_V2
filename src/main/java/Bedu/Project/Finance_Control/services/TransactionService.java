package Bedu.Project.Finance_Control.services;

import Bedu.Project.Finance_Control.models.Transaction;
import Bedu.Project.Finance_Control.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public Transaction save(Transaction transaction) {
        return repository.save(transaction);
    }
    
    public List<Transaction> findAll() {
        return repository.findAll();
    }
    
    public Optional<Transaction> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}