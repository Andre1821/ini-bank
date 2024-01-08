package com.inibank.inibank.service;

import com.inibank.inibank.dto.request.TransactionRequest;
import com.inibank.inibank.dto.response.TransactionResponse;

import java.util.List;

public interface TransactionService {
    TransactionResponse transfer (TransactionRequest transactionRequest);
    List<TransactionResponse> getAll();
    TransactionResponse getById (String id);
}
