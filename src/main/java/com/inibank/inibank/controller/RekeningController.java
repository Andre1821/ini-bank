package com.inibank.inibank.controller;

import com.inibank.inibank.constant.AppPath;
import com.inibank.inibank.dto.request.RekeningRequest;
import com.inibank.inibank.dto.response.CommonResponse;
import com.inibank.inibank.dto.response.RekeningResponse;
import com.inibank.inibank.service.RekeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.REKENING)
public class RekeningController {
    private final RekeningService rekeningService;

    @PostMapping
    public ResponseEntity<?> createRekening(@RequestBody RekeningRequest rekeningRequest){
        RekeningResponse rekeningResponse = rekeningService.create(rekeningRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .messages("Berhasil membuat rekening baru")
                        .data(rekeningResponse)
                        .build());
    }

    @GetMapping
    public ResponseEntity<?> getAllRekening(){
        List<RekeningResponse> rekeningResponses = rekeningService.getAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .messages("Berhasil mengakses semua data rekening")
                        .data(rekeningResponses)
                        .build());
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody RekeningRequest rekeningRequest){
        RekeningResponse rekeningResponse = rekeningService.update(rekeningRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .messages("Berhasil update 1 data")
                        .data(rekeningResponse)
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        RekeningResponse rekeningResponse = rekeningService.getById(id);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.FOUND.value())
                        .messages("Data Rekening ditemukan")
                        .data(rekeningResponse)
                        .build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        rekeningService.delete(id);
    }
}
