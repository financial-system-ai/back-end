package br.project.financial.rest.specs;

import br.project.financial.dtos.transaction.input.BranchAmountInputDTO;
import br.project.financial.dtos.transaction.input.BranchTransactionRevenueInputDTO;
import br.project.financial.dtos.transaction.input.Top2BranchesComparisonInputDTO;
import br.project.financial.dtos.transaction.input.TransactionDetailedInputDTO;
import br.project.financial.dtos.transaction.input.TransactionRevenueByDateInputDTO;
import br.project.financial.dtos.transaction.input.TransactionRevenueByPeriodInputDTO;
import br.project.financial.dtos.transaction.output.*;
import br.project.financial.rest.specs.commons.response.error.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "Transactions", description = "Endpoints for financial transactions")
@RequestMapping("/v1/transactions")
@ApiResponseInternalServerError
public interface TransactionControllerSpecs {

    @Operation(summary = "Get transaction by type and date")
    @ApiResponseBadRequest
    @ApiResponseNotFound
    @GetMapping
    ResponseEntity<TransactionRevenueOutputDTO> getRevenueByTypeAndDate(TransactionRevenueByDateInputDTO inputDTO);

    @Operation(summary = "Get transaction by type and period")
    @ApiResponseBadRequest
    @ApiResponseNotFound
    @ApiResponseInvalidPeriod
    @ApiResponseNoTransactionsFound
    @GetMapping("/period")
    ResponseEntity<TransactionRevenueOutputDTO> getRevenueByTypeAndPeriod(TransactionRevenueByPeriodInputDTO inputDTO);

    @Operation(summary = "Get transaction by branch, type and period")
    @ApiResponseBadRequest
    @ApiResponseInvalidPeriod
    @ApiResponseNotFound
    @ApiResponseNoTransactionsFound
    @GetMapping("/branch")
    ResponseEntity<BranchTransactionRevenueOutputDTO> getRevenueByBranchTypeAndPeriod(BranchTransactionRevenueInputDTO inputDTO);

    @Operation(summary = "get the branch that had the highest value by transaction type")
    @ApiResponseBadRequest
    @ApiResponseNotFound
    @ApiResponseNoTransactionsFound
    @GetMapping("/branch/top")
    ResponseEntity<BranchAmountOutputDTO> getTopBranchByType(BranchAmountInputDTO inputDTO);

    @Operation(summary = "Compare the turnover of the top 2 transaction branches for a given type and period")
    @ApiResponseBadRequest
    @ApiResponseNotFound
    @ApiResponseInvalidPeriod
    @ApiResponseNoTransactionsFound
    @GetMapping("/comparison")
    ResponseEntity<Top2BranchesComparisonDTO> compareTop2Branches(Top2BranchesComparisonInputDTO inputDTO);

    @Operation(summary = "List detailed transactions for a branch and date period")
    @ApiResponseBadRequest
    @ApiResponseNotFound
    @ApiResponseInvalidPeriod
    @ApiResponseNoTransactionsFound
    @GetMapping("/detailed")
    ResponseEntity<List<TransactionDetailedOutputDTO>> getDetailedTransactions(TransactionDetailedInputDTO inputDTO);
}
