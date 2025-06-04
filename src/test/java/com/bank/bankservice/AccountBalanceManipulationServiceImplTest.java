// package com.bank.bankservice;

// import static org.mockito.Mockito.*;

// import java.math.BigDecimal;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import com.bank.bankservice.domain.dto.AccountDto;
// import com.bank.bankservice.domain.model.Account;
// import com.bank.bankservice.mapper.AccountMapper;
// import com.bank.bankservice.repository.AccountRepository;
// import com.bank.bankservice.service.impl.AccountBalanceManipulationServiceImpl;

// import static org.junit.jupiter.api.Assertions.*;

// @ExtendWith(MockitoExtension.class)
// public class AccountBalanceManipulationServiceImplTest {

//     @Mock
//     private AccountRepository accountRepository;

//     @InjectMocks
//     private AccountBalanceManipulationServiceImpl accountDepositService;

//     private AccountDto accountDto;
//     private BigDecimal amount;
//     private AccountDto senderAccountDto;
//     private AccountDto destinitionAccountDto;

//     @BeforeEach
//     void setUp() {
//         accountDto = new AccountDto();
//         accountDto.setBalance(new BigDecimal("100.00"));
//         amount = new BigDecimal("50.00");
//         senderAccountDto = new AccountDto();
//         destinitionAccountDto = new AccountDto();
//         senderAccountDto.setBalance(new BigDecimal("100.00"));
//         destinitionAccountDto.setBalance(new BigDecimal("100.00"));
//     }

//     @Test
//     void testDeposit() {
//         try (var mockStatic = mockStatic(AccountMapper.class)) {
//             Account account = new Account();
//             account.setBalance(accountDto.getBalance().add(amount));

//             mockStatic.when(() -> AccountMapper.dtoToEntity(accountDto)).thenReturn(account);

//             accountDepositService.deposit(accountDto, amount);

//             assertEquals(new BigDecimal("150.00"), accountDto.getBalance());

//             verify(accountRepository, times(1)).save(account);
//         }
//     }

//     @Test
//     void testWithdraw() {
//         try (var mockStatic = mockStatic(AccountMapper.class)) {
//             Account account = new Account();
//             account.setBalance(accountDto.getBalance().subtract(amount));

//             mockStatic.when(() -> AccountMapper.dtoToEntity(accountDto)).thenReturn(account);

//             accountDepositService.withdraw(accountDto, amount);

//             assertEquals(new BigDecimal("50.00"), accountDto.getBalance());

//             verify(accountRepository, times(1)).save(account);
//         }
//     }

//     @Test
//     void testTransfer() {
//         try (var mockStatic = mockStatic(AccountMapper.class)) {
//             Account sender = new Account();
//             Account destination = new Account();
//             sender.setBalance(senderAccountDto.getBalance().subtract(amount));
//             destination.setBalance(destinitionAccountDto.getBalance().add(amount));
    
//             mockStatic.when(() -> AccountMapper.dtoToEntity(senderAccountDto)).thenReturn(sender);
//             mockStatic.when(() -> AccountMapper.dtoToEntity(destinitionAccountDto)).thenReturn(destination);
    
//             accountDepositService.transfer(senderAccountDto, destinitionAccountDto, amount);
//             assertEquals(new BigDecimal("50.00"), senderAccountDto.getBalance());
//             assertEquals(new BigDecimal("150.00"), destinitionAccountDto.getBalance());
//             verify(accountRepository, times(1)).save(sender);  
//             verify(accountRepository, times(1)).save(destination);
//         }
//     }
// }
