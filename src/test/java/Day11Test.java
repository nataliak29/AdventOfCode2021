import junit.framework.TestCase;

import java.io.FileNotFoundException;

public class Day11Test extends TestCase {
    Day11 day11 = new Day11();
    private static final String RESOURCE = "src/test/resources/day11_test.txt";

    public void testPartOneAnswer() throws FileNotFoundException {
        String actualAnswer = day11.partOneAnswer(RESOURCE);
        String expectedAnswer = "1656";
        assertEquals(expectedAnswer, actualAnswer);
    }


    public void testPartTwoAnswer() throws FileNotFoundException {
        String actualAnswer = day11.partTwoAnswer(RESOURCE);
        String expectedAnswer = "195";
        assertEquals(expectedAnswer, actualAnswer);
    }


}
