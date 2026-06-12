package com.controle.financeiro.controller;

import com.controle.financeiro.model.dto.BalanceDTO;
import com.controle.financeiro.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1/balance")
@CrossOrigin("*")
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceService balanceService;

    @PostMapping
    public ResponseEntity<BalanceDTO> updateBalance(@RequestParam(name = "value")BigDecimal value, @RequestParam(name = "type") String type) {
        BalanceDTO balanceDTO = balanceService.updateMonthBalance(value, type);
        return ResponseEntity.ok(balanceDTO);
    }

    @GetMapping
    public ResponseEntity<BalanceDTO> getBalance(@RequestParam(name = "year") int year, @RequestParam(name = "month") int month) {
        return ResponseEntity.ok(balanceService.getBalance(year, month));
    }
}
