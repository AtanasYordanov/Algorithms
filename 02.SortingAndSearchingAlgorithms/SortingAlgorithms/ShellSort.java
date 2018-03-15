package SortingAndSearchingAlgorithms.SortingAlgorithms;

import java.util.List;

public class ShellSort {
    public static <T extends Comparable<T>> void sort(List<T> nums) {
        for (int gap = nums.size() / 2; gap > 0; gap /= 2)
            for (int i = gap; i < nums.size(); i++)
                for (int j = i - gap; j >= 0 && Helpers.isLess(nums.get(j + gap), nums.get(j)); j -= gap) {
                    T temp = nums.get(j);
                    nums.set(j, nums.get(j + gap));
                    nums.set(j + gap, temp);
                }
    }
}
