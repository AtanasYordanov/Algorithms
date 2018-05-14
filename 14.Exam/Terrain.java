package Exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Terrain {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        char[] elements = reader.readLine().toCharArray();
        Map<Character, Integer> uniqueElements = new HashMap<>();
        for (Character element : elements) {
            uniqueElements.putIfAbsent(element, 0);
            uniqueElements.put(element, uniqueElements.get(element) + 1);
        }
        BigInteger factorial = calcFactorial(elements.length);
        BigInteger divisor = BigInteger.ONE;
        for (Integer count : uniqueElements.values()) {
            divisor = divisor.multiply(calcFactorial(count));
        }
        BigInteger result = factorial.divide(divisor);
        System.out.println(result);
    }

    private static BigInteger calcFactorial(int num) {
        BigInteger output = BigInteger.ONE;
        for (int i = 2; i <= num; i++) {
            output = output.multiply(BigInteger.valueOf(i));
        }
        return output;
    }
}
