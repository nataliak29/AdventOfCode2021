import junit.framework.TestCase;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day15Test extends TestCase {
    Day15 day15 = new Day15();
    private static final String RESOURCE = "src/test/resources/day15_test.txt";

    public void testPartOneAnswer() throws FileNotFoundException {
        String actualAnswer = day15.partOneAnswer(RESOURCE);
        String expectedAnswer = "40";
        assertEquals(expectedAnswer, actualAnswer);
    }

    public void testPartTwoAnswer() throws FileNotFoundException {
        String actualAnswer = day15.partTwoAnswer(RESOURCE);
        String expectedAnswer = "315";
        assertEquals(expectedAnswer, actualAnswer);
    }

    public void testCreateGrid() {
        ArrayList<String> inputArray = new ArrayList<String>(
                Arrays.asList("123", "456"));
        Grid actualGrid = day15.createGrid(inputArray);
        Grid expectedGrid = new Grid(new int[][]{{1,2,3},{4,5,6}});
        assertEquals(expectedGrid,actualGrid);
    }

    public void testCreateGridLarge() {
        ArrayList<String> inputArray = new ArrayList<String>(
                Arrays.asList("123", "456"));
        Grid actualGrid = day15.createGrid(inputArray);
        Grid expectedGrid = new Grid(new int[][]{{1,2,3},{4,5,6}});
        assertEquals(expectedGrid,actualGrid);
    }

    public void testCreateRisksGraph() {
        Grid grid = new Grid(new int[][]{{1,2,3},{4,5,6}});
        Graph actualGraph = day15.createRisksGraph(grid);
        System.out.println(actualGraph);

    }

    public void testGetAdjacentNode() {
        Grid grid = new Grid(new int[][]{{1,2,3},{4,5,6}});
        Node targetNode = new Node(0, 0);
        Map<Node, Integer> actualMap = day15.getAdjacentNode(targetNode, grid);
        Map<Node, Integer> expectedMap = new HashMap<Node, Integer>();
        expectedMap.put(new Node(1, 0),4);
        expectedMap.put(new Node(0, 1),2);
        for (Node n : actualMap.keySet()){
            System.out.println(n);
            if (expectedMap.containsKey(n)){
                System.out.println("a");
            }
            if (expectedMap.containsKey(n) && actualMap.get(n) == expectedMap.get(n)){
                System.out.println(true);
            }
        }
    }
}
