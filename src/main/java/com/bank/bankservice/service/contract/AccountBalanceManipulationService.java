package com.bank.bankservice.service.contract;

import java.math.BigDecimal;

import com.bank.bankservice.domain.dto.DepositResponse;
import com.bank.bankservice.domain.dto.TransferResponse;
import com.bank.bankservice.domain.dto.WithdrawResponse;

public interface AccountBalanceManipulationService {
    DepositResponse deposit(Long payeeNumber, BigDecimal amount);

    WithdrawResponse withdraw(Long sourceMoneyNumber, BigDecimal amount);

    TransferResponse transfer(Long sourceMoneyNumber, BigDecimal amount, Long payeeNumber);
}

