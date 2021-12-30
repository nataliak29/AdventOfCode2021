import junit.framework.TestCase;

import java.io.FileNotFoundException;
import java.util.*;

public class Day12Test extends TestCase {
    Day12 day12 = new Day12();
    private static final String RESOURCE = "src/test/resources/day12_test.txt";
    private static final String RESOURCE_LARGE = "src/test/resources/day12_test_large.txt";

    public void testPartOneAnswer() throws FileNotFoundException {
        String actualAnswer = day12.partOneAnswer(RESOURCE);
        String expectedAnswer = "10";
        assertEquals(expectedAnswer, actualAnswer);
    }

    public void testPartOneAnswerLarge() throws FileNotFoundException {
        String actualAnswer = day12.partOneAnswer(RESOURCE_LARGE);
        String expectedAnswer = "226";
        assertEquals(expectedAnswer, actualAnswer);
    }

    public void testPartTwoAnswer() throws FileNotFoundException {
        String actualAnswer = day12.partTwoAnswer(RESOURCE);
        String expectedAnswer = "36";
        assertEquals(expectedAnswer, actualAnswer);
    }

    public void testPartTwoAnswerLarge() throws FileNotFoundException {
        String actualAnswer = day12.partTwoAnswer(RESOURCE_LARGE);
        String expectedAnswer = "3509";
        assertEquals(expectedAnswer, actualAnswer);
    }

    public void testDeepSearch() {
        ArrayList<String> inputArrayString = new ArrayList<String>(
                Arrays.asList("start-A", "start-b", "A-c", "A-b", "b-d", "A-end", "b-end"));
        Day12.CaveMap myCaveMap = new Day12.CaveMap(inputArrayString);
        int actual = day12.deepSearch(myCaveMap.caveMap.get("start"), new HashSet<>());
        assertEquals(10, actual);
    }

    public void testDeepSearchPart2() {
        ArrayList<String> inputArrayString = new ArrayList<String>(
                Arrays.asList("start-A", "start-b", "A-c", "A-b", "b-d", "A-end", "b-end"));
        Day12.CaveMap myCaveMap = new Day12.CaveMap(inputArrayString);
        int actual = day12.deepSearchPart2(myCaveMap.caveMap.get("start"), new HashSet<>(),false);
        assertEquals(36, actual);
    }




}


