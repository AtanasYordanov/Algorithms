package SortingAndSearchingAlgorithms.SortingAlgorithms;

import java.util.List;

public class InsertionSort {
    public static <T extends Comparable<T>> void sort(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            int previous = i - 1;
            int current = i;
            while (true) {
                if (previous < 0 || Helpers.isLess(list.get(previous), list.get(current))) {
                    break;
                }
                Helpers.swap(list, current, previous);
                previous--;
                current--;
            }
        }
    }
}
