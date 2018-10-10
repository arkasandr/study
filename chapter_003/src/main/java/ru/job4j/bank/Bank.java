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
                bank.get(user).add(account);
            }
        }
    }

        /**
         * Метод удаляет у пользователя банковский счет.
         //     */
//    public void deleteAccountFromUser(String passport, )

//    /**
//     * Метод получает список банковских счетов пользователя.
//     */

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
//    public User getUser(String passport) {
//        User wanted = new User();
//        for (HashMap.Entry<User, List<Account>> entry : bank.entrySet()) {
//           if(entry.getKey().getPassport().equals(passport)) {
//               wanted = entry.getKey();
//           }
//        }
//        return wanted;
//    }


        /**
         * Метод переводит деньги с одного счета на другой, если счет не найден или не хватает денег , то возвращает false.
         */
//    public boolean transferMoney (String srcPassport, String srcRequisite, String destPassport, String destRequisite, double amount){
//    boolean b = true;
//    return b;
//   }
//        public static void main (String[] args){
//            Bank newSber = new Bank();
//            newSber.addUser(new User("Petrov", "1200 567019"));

//        newSber.addUser(new User("Smirnov", "5504 116280"));
//        newSber.addAccountToUser("1200 567019", new Account(145000, "1001"));
//        newSber.getUserAccounts("1200 567019");
//
//
//        System.out.println(Arrays.asList(newSber));
//       System.out.println(Arrays.asList(newSber.getUserAccounts("1200 567019")));
//       //System.out.println(newSber.getUserAccounts("1200 567019").toArray());
//
//
        //}
    }


