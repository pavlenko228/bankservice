package com.bank.bankservice.service.contract;

import com.bank.bankservice.domain.dto.AccountDto;

public interface AccountCreationService {
    AccountDto create(AccountDto accountDto);
}
