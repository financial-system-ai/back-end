package br.project.financial.mappers.transaction;

import br.project.financial.dtos.transaction.output.TransactionDetailedOutputDTO;
import br.project.financial.entities.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionDetailedMapper {
    TransactionDetailedOutputDTO toDto(Transaction entity);
    List<TransactionDetailedOutputDTO> toDtos(List<Transaction> entities);
}
