package org.example.service;

import org.example.exception.EmptyFieldException;
import org.example.exception.UserIsAlreadyExistsException;
import org.example.model.Account;

import java.util.Map;

public interface AccountService {
    Map<String, Account> allAccounts();
    @Deprecated(forRemoval = true)
    void saveAccount(String name, String password, Double balance) throws UserIsAlreadyExistsException, EmptyFieldException;
    void saveAccount(String email, String name, String password) throws UserIsAlreadyExistsException, EmptyFieldException;;
}
