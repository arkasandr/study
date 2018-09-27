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
        int temp = tasks.size();
            if (tasks.isEmpty()) {
                tasks.add(count, task);
            } else {
           for (count = 0; count <= tasks.size() - 1; count++) {
            if (task.getPriority() < tasks.get(count).getPriority()) {
                temp = count;
                break;
           }
            }
        tasks.add(temp, task);
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}