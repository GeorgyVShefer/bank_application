package org.example.repository.impl;

import org.example.model.Account;
import org.example.repository.AccountRepository;
import org.example.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DbAccountRepositoryImpl implements AccountRepository {
    private final String SELECT_ALL = "SELECT * FROM test_table";
    private final String INSERT_ACC = "INSERT INTO test_table (email,name,password) VALUES(?,?,?)";
    @Override
    public Map<String, Account> allAccounts() {
        Map<String,Account> accounts = new HashMap<>();
        try(Connection connection = ConnectionManager.open();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                double balance = resultSet.getDouble("balance");

                Account account = new Account(id, email, name, password, balance);
                accounts.put(account.getEmail(),account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }

    @Override
    public void saveAccount(Account account) {
        try(Connection connection = ConnectionManager.open();
            PreparedStatement statement = connection.prepareStatement(INSERT_ACC)){
            statement.setString(1,account.getEmail());
            statement.setString(2,account.getName());
            statement.setString(3,account.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findAccountByName(String name) {
        return null;
    }

    @Override
    public void deleteAccount(Account account) {

    }
}
