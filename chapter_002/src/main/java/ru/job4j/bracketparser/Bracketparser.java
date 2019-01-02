package ru.job4j.bracketparser;

import java.util.*;

public class Bracketparser {
    private static Map<Character, Character> brackets = new HashMap<>();

    static {
        brackets.put('(', ')');
        brackets.put('{', '}');
        brackets.put('[', ']');
    }

    private static Stack<Character> stack = new Stack<>();

//    public boolean parseBrackets(String input) {
//        boolean result = true;
//        if (input.length() == 0) {
//            result = false;
//        } else if (input.length() % 2 != 0) {
//            System.out.println("Нечетное количество скобок!");
//            result = false;
//        }
//        for (var i = 0; i < input.length(); i++) {
//            char[] charArray = input.toCharArray();
//            if (brackets.containsKey(charArray[i])) {
//                stack.push(charArray[i]);
//            } else if (stack.empty() || charArray[i] != brackets.get(stack.pop())) {
//                result = false;
//            }
//        }
//        return result;
//    }
//}


    public Character[] findBrackets(String input) {
        List<Character> charArray2 = new ArrayList<>();
        for (int k = 0; k < input.length(); k++) {
            char[] charArray1 = input.toCharArray();
            if (charArray1[k] == '(' || charArray1[k] == ')' || charArray1[k] == '{' || charArray1[k] == '}' || charArray1[k] == '[' || charArray1[k] == ']') {
                charArray2.add(charArray1[k]);
            }
        }
        if (charArray2.toArray(new Character[charArray2.size()]).length % 2 != 0) {
            String warning = "Odd!";
            System.out.println(warning);
            char[] warn = warning.toCharArray();
            Character[] warns = new Character[warn.length];
            return warns;
        } else {
            return charArray2.toArray(new Character[charArray2.size()]);
        }
    }


        public boolean parseBrackets(String input2) {
        boolean result = true;
        if (input2.length() == 0) {
            result = false;
        }
        Character[] charArray = findBrackets(input2);
        for (var i = 0; i < charArray.length; i++) {
  //          char[] charArray = input3.toCharArray();
            if (brackets.containsKey(charArray[i])) {
                stack.push(charArray[i]);
            } else if (stack.empty() || charArray[i] != brackets.get(stack.pop())) {
                result = false;
            }
        }
        return result;
    }
}
