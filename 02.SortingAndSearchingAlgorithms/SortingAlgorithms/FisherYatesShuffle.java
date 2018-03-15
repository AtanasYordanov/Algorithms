package SortingAndSearchingAlgorithms.SortingAlgorithms;

import java.util.List;
import java.util.Random;

public class FisherYatesShuffle {
    public static <T> void shuffle(List<T> list) {
        Random rnd = new Random();
        for (int i = 0; i < list.size(); i++) {
            int r = i + rnd.nextInt(list.size() - i);
            T temp = list.get(i);
            list.set(i, list.get(r));
            list.set(r, temp);
        }
    }
}
