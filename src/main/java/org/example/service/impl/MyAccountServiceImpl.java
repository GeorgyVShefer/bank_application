package org.example.service.impl;

import org.example.exception.EmptyFieldException;
import org.example.exception.UserIsAlreadyExistsException;
import org.example.model.Account;
import org.example.repository.AccountRepository;
import org.example.repository.impl.DbAccountRepositoryImpl;
import org.example.repository.impl.InMemoryAccountRepositoryImpl;
import org.example.service.AccountService;
import org.example.util.MyPasswordHashing;
import org.example.util.PasswordHashing;

import java.util.Map;

public class MyAccountServiceImpl implements AccountService {

    private AccountRepository repository;
    
    private PasswordHashing passwordHashing;
    
    private System.Logger logger = System.getLogger(MyAccountServiceImpl.class.getName());

    public MyAccountServiceImpl() {
        repository = new DbAccountRepositoryImpl();
        passwordHashing = new MyPasswordHashing();
    }

    @Override
    public Map<String, Account> allAccounts() {
        return repository.allAccounts();
    }



    @Override
    public void saveAccount(String name, String password, Double balance) throws UserIsAlreadyExistsException, EmptyFieldException {
        if(repository.allAccounts().containsKey(name)) {
            logger.log(System.Logger.Level.WARNING, name + "is already exist");
            throw new UserIsAlreadyExistsException();
        }
        if(name.equals("") || password.equals("")){
            logger.log(System.Logger.Level.WARNING, "The filed must be filled");
            throw new EmptyFieldException();
        }
        Account account = new Account(name, password, balance);
        repository.saveAccount(account);
    }

    @Override
    public void saveAccount(String email, String name, String password) throws UserIsAlreadyExistsException, EmptyFieldException {
        if(repository.allAccounts().containsKey(name)) {
            logger.log(System.Logger.Level.WARNING, name + "is already exist");
            throw new UserIsAlreadyExistsException();
        }
        if(email.equals("") || name.equals("") || password.equals("")){
            logger.log(System.Logger.Level.WARNING, "The filed must be filled");
            throw new EmptyFieldException();
        }
        Long id = Long.valueOf(repository.allAccounts().size() + 1);
        String hashedPass = passwordHashing.doHashing(email, password);
        Account account = new Account(id, email, name, hashedPass,0.00);
        repository.saveAccount(account);
    }
}
