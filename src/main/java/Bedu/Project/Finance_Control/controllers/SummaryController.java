package Bedu.Project.Finance_Control.controllers;

import Bedu.Project.Finance_Control.models.MonthlySummaryDTO;
import Bedu.Project.Finance_Control.services.TransactionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.math.BigDecimal;
import java.util.List;

@RestController 
@RequestMapping("/api/summary") 
@CrossOrigin(origins = "http://localhost:5173") 
public class SummaryController {

    private final TransactionService service;

    public SummaryController(TransactionService service) {
        this.service = service;
    }

    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getBalance() {
        BigDecimal balance = service.calculateBalance();
        BigDecimal safeBalance = balance != null ? balance : BigDecimal.ZERO; 
        
        return ResponseEntity.ok(safeBalance);
    }

    @GetMapping("/monthly")
    public ResponseEntity<List<MonthlySummaryDTO>> getMonthlySummary() {
        List<MonthlySummaryDTO> summary = service.getMonthlySummary(); 
        return ResponseEntity.ok(summary);
    }
}