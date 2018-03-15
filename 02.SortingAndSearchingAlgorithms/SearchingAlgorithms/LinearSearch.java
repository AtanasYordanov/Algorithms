package SortingAndSearchingAlgorithms.SearchingAlgorithms;

import java.util.List;

public class LinearSearch {
    private static int search(List<Integer> nums, int key) {
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) == key) {
                return i;
            } else if (nums.get(i) > key) {
                return -1;
            }
        }
        return -1;
    }
}
