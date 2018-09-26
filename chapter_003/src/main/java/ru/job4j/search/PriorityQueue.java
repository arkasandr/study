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
        } else {
            for (count = 0; count <= tasks.size() - 1; count++) {
            if (task.getPriority() < tasks.get(count).getPriority()) {
                tasks.add(count, task);
                return;
             }
            }
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}