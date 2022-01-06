import junit.framework.TestCase;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Day14Test extends TestCase {
    Day14 day14 = new Day14();
    private static final String RESOURCE = "src/test/resources/day14_test.txt";

    public void testPartOneAnswer() throws FileNotFoundException {
        String actualAnswer = day14.partOneAnswer(RESOURCE);
        String expectedAnswer = "1588";
        assertEquals(expectedAnswer, actualAnswer);
    }

    public void testPartTwoAnswer() throws FileNotFoundException {
        String actualAnswer = day14.partTwoAnswer(RESOURCE);
        String expectedAnswer = "2188189693529";
        assertEquals(expectedAnswer, actualAnswer);
    }

    public void testLoopOverPairs(){
        HashMap <String,String> instructions = new HashMap<>();
        instructions.put("NN","C");
        instructions.put("NC","B");
        instructions.put("CB","H");
        String polymer = "NNCB";
        String expected = "NCNBCHB";
        String actual = day14.processStep(polymer,instructions);
        assertEquals(expected,actual);
    }

    public void testGetInstructionsHashMap() {
        ArrayList<String> inputArrayString = new ArrayList<String>(
                Arrays.asList("KO -> H", "CH -> B"));
        HashMap <String,String> actualMap = day14.getInstructionsHashMap(inputArrayString);
        HashMap <String,String> expectedMap = new HashMap<>();
        expectedMap.put("KO","H");
        expectedMap.put("CH","B");
        assertEquals(expectedMap,actualMap);
    }

}
