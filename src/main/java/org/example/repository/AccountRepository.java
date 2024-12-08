package org.example.repository;

import org.example.model.Account;

import java.util.Map;

public interface AccountRepository {
    Map<String, Account> allAccounts();
    void saveAccount(Account account);
    Account findAccountByName(String name);
    void deleteAccount(Account account);
}
