package GraphAndGraphAlgorithms.Lab;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class TopologicalSortTests {

    @Test
    public void TestTopSortAcyclicGraph6Vertices()
    {
        // Arrange
        Map<String, List<String>> graph = new LinkedHashMap<>();
        graph.put("A", new ArrayList<>(Arrays.asList("B", "C")));
        graph.put("B", new ArrayList<>(Arrays.asList("D", "E")));
        graph.put("C", new ArrayList<>(Arrays.asList("F")));
        graph.put("D", new ArrayList<>(Arrays.asList("C", "F")));
        graph.put("E", new ArrayList<>(Arrays.asList("D")));
        graph.put("F", new ArrayList<>());

        // Act
        Collection<String> topSorter = TopologicalSortDFS.topSort(graph);

        // Assert
        Assert.assertEquals("[A, B, E, D, C, F]", topSorter.toString());
    }

    @Test
    public void TestTopSortAcyclicGraph5Vertices()
    {
        // Arrange
        Map<String, List<String>> graph = new LinkedHashMap<>();
        graph.put("IDEs", new ArrayList<>(Arrays.asList("variables", "loops")));
        graph.put("variables", new ArrayList<>(Arrays.asList("conditionals", "loops", "bits")));
        graph.put("loops", new ArrayList<>(Arrays.asList("bits")));
        graph.put("bits", new ArrayList<>(Arrays.asList()));
        graph.put("conditionals", new ArrayList<>(Arrays.asList("loops")));

        // Act
        Collection<String> topSorter = TopologicalSortDFS.topSort(graph);

        // Assert
        Assert.assertEquals("[IDEs, variables, conditionals, loops, bits]", topSorter.toString());
    }

    @Test
    public void TestTopSortGraph1Vertex()
    {
        // Arrange
        Map<String, List<String>> graph = new LinkedHashMap<>();
        graph.put("A", new ArrayList<>());

        // Act
        Collection<String> topSorted = TopologicalSortDFS.topSort(graph);

        // Assert
        Assert.assertEquals("[A]", topSorted.toString());
    }

    @Test
    public void TestTopSortEmptyGraph()
    {
        // Arrange
        Map<String, List<String>> graph = new LinkedHashMap<>();


        // Act
        Collection<String> topSorted = TopologicalSortDFS.topSort(graph);

        // Assert
        Assert.assertEquals("[]", topSorted.toString());
    }

    @Test
    public void TestTopSortAcyclicGraph8Vertices()
    {
        // Arrange
        Map<String, List<String>> graph = new LinkedHashMap<>();
        graph.put("H", new ArrayList<>(Arrays.asList("G")));
        graph.put("G", new ArrayList<>());
        graph.put("B", new ArrayList<>(Arrays.asList("A")));
        graph.put("A", new ArrayList<>());
        graph.put("F", new ArrayList<>(Arrays.asList("B", "C", "E")));
        graph.put("C", new ArrayList<>(Arrays.asList("A")));
        graph.put("E", new ArrayList<>(Arrays.asList("C", "A")));
        graph.put("D", new ArrayList<>(Arrays.asList("A", "B")));

        // Act
        Collection<String> topSorted = TopologicalSortDFS.topSort(graph);

        // Assert
        Assert.assertEquals("[D, F, E, C, B, A, H, G]", topSorted.toString());
    }

    @Test
    public void TestTopSortAcyclicGraph2Vertices()
    {
        // Arrange
        Map<String, List<String>> graph = new LinkedHashMap<>();
        graph.put("First", new ArrayList<>(Arrays.asList("Second")));
        graph.put("Second", new ArrayList<>());

        // Act
        Collection<String> topSorted = TopologicalSortDFS.topSort(graph);

        // Assert
        Assert.assertEquals("[First, Second]", topSorted.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestTopSortGraph2VerticesWithCycle()
    {
        // Arrange
        Map<String, List<String>> graph = new LinkedHashMap<>();
        graph.put("First", new ArrayList<>(Arrays.asList("Second")));
        graph.put("Second", new ArrayList<>(Arrays.asList("First")));

        // Act
        // Assert
        Collection<String> topSorted = TopologicalSortDFS.topSort(graph);
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestTopSortGraph7VerticesWithCycle()
    {
        // Arrange
        Map<String, List<String>> graph = new LinkedHashMap<>();
        graph.put("A", new ArrayList<>(Arrays.asList("B")));
        graph.put("B", new ArrayList<>(Arrays.asList("C")));
        graph.put("C", new ArrayList<>(Arrays.asList("D", "E")));
        graph.put("D", new ArrayList<>(Arrays.asList("E")));
        graph.put("E", new ArrayList<>(Arrays.asList("F", "C")));
        graph.put("F", new ArrayList<>());
        graph.put("Z", new ArrayList<>(Arrays.asList("A")));

        // Act
        // Assert
        Collection<String> topSorted = TopologicalSortDFS.topSort(graph);
    }
}
