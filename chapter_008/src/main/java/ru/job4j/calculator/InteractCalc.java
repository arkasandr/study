package ru.job4j.calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InteractCalc {

    public static final Pattern ICPATTERN = Pattern.compile("\\d*+[+\\/\\*-]+\\d+");
    private final Calculator calculator;
    private final Scanner scanner;
    private Double result;
    private Map<String, BiConsumer<Double, Double>> operations = new HashMap<>();

    public InteractCalc() {
        this.scanner = new Scanner(System.in);
        this.calculator = new Calculator();
        this.loadActs();
    }

    public void loadActs() {
        this.operations.put("+", functionAdd());
        this.operations.put("-", functionSubtrack());
        this.operations.put("/", functionDiv());
        this.operations.put("*", functionMultiple());
    }


    private BiConsumer<Double, Double> functionAdd() {
        return calculator::add;
    }

    private BiConsumer<Double, Double> functionSubtrack() {
        return calculator::subtrack;
    }

    private BiConsumer<Double, Double> functionDiv() {
        return calculator::div;
    }

    private BiConsumer<Double, Double> functionMultiple() {
        return calculator::multiple;
    }

    public boolean execute(String expression) {
        boolean isCorrect = this.checkExpression(expression);
        if (isCorrect) {
            Pattern pattern = Pattern.compile("[\\+\\/\\*\\-]");
            Matcher matcher = pattern.matcher(expression);
            matcher.find();
            int index = matcher.start();
            if (index == 0 && this.result != null) {
                var getValue = expression.substring(1).trim();
                double operandSecond = Double.valueOf(getValue);
                this.operations.get(expression.substring(index, index + 1)).accept(
                        result,
                        operandSecond);
                this.result = calculator.getResult();
            } else {
                var getFirstValue = expression.substring(0, index).trim();
                double operandFirst = Double.valueOf(getFirstValue);
                var getSecondValue = expression.substring(index + 1).trim();
                double operandSecond = Double.valueOf(getSecondValue);
                this.operations.get(expression.substring(index, index + 1)).accept(
                        operandFirst,
                        operandSecond
                );
                this.result = calculator.getResult();
            }
        }
        return isCorrect;
    }

    public void printResult(boolean valid) {
        if (valid) {
            System.out.println(this.result);
        } else {
            System.out.println("Выражение недопустимо");
        }
    }

    public boolean checkExpression(String expression) {
        Matcher m = ICPATTERN.matcher(expression);
        return m.find();
    }

    public void run() {
        System.out.println("Введите без пробелов в формате А+В");
        String expression = scanner.nextLine();
        while (!"выход".equals(expression)) {
            boolean valid = this.execute(expression);
            this.printResult(valid);
            expression = scanner.nextLine();
        }
        System.out.println("Завершено!");
    }

    public static void main(String[] args) {
        InteractCalc iCalc = new InteractCalc();
        iCalc.run();
    }

    public Double getResult() {
        return this.result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public Map<String, BiConsumer<Double, Double>> getOperations() {
        return this.operations;
    }

    public Calculator getCalculator() {
        return calculator;
    }
}