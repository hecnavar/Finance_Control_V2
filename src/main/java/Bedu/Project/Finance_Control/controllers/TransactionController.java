package Bedu.Project.Finance_Control.controllers;

import Bedu.Project.Finance_Control.models.Transaction;
import Bedu.Project.Finance_Control.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transactions") 
@CrossOrigin(origins = "http://localhost:5173")
public class TransactionController {

private final TransactionService service;

    public TransactionController(TransactionService service) {
    this.service = service;
    }


    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
    Transaction newTransaction = service.save(transaction); 
    return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transactionDetails) {
    if (!service.findById(id).isPresent()) {
    return ResponseEntity.notFound().build();
    }

    transactionDetails.setId(id); 
    Transaction updatedTransaction = service.save(transactionDetails);
    return ResponseEntity.ok(updatedTransaction);
    }

    @GetMapping
    public List<Transaction> findAll() {
    return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable Long id) {
    return service.findById(id)
    .map(ResponseEntity::ok)
    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    if (!service.findById(id).isPresent()) {
    return ResponseEntity.notFound().build();
    }
    service.deleteById(id);
    return ResponseEntity.noContent().build();
    }
}