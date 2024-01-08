package com.inibank.inibank.service.impl;

import com.inibank.inibank.dto.request.NasabahRequest;
import com.inibank.inibank.dto.response.NasabahResponse;
import com.inibank.inibank.repository.NasabahRepository;
import com.inibank.inibank.service.NasabahService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class NasabahServiceImpl implements NasabahService {
    private final NasabahRepository nasabahRepository;

    @Override
    public NasabahResponse create(NasabahRequest nasabahRequest) {
        return nasabahRepository.create(nasabahRequest);
    }

    @Override
    public List<NasabahResponse> getAll() {
        return nasabahRepository.getAll();
    }

    @Override
    public NasabahResponse getById(String id) {
        NasabahResponse currentNasabah = nasabahRepository.getById(id);
        if (currentNasabah == null) throw new NoSuchElementException("Nasabah dengan ID " + id + " tidak ditemukan");

        return currentNasabah;
    }

    @Override
    public NasabahResponse update(NasabahRequest nasabahRequest) {
        NasabahResponse currentNasabah = getById(nasabahRequest.getId());
        if (currentNasabah == null) throw new NoSuchElementException("Nasabah dengan ID " + nasabahRequest.getId() + " tidak ditemukan");
        return nasabahRepository.update(nasabahRequest);
    }

    @Override
    public void delete(String id) {
        NasabahResponse currentNasabah = getById(id);
        if (currentNasabah == null) throw new NoSuchElementException("Nasabah dengan ID " + id + " tidak ditemukan");
        nasabahRepository.deleteById(id);
    }
}
