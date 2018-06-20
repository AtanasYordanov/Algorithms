package ExamPreparation.Exam3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class NestedRectangles {

    private static TreeSet<Rectangle> rectangles = new TreeSet<>(Comparator.comparing(Rectangle::getX1)
            .thenComparing(Rectangle::getY1)
            .thenComparing(Rectangle::getY2, Comparator.reverseOrder())
            .thenComparing(Rectangle::getX2));

    public static void main(String[] args) throws IOException {
        readRectangles();
        sweepLine();
        printResult();
    }

    private static void sweepLine() {
        Set<Rectangle> open = new HashSet<>();
        PriorityQueue<Rectangle> queue = new PriorityQueue<>(Comparator.comparing(Rectangle::getX2)
                .thenComparing(Rectangle::getY1)
                .thenComparing(Rectangle::getY2, Comparator.reverseOrder())
                .thenComparing(Rectangle::getX1, Comparator.reverseOrder()));
        for (Rectangle rectangle : rectangles) {
            int x1 = rectangle.getX1();
            while (!queue.isEmpty() && x1 > queue.peek().getX2()) {
                removeRectangle(open, queue);
            }
            queue.offer(rectangle);
            open.add(rectangle);
        }
        while (!queue.isEmpty()) {
            removeRectangle(open, queue);
        }
    }

    private static void printResult() {
        Rectangle current = getFirstRectangle();
        StringBuilder sb = new StringBuilder();
        sb.append(current.getName());
        while (current.getNext() != null) {
            current = current.getNext();
            sb.append(" < ").append(current.getName());
        }
        System.out.println(sb);
    }

    private static Rectangle getFirstRectangle() {
        Rectangle current = rectangles.first();
        int maxLayers = -1;
        for (Rectangle rectangle : rectangles) {
            int layers = rectangle.getLayers();
            if (layers > maxLayers) {
                current = rectangle;
                maxLayers = rectangle.getLayers();
            } else if (layers == maxLayers && current.getName().compareTo(rectangle.getName()) > 0) {
                current = rectangle;
            }
        }
        return current;
    }

    private static void readRectangles() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (String line = reader.readLine(); !line.equals("End"); line = reader.readLine()) {
            String[] tokens = line.split(": | ");
            String name = tokens[0];
            int x1 = Integer.parseInt(tokens[1]);
            int y1 = Integer.parseInt(tokens[2]);
            int x2 = Integer.parseInt(tokens[3]);
            int y2 = Integer.parseInt(tokens[4]);
            Rectangle rectangle = new Rectangle(name, x1, y1, x2, y2);
            rectangles.add(rectangle);
        }
    }

    private static void removeRectangle(Set<Rectangle> open, PriorityQueue<Rectangle> queue) {
        Rectangle closingRectangle = queue.poll();
        open.remove(closingRectangle);
        for (Rectangle openRec : open) {
            if (closingRectangle.isNestedIn(openRec)) {
                if (closingRectangle.getLayers() >= openRec.getLayers()) {
                    int layers = closingRectangle.getLayers() + 1;
                    openRec.setLayers(layers);
                }
                if (closingRectangle.getLayers() >= openRec.getLayers() - 1) {
                    Rectangle next = openRec.getNext();
                    if (next == null || (closingRectangle.getLayers() == next.getLayers()
                            && closingRectangle.getName().compareTo(next.getName()) < 0)
                            || closingRectangle.getLayers() > next.getLayers()) {
                        openRec.setNext(closingRectangle);
                    }
                }
            }
        }
    }

    private static class Rectangle {
        private String name;
        private int x1;
        private int y1;
        private int x2;
        private int y2;
        private int layers;
        private Rectangle next;

        Rectangle(String name, int x1, int y1, int x2, int y2) {
            this.name = name;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.layers = 0;
            this.next = null;
        }

        String getName() {
            return this.name;
        }

        void setNext(Rectangle next) {
            this.next = next;
        }

        int getX1() {
            return this.x1;
        }

        int getY1() {
            return this.y1;
        }

        int getX2() {
            return this.x2;
        }

        int getY2() {
            return this.y2;
        }

        Rectangle getNext() {
            return this.next;
        }

        int getLayers() {
            return this.layers;
        }

        void setLayers(int layers) {
            this.layers = layers;
        }

        boolean isNestedIn(Rectangle other) {
            return this.x1 >= other.x1 && this.y1 <= other.y1 && this.y2 >= other.y2;
        }
    }
}
