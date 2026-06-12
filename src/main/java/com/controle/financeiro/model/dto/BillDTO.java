package com.controle.financeiro.model.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record BillDTO(Long id, String description, BigDecimal billValue, LocalDate dueDate, boolean payed) {
}
