package br.project.financial.mappers.transaction;

import br.project.financial.dtos.transaction.output.TransactionDetailedDTO;
import br.project.financial.entities.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionDetailedMapper {
    TransactionDetailedDTO toDto(Transaction entity);
    List<TransactionDetailedDTO> toDtos(List<Transaction> entities);
}
