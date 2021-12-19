import junit.framework.TestCase;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day10Test extends TestCase {
    Day10 day10 = new Day10();
    private static final String RESOURCE = "src/test/resources/day10_test.txt";

    public void testPartOneAnswer() throws FileNotFoundException {
        String actualAnswer = day10.partOneAnswer(RESOURCE);
        String expectedAnswer = "26397";
        assertEquals(expectedAnswer, actualAnswer);
    }

    public void testPartTwoAnswer() throws FileNotFoundException {
        String actualAnswer = day10.partTwoAnswer(RESOURCE);
        String expectedAnswer = "-1";
        assertEquals(expectedAnswer, actualAnswer);
    }

    public void testFindCorruptedCharacter() {
        String line = "{([(<{}[<>[]}>{[]{[(<()>";
        Day10.NavigationSubsystemLine navigationSubsystemLine = new Day10.NavigationSubsystemLine(line);
        String actualString = navigationSubsystemLine.getCorruptedCharacter();
        String expectedString = "}";
        assertEquals(expectedString, actualString);
    }

    public void testFindCorruptedCharacterCase2() {
        String line = "[[<[([]))<([[{}[[()]]]";
        Day10.NavigationSubsystemLine navigationSubsystemLine = new Day10.NavigationSubsystemLine(line);
        String actualString = navigationSubsystemLine.getCorruptedCharacter();
        String expectedString = ")";
        assertEquals(expectedString, actualString);
    }

    public void testFindCorruptedCharacterCase3() {
        String line = "[({(<(())[]>[[{[]{<()<>>";
        Day10.NavigationSubsystemLine navigationSubsystemLine = new Day10.NavigationSubsystemLine(line);
        String actualString = navigationSubsystemLine.getCorruptedCharacter();
        String expectedString = null;
        assertEquals(expectedString, actualString);
    }

    public void testGetPoints() {
        String line = "[[<[([]))<([[{}[[()]]]";
        Day10.NavigationSubsystemLine navigationSubsystemLine = new Day10.NavigationSubsystemLine(line);
        String corruptedCharacter = navigationSubsystemLine.getCorruptedCharacter();
        Integer actualPoints = navigationSubsystemLine.getPoints( corruptedCharacter);
        Integer expectedPoints = 3;
        assertEquals( expectedPoints, actualPoints);
    }

    public void testTotalPointsFromCorruptedCharacters() {
        ArrayList<String> inputArrayString = new ArrayList<String>(
                Arrays.asList("{([(<{}[<>[]}>{[]{[(<()>", "[[<[([]))<([[{}[[()]]]",
                        "[{[{({}]{}}([{[{{{}}([]", "[<(<(<(<{}))><([]([]()","<{([([[(<>()){}]>(<<{{"));
        Integer totalPoints = day10.totalPointsFromCorruptedCharacters(inputArrayString);
        Integer expectedPoints = 26397;
        assertEquals( expectedPoints, totalPoints);
    }


}
