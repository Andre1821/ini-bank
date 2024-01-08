package com.inibank.inibank.dto.response;

import com.inibank.inibank.constant.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class NasabahResponse {
    private String id;
    private String name;
    private Gender gender;
    private String email;
    private String noPhone;
    private String address;
}
