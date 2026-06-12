package com.controle.financeiro.repository;

import com.controle.financeiro.model.entities.MonthBalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<MonthBalanceEntity, Long> {


    MonthBalanceEntity findByYearAndMonth(int year, int month);

    MonthBalanceEntity getByYearAndMonth(int year, int month);
}
