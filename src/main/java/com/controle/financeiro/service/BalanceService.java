package com.controle.financeiro.service;

import com.controle.financeiro.mapper.BalanceMapper;
import com.controle.financeiro.model.dto.BalanceDTO;
import com.controle.financeiro.model.entities.MonthBalanceEntity;
import com.controle.financeiro.repository.BalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final BalanceRepository balanceRepository;

    private final BalanceMapper balanceMapper;

    @Cacheable("balance")
    public BalanceDTO getBalance(int year, int month) {
        MonthBalanceEntity balanceEntity = balanceRepository.getByYearAndMonth(year, month);
        return balanceMapper.toDTO(balanceEntity);
    }

    @CacheEvict(value = "balance", allEntries = true)
    public BalanceDTO updateMonthBalance(BigDecimal value, String type) {
        LocalDate today = LocalDate.now();

        MonthBalanceEntity balanceEntity = balanceRepository.findByYearAndMonth(today.getYear(), today.getMonthValue());

        var delta = type.equals("INCOME") ? value : value.negate();

        if(!ObjectUtils.isEmpty(balanceEntity)) {
            balanceEntity.setBalance(balanceEntity.getBalance().add(delta));
            balanceEntity.setMoneyInput((balanceEntity.getMoneyInput() != null ? balanceEntity.getMoneyInput() : BigDecimal.ZERO)
                    .add(type.equals("INCOME")  ? value : BigDecimal.ZERO));
            balanceEntity.setMoneyOutput((balanceEntity.getMoneyOutput() != null ? balanceEntity.getMoneyOutput() : BigDecimal.ZERO)
                    .add(type.equals("EXPENSE")  ? value : BigDecimal.ZERO));
            balanceRepository.save(balanceEntity);
            return balanceMapper.toDTO(balanceEntity);
        }

        MonthBalanceEntity monthBalanceEntity = MonthBalanceEntity.builder()
                .year(today.getYear())
                .month(today.getMonthValue())
                .balance(delta)
                .moneyInput(type.equals("INCOME") ? value : BigDecimal.ZERO)
                .moneyOutput(type.equals("EXPENSE") ? value : BigDecimal.ZERO)
                .build();
        balanceRepository.save(monthBalanceEntity);
        return balanceMapper.toDTO(monthBalanceEntity);
    }

    @CacheEvict(value = "balance", allEntries = true)
    public void updateBalance(BigDecimal value) {
        LocalDate today = LocalDate.now();

        MonthBalanceEntity balanceEntity = balanceRepository.findByYearAndMonth(today.getYear(), today.getMonthValue());

        if(!ObjectUtils.isEmpty(balanceEntity)) {
            balanceEntity.setBalance(balanceEntity.getBalance().add(value));
            balanceEntity.setMoneyOutput(balanceEntity.getMoneyOutput().subtract(value));
        }
        balanceRepository.save(balanceEntity);
    }
}
