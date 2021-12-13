import junit.framework.TestCase;

import java.io.FileNotFoundException;

public class Day7Test extends TestCase {
    Day7 day7 = new Day7();
    private static final String RESOURCE = "src/test/resources/day7_test.txt";

    public void testPartOneAnswer() throws FileNotFoundException {
        String actualAnswer = day7.partOneAnswer(RESOURCE);
        String expectedAnswer = "37";
        assertEquals(expectedAnswer, actualAnswer);
    }

    public void testCalculateMedianOdd() {
        int[]  inputArray = {2,3,4,5,6};
        int actualMedian  = day7.calculateMedian(inputArray);
        int expectedMedian = 4;
        assertEquals(actualMedian, expectedMedian);
    }

    public void testCalculateMedianEven() {
        int[]  inputArray = {2,3,5,10,12,13};
        int actualMedian  = day7.calculateMedian(inputArray);
        int expectedMedian = 8;
        assertEquals(actualMedian, expectedMedian);
    }

    public void testGetFuelUsed() {
        int[]  inputArray = {2,3,5,10,12,13};
        int median = 8;
        int actualTotalFuel = day7.getFuelUsed(inputArray, median);
        int expectedTotalFuel = 25;
        assertEquals(actualTotalFuel, expectedTotalFuel);

    }
}
