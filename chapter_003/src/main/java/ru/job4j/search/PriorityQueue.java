package ru.job4j.search;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
        int count = 0;
        if (tasks.isEmpty() || tasks.get(count).getPriority() >= task.getPriority()) {
            tasks.add(count, task);
        } else if (tasks.get(count).getPriority() <= task.getPriority()) {
            count++;
            tasks.add(count, task);
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}

