package com.bank.bankservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bank.bankservice.domain.dto.AccountDto;
import com.bank.bankservice.domain.dto.Role;
import com.bank.bankservice.repository.AccountRepository;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Aspect
@Hidden
@RequiredArgsConstructor
public class AccessControlAspect {

    private final AccountRepository accountRepository;

    @Before("@annotation(VerifyListningAccess) && args(userId,..)")
    public void verifyCardOwnership(JoinPoint joinPoint, Long userId) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        Role role = (Role) request.getAttribute("role");

        if (role.equals(Role.ROLE_USER)) {
            Long userIdFromAttribute = (Long) request.getAttribute("userId");

            if (!userId.equals(userIdFromAttribute)) {
                throw new AccessDeniedException("User does not own this card.");
            }
        }
    }

    @Before("@annotation(VerifyBalanceManipulationAccess) && args(sourceMoneyNumber)")
    public void verifyWithdrawalAccess(JoinPoint joinPoint, Long sourceMoneyNumber) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        Role role = (Role) request.getAttribute("role");

        if (role.equals(Role.ROLE_USER)) {
            Long userIdFromAttribute = (Long) request.getAttribute("userId");

            Long userId = accountRepository.findByNumber(sourceMoneyNumber)
                    .getId();

            if (!userId.equals(userIdFromAttribute)) {
                throw new AccessDeniedException("User does not own this account.");
            }
        }
    }

    @Before("@annotation(VerifyCreatingAccess) && args(accountDto)")
    public void verifyCreatingAccess(JoinPoint joinPoint, AccountDto accountDto) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        Role role = (Role) request.getAttribute("role");

        if (role.equals(Role.ROLE_USER)) {

            Long userIdFromAttribute = (Long) request.getAttribute("userId");

            if (!accountDto.getUserId().equals(userIdFromAttribute)) {
                throw new AccessDeniedException("User does not own this account.");
            }
        }
    }
}