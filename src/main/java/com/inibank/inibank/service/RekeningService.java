package com.inibank.inibank.service;

import com.inibank.inibank.dto.request.RekeningRequest;
import com.inibank.inibank.dto.response.RekeningResponse;

import java.util.List;

public interface RekeningService {
    RekeningResponse create(RekeningRequest rekeningRequest);
    List<RekeningResponse> getAll();
    RekeningResponse getById(String id);
    RekeningResponse update(RekeningRequest rekeningRequest);
    void delete(String id);
}
