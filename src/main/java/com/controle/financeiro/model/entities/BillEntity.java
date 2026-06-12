package com.controle.financeiro.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_bill")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "bill_value", nullable = false, precision = 10, scale = 2)
    private BigDecimal billValue;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    private boolean paid;
}
