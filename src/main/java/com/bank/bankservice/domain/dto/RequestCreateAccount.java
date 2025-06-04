package com.bank.bankservice.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor  
@Builder  
public class RequestCreateAccount {
    
    @NotNull(message = "User ID is required")
    @Positive(message = "User ID must be a positive number")
    private Long userId;
    
    @NotBlank(message = "Account number cannot be blank")
    @Size(min = 20, max = 20, message = "Account number must be exactly 20 characters long")
    @Pattern(regexp = "^[0-9]+$", message = "Account number must contain only digits")
    private Long number;
    
    @NotNull(message = "Account type is required")
    private AccountType accountType;
}
