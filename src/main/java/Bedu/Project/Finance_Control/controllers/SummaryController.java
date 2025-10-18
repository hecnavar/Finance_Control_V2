package Bedu.Project.Finance_Control.controllers;

import Bedu.Project.Finance_Control.models.MonthlySummaryProjection;
import Bedu.Project.Finance_Control.services.TransactionService;
import org.springframework.web.bind.annotation.*;
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
    public BigDecimal getBalance() {
        return service.calculateBalance();
    }

    @GetMapping("/monthly")
    public List<MonthlySummaryProjection> getMonthlySummary() {
        return service.getMonthlySummary();
    }
}