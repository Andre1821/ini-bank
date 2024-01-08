package com.inibank.inibank.repository;

import com.inibank.inibank.dto.request.RekeningRequest;
import com.inibank.inibank.dto.response.NasabahResponse;
import com.inibank.inibank.dto.response.RekeningResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RekeningRepository {

    public final JdbcTemplate jdbcTemplate;
    public final NasabahRepository nasabahRepository;

    public RekeningResponse create(RekeningRequest rekeningRequest) {
        String query = "INSERT INTO m_rekening (no_rekening,saldo,nasabah_id) VALUES (?,?,?) RETURNING id";
        String idRekening = jdbcTemplate.queryForObject(query,
                new Object[]{
                        rekeningRequest.getNoRekening(),
                        rekeningRequest.getSaldo(),
                        rekeningRequest.getIdNasabah()
                }, String.class);

        NasabahResponse nasabahResponse = nasabahRepository.getById(rekeningRequest.getIdNasabah());

        return RekeningResponse.builder()
                .id(idRekening)
                .noRekening(rekeningRequest.getNoRekening())
                .saldo(rekeningRequest.getSaldo())
                .idNasabah(nasabahResponse)
                .build();
    }

    public List<RekeningResponse> getAll() {
        String query = "SELECT * FROM m_rekening";

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            String id = rs.getString("id");
            Integer noRekening = rs.getInt("no_rekening");
            Integer saldo = rs.getInt("saldo");
            String idNasabah = rs.getString("nasabah_id");


            NasabahResponse nasabahResponse = nasabahRepository.getById(idNasabah);

            return RekeningResponse.builder()
                    .id(id)
                    .noRekening(noRekening)
                    .saldo(saldo)
                    .idNasabah(nasabahResponse)
                    .build();
        });
    }

    public RekeningResponse getById(String id) {
        String query = "SELECT * FROM m_rekening WHERE id = ?";

        return jdbcTemplate.queryForObject(query, new Object[]{id}, (rs, rowNum) -> {
            String idRek = rs.getString("id");
            Integer noRekening = rs.getInt("no_rekening");
            Integer saldo = rs.getInt("saldo");
            String idNasabah = rs.getString("nasabah_id");

            NasabahResponse nasabahResponse = nasabahRepository.getById(idNasabah);

            return RekeningResponse.builder()
                    .id(idRek)
                    .noRekening(noRekening)
                    .saldo(saldo)
                    .idNasabah(nasabahResponse)
                    .build();
        });
    }

    public RekeningResponse update(RekeningRequest rekeningRequest){
        String query = "UPDATE m_rekening SET no_rekening = ?, saldo = ?, nasabah_id = ? WHERE id = ?";
        jdbcTemplate.update(query,
                rekeningRequest.getNoRekening(),
                rekeningRequest.getSaldo(),
                rekeningRequest.getIdNasabah(),
                rekeningRequest.getId()
        );
        return getById(rekeningRequest.getId());
    }

    public void delete(String id) {
        String query = "DELETE FROM m_rekening WHERE id = ?";
        jdbcTemplate.update(query, id);
    }
}
