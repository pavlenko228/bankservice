// package com.bank.bankservice;

// import java.math.BigDecimal;
// import java.util.List;

// import org.assertj.core.api.Assertions;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// import com.bank.bankservice.domain.dto.AccountType;
// import com.bank.bankservice.domain.model.Account;
// import com.bank.bankservice.repository.AccountRepository;

// import jakarta.transaction.Transactional;


// @Transactional
// @SpringBootTest
// public class AccountRepositoryTest {
    
//     @Autowired
//     private AccountRepository accountRepository;
        
//     private Account account;

//     @BeforeEach
//     public void setUp() {
//         account = Account.builder()
//                 .number("1000")
//                 .clientId(1L)
//                 .accountType(AccountType.SAVING)
//                 .balance(new BigDecimal(1000))
//                 .build();

//         accountRepository.save(account);
//     }

//     @Test
//     public void testFindByClientIdHappy() {
//         List<Account> accounts = accountRepository.findByClientId(1L);
        
//         Assertions.assertThat(accounts).isNotEmpty();
//         Assertions.assertThat(accounts.get(0).getClientId()).isEqualTo(1L);
//     }

//     @Test
//     public void testFindByClientIdSad() {
//         List<Account> accounts = accountRepository.findByClientId(2L);
        
//         Assertions.assertThat(accounts).isEmpty();
//     }

//     @Test
//     public void testFindByClientIdAndAccountTypeHappy() {
//         List<Account> accounts = accountRepository.findByClientIdAndAccountType(1L, AccountType.SAVING);

//         Assertions.assertThat(accounts).isNotEmpty();
//         Assertions.assertThat(accounts.get(0).getAccountType()).isEqualTo(AccountType.SAVING);
//     }

//     @Test
//     public void testFindByClientIdAndAccountTypeSad() {
//         List<Account> accounts = accountRepository.findByClientIdAndAccountType(1L, AccountType.FIXED);

//         Assertions.assertThat(accounts).isEmpty();
//     }

//     @Test
//     public void testFindByClientIdAndNumberHappy() {
//         Account foundAccount = accountRepository.findByClientIdAndNumber(1L, "1000");

//         Assertions.assertThat(foundAccount).isNotNull();
//         Assertions.assertThat(foundAccount.getNumber()).isEqualTo("1000");
//     }

//     @Test
//     public void testFindByClientIdAndNumberSad() {
//         Account foundAccount = accountRepository.findByClientIdAndNumber(1L, "2000");

//         Assertions.assertThat(foundAccount).isNull();
//     }
// }
