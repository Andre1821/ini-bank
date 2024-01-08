package com.inibank.inibank.service.impl;

import com.inibank.inibank.dto.request.RekeningRequest;
import com.inibank.inibank.dto.request.TransactionRequest;
import com.inibank.inibank.dto.response.RekeningResponse;
import com.inibank.inibank.dto.response.TransactionResponse;
import com.inibank.inibank.repository.RekeningRepository;
import com.inibank.inibank.repository.TransactionRepository;
import com.inibank.inibank.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final RekeningRepository rekeningRepository;


    @Override
    public TransactionResponse transfer(TransactionRequest transactionRequest) {
        RekeningResponse rekeningSender = rekeningRepository.getById(transactionRequest.getIdRekeningSender());
        RekeningResponse rekeningRecipient = rekeningRepository.getById(transactionRequest.getIdRekeningRecipient());

        if (rekeningSender != null && rekeningRecipient != null && !rekeningSender.getNoRekening().equals(rekeningRecipient.getNoRekening())){
            if (rekeningSender.getSaldo() >= transactionRequest.getNominal()){
                TransactionResponse transactionResponse = transactionRepository.transfer(transactionRequest);

                Integer senderRemainingBalance = rekeningSender.getSaldo() - transactionRequest.getNominal();
                Integer recipientBalance = rekeningRecipient.getSaldo() + transactionRequest.getNominal();

                RekeningRequest senderRekeningRequest = RekeningRequest.builder()
                        .id(rekeningSender.getId())
                        .noRekening(rekeningSender.getNoRekening())
                        .saldo(senderRemainingBalance)
                        .idNasabah(rekeningSender.getIdNasabah().getId())
                        .build();

                RekeningRequest recipientRekeningRequest = RekeningRequest.builder()
                        .id(rekeningRecipient.getId())
                        .noRekening(rekeningRecipient.getNoRekening())
                        .saldo(recipientBalance)
                        .idNasabah(rekeningRecipient.getIdNasabah().getId())
                        .build();
                rekeningRepository.update(senderRekeningRequest);
                rekeningRepository.update(recipientRekeningRequest);

                RekeningResponse rekeningSenderLatest = rekeningRepository.getById(transactionRequest.getIdRekeningSender());
                RekeningResponse rekeningRecipientLatest = rekeningRepository.getById(transactionRequest.getIdRekeningRecipient());

                return TransactionResponse.builder()
                        .id(transactionResponse.getId())
                        .transDate(transactionResponse.getTransDate())
                        .nominal(transactionResponse.getNominal())
                        .idRekeningSender(rekeningSenderLatest)
                        .idRekeningRecipient(rekeningRecipientLatest)
                        .build();
            } else {
                throw new IllegalStateException("Saldo anda tidak mencukupi untuk melakukan transfer");
            }
        } else {
            throw  new NoSuchElementException("Rekening yang anda cari tidak ditemukan atau anda transfer ke rekening anda sendiri tidak diizinkan");
        }
    }

    @Override
    public List<TransactionResponse> getAll() {
        return transactionRepository.getAll();
    }

    @Override
    public TransactionResponse getById(String id) {
        TransactionResponse transactionResponse = transactionRepository.getById(id);

        if (transactionResponse == null) {
            throw new NoSuchElementException("Transaction dengan ID " + id + " tidak ditemukan");
        }

        return transactionResponse;
    }
}
