package ru.job4j.bank;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BankTest {

    @Test
    public void whenAddUserWithOutAccount() {
        Bank vtb = new Bank();
        vtb.addUser(new User("Petrov", "1200 567019"));
        List<Account> client = vtb.getUserAccounts("Petrov");
        assertThat(client.toString(), is("[]"));
    }

    @Test
    public void whenDeleteUser() {
        Bank vtb = new Bank();
        //vtb.addUser(new User("Petrov", "1200 567019"));
        vtb.addUser(new User("Smirnov", "5504 116280"));
        //vtb.deleteUser(new User("Smirnov", "5504 116280"));
        vtb.deleteUser(new User("Smirnov", "5504 116280"));
        //List<Account> client = vtb.getUserAccounts("Petrov");
        vtb.getUserAccounts("5504 116280").toString();

       // assertThat(client.toString(), is("[]"));
        //assertThat(vtb.getUser("5504 116280").toString(), is("null"));
    }

}
