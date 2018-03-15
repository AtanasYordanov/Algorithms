package SortingAndSearchingAlgorithms.SearchingAlgorithms;

import java.util.List;

public class BinarySearch {
    private static int binarySearch(List<Integer> nums, int key, int lo, int hi) {
        if (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < nums.get(mid)) {
                return binarySearch(nums, key, lo, mid);
            } else if (key > nums.get(mid)) {
                return binarySearch(nums, key, mid + 1, hi);
            } else {
                return mid;
            }
        }
        return -1;
    }
}
