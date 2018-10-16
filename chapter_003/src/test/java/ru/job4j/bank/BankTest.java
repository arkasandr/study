package ru.job4j.bank;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BankTest {

    @Test
    public void whenAddUserWithOutAccountThenEmptyValue() {
        Bank vtb = new Bank();
        vtb.addUser(new User("Petrov", "1200 567019"));
        List<Account> client = vtb.getUserAccounts("Petrov");
        assertThat(client.toString(), is("[]"));
    }

    @Test
    public void whenDeleteUserThenUserIsAbsent() {
        Bank vtb = new Bank();
        User user = new User("Smirnov", "5504 116280");
        vtb.addUser(user);
        vtb.deleteUser(user);
        assertThat(vtb.getUser("5504 116280").equals(user), is(false));
    }

    @Test
    public void whenAddAccountToUserThenValueIsNotEmpty() {
        Bank vtb = new Bank();
        User user = new User("Smirnov", "5504 116280");
        vtb.addUser(user);
        Account userAcc1 = new Account(145000D, "1001");
        Account userAcc2 = new Account(20D, "1002");
        List<Account> accounts = new ArrayList<>();
        accounts.add(userAcc1);
        accounts.add(userAcc2);
        vtb.addAccountToUser(user.getPassport(), userAcc1);
        vtb.addAccountToUser(user.getPassport(), userAcc2);
        assertThat(vtb.getUserAccounts(user.getPassport()), is(accounts));
    }

    @Test
    public void whenDeleteAccountFromUserThenValueIsEmpty() {
        Bank vtb = new Bank();
        User user = new User("Smirnov", "5504 116280");
        vtb.addUser(user);
        Account userAcc1 = new Account(145000D, "1001");
        Account userAcc2 = new Account(20D, "1002");
        List<Account> accounts = new ArrayList<>();
        accounts.add(userAcc1);
        accounts.add(userAcc2);
        vtb.addAccountToUser(user.getPassport(), userAcc1);
        vtb.addAccountToUser(user.getPassport(), userAcc2);
        vtb.deleteAccountFromUser(user.getPassport(), userAcc2);
        accounts.remove(userAcc2);
        assertThat(vtb.getUserAccounts(user.getPassport()), is(accounts));
    }

    @Test
    public void whenGetUserAccountsThenGiveAll() {
        Bank vtb = new Bank();
        User user = new User("Smirnov", "5504 116280");
        vtb.addUser(user);
        Account userAcc1 = new Account(145000D, "1001");
        Account userAcc2 = new Account(20D, "1002");
        List<Account> accounts = new ArrayList<>();
        accounts.add(userAcc1);
        accounts.add(userAcc2);
        vtb.addAccountToUser(user.getPassport(), userAcc1);
        vtb.addAccountToUser(user.getPassport(), userAcc2);
        vtb.getUserAccounts(user.getPassport());
        assertThat(vtb.getUserAccounts(user.getPassport()), is(accounts));
    }

    @Test
    public void whenTransferMoneyThenDeltaAccounts() {
        Bank vtb = new Bank();
        vtb.addUser(new User("Smirnov", "5504 116280"));
        vtb.addUser(new User("Petrov", "1200 567019"));
        Account userAcc1 = new Account(145000D, "1001");
        Account userAcc2 = new Account(20D, "1002");
        vtb.addAccountToUser("5504 116280", userAcc1);
        vtb.addAccountToUser("1200 567019", userAcc2);
        //vtb.transferMoney("5504 116280","1001", "1200 567019", "1002", 100D);
        assertThat(vtb.transferMoney("5504 116280", "1001", "1200 567019", "1003", 144000D), is(true));
    }

    @Test
    public void whenGetUserAccountThenGiveOne() {
        Bank vtb = new Bank();
        User user = new User("Smirnov", "5504 116280");
        vtb.addUser(user);
        Account userAcc1 = new Account(145000D, "1001");
        Account userAcc2 = new Account(20D, "1002");
        List<Account> accounts = new ArrayList<>();
        accounts.add(userAcc1);
        accounts.add(userAcc2);
        vtb.addAccountToUser(user.getPassport(), userAcc1);
        vtb.addAccountToUser(user.getPassport(), userAcc2);
        assertThat(vtb.getUserAccount("5504 116280", "1001"), is(userAcc1));
    }

}
