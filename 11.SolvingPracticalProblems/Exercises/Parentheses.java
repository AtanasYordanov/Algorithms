package SolvingPracticalProblems.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Parentheses {

    private static StringBuilder sb = new StringBuilder();
    private static StringBuilder output = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        sb.append("(");
        generateParentheses(n - 1, 1);
        System.out.print(output);
    }

    private static void generateParentheses(int openingParentheses, int closingParentheses) {
        if (openingParentheses == 0 && closingParentheses == 0) {
            output.append(sb).append(System.lineSeparator());
            return;
        }
        if (openingParentheses == -1 || closingParentheses == -1) {
            return;
        }
        sb.append("(");
        generateParentheses(openingParentheses - 1, closingParentheses + 1);
        sb.replace(sb.length() - 1, sb.length(), ")");
        generateParentheses(openingParentheses, closingParentheses - 1);
        sb.delete(sb.length() - 1, sb.length());
    }
}
