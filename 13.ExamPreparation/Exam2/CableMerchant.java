package ExamPreparation.Exam2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CableMerchant {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = reader.readLine().split(" ");
        int[] prices = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            prices[i] = Integer.parseInt(tokens[i]);
        }
        int connectorPrice = Integer.parseInt(reader.readLine());
        for (int i = 1; i < prices.length; i++) {
            for (int j = 0; j < i / 2; j++) {
                int price = prices[j] + prices[i - 1 - j] - 2 * connectorPrice;
                if (price > prices[i]) {
                    prices[i] = price;
                }
            }
            if (i % 2 == 1) {
                int price = (prices[i / 2] - connectorPrice) * 2;
                if (price > prices[i]) {
                    prices[i] = price;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int price : prices) {
            sb.append(price).append(" ");
        }
        System.out.println(sb);
    }
}
