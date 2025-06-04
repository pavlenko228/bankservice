package com.bank.bankservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum AccountType {
    CHECKING,
    SAVING,
    FIXED
}

