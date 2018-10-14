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
            if (user.getKey().getPassport().equals(passport)) {
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


//        /**
//         * Метод переводит деньги с одного счета на другой, если счет не найден или не хватает денег , то возвращает false.
//         */
//    public boolean transferMoney (String srcPassport, String srcRequisite, String destPassport, String destRequisite, double amount){
//        boolean result = false;
//            List<Account> srcAccounts = new ArrayList<>();
//            List<Account> destAccounts = new ArrayList<>();
//        for (HashMap.Entry<User, List<Account>> entry1 : bank.entrySet()) {
//            if (entry1 != null && entry1.getKey().getPassport().equals(srcPassport)) {
//                srcAccounts = entry1.getValue();
//                for (HashMap.Entry<User, List<Account>> entry2 : bank.entrySet()) {
//                if (entry2 != null && entry2.getKey().getPassport().equals(destPassport)) {
//                    destAccounts = entry2.getValue();
//                    for (Account a:srcAccounts) {
//                        if(a != null && a.getRequisites().equals(srcRequisite)) {
//
//                        }
//                    }
//                         ) {
//
//                    }
//                }
//
//            }
//
//
//            }
//        }



//        for (Account srcAcc:srcAccounts) {
//            if(srcAcc.getRequisites().equals(srcRequisite)) {
//                //List<Account> destAccounts = new ArrayList<>();
//                for (HashMap.Entry<User, List<Account>> entry : bank.entrySet()) {
//                    if (entry != null && entry.getKey().getPassport().equals(destPassport)) {
//                        //destAccounts = entry.getValue();
//
//                    }
//                }
//
//            }
//        }

//
//    boolean b = true;
//    return b;
//   }


    }


