package com.inibank.inibank.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RekeningRequest {
    private String id;

    @NotBlank(message = "Nomor rekening harus diisi")
    private Integer noRekening;

    @NotBlank(message = "saldo harus diisi")
    @Min(value = 0, message = "saldo must be greater then equal 0")
    private Integer saldo;

    @NotBlank(message = "ID nasabah harus diisi")
    private String idNasabah;
}
