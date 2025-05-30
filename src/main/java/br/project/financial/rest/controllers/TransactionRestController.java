package br.project.financial.rest.controllers;

import br.project.financial.dtos.transaction.input.BranchAmountInputDTO;
import br.project.financial.dtos.transaction.input.BranchTransactionRevenueInputDTO;
import br.project.financial.dtos.transaction.input.Top2BranchesComparisonInputDTO;
import br.project.financial.dtos.transaction.input.TransactionDetailedInputDTO;
import br.project.financial.dtos.transaction.input.TransactionRevenueByDateInputDTO;
import br.project.financial.dtos.transaction.input.TransactionRevenueByPeriodInputDTO;
import br.project.financial.dtos.transaction.output.*;
import br.project.financial.rest.specs.TransactionControllerSpecs;
import br.project.financial.usecases.transaction.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/transactions")
public class TransactionRestController implements TransactionControllerSpecs {

    private final CalculateRevenueByTypeAndDateUseCase byDateUseCase;
    private final CalculateRevenueByTypeAndPeriodUseCase byPeriodUseCase;
    private final CalculateBranchRevenueByTypeAndPeriodUseCase byBranchUseCase;
    private final CalculateTopBranchByTypeUseCase topBranchUseCase;
    private final CalculateTop2BranchesComparisonUseCase comparisonUseCase;
    private final ListDetailedTransactionsUseCase detailedTransactionsUseCase;

    public TransactionRestController(
            CalculateRevenueByTypeAndPeriodUseCase byPeriodUseCase,
            CalculateRevenueByTypeAndDateUseCase byDateUseCase,
            CalculateBranchRevenueByTypeAndPeriodUseCase byBranchUseCase,
            CalculateTopBranchByTypeUseCase topBranchUseCase,
            CalculateTop2BranchesComparisonUseCase comparisonUseCase,
            ListDetailedTransactionsUseCase detailedTransactionsUseCase
    ) {
        this.byPeriodUseCase = byPeriodUseCase;
        this.byDateUseCase = byDateUseCase;
        this.byBranchUseCase = byBranchUseCase;
        this.topBranchUseCase = topBranchUseCase;
        this.comparisonUseCase = comparisonUseCase;
        this.detailedTransactionsUseCase = detailedTransactionsUseCase;
    }

    @GetMapping
    public ResponseEntity<TransactionRevenueOutputDTO> getRevenueByTypeAndDate(
            @ModelAttribute TransactionRevenueByDateInputDTO inputDTO){
        return ResponseEntity.ok(byDateUseCase.execute(inputDTO.getType(), inputDTO.getDate()));
    }

            @GetMapping("/period")
    public ResponseEntity<TransactionRevenueOutputDTO> getRevenueByTypeAndPeriod(@ModelAttribute TransactionRevenueByPeriodInputDTO inputDTO) {
        return ResponseEntity.ok(byPeriodUseCase.execute(inputDTO.getType(), inputDTO.getStartDate(), inputDTO.getEndDate()));
    }

    @GetMapping("/branch")
    public ResponseEntity<BranchTransactionRevenueOutputDTO> getRevenueByBranchTypeAndPeriod(@ModelAttribute BranchTransactionRevenueInputDTO inputDTO) {
        return ResponseEntity.ok(byBranchUseCase.execute(inputDTO.getType(), inputDTO.getBranch(), inputDTO.getStartDate(), inputDTO.getEndDate()));
    }

    @GetMapping("/branch/top")
    public ResponseEntity<BranchAmountOutputDTO> getTopBranchByType(@ModelAttribute BranchAmountInputDTO inputDTO) {
        return ResponseEntity.ok(topBranchUseCase.execute(inputDTO.getType()));
    }

    @GetMapping("/comparison")
    public ResponseEntity<Top2BranchesComparisonDTO> compareTop2Branches(@ModelAttribute Top2BranchesComparisonInputDTO inputDTO) {
        return ResponseEntity.ok(comparisonUseCase.execute(inputDTO.getType(), inputDTO.getStartDate(), inputDTO.getEndDate()));
    }

    @GetMapping("/detailed")
    public ResponseEntity<List<TransactionDetailedDTO>> getDetailedTransactions(@ModelAttribute TransactionDetailedInputDTO inputDTO) {
        return ResponseEntity.ok(detailedTransactionsUseCase.execute(inputDTO.getBranch(), inputDTO.getStartDate(), inputDTO.getEndDate()));
    }
}
