package com.transactiontransferworker.converters;

import com.transactiontransferworker.api.dtos.TransactionTransferDTO;
import com.transactiontransferworker.api.dtos.UserTransferDTO;
import com.transactiontransferworker.repository.models.Transaction;
import com.transactiontransferworker.repository.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionConverter {

    public List<TransactionTransferDTO> convertToTransactionTransferDTOList(List<Transaction> transactionList) {

        return transactionList
                .stream()
                .map(this::convertToTransactionTransferDTO)
                .collect(Collectors.toList());
    }

    public TransactionTransferDTO convertToTransactionTransferDTO(Transaction transaction) {
        TransactionTransferDTO transactionTransferDTO = new TransactionTransferDTO();

        transactionTransferDTO.setSender(getUserTransferDTO(transaction.getSender()));
        transactionTransferDTO.setReceiver(getUserTransferDTO(transaction.getReceiver()));
        transactionTransferDTO.setAmountTransferred(transaction.getAmount());

        return transactionTransferDTO;
    }

    private UserTransferDTO getUserTransferDTO(User user) {
        UserTransferDTO userTransferDTO = new UserTransferDTO();

        userTransferDTO.setFirstName(user.getFirstName());
        userTransferDTO.setLastName(user.getLastName());
        userTransferDTO.setDocument(user.getDocument());
        userTransferDTO.setEmail(user.getEmail());
        userTransferDTO.setBalance(user.getBalance());
        userTransferDTO.setUserType(user.getUserType());

        return userTransferDTO;
    }
}
