package SolvingPracticalProblems.Exercises;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class ParkingZones {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Zone> zones = new LinkedHashMap<>();
        Map<String, Pair<Integer, Integer>> bestSpotsPerZone = new LinkedHashMap<>();
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split(": |, ");
            String zoneName = tokens[0];
            int x1 = Integer.parseInt(tokens[1]);
            int y1 = Integer.parseInt(tokens[2]);
            int x2 = x1 + Integer.parseInt(tokens[3]) - 1;
            int y2 = y1 + Integer.parseInt(tokens[4]) - 1;
            double price = Double.parseDouble(tokens[5]);
            Zone zone = new Zone(zoneName, x1, y1, x2, y2, price);
            zones.put(zoneName, zone);
        }
        String[] tokens = reader.readLine().split("; ");
        String[] targetXandY = reader.readLine().split(", ");
        int secondsPerBlock = Integer.parseInt(reader.readLine());
        int targetX = Integer.parseInt(targetXandY[0]);
        int targetY = Integer.parseInt(targetXandY[1]);
        for (String token : tokens) {
            String[] xAndY = token.split(", ");
            int x = Integer.parseInt(xAndY[0]);
            int y = Integer.parseInt(xAndY[1]);
            Zone zone = null;
            for (Zone z : zones.values()) {
                if (x >= z.getX1() && x <= z.getX2() && y >= z.getY1() && y <= z.getY2()) {
                    zone = z;
                }
            }
            Pair<Integer, Integer> pair = bestSpotsPerZone.getOrDefault(zone.getName(), null);
            if (pair != null) {
                if (Math.abs(pair.getKey() - targetX) + Math.abs(pair.getValue() - targetY) - 1
                        > Math.abs(x - targetX) + Math.abs(y - targetY) - 1) {
                    bestSpotsPerZone.put(zone.getName(), new Pair<>(x, y));
                }
            } else {
                bestSpotsPerZone.put(zone.getName(), new Pair<>(x, y));
            }
        }
        String bestZone = "";
        int bestX = -1;
        int bestY = -1;
        double bestCost = Double.MAX_VALUE;
        double bestDistance = 0d;
        for (Map.Entry<String, Pair<Integer, Integer>> pair : bestSpotsPerZone.entrySet()) {
            int distance = Math.abs(pair.getValue().getKey() - targetX)
                    + Math.abs(pair.getValue().getValue() - targetY) - 1;
            Zone zone = zones.get(pair.getKey());
            double cost = Math.ceil(distance * 2 * (secondsPerBlock / 60d)) * zone.getPricePerMinute();
            if (cost < bestCost || (cost == bestCost && distance < bestDistance)) {
                bestZone = zone.getName();
                bestDistance = distance;
                bestCost = cost;
                bestX = pair.getValue().getKey();
                bestY = pair.getValue().getValue();
            }
        }
        System.out.printf("Zone Type: %s; X: %d; Y: %d; Price: %.2f%n",
                bestZone, bestX, bestY, bestCost);
    }
}

class Zone {
    private String name;
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private double pricePerMinute;

    Zone(String name, int x1, int y1, int x2, int y2, double pricePerMinute) {
        this.name = name;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.pricePerMinute = pricePerMinute;
    }

    public String getName() {
        return name;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public double getPricePerMinute() {
        return pricePerMinute;
    }
}
