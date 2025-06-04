package com.bank.bankservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.bankservice.domain.dto.AccountDto;
import com.bank.bankservice.domain.dto.AccountType;
import com.bank.bankservice.domain.model.Account;
import com.bank.bankservice.exception.NoSuchAccount;
import com.bank.bankservice.mapper.AccountMapper;
import com.bank.bankservice.repository.AccountRepository;
import com.bank.bankservice.service.contract.AccountListingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountListingServiceImpl implements AccountListingService {
    private final AccountRepository accountRepository;

    @Override
    public AccountDto getUserAccount(Long userId, Long number) {

        List<Account> accounts = accountRepository.findByUserId(userId);

        if (accounts.isEmpty()) {
            throw new NoSuchAccount("The user does not have any accounts");
        }

        Account account = accounts.stream()
                            .filter(acc -> acc.getNumber().equals(number))
                            .findFirst()
                            .orElse(null);

        if (account == null) {
            throw new NoSuchAccount("There is no account with this number");
        }

        return AccountMapper.entityToDto(account);
    }

    @Override
    public List<AccountDto> getUserAccounts(Long userId) {

        List<Account> accounts = accountRepository.findByUserId(userId);

        if (accounts.isEmpty()) {
            throw new NoSuchAccount("The user does not have any accounts");
        }

        List<AccountDto> accountsDto = accounts.stream()
                                               .map(AccountMapper::entityToDto)
                                               .toList();

        return accountsDto;
    }

    @Override
    public List<AccountDto> getUserAccountsByType(Long userId, AccountType accountType) {

        List<Account> accounts = accountRepository.findByUserId(userId);

        if (accounts.isEmpty()) {
            throw new NoSuchAccount("The user does not have any accounts");
        }

        List<AccountDto> accountsWithType = accounts.stream()
                            .filter(acc -> acc.getAccountType().equals(accountType))
                            .map(AccountMapper::entityToDto)
                            .toList();

        if (accountsWithType.isEmpty()) {
            throw new NoSuchAccount("The user does not have an account with this type");
        }

        return accountsWithType;
    }
}