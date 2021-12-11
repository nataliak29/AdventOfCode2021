import junit.framework.TestCase;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day6Test extends TestCase {
    Day6 day6 = new Day6();
    private static final String RESOURCE = "src/test/resources/day6_test.txt";

    public void testPartOneAnswer() throws FileNotFoundException {
        String actualAnswer = day6.partOneAnswer(RESOURCE);
        String expectedAnswer = "5934";
        assertEquals(expectedAnswer, actualAnswer);
    }

    public void testPartTwoAnswer() {
        String actualAnswer = day6.partTwoAnswer(RESOURCE);
        String expectedAnswer = "26984457539";
        assertEquals(expectedAnswer, actualAnswer);
    }

    public void testGetLanternfishArray() {
        ArrayList<String> inputArray = new ArrayList<String>(Arrays.asList("2,5,6,7"));
        Long[] actualArray  = day6.getLanternfishArray(inputArray);
        Long[] expectedArray = {Long.valueOf(2), Long.valueOf(5), Long.valueOf(6), Long.valueOf(7)};
        assertTrue(Arrays.deepEquals(expectedArray, actualArray));

    }

    public void testProcessOneDay () {
        Long[]  inputArray = {Long.valueOf(2), Long.valueOf(0), Long.valueOf(6), Long.valueOf(7)};
        Long[] actualArray  = day6.processOneDay(inputArray);
        Long[]  expectedArray = {Long.valueOf(1), Long.valueOf(6), Long.valueOf(5), Long.valueOf(6), Long.valueOf(8)};
        assertTrue(Arrays.deepEquals(expectedArray, actualArray));
    }

    public void testProcessOneDayAddMultipleFish () {
        Long[] inputArray =  {Long.valueOf(2), Long.valueOf(0), Long.valueOf(6), Long.valueOf(7), Long.valueOf(8), Long.valueOf(0), Long.valueOf(0)};
        Long[] actualArray  = day6.processOneDay(inputArray);
        Long[]  expectedArray = {Long.valueOf(1), Long.valueOf(6), Long.valueOf(5), Long.valueOf(6), Long.valueOf(7),
                Long.valueOf(6), Long.valueOf(6), Long.valueOf(8), Long.valueOf(8), Long.valueOf(8)};
        assertTrue(Arrays.deepEquals(expectedArray, actualArray));
    }

    public void testRunSimuation5Days() {
        Long[]  inputArray = {Long.valueOf(3), Long.valueOf(4), Long.valueOf(3), Long.valueOf(1), Long.valueOf(2)};
        int actualSize  = day6.runSimuation(inputArray, 5);
        int expectedSize = 10;
        assertEquals(expectedSize, actualSize);
    }

    public void testRunSimuation18Days() {
        Long[]  inputArray = {Long.valueOf(3), Long.valueOf(4), Long.valueOf(3), Long.valueOf(1), Long.valueOf(2)};
        int actualSize  = day6.runSimuation(inputArray, 18);
        int expectedSize = 26;
        assertEquals(expectedSize, actualSize);
    }

    public void testRunSimuation80Days() {
        Long[]  inputArray = {Long.valueOf(3), Long.valueOf(4), Long.valueOf(3), Long.valueOf(1), Long.valueOf(2)};
        int actualSize  = day6.runSimuation(inputArray, 80);
        int expectedSize = 5934;
        assertEquals(expectedSize, actualSize);
    }
}