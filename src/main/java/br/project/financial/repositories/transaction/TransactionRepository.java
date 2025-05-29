package br.project.financial.repositories.transaction;

import br.project.financial.dtos.transaction.output.TransactionRevenueOutputDTO;
import br.project.financial.dtos.transaction.output.BranchTransactionRevenueDTO;
import br.project.financial.dtos.transaction.output.BranchAmountDTO;
import br.project.financial.entities.Transaction;
import br.project.financial.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("""
                SELECT new br.project.financial.dtos.transaction.output.TransactionRevenueOutputDTO(
                    t.transactionType,
                    :startDate,
                    :endDate,
                    SUM(t.amount)
                )
                FROM Transaction t
                WHERE t.transactionType = :transactionType
                  AND t.date BETWEEN :startDate AND :endDate
            """)
    TransactionRevenueOutputDTO sumByTypeAndPeriod(
            @Param("transactionType") TransactionType transactionType,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("""
                SELECT new br.project.financial.dtos.transaction.output.BranchTransactionRevenueDTO(
                    t.transactionType,
                    t.branch,
                    :startDate,
                    :endDate,
                    SUM(t.amount)
                )
                FROM Transaction t
                WHERE t.transactionType = :transactionType
                  AND t.branch = :branch
                  AND t.date BETWEEN :startDate AND :endDate
            """)
    BranchTransactionRevenueDTO sumByTypeBranchAndPeriod(
            @Param("transactionType") TransactionType transactionType,
            @Param("branch") String branch,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("""
                SELECT new br.project.financial.dtos.transaction.output.BranchAmountDTO(
                    t.branch,
                    SUM(t.amount)
                )
                FROM Transaction t
                WHERE t.transactionType = :transactionType
                GROUP BY t.branch
                ORDER BY SUM(t.amount) DESC
            """)
    List<BranchAmountDTO> sumByTypeGroupedByBranchOrderedDesc(
            @Param("transactionType") TransactionType transactionType
    );

    @Query("""
                SELECT new br.project.financial.dtos.transaction.output.BranchAmountDTO(
                    t.branch,
                    SUM(t.amount)
                )
                FROM Transaction t
                WHERE t.transactionType = :transactionType
                  AND t.date BETWEEN :startDate AND :endDate
                GROUP BY t.branch
                ORDER BY SUM(t.amount) DESC
            """)
    List<BranchAmountDTO> findTop2BranchesByTransactionTypeAndDateBetween(
            @Param("transactionType") TransactionType transactionType,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    List<Transaction> findByBranchAndDateBetweenOrderByDateAsc(
            @Param("branch") String branch,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
