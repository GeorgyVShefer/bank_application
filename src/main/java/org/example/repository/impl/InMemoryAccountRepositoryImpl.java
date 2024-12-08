package org.example.repository.impl;

import org.example.model.Account;
import org.example.repository.AccountRepository;
import org.example.repository.FakeStorage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryAccountRepositoryImpl implements AccountRepository {

    private Map<String, Account> accountRep;

    private final System.Logger logger = System.getLogger(InMemoryAccountRepositoryImpl.class.getName());




    @Override
    public Map<String, Account> allAccounts() {
        return FakeStorage.storage().allAccounts();
    }

    @Override
    public void saveAccount(Account account) {
        FakeStorage.storage().allAccounts().put(account.getEmail(), account);
        logger.log(System.Logger.Level.INFO, "Save account " + account.toString());
    }

    @Override
    public Account findAccountByName(String name) {
        return FakeStorage.storage().allAccounts().get(name);
    }

    @Override
    public void deleteAccount(Account account) {
        FakeStorage.storage().allAccounts().remove(account.getName(), account);
        logger.log(System.Logger.Level.INFO, "Delete account");
    }
}
