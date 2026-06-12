package com.controle.financeiro.model.dto;

import java.math.BigDecimal;

public record BalanceDTO(Integer year, Integer month, BigDecimal balance, BigDecimal moneyInput, BigDecimal moneyOutPut) {
}
