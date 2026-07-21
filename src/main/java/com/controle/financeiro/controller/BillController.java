package com.controle.financeiro.controller;

import com.controle.financeiro.model.dto.BillDTO;
import com.controle.financeiro.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/bill")
@CrossOrigin("*")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @PostMapping
    public ResponseEntity<BillDTO> createBill(@RequestBody BillDTO billDTO) {
        BillDTO bill = billService.createBill(billDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(bill);
    }

    @GetMapping
    public ResponseEntity<List<BillDTO>> getBillByMonth(@RequestParam(name = "dueDate") LocalDate dueDate) {
        return ResponseEntity.ok(billService.getBillByDueDate(dueDate));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateBillInfo(@PathVariable Long id, @RequestParam(name = "payed") boolean payed) {
        billService.updateBillInfo(id, payed);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBill(@PathVariable Long id) {
        billService.deleteBillById(id);
        return ResponseEntity.noContent().build();
    }
}
