package com.inibank.inibank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "m_rekening")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Rekening {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    @Size(max = 10, message = "No Rekening should be at most 10 digits")
    private Integer noRekening;

//    @Column(nullable = false, columnDefinition = "int check (saldo > 0)")
    private Integer saldo;

    @OneToOne
    @JoinColumn(name = "nasabah_id")
    private Nasabah idNasabah;
}
