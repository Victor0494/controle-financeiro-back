package com.controle.financeiro.service;

import com.controle.financeiro.mapper.BillMapper;
import com.controle.financeiro.model.dto.BillDTO;
import com.controle.financeiro.model.entities.BillEntity;
import com.controle.financeiro.repository.BillRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    private final Counter paymentCounter;

    private final BillRepository billRepository;

    private final BillMapper billMapper;

    public BillService(BillRepository billRepository, BillMapper billMapper, MeterRegistry registry) {
        this.paymentCounter =  Counter.builder("get_bill_completed").description("Total completed get bills").register(registry);
        this.billRepository = billRepository;
        this.billMapper = billMapper;
    }

    public BillDTO createBill(BillDTO billDTO) {
        BillEntity entity = billMapper.toEntity(billDTO);
        return billMapper.toDTO(billRepository.save(entity));
    }

    @Cacheable("bills")
    public List<BillDTO> getBillByDueDate(LocalDate dueDate) {
        LocalDate startDate = dueDate.withDayOfMonth(1);
        LocalDate endDate = dueDate.withDayOfMonth(dueDate.lengthOfMonth());

        List<BillEntity> billEntityList = billRepository.findByDueDateBetween(startDate, endDate);
        this.paymentCounter.increment();

        return billEntityList.stream().map(billMapper::toDTO).toList();
    }

    public void updateBillInfo(Long id, boolean payed) {
        Optional<BillEntity> billEntity = billRepository.findById(id);

        if(billEntity.isEmpty()) {
            throw new EntityNotFoundException();
        }

        billEntity.get().setPaid(payed);

        billRepository.save(billEntity.get());
    }
}
