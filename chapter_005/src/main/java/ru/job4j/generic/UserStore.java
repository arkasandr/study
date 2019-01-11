package ru.job4j.generic;

public class UserStore<User extends Base> implements Store<User>  {
   private SimpleArray<User> users;

    public UserStore(int size) {
        users = new SimpleArray<>(size);
    }

    @Override
    public void add(User model) {
        users.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        boolean result = false;
        for (int i = 0; i < users.getSize(); i++) {
                if (users.get(i) != null && users.get(i).getId().equals(id)) {
                    users.set(i, model);
                    result = true;
                    break;
                }
            }
        return result;
    }


    @Override
    public boolean delete(String id) {
        boolean result = false;
        for (int i = 0; i < users.getSize(); i++) {
                if (users.get(i) != null && users.get(i).getId().equals(id)) {
                    users.remove(i);
                    result = true;
                    break;
                }
        }
        return result;
    }

    @Override
    public User findById(String id) {
        User result = null;
        for (int i = 0; i < users.getSize(); i++) {
                if (users.get(i) != null && users.get(i).getId().equals(id)) {
                    result = users.get(i);
                    break;
                }
            }
        return result;
    }


    public User get(int position) {
        return users.get(position);
    }
}
