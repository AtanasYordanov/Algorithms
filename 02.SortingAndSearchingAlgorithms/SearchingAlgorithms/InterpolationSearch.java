package SortingAndSearchingAlgorithms.SearchingAlgorithms;

public class InterpolationSearch {
    private static int[] sorted = null;

    public static final int find(int value, int[] array) {
        InterpolationSearch.sorted = array;
        try {
            return recursiveFind(value, 0, InterpolationSearch.sorted.length - 1);
        } finally {
            InterpolationSearch.sorted = null;
        }
    }

    private static int recursiveFind(int value, int start, int end) {
        if (start == end) {
            int lastValue = sorted[start];
            if (value == lastValue)
                return start;
            return Integer.MAX_VALUE;
        }

        final int mid = start + ((value - sorted[start]) * (end - start)) / (sorted[end] - sorted[start]);
        if (mid < 0 || mid > end)
            return Integer.MAX_VALUE;
        int midValue = sorted[mid];
        if (value == midValue)
            return mid;
        if (value > midValue)
            return recursiveFind(value, mid + 1, end);
        return recursiveFind(value, start, mid - 1);
    }
}
