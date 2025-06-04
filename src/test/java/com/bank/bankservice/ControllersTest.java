// package com.bank.bankservice;

// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.Test;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.context.annotation.Import;
// import org.springframework.http.MediaType;
// import org.springframework.test.context.bean.override.mockito.MockitoBean;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.MvcResult;

// import com.bank.bankservice.controller.AccountController;
// import com.bank.bankservice.mapper.AccountMapper;
// import com.bank.bankservice.model.AccountType;
// import com.bank.bankservice.model.dto.AccountDto;
// import com.bank.bankservice.model.entities.Account;
// import com.bank.bankservice.repository.AccountRepository;
// import com.bank.bankservice.service.AccountCreationServiceImpl;
// import com.fasterxml.jackson.databind.ObjectMapper;

// import jakarta.annotation.Resource;

// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.when;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

// import java.math.BigDecimal;

// @WebMvcTest(controllers = AccountController.class)
// @AutoConfigureMockMvc
// @Import({
//     AccountCreationServiceImpl.class,
//     AccountMapper.class
// })
// public class ControllersTest {
//     @Resource
//     private MockMvc mockMvc;

//     @Resource
//     private ObjectMapper objectMapper;
    
//     @MockitoBean
//     private AccountRepository accountRepository;
    
//     @Test 
//     void createTest() throws Exception {
//         AccountDto accountDto = new AccountDto(1L, "1000", 1L, AccountType.SAVING, new BigDecimal(1000));

//         String url = "/api/accounts/create";

//         when(accountRepository.save(any(Account.class))).thenReturn(new Account());

//         MvcResult mvcResult = mockMvc.perform(post(url)
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(objectMapper.writeValueAsString(accountDto))
//                         .characterEncoding("UTF-8"))
//                .andReturn();

//         Assertions.assertEquals(mvcResult.getResponse().getStatus(), 200);
//     }
// }
