package com.inibank.inibank.repository;

import com.inibank.inibank.constant.Gender;
import com.inibank.inibank.dto.request.NasabahRequest;
import com.inibank.inibank.dto.response.NasabahResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NasabahRepository {

    private final JdbcTemplate jdbcTemplate;

    public NasabahResponse create(NasabahRequest nasabahRequest) {
        String query = "INSERT INTO m_nasabah (name,gender,email,no_phone,address) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(query,
                nasabahRequest.getName(),
                nasabahRequest.getGender().toString(),
                nasabahRequest.getEmail(),
                nasabahRequest.getNoPhone(),
                nasabahRequest.getAddress());

        String select = "SELECT * FROM m_nasabah WHERE email = ?";
        return jdbcTemplate.queryForObject(select, new Object[]{nasabahRequest.getEmail()}, (rs, rowNum) ->
                new NasabahResponse(
                        rs.getString("id"),
                        rs.getString("name"),
                        Gender.valueOf(rs.getString("gender")),
                        rs.getString("email"),
                        rs.getString("no_phone"),
                        rs.getString("address"))
        );
    }

    public List<NasabahResponse> getAll() {
        String query = "SELECT * FROM m_nasabah";
        return jdbcTemplate.query(query, (rs, rowNum) ->
                new NasabahResponse(
                        rs.getString("id"),
                        rs.getString("name"),
                        Gender.valueOf(rs.getString("gender")),
                        rs.getString("email"),
                        rs.getString("no_phone"),
                        rs.getString("address")
                ));
    }

    public NasabahResponse getById(String id) {
        String query = "SELECT * FROM m_nasabah WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, (rs, rowNum) ->
                new NasabahResponse(
                        rs.getString("id"),
                        rs.getString("name"),
                        Gender.valueOf(rs.getString("gender")),
                        rs.getString("email"),
                        rs.getString("no_phone"),
                        rs.getString("address")
                )
        );
    }

    public NasabahResponse update(NasabahRequest nasabahRequest) {
        String query = "UPDATE m_nasabah SET name = ?, gender = ?, email = ?, no_phone = ?, address = ? WHERE id = ?";

        jdbcTemplate.update(query,
                nasabahRequest.getName(),
                nasabahRequest.getGender().toString(),
                nasabahRequest.getEmail(),
                nasabahRequest.getNoPhone(),
                nasabahRequest.getAddress(),
                nasabahRequest.getId());

        String select = "SELECT * FROM m_nasabah WHERE email = ?";
        return jdbcTemplate.queryForObject(select, new Object[]{nasabahRequest.getEmail()}, (rs, rowNum) ->
                new NasabahResponse(
                        rs.getString("id"),
                        rs.getString("name"),
                        Gender.valueOf(rs.getString("gender")),
                        rs.getString("email"),
                        rs.getString("no_phone"),
                        rs.getString("address"))
        );
    }

    public void deleteById(String id) {
        String query = "DELETE FROM m_nasabah WHERE id = ?";
        jdbcTemplate.update(query, id);
    }
}
