package org.example.service.impl;


import org.example.exception.EmptyFieldException;
import org.example.exception.UserIsAlreadyExistsException;
import org.example.model.Account;
import org.example.repository.AccountRepository;
import org.example.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class MyAccountServiceImplTest {
    @Mock
    private AccountRepository repository;
    @InjectMocks
    private MyAccountServiceImpl service;

    @Test
    void shouldReturnAllAccounts() {
        Account account1 = new Account("acc1", "e12e", 15.01);
        Account account2 = new Account("acc2", "e1122e", 115.01);
        Account account3 = new Account("acc3", "e11312e", 132.01);

        Mockito.when(repository.allAccounts()).thenReturn(Map.of(
                account1.getName(), account1,
                account2.getName(), account1,
                account3.getName(), account2
        ));

        Map<String, Account> expected = repository.allAccounts();
        Map<String, Account> accountMap = service.allAccounts();

        assertEquals(expected, accountMap);
    }

    @Test
    void shouldThrowUserIsAlreadyExistsException() {
        Account account1 = new Account("acc1", "e12e", 15.01);

        Mockito.when(repository.allAccounts()).thenReturn(Map.of(account1.getName(), account1));

        assertThrows(UserIsAlreadyExistsException.class,
                () -> service.saveAccount(
                account1.getName(),
                account1.getPassword(),
                account1.getBalance()));
    }

    @Test
    void shouldThrowEmptyFieldException(){
        assertThrows(EmptyFieldException.class,
                () -> service.saveAccount(
                        "account1.getPassword()",
                        "",
                        123.01));
    }
}