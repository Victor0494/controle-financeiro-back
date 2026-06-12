package com.controle.financeiro.repository;

import com.controle.financeiro.model.entities.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<BillEntity, Long> {

   List<BillEntity> findByDueDate(LocalDate dueDate);

   List<BillEntity> findByDueDateBetween(LocalDate startDate, LocalDate endDate);
}
