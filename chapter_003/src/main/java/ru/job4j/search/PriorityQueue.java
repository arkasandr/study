package ru.job4j.search;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * @param task задача
     */

//    public void put(Task task) {
//      int count;
//        int temp = tasks.size();
//           for (count = 0; count <= tasks.size() - 1; count++) {
//            if (task.getPriority() < tasks.get(count).getPriority()) {
//                temp = count;
//                break;
//           }
//            }
//        tasks.add(temp, task);
//        }


    public void put(Task task) {
        tasks.add(task);
        tasks = tasks.stream().sorted(Comparator.comparingInt(Task::getPriority))
                .collect(Collectors.toCollection(LinkedList ::new));

    }
    public Task take() {
        return this.tasks.poll();
    }
}