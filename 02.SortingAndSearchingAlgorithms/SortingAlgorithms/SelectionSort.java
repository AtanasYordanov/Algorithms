package SortingAndSearchingAlgorithms.SortingAlgorithms;

import java.util.List;

public class SelectionSort {
    public static <T extends Comparable<T>> void sort(List<T> list) {
        for (int index = 0; index < list.size(); index++) {
            int min = index;
            for (int current = index + 1; current < list.size(); current++) {
                if (Helpers.isLess(list.get(current), list.get(min))) {
                    min = current;
                }
            }
            Helpers.swap(list, min, index);
        }
    }
}
