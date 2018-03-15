package SortingAndSearchingAlgorithms.SearchingAlgorithms;

import java.util.List;

public class FibbonacciSearch {
    private static int fibonacciSearch(List<Integer> nums, int key) {
        int fibPrev = 0;
        int fibCrnt = 1;
        int fibNext = fibPrev + fibCrnt;
        while (fibNext < nums.size()) {
            fibPrev = fibCrnt;
            fibCrnt = fibNext;
            fibNext = fibPrev + fibCrnt;
        }
        int offset = -1;
        while (fibNext > 1) {
            int mid = Math.min(offset + fibPrev, nums.size() - 1);
            if (nums.get(mid) < key) {
                fibNext = fibCrnt;
                fibCrnt = fibPrev;
                fibPrev = fibNext - fibCrnt;
                offset = mid;
            } else if (nums.get(mid) > key) {
                fibNext = fibPrev;
                fibCrnt = fibCrnt - fibPrev;
                fibPrev = fibNext - fibCrnt;
            } else return mid;
        }

        if (fibCrnt != 0 && offset + 1 < nums.size() && nums.get(offset + 1) == key) {
            return offset + 1;
        }

        return -1;
    }
}
