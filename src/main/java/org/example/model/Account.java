package org.example.model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String password;
    private Double balance;

    @Deprecated(forRemoval = true, since = "Use the constructor with email ")
    public Account(String name, String password, Double balance) {
        this.name = name;
        this.password = password;
        this.balance = balance;
    }

    public Account(String email, String name, String password){
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
