
package Bedu.Project.Finance_Control;

import Bedu.Project.Finance_Control.models.Transaction;
import Bedu.Project.Finance_Control.repositories.TransactionRepository;
import org.springframework.boot.CommandLineRunner; 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean; 

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class FinanceControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceControlApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner initData(TransactionRepository repository) {
        return (args) -> {
            if (repository.count() == 0) { 
                
                
                repository.save(new Transaction(
                    new BigDecimal("3500.00"), 
                    "INCOME", 
                    LocalDate.parse("2025-09-15"), 
                    "Salario Septiembre", 
                    "Salario")
                );
                repository.save(new Transaction(
                    new BigDecimal("500.00"), 
                    "EXPENSE", 
                    LocalDate.parse("2025-09-20"), 
                    "Renta", 
                    "Vivienda")
                );

                repository.save(new Transaction(
                    new BigDecimal("150.50"), 
                    "INCOME", 
                    LocalDate.parse("2025-10-05"), 
                    "Venta de garage", 
                    "Varios")
                );
                repository.save(new Transaction(
                    new BigDecimal("30.00"), 
                    "EXPENSE", 
                    LocalDate.parse("2025-10-01"), 
                    "Café", 
                    "Comida")
                );
                repository.save(new Transaction(
                    new BigDecimal("45.99"), 
                    "EXPENSE", 
                    LocalDate.parse("2025-10-14"), 
                    "Cena", 
                    "Comida")
                );
            }
        };
    }
}