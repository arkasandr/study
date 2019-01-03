package ru.job4j.bracketparser;

import java.util.*;

/**
 * @author Alex Arks (arkaleks@yandex.ru)
 * @version $Id$
 * @since 0.1
 */

public class Bracketparser {

    /**
     * Отображение содержит искомые наборы символов
     */
    private static Map<Character, Character> brackets = new HashMap<>();

    static {
        brackets.put('(', ')');
        brackets.put('{', '}');
        brackets.put('[', ']');
    }

    /**
     * Вспомогательный список
     */
    private static LinkedList<Character> linkedList = new LinkedList<>();

    /**
     * Метод реализует поиск и выборку символов, указанных в brackets
     */
    public Character[] findBrackets(String input) {
        List<Character> onlyBrackets = new ArrayList<>();
        for (int k = 0; k < input.length(); k++) {
            char[] charArray1 = input.toCharArray();
            if (charArray1[k] == '(' || charArray1[k] == ')' || charArray1[k] == '{' || charArray1[k] == '}'
                    || charArray1[k] == '[' || charArray1[k] == ']') {
                onlyBrackets.add(charArray1[k]);
            }
        }
        return onlyBrackets.toArray(new Character[onlyBrackets.size()]);
    }

    /**
     * Метод проверяет строку из выбранных скобок на валидность
     */
        public boolean parseBrackets(String input) {
        boolean result = true;
        if (input.length() == 0) {
            result = false;
        }
        Character[] brackets = findBrackets(input);
            if (brackets.length % 2 != 0) {
                result = false;
            }
        for (var i = 0; i < brackets.length; i++) {
            if (Bracketparser.brackets.containsKey(brackets[i])) {
                linkedList.push(brackets[i]);
            } else if (linkedList.isEmpty() || brackets[i] != Bracketparser.brackets.get(linkedList.pop())) {
                result = false;
            }
        }
        return result;
    }
}
