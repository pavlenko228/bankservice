package com.bank.bankservice.domain.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequest {
    private Long sourceMoneyNumber;
    private BigDecimal amount;
    private Long payeeNumber;
}
