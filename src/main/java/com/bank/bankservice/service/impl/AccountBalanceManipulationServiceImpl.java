package com.bank.bankservice.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.bank.bankservice.domain.dto.DepositResponse;
import com.bank.bankservice.domain.dto.TransferRequest;
import com.bank.bankservice.domain.dto.TransferResponse;
import com.bank.bankservice.domain.dto.WithdrawResponse;
import com.bank.bankservice.domain.model.Account;
import com.bank.bankservice.exception.NoSuchAccount;
import com.bank.bankservice.mapper.AccountMapper;
import com.bank.bankservice.repository.AccountRepository;
import com.bank.bankservice.service.contract.AccountBalanceManipulationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountBalanceManipulationServiceImpl implements AccountBalanceManipulationService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public DepositResponse deposit(Long payeeNumber, BigDecimal amount) {

        Account payeeAccount = accountRepository.findByNumber(payeeNumber);
        if (payeeAccount == null) {
            throw new NoSuchAccount("The account with this number does not exist");
        }
        
        BigDecimal finalBalance = payeeAccount.getBalance().add(amount);
        payeeAccount.setBalance(finalBalance);
        accountRepository.save(payeeAccount);

        return new DepositResponse(true,"The balance has been successfully replenished", amount, AccountMapper.entityToDto(payeeAccount));
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public WithdrawResponse withdraw(Long sourceMoneyNumber, BigDecimal amount) {

        Account sourceMoneyAccount = accountRepository.findByNumber(sourceMoneyNumber);
        if (sourceMoneyAccount == null) {
            throw new NoSuchAccount("The account with this number does not exist");
        }

        if (sourceMoneyAccount.getBalance().compareTo(amount) >= 0) {
            BigDecimal finalBalance = sourceMoneyAccount.getBalance().subtract(amount);
            sourceMoneyAccount.setBalance(finalBalance);
            accountRepository.save(sourceMoneyAccount);

            return new WithdrawResponse(true, "The debit was successful", amount, AccountMapper.entityToDto(sourceMoneyAccount));
        } else {
            return new WithdrawResponse(false, "There is not enough money on the balance sheet", amount, AccountMapper.entityToDto(sourceMoneyAccount));
        }
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public TransferResponse transfer(TransferRequest transferRequest) {

        WithdrawResponse withdrawResponse = withdraw(transferRequest.getSourceMoneyNumber(), transferRequest.getAmount());

        if (withdrawResponse.getSuccess()) {

            DepositResponse depositResponse = deposit(transferRequest.getPayeeNumber(), transferRequest.getAmount());

            return new TransferResponse(true, "The transfer of funds was completed successfully", transferRequest.getAmount(), transferRequest.getSourceMoneyNumber(), transferRequest.getPayeeNumber());
        } else {
            return new TransferResponse(false, withdrawResponse.getMessage(), transferRequest.getAmount(), transferRequest.getSourceMoneyNumber(), transferRequest.getPayeeNumber());
        }
    }

}