package com.inibank.inibank.controller;

import com.inibank.inibank.constant.AppPath;
import com.inibank.inibank.dto.request.NasabahRequest;
import com.inibank.inibank.dto.response.CommonResponse;
import com.inibank.inibank.dto.response.NasabahResponse;
import com.inibank.inibank.service.NasabahService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.NASABAH)
public class NasabahController {
    private final NasabahService nasabahService;

    @PostMapping
    public ResponseEntity<?> createNasabah(@RequestBody NasabahRequest nasabahRequest){
        NasabahResponse nasabahResponse = nasabahService.create(nasabahRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .messages("Berhasil membuat nasabah baru")
                        .data(nasabahResponse)
                        .build());
    }

    @GetMapping
    public ResponseEntity<?> getAllNasabah(){
        List<NasabahResponse> nasabahResponses = nasabahService.getAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .messages("Berhasil mengakses semua data nasabah")
                        .data(nasabahResponses)
                        .build());
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody NasabahRequest nasabahRequest){
        NasabahResponse nasabahResponse = nasabahService.update(nasabahRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .messages("Berhasil update 1 data")
                        .data(nasabahResponse)
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        NasabahResponse nasabahResponse = nasabahService.getById(id);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.FOUND.value())
                        .messages("Data nasabah ditemukan")
                        .data(nasabahResponse)
                        .build());
    }

    @DeleteMapping("/{id}")
    public void deleteNasabah(@PathVariable String id){
        nasabahService.delete(id);
    }
}
