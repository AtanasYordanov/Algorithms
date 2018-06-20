package CombinatorialAlgorithms.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Snakes {
    private static Set<String> visited = new HashSet<>();
    private static Set<String> snakes = new HashSet<>();
    private static char[] currentSnake;
    private static int n;
    private static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        currentSnake = new char[n];
        currentSnake[0] = 'S';
        visited.add(0 + " " + 0);
        generateSnakes(1, 0, 1, 'R');
        System.out.printf("Snakes count = %d%n", count);
    }

    private static void generateSnakes(int index, int row, int col, char direction) {
        if (index >= n) {
            String snake = new String(currentSnake);
            if (!snakes.contains(snake)) {
                markSnake(snake);
            }
            return;
        }
        String cell = row + " " + col;
        if (!visited.contains(cell)) {
            visited.add(cell);
            currentSnake[index] = direction;
            generateSnakes(index + 1, row, col + 1, 'R');
            generateSnakes(index + 1, row + 1, col, 'D');
            generateSnakes(index + 1, row, col - 1, 'L');
            generateSnakes(index + 1, row - 1, col, 'U');
            visited.remove(cell);
        }
    }

    private static void markSnake(String snake) {
        System.out.println(snake);
        count++;
        String flipped = flip(snake);
        String reversed = reverse(snake);
        String flippedReversed = reverse(flipped);
        addAllRotations(snake);
        addAllRotations(flipped);
        addAllRotations(reversed);
        addAllRotations(flippedReversed);
    }

    private static void addAllRotations(String snake) {
        for (int i = 0; i < 4; i++) {
            snake = rotate(snake);
            snakes.add(snake);
        }
    }

    private static String rotate(String snake) {
        char[] rotated = new char[snake.length()];
        for (int i = 0; i < snake.length(); i++) {
            switch (snake.charAt(i)) {
                case 'R':
                    rotated[i] = 'D';
                    break;
                case 'D':
                    rotated[i] = 'L';
                    break;
                case 'L':
                    rotated[i] = 'U';
                    break;
                case 'U':
                    rotated[i] = 'R';
                    break;
                default:
                    rotated[i] = 'S';
                    break;
            }
        }
        return new String(rotated);
    }

    private static String reverse(String snake) {
        char[] reversed = new char[snake.length()];
        reversed[0] = 'S';
        for (int i = snake.length() - 1; i >= 1; i--) {
            reversed[snake.length() - i] = snake.charAt(i);
        }
        return new String(reversed);
    }

    private static String flip(String snake) {
        char[] flipped = new char[snake.length()];
        for (int i = 0; i < snake.length(); i++) {
            if (snake.charAt(i) == 'U') {
                flipped[i] = 'D';
            } else if (snake.charAt(i) == 'D') {
                flipped[i] = 'U';
            } else {
                flipped[i] = snake.charAt(i);
            }
        }
        return new String(flipped);
    }
}
