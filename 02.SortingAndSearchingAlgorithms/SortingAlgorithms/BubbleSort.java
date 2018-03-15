package SortingAndSearchingAlgorithms.SortingAlgorithms;

import java.util.List;

public class BubbleSort {
    public static <T extends Comparable<T>> void sort(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                if (!Helpers.isLess(list.get(j), list.get(j + 1))) {
                    Helpers.swap(list, j, j + 1);
                }
            }
        }
    }
}
