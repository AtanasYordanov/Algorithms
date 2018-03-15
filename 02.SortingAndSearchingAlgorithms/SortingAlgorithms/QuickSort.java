package SortingAndSearchingAlgorithms.SortingAlgorithms;

import java.util.List;

public class QuickSort<T extends Comparable<T>> {

    public static <T extends Comparable<T>> void sort(List<T> list) {
        FisherYatesShuffle.shuffle(list);
        sort(list, 0, list.size() - 1);
    }

    private static <T extends Comparable<T>> void sort(List<T> list, int left, int right) {
        if (left >= right) {
            return;
        }
        int p = partition(list, left, right);
        sort(list, left, p - 1);
        sort(list, p + 1, right);
    }

    private static <T extends Comparable<T>> int partition(List<T> list, int left, int right) {
        if (left >= right) {
            return left;
        }
        int i = left;
        int j = right + 1;
        while (true) {
            while (Helpers.isLess(list.get(++i), list.get(left))) {
                if (i == right) break;
            }
            while (Helpers.isLess(list.get(left), list.get(--j))) {
                if (j == left) break;
            }
            if (i >= j) break;
            Helpers.swap(list, i, j);
        }
        Helpers.swap(list, left, j);
        return j;
    }
}
