package com.bank.bankservice.domain.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class WithdrawResponse {
    private Boolean success;
    private String message;
    private BigDecimal amount;
    private AccountDto sourceMoney;
}
