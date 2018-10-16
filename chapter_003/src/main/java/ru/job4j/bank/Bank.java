package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Bank {

    private Map<User, List<Account>> bank = new HashMap<>();


    /**
     * Метод добавляет пользователя.
     */
    public void addUser(User user) {
        List<Account> accounts = new ArrayList<>();
        bank.putIfAbsent(user, accounts);
    }

    /**
     * Метод удаляет пользователя.
     */
    public void deleteUser(User user) {
       bank.remove(user);
    }

    /**
     * Метод добавляет пользователю банковский счет.
     */
    public void addAccountToUser(String passport, Account account) {
        for (HashMap.Entry<User, List<Account>> user : bank.entrySet()) {
            if (user.getKey().getPassport().equals(passport) && !user.getValue().contains(account)) {
                user.getValue().add(account);
            }
        }
    }

        /**
         * Метод удаляет у пользователя банковский счет.
         //     */
    public void deleteAccountFromUser(String passport, Account account) {
        for (HashMap.Entry<User, List<Account>> user : bank.entrySet()) {
            if (user.getKey().getPassport().equals(passport)) {
                user.getValue().remove(account);
            }
        }
    }

    /**
     * Метод получает список банковских счетов пользователя.
     */

        public List<Account> getUserAccounts(String passport) {
            List<Account> accounts = new ArrayList<>();
            for (HashMap.Entry<User, List<Account>> entry : bank.entrySet()) {
                if (entry != null && entry.getKey().getPassport().equals(passport)) {
                    accounts = entry.getValue();
                }
            }
            return accounts;
        }


        /**
         * Метод возвращает пользователя.
         */
    public User getUser(String passport) {
        User wanted = new User();
        for (HashMap.Entry<User, List<Account>> entry : bank.entrySet()) {
           if (entry.getKey().getPassport().equals(passport)) {
               wanted = entry.getKey();
           }
        }
        return wanted;
    }

    /**
     * Метод получает счет пользователя по паспорту и реквизитам
     */
    public Account getUserAccount(String passport, String requisite) {
        Account wantedAcc = new Account();
        List<Account> accounts = this.getUserAccounts(passport);
        for (Account acc : accounts
        ) {
            if (acc.getRequisites().equals(requisite)) {
                wantedAcc = acc;
            }
        }
            return wantedAcc;
    }


        /**
         * Метод переводит деньги с одного счета на другой, если счет не найден или не хватает денег , то возвращает false.
         */

    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String destRequisite, double amount) {
        boolean result = true;
        Account srcAccount = this.getUserAccount(srcPassport, srcRequisite);
        Account destAccount = this.getUserAccount(destPassport, destRequisite);
        if (srcAccount.getValue() >= amount) {
            destAccount.setValue(destAccount.getValue() + amount);
                    } else {
            result = false;
        }

        return result;
    }


    }


