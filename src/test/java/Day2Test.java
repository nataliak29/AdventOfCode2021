import javafx.util.Pair;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2Test extends TestCase {
    private static String RESOURCE = "src/test/resources/day2_test.txt";
    Day2 day2 = new Day2();
    private static List<Integer> startCoordinates;

    @Test
    public void testParseInstruction() {
        Pair<String, Integer> expectedInstruction = new Pair<String, Integer>("up", 5);
        Pair<String, Integer> actualInstruction = day2.parseInstruction("up 5");
        assertEquals(expectedInstruction, actualInstruction);
    }
    @Test
    public void testMoveByInstructionForward() {
        startCoordinates = new ArrayList<Integer>(Arrays.asList(0, 0));
        List<Integer> expectedCoordinates = new ArrayList<Integer>(Arrays.asList(2, 0));
        List<Integer> actualCoordinates = day2.moveByInstruction(startCoordinates, "forward 2");
        assertEquals(expectedCoordinates, actualCoordinates);
    }
    @Test
    public void testMoveByInstructionUp() {
        startCoordinates = new ArrayList<Integer>(Arrays.asList(0, 0));
        List<Integer> expectedCoordinates = new ArrayList<Integer>(Arrays.asList(0, -3));
        List<Integer> actualCoordinates = day2.moveByInstruction(startCoordinates, "up 3");
        assertEquals(expectedCoordinates, actualCoordinates);
    }

    @Test
    public void testMoveByInstructionDown() {
        startCoordinates = new ArrayList<Integer>(Arrays.asList(0, 0));
        List<Integer> expectedCoordinates = new ArrayList<Integer>(Arrays.asList(0, 2));
        List<Integer> actualCoordinates = day2.moveByInstruction(startCoordinates, "down 2");
        assertEquals(expectedCoordinates, actualCoordinates);
    }

    @Test
    public void testFinalCoordinates() {
        ArrayList<String> instructionsArray = new ArrayList<String>(Arrays.asList("down 2", "forward 7"));
        List<Integer> expectedCoordinates = new ArrayList<Integer>(Arrays.asList(7, 2));
        List<Integer> actualCoordinates = day2.finalCoordinates(instructionsArray);
        assertEquals(expectedCoordinates, actualCoordinates);
    }
    @Test
    public void testPartOneAnswer() throws FileNotFoundException {
        assertEquals("150", day2.partOneAnswer(RESOURCE));
    }

    @Test
    public void testMoveByInstructionWithAimForward() {
        startCoordinates = new ArrayList<Integer>(Arrays.asList(0, 1, 1));
        List<Integer> expectedCoordinates = new ArrayList<Integer>(Arrays.asList(2, 3, 1));
        List<Integer> actualCoordinates = day2.moveByInstructionWithAim(startCoordinates, "forward 2");
        assertEquals(expectedCoordinates, actualCoordinates);
    }
    @Test
    public void testMoveByInstructionWithAimUp() {
        startCoordinates = new ArrayList<Integer>(Arrays.asList(0, 0, 0 ));
        List<Integer> expectedCoordinates = new ArrayList<Integer>(Arrays.asList(0, 0, -3));
        List<Integer> actualCoordinates = day2.moveByInstructionWithAim(startCoordinates, "up 3");
        assertEquals(expectedCoordinates, actualCoordinates);
    }

    @Test
    public void testMoveByInstructionWithAimDown() {
        startCoordinates = new ArrayList<Integer>(Arrays.asList(0, 0, 0));
        List<Integer> expectedCoordinates = new ArrayList<Integer>(Arrays.asList(0, 0, 2));
        List<Integer> actualCoordinates = day2.moveByInstructionWithAim(startCoordinates, "down 2");
        assertEquals(expectedCoordinates, actualCoordinates);
    }

    @Test
    public void testFinalCoordinatesWithAim() {
        ArrayList<String> instructionsArray = new ArrayList<String>(Arrays.asList("down 2", "forward 7"));
        List<Integer> expectedCoordinates = new ArrayList<Integer>(Arrays.asList(7, 14, 2));
        List<Integer> actualCoordinates = day2.finalCoordinatesWithAim(instructionsArray);
        assertEquals(expectedCoordinates, actualCoordinates);
    }

    @Test
    public void testPartTwoAnswer() throws FileNotFoundException {
        assertEquals("900", day2.partTwoAnswer(RESOURCE));
    }
}