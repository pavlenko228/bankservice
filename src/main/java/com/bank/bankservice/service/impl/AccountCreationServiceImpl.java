package com.bank.bankservice.service.impl;

import org.springframework.stereotype.Service;

import com.bank.bankservice.domain.dto.AccountDto;
import com.bank.bankservice.domain.model.Account;
import com.bank.bankservice.mapper.AccountMapper;
import com.bank.bankservice.repository.AccountRepository;
import com.bank.bankservice.service.contract.AccountCreationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountCreationServiceImpl implements AccountCreationService {

    private final AccountRepository accountRepository;

    @Override 
    public AccountDto create(AccountDto accountDto) {
        Account account = AccountMapper.dtoToEntity(accountDto);
        accountRepository.save(account);
        return accountDto;
    }
}
