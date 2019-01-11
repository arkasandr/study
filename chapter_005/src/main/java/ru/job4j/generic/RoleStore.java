package ru.job4j.generic;

public class RoleStore<Role extends Base> implements Store<Role>  {
        private SimpleArray<Role> roles;

        public RoleStore(int size) {
            roles = new SimpleArray<>(size);
        }

        @Override
        public void add(Role model) {
            roles.add(model);
        }

        @Override
        public boolean replace(String id, Role model) {
            boolean result = false;
            for (int i = 0; i < roles.getSize(); i++) {
                if (roles.get(i) != null && roles.get(i).getId().equals(id)) {
                    roles.set(i, model);
                    result = true;
                    break;
                }
            }
            return result;
        }


        @Override
        public boolean delete(String id) {
            boolean result = false;
            for (int i = 0; i < roles.getSize(); i++) {
                if (roles.get(i) != null && roles.get(i).getId().equals(id)) {
                    roles.remove(i);
                    result = true;
                    break;
                }
            }
            return result;
        }

        @Override
        public Role findById(String id) {
            Role result = null;
            for (int i = 0; i < roles.getSize(); i++) {
                if (roles.get(i) != null && roles.get(i).getId().equals(id)) {
                    result = roles.get(i);
                    break;
                }
            }
            return result;
        }


        public Role get(int position) {
            return roles.get(position);
        }

}
