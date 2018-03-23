package ru.job4j.calculator;

/**
 * Программа расчета идеального веса.
 */
public class Fit {

    double weight;
    /**
     * Идеальный вес для мужщины.
     * @param height Рост.
     * @return идеальный вес.
     */
    public double manWeight(double height) {
        this.weight = (height - 100.0) * 1.15;
        return this.weight;
    }

    /**
     * Идеальный вес для женщины.
     * @param height Рост.
     * @return идеальный вес.
     */
    public double womanWeight(double height) {
        this.weight = (height - 110.0) * 1.15;
        return this.weight;
    }
}