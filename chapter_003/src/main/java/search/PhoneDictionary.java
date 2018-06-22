package search;

import java.util.ArrayList;
import java.util.List;

public class PhoneDictionary {
    private List<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public List<Person> find(String key) {
        List<Person> result = new ArrayList<>();
        for (Person p:persons) {
            String[] per = {p.getAdress(), p.getName(), p.getPhone(), p.getSurname()};
            for (int i = 0; i < per.length; i++) {
                if (per[i].contains(key)) {
                    result.add(p);
                }
            }
        }
        return result;
    }
}
