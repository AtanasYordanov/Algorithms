package SortingAndSearchingAlgorithms.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InversionCount {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] nums = parseInput(reader);
        System.out.println(mergeSort(nums, new int[nums.length], 0, nums.length - 1));
    }

    private static int[] parseInput(BufferedReader reader) throws IOException {
        String[] line = reader.readLine().split("\\s+");
        int[] nums = new int[line.length];
        for (int i = 0; i < line.length; i++) {
            nums[i] = Integer.parseInt(line[i]);
        }
        return nums;
    }

    private static int mergeSort(int arr[], int temp[], int left, int right) {
        int mid, invCount = 0;
        if (right > left) {
            mid = (right + left) / 2;
            invCount = mergeSort(arr, temp, left, mid);
            invCount += mergeSort(arr, temp, mid + 1, right);
            invCount += merge(arr, temp, left, mid + 1, right);
        }
        return invCount;
    }

    private static int merge(int arr[], int temp[], int left, int mid, int right) {
        int i, j, k;
        int invCount = 0;
        i = left;
        j = mid;
        k = left;
        while ((i <= mid - 1) && (j <= right)) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                invCount = invCount + (mid - i);
            }
        }
        while (i <= mid - 1)
            temp[k++] = arr[i++];
        while (j <= right)
            temp[k++] = arr[j++];
        for (i = left; i <= right; i++)
            arr[i] = temp[i];
        return invCount;
    }
}
