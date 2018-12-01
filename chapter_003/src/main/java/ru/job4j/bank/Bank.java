package ru.job4j.bank;

import java.util.*;
import java.util.stream.Collectors;


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
//    public void addAccountToUser(String passport, Account account) {
//        for (HashMap.Entry<User, List<Account>> user : bank.entrySet()) {
//            if (user.getKey().getPassport().equals(passport) && !user.getValue().contains(account)) {
//                user.getValue().add(account);
//            }
//        }
//    }

    public void addAccountToUser(String passport, Account account) {
        Optional<User> us = getUser(passport);
        us.ifPresent(user -> bank.get(user).add(account));
    }
        /**
         * Метод удаляет у пользователя банковский счет.
         //     */

//        public void deleteAccountFromUser(String passport, Account account) {
//            for (HashMap.Entry<User, List<Account>> user : bank.entrySet()) {
//                if (user.getKey().getPassport().equals(passport)) {
//                    user.getValue().remove(account);
//                }
//            }
//        }

    public void deleteAccountFromUser(String passport, Account account) {
        Optional<User> us = getUser(passport);
        us.ifPresent(user -> bank.get(user).remove(account));
    }

    /**
     * Метод получает список банковских счетов пользователя.
     */

//        public List<Account> getUserAccounts(String passport) {
//            List<Account> accounts = new ArrayList<>();
//            for (HashMap.Entry<User, List<Account>> entry : bank.entrySet()) {
//                if (entry != null && entry.getKey().getPassport().equals(passport)) {
//                    accounts = entry.getValue();
//                }
//            }
//            return accounts;
//        }

    public List<Account> getUserAccounts(String passport) {
          List<Account> accounts = new ArrayList<>();
          Optional<User> us = getUser(passport);
          if (us.isPresent()) {
              accounts = bank.get(us.get());
          }
          return accounts;
    }


        /**
         * Метод возвращает пользователя.
         */
//    public User getUser(String passport) {
//        User wanted = new User();
//        for (HashMap.Entry<User, List<Account>> entry : bank.entrySet()) {
//           if (entry.getKey().getPassport().equals(passport)) {
//               wanted = entry.getKey();
//           }
//        }
//        return wanted;
//    }

    public Optional<User> getUser(String passport) {
        return bank.keySet().stream().
                filter(user -> passport.equals(user.getPassport()))
                .findFirst();
    }

    /**
     * Метод получает счет пользователя по паспорту и реквизитам
     */
//    public Account getUserAccount(String passport, String requisite) {
//        List<Account> accounts = this.getUserAccounts(passport);
//        for (Account acc : accounts
//        ) {
//            if (acc.getRequisites().equals(requisite)) {
//                return acc;
//            }
//        }
//            return null;
//    }

    public Optional<Account> getUserAccount(String passport, String requisite) {
        return getUserAccounts(passport).stream()
                .filter(account -> requisite.equals(account.getRequisites()))
                .findFirst();
    }



//    public Optional<Account> getUserAccount(User user, String requisite) {
//        return bank.get(user).stream()
//                .filter(account -> requisite.equals(account.getRequisites()))
//                .findFirst();
//    }

        /**
         * Метод переводит деньги с одного счета на другой, если счет не найден или не хватает денег , то возвращает false.
         */

    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String destRequisite, double amount) {
        boolean result = true;
        Optional<User> srcUser = getUser(srcPassport);
        Optional<User> destUser = getUser(destPassport);
        if (srcUser.isPresent() && destUser.isPresent()) {
            Optional<Account> srcAccount = getUserAccount(srcPassport, srcRequisite);
            Optional<Account> destAccount = getUserAccount(destPassport, destRequisite);
            if (srcAccount.isPresent() && destAccount.isPresent() && (srcAccount.get().getValue() >= amount)) {
                srcAccount.get().setValue(srcAccount.get().getValue() - amount);
                destAccount.get().setValue(destAccount.get().getValue() + amount);
            } else {
                result = false;
            }
        }

            return result;

    }

    }


