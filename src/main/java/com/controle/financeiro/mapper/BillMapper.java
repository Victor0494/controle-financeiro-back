package com.controle.financeiro.mapper;

import com.controle.financeiro.model.dto.BillDTO;
import com.controle.financeiro.model.entities.BillEntity;
import org.springframework.stereotype.Component;

@Component
public class BillMapper {

    public BillEntity toEntity(BillDTO billDTO) {
        return BillEntity.builder()
                .description(billDTO.description())
                .billValue(billDTO.billValue())
                .dueDate(billDTO.dueDate())
                .paid(billDTO.payed())
                .build();
    }

    public BillDTO toDTO(BillEntity billEntity) {
        return new BillDTO(billEntity.getId(), billEntity.getDescription(), billEntity.getBillValue(), billEntity.getDueDate(), billEntity.isPaid());
    }
}
