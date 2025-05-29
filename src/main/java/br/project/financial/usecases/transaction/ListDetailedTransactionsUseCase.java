package br.project.financial.usecases.transaction;

import br.project.financial.dtos.transaction.output.TransactionDetailedOutputDTO;
import br.project.financial.errors.ExceptionCode;
import br.project.financial.errors.exceptions.BusinessRuleException;
import br.project.financial.errors.exceptions.NoTransactionsFoundException;
import br.project.financial.mappers.transaction.TransactionDetailedMapper;
import br.project.financial.repositories.transaction.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ListDetailedTransactionsUseCase {

    private final TransactionRepository repository;
    private final TransactionDetailedMapper mapper;

    public ListDetailedTransactionsUseCase(
            TransactionRepository repository,
            TransactionDetailedMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<TransactionDetailedOutputDTO> execute(
            String branch,
            LocalDate startDate,
            LocalDate endDate
    ) {
        if (startDate.isAfter(endDate)) {
            throw new BusinessRuleException(
                    ExceptionCode.INVALID_PERIOD,
                    Map.of("message", "Start date must be before end date")
            );
        }

        var entities = repository.findByBranchAndDateBetweenOrderByDateAsc(branch, startDate, endDate);

        if (entities.isEmpty()) {
            throw new NoTransactionsFoundException(Map.of(
                    "branch", branch,
                    "startDate", startDate,
                    "endDate", endDate
            ));
        }

        return mapper.toDtos(entities);

    }
}
