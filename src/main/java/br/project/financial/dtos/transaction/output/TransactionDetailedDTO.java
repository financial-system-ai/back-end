package br.project.financial.dtos.transaction.output;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TransactionDetailedDTO {
    private Long id;
    private String transactionType;
    private String customer;
    private String category;
    private BigDecimal amount;
    private String branch;
    private LocalDate date;

    public TransactionDetailedDTO(
            Long id,
            String transactionType,
            String customer,
            String category,
            BigDecimal amount,
            String branch,
            LocalDate date) {
        this.id = id;
        this.transactionType = transactionType;
        this.customer = customer;
        this.category = category;
        this.amount = amount;
        this.branch = branch;
        this.date = date;
    }
}
