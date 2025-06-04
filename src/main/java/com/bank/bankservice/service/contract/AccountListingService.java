package com.bank.bankservice.service.contract;

import java.util.List;

import com.bank.bankservice.domain.dto.AccountDto;
import com.bank.bankservice.domain.dto.AccountType;

public interface AccountListingService {
    AccountDto getUserAccount(Long userId, Long number);

    List<AccountDto> getUserAccounts(Long userId);

    List<AccountDto> getUserAccountsByType(Long userId, AccountType accountType);
}
    