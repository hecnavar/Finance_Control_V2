package Bedu.Project.Finance_Control.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private String type;
    private LocalDate date;
    private String description;
    private String category;
    
    public Transaction() {}



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}