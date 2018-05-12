package ExamPreparation.Exam6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AbaspaBasapa {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str1 = reader.readLine();
        String str2 = reader.readLine();
        int bestLength = 0;
        int bestStart = -1;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.length() - i < bestLength) {
                break;
            }
            char ch = str1.charAt(i);
            for (int j = 0; j < str2.length(); j++) {
                if (str2.charAt(j) == ch) {
                    int length = 1;
                    for (int k = 1; k < Math.min(str1.length() - i, str2.length() - j); k++) {
                        if (str1.charAt(i + k) == str2.charAt(j + k)) {
                            length++;
                        } else {
                            break;
                        }
                    }
                    if (length > bestLength) {
                        bestLength = length;
                        bestStart = i;
                    }
                }
            }
        }
        char[] subsequence = new char[bestLength];
        int index = 0;
        for (int i = bestStart; i < bestStart + bestLength; i++) {
            subsequence[index++] = str1.charAt(i);
        }
        System.out.println(subsequence);
    }
}
