package com.inibank.inibank.dto.request;

import com.inibank.inibank.constant.Gender;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class NasabahRequest {
    private String id;

    @NotBlank(message = "Nama harus diisi")
    private String name;

    @NotBlank(message = "Jenis Kelamin harus diisi")
    private Gender gender;

    @NotBlank(message = "Email harus diisi")
    private String email;

    @NotBlank(message = "No Telepon harus diisi")
    private String noPhone;

    @NotBlank(message = "Alamat harus diisi")
    private String address;
}
