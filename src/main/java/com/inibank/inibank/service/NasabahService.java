package com.inibank.inibank.service;

import com.inibank.inibank.dto.request.NasabahRequest;
import com.inibank.inibank.dto.response.NasabahResponse;

import java.util.List;

public interface NasabahService{
    NasabahResponse create(NasabahRequest nasabahRequest);
    List<NasabahResponse> getAll();
    NasabahResponse getById(String id);
    NasabahResponse update(NasabahRequest nasabahRequest);
    void delete(String id);
}
