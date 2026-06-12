package com.controle.financeiro.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_month_balance")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthBalanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "balance_year")
    private Integer year;

    @Column(name = "balance_month")
    private Integer month;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "money_input")
    private BigDecimal moneyInput;

    @Column(name = "money_output")
    private BigDecimal moneyOutput;
}
