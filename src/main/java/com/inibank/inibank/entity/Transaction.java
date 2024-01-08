package com.inibank.inibank.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_transaction")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "trans_date", nullable = false)
    private LocalDateTime transDate;

//    @Column(nullable = false, columnDefinition = "int check (nominal > 0)")
    private Integer nominal;

    @ManyToOne
    @JoinColumn(name = "rekening_sender_id")
    private Rekening idRekeningSender;

    @ManyToOne
    @JoinColumn(name = "rekening_recipient_id")
    private Rekening idRekeningRecipient;
}
