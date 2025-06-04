// package com.bank.bankservice;

// import java.math.BigDecimal;

// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.Test;
// import org.springframework.boot.test.context.SpringBootTest;

// import com.bank.bankservice.domain.dto.AccountDto;
// import com.bank.bankservice.domain.dto.AccountType;
// import com.bank.bankservice.domain.model.Account;
// import com.bank.bankservice.mapper.AccountMapper;

// @SpringBootTest
// class BankserviceApplicationTests {

// 	@Test
// 	void contextLoads() {
// 	}

// 	@Test
// 	void testMapping() {
// 		Account account = new Account(1L, "1000", 1L, AccountType.SAVING, new BigDecimal(1000));
// 		AccountDto accountDto = new AccountDto(1L, "1000", 1L, AccountType.SAVING, new BigDecimal(1000));

// 		AccountDto accountDtoTest = AccountMapper.entityToDto(account);
// 		Account accountTest = AccountMapper.dtoToEntity(accountDto);

// 		Assertions.assertEquals(accountDtoTest, accountDto);
// 		Assertions.assertEquals(accountTest, account);
// 	}

	

// }
