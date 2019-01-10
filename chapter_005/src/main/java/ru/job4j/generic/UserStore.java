package ru.job4j.generic;

import java.util.Iterator;

public class UserStore<User extends Base> implements Store<User>  {
   private SimpleArray<User> users;
   private int size;

    public UserStore(int size) {
        users = new SimpleArray<>(size);
    }

    @Override
    public void add(User model) {
        users.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        for (int i = 0; i < users.getSize(); i++) {
            if (users.get(i) != null) {
                if (users.get(i).getId().equals(id)) {
                    users.set(i, model);
                    return true;
                }
            }
        }
        return false;
    }



//    public void delete(String id) {
//        for (int i = 0; i < users.; i++) {
//            if (items.get(i) != null) {
//                if (items.get(i).getId().equals(id)) {
//                    items.remove(i);
//                    break;
//                }
//            }
//        }

//    @Override
//    public boolean replace(String id, User model) {
//        boolean rst = false;
//        Iterator<User> it = users.iterator();
//        for (int i = 0; it.hasNext(); i++) {
//            if (id.equals(it.next().getId())) {
//                users.set(i, model);
//                rst = true;
//                break;
//            }
//        }
//        return rst;
//    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public User findById(String id) {
        return null;
    }


    public User get(int position) {
        return users.get(position);
    }
}
