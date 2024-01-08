package com.inibank.inibank.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TransactionResponse {
    private String id;
    private LocalDateTime transDate;
    private Integer nominal;
    private RekeningResponse idRekeningSender;
    private RekeningResponse idRekeningRecipient;
}
