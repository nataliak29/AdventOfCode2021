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
        String expectedAnswer = "288957";
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

    public void testGetLineToAutoComplete() {
        String line = "[({(<(())[]>[[{[]{<()<>>";
        Day10.NavigationSubsystemLine navigationSubsystemLine = new Day10.NavigationSubsystemLine(line);
        String actualString = navigationSubsystemLine.getLineToAutoComplete();
        String expectedString ="[({([[{{";
        assertEquals(expectedString, actualString);
    }

    public void testGetLineToAutoComplete2() {
        String line = "[[<[([]))<([[{}[[()]]]";
        Day10.NavigationSubsystemLine navigationSubsystemLine = new Day10.NavigationSubsystemLine(line);
        String actualString = navigationSubsystemLine.getLineToAutoComplete();
        String expectedString ="";
        assertEquals(expectedString, actualString);
    }

    public void testFindCorruptedCharacterCase4() {
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

    public void testAutoCompleteScore(){
        String input = "[({([[{{";
        Day10.NavigationSubsystemLine navigationSubsystemLine = new Day10.NavigationSubsystemLine(input);
        Long expectedScore = Long.valueOf(288957);
        Long actualScore = navigationSubsystemLine.autoCompleteScore(input);
        assertEquals( expectedScore, actualScore);
    }


    public void testPointsFromMissingCharacters() {
        ArrayList<String> inputArrayString = new ArrayList<String>(
                Arrays.asList("[({(<(())[]>[[{[]{<()<>>", "[(()[<>])]({[<{<<[]>>(",
                        "(((({<>}<{<{<>}{[]{[]{}", "{<[[]]>}<{[{[{[]{()[[[]","<{([{{}}[<[[[<>{}]]]>[]]"));
        ArrayList<Long> actualArray = day10.pointsFromMissingCharacters(inputArrayString);
        ArrayList<Long> expectedArray = new ArrayList<Long>(
                Arrays.asList(Long.valueOf(288957), Long.valueOf(5566), Long.valueOf(1480781),
                        Long.valueOf(995444), Long.valueOf(294)));
        assertTrue(Arrays.deepEquals(new ArrayList[]{expectedArray}, new ArrayList[]{actualArray}));
    }



}
