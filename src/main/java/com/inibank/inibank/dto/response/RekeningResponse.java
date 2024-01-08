package com.inibank.inibank.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RekeningResponse {
    private String id;
    private Integer noRekening;
    private Integer saldo;
    private NasabahResponse idNasabah;
}
