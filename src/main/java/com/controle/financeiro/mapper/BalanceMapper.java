package com.controle.financeiro.mapper;

import com.controle.financeiro.model.dto.BalanceDTO;
import com.controle.financeiro.model.entities.MonthBalanceEntity;
import org.springframework.stereotype.Component;

@Component
public class BalanceMapper {

    public MonthBalanceEntity toEntity(BalanceDTO balanceDTO) {
        return MonthBalanceEntity.builder()
                .year(balanceDTO.year())
                .month(balanceDTO.month())
                .balance(balanceDTO.balance())
                .moneyInput(balanceDTO.moneyInput())
                .moneyOutput(balanceDTO.moneyOutPut())
                .build();
    }

    public BalanceDTO toDTO(MonthBalanceEntity monthBalanceEntity) {
        return new BalanceDTO(monthBalanceEntity.getYear(),
                monthBalanceEntity.getMonth(),
                monthBalanceEntity.getBalance(),
                monthBalanceEntity.getMoneyInput(),
                monthBalanceEntity.getMoneyOutput());
    }
}
