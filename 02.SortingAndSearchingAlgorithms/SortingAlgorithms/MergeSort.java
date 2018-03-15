package SortingAndSearchingAlgorithms.SortingAlgorithms;

import java.util.List;

public class MergeSort<T extends Comparable<T>> {

    private T[] aux;

    public void sort(List<T> list) {
        this.aux = (T[]) (new Comparable[list.size()]);
        this.sort(list, 0, list.size() - 1);
    }

    private void merge(List<T> list, int left, int mid, int right) {
        if (Helpers.isLess(list.get(mid), list.get(mid + 1))) {
            return;
        }
        for (int index = left; index < right + 1; index++) {
            this.aux[index] = list.get(index);
        }
        int i = left;
        int j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i > mid) {
                list.set(k, this.aux[j++]);
            } else if (j > right) {
                list.set(k, this.aux[i++]);
            } else if (Helpers.isLess(aux[i], aux[j])) {
                list.set(k, this.aux[i++]);
            } else {
                list.set(k, this.aux[j++]);
            }
        }
    }

    private void sort(List<T> list, int left, int right) {
        if (left >= right) {
            return;
        }
        int middle = left + (right - left) / 2;
        sort(list, left, middle);
        sort(list, middle + 1, right);
        merge(list, left, middle, right);
    }
}
