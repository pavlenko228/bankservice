package com.bank.bankservice.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bankservice.aspect.VerifyBalanceManipulationAccess;
import com.bank.bankservice.aspect.VerifyCreatingAccess;
import com.bank.bankservice.aspect.VerifyListningAccess;
import com.bank.bankservice.domain.dto.AccountDto;
import com.bank.bankservice.domain.dto.AccountType;
import com.bank.bankservice.domain.dto.DepositResponse;
import com.bank.bankservice.domain.dto.RequestCreateAccount;
import com.bank.bankservice.domain.dto.TransferResponse;
import com.bank.bankservice.domain.dto.WithdrawResponse;
import com.bank.bankservice.service.contract.AccountBalanceManipulationService;
import com.bank.bankservice.service.contract.AccountCreationService;
import com.bank.bankservice.service.contract.AccountListingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor
@Tag(name = "Bank logic")
public class AccountController {
    private final AccountBalanceManipulationService accountBalanceManipulationService;
    private final AccountCreationService accountCreationService;
    private final AccountListingService accountListingService;

    @Operation(summary = "Creating a bank account")
    @VerifyCreatingAccess
    @PostMapping("/api/accounts/create")
    public ResponseEntity<AccountDto> create(@RequestBody @Valid RequestCreateAccount requestCreateAccount) {

        AccountDto accountDto = accountCreationService.create(AccountDto.builder()
                                                                        .id(null)
                                                                        .number(requestCreateAccount.getNumber())
                                                                        .userId(requestCreateAccount.getUserId())
                                                                        .accountType(requestCreateAccount.getAccountType())
                                                                        .balance(new BigDecimal(0))
                                                                        .build());
        return ResponseEntity.ok(accountDto);
    } 

    @Operation(summary = "Getting a specific user's bank account")
    @VerifyListningAccess
    @GetMapping("/api/account/get")
    public ResponseEntity<AccountDto> getAccount(@RequestParam Long userId, @RequestParam Long number) {

        AccountDto accountDto = accountListingService.getUserAccount(userId, number);
        return ResponseEntity.ok(accountDto);
    }

    @Operation(summary = "Getting all bank accounts of a specific user")
    @VerifyListningAccess
    @GetMapping("/api/accounts/get")
    public ResponseEntity<List<AccountDto>> getAccounts(@RequestParam Long userId) {

        List<AccountDto> accountsDto = accountListingService.getUserAccounts(userId);
        return ResponseEntity.ok(accountsDto);
    }

    @Operation(summary = "Getting accounts of a certain type of a specific user")
    @VerifyListningAccess
    @GetMapping("/api/accounts/getByType")
    public ResponseEntity<List<AccountDto>> getByType(@RequestParam Long userId, @RequestParam AccountType accountType) {

        List<AccountDto> accountsDto = accountListingService.getUserAccountsByType(userId, accountType);
        return ResponseEntity.ok(accountsDto);
    }

    @Operation(summary = "Adding money to a specific bank account")
    @PostMapping("/api/account/deposit")
    public ResponseEntity<DepositResponse> deposit(@RequestParam Long payeeNumber, @RequestParam BigDecimal amount) {

        DepositResponse depositResponse = accountBalanceManipulationService.deposit(payeeNumber, amount);
        return ResponseEntity.ok(depositResponse);
    }

    @Operation(summary = "Debiting money from specific bank account")
    @VerifyBalanceManipulationAccess
    @PostMapping("/api/account/withdraw")
    public ResponseEntity<WithdrawResponse> withdraw(@RequestParam Long sourceMoneyNumber, @RequestParam BigDecimal amount) {

        WithdrawResponse withdrawResponse = accountBalanceManipulationService.withdraw(sourceMoneyNumber, amount);
        return ResponseEntity.ok(withdrawResponse);
    }

    @Operation(summary = "Trasfering money from one bank account to another")
    @VerifyBalanceManipulationAccess
    @PostMapping("/api/accounts/transfer")
    public ResponseEntity<TransferResponse> withdraw(@RequestParam Long sourceMoneyNumber, @RequestParam BigDecimal amount, @RequestParam Long payeeNumber) {
        
        TransferResponse transferResponse = accountBalanceManipulationService.transfer(sourceMoneyNumber, amount, payeeNumber);
            return ResponseEntity.ok(transferResponse);
    }
}
