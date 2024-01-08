package com.inibank.inibank.dto.request;

import com.inibank.inibank.entity.Rekening;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TransactionRequest {
    private Integer nominal;
    private String idRekeningSender;
    private String idRekeningRecipient;
}
