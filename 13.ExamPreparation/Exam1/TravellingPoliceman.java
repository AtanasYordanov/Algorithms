package ExamPreparation.Exam1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TravellingPoliceman {

    private static List<Street> streets = new ArrayList<>();
    private static int fuel;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        fuel = Integer.parseInt(reader.readLine());
        addStreets(reader);
        Deque<Street> visitedStreets = getVisitedStreets();
        printOutput(visitedStreets);
    }

    private static void addStreets(BufferedReader reader) throws IOException {
        for (String line = reader.readLine(); !line.equals("End"); line = reader.readLine()) {
            String[] tokens = line.split(", ");
            String name = tokens[0];
            int carDamage = Integer.parseInt(tokens[1]);
            int pokemonsCount = Integer.parseInt(tokens[2]);
            int length = Integer.parseInt(tokens[3]);
            Street street = new Street(name, carDamage, pokemonsCount, length);
            if (street.getValue() >= 0) {
                streets.add(street);
            }
        }
    }

    private static void printOutput(Deque<Street> visitedStreets) {
        StringBuilder sb = new StringBuilder();
        int pokemonsCaught = 0;
        int carDamage = 0;
        if (!visitedStreets.isEmpty()) {
            while (!visitedStreets.isEmpty()) {
                Street street = visitedStreets.pop();
                sb.append(street.getName()).append(" -> ");
                pokemonsCaught += street.getPokemonsCount();
                carDamage += street.getCarDamage();
            }
            sb.delete(sb.length() - 4, sb.length());
            sb.append(System.lineSeparator());
        }
        sb.append("Total pokemons caught -> ").append(pokemonsCaught);
        sb.append(System.lineSeparator());
        sb.append("Total car damage -> ").append(carDamage);
        sb.append(System.lineSeparator());
        sb.append("Fuel Left -> ").append(fuel);
        System.out.println(sb);
    }

    private static Deque<Street> getVisitedStreets() {
        int[][] values = new int[streets.size() + 1][fuel + 1];
        boolean[][] isIncluded = new boolean[streets.size() + 1][fuel + 1];
        for (int streetIndex = 0; streetIndex < streets.size(); streetIndex++) {
            int row = streetIndex + 1;
            Street street = streets.get(streetIndex);
            for (int currentFuel = 0; currentFuel <= fuel; currentFuel++) {
                int excludedValue = values[row - 1][currentFuel];
                int includedValue = 0;
                if (street.getLength() <= currentFuel) {
                    includedValue = street.getValue() + values[row - 1][currentFuel - street.getLength()];
                }
                if (includedValue > excludedValue) {
                    values[row][currentFuel] = includedValue;
                    isIncluded[row][currentFuel] = true;
                } else {
                    values[row][currentFuel] = excludedValue;
                }
            }
        }
        Deque<Street> visitedStreets = new ArrayDeque<>();
        for (int index = streets.size(); index >= 1; index--) {
            if (isIncluded[index][fuel]) {
                Street street = streets.get(index - 1);
                visitedStreets.push(street);
                fuel -= street.getLength();
            }
        }
        return visitedStreets;
    }

    private static class Street {
        private String name;
        private int pokemonsCount;
        private int carDamage;
        private int value;
        private int length;

        Street(String name, int carDamage, int pokemonsCount, int length) {
            this.name = name;
            this.pokemonsCount = pokemonsCount;
            this.carDamage = carDamage;
            this.length = length;
            this.setValue();
        }

        private void setValue() {
            this.value = this.pokemonsCount * 10 - this.carDamage;
        }

        int getPokemonsCount() {
            return pokemonsCount;
        }

        int getValue() {
            return this.value;
        }

        int getLength() {
            return this.length;
        }

        String getName() {
            return this.name;
        }

        int getCarDamage() {
            return carDamage;
        }
    }
}

