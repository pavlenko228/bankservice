package com.bank.bankservice.mapper;

import com.bank.bankservice.domain.dto.AccountDto;
import com.bank.bankservice.domain.model.Account;

public class AccountMapper {
    public static Account dtoToEntity(AccountDto accountDto) {
        Account account = new Account();
        account.setId(accountDto.getId());
        account.setNumber(accountDto.getNumber());
        account.setUserId(accountDto.getUserId());
        account.setAccountType(accountDto.getAccountType());
        account.setBalance(accountDto.getBalance());
        return account;
    }

    public static AccountDto entityToDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setNumber(account.getNumber());
        accountDto.setUserId(account.getUserId());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setBalance(account.getBalance());
        return accountDto;
    }
}