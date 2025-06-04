package com.bank.bankservice.domain.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor  
@Builder        
public class AccountDto {
    private Long id;
    private Long number;
    private Long userId;
    private AccountType accountType;
    private BigDecimal balance;
}