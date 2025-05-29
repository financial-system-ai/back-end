package br.project.financial.dtos.transaction.input;

import br.project.financial.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BranchAmountInputDTO {

    private TransactionType transactionType;
}
