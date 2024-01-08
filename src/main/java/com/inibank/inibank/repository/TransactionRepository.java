package com.inibank.inibank.repository;

import com.inibank.inibank.dto.request.TransactionRequest;
import com.inibank.inibank.dto.response.RekeningResponse;
import com.inibank.inibank.dto.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TransactionRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RekeningRepository rekeningRepository;

    public TransactionResponse transfer(TransactionRequest transactionRequest) {
        String query = "INSERT INTO t_transaction (nominal, trans_date, rekening_recipient_id, rekening_sender_id) VALUES (?,?,?,?) RETURNING id";

        String idTransaction = jdbcTemplate.queryForObject(query,
                new Object[]{
                        transactionRequest.getNominal(),
                        LocalDateTime.now(),
                        transactionRequest.getIdRekeningRecipient(),
                        transactionRequest.getIdRekeningSender()
                }, String.class);

        RekeningResponse senderRekeningResponse = rekeningRepository.getById(transactionRequest.getIdRekeningSender());
        RekeningResponse recipientRekeningResponse = rekeningRepository.getById(transactionRequest.getIdRekeningRecipient());

        return TransactionResponse.builder()
                .id(idTransaction)
                .transDate(LocalDateTime.now())
                .nominal(transactionRequest.getNominal())
                .idRekeningSender(senderRekeningResponse)
                .idRekeningRecipient(recipientRekeningResponse)
                .build();
    }

    public List<TransactionResponse> getAll() {
        String query = "SELECT * FROM t_transaction";

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            String id = rs.getString("id");
            LocalDateTime transDate = rs.getTimestamp("trans_date").toLocalDateTime();
            Integer nominal = rs.getInt("nominal");
            String idRekSender = rs.getString("rekening_sender_id");
            String idRekRecipient = rs.getString("rekening_recipient_id");

            RekeningResponse senderRekeningResponse = rekeningRepository.getById(idRekSender);
            RekeningResponse recipientRekeningResponse = rekeningRepository.getById(idRekRecipient);

            return TransactionResponse.builder()
                    .id(id)
                    .transDate(transDate)
                    .nominal(nominal)
                    .idRekeningSender(senderRekeningResponse)
                    .idRekeningRecipient(recipientRekeningResponse)
                    .build();
        });
    }

    public TransactionResponse getById(String id) {
        String query = "SELECT * FROM t_transaction WHERE id = ?";

        return jdbcTemplate.queryForObject(query, new Object[]{id}, (rs, rowNum) -> {
            String idTransaction = rs.getString("id");
            LocalDateTime transDate = rs.getTimestamp("trans_date").toLocalDateTime();
            Integer nominal = rs.getInt("nominal");
            String idRekSender = rs.getString("rekening_sender_id");
            String idRekRecipient = rs.getString("rekening_recipient_id");

            RekeningResponse senderRekeningResponse = rekeningRepository.getById(idRekSender);
            RekeningResponse recipientRekeningResponse = rekeningRepository.getById(idRekRecipient);

            return TransactionResponse.builder()
                    .id(idTransaction)
                    .transDate(transDate)
                    .nominal(nominal)
                    .idRekeningSender(senderRekeningResponse)
                    .idRekeningRecipient(recipientRekeningResponse)
                    .build();
        });
    }
}
