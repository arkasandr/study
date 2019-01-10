package ru.job4j.generic;

import java.util.Iterator;

public class UserStore<User extends Base> implements Store<User>  {
   private SimpleArray<User> users;

    public UserStore(int size) {
        users = new SimpleArray<>(size);
    }

    @Override
    public void add(User model) {
        users.add(model);
    }

//    @Override
//    public boolean replace(int id, User model) {
//        //boolean result = false;
//        for (User user:users) {
//            if (user != null) {
//                if (users.get(id).equals(id)) {
//                    users.set(id, model);
//                    return true;
//                }
//
//            }
//        }
//        return false;
//    }

    @Override
    public boolean replace(int id, User model) {
        boolean rst = false;
        Iterator<User> it = users.iterator();
        for (int i = 0; it.hasNext(); i++) {
            if (id.equals(it.next().getId())) {
                users.set(i, model);
                rst = true;
                break;
            }
        }
        return rst;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User findById(int id) {
        return null;
    }


    public User get(int position) {
        return users.get(position);
    }
}
