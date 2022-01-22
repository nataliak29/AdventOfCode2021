import junit.framework.TestCase;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class Day17Test extends TestCase {
    Day17 day17 = new Day17();
    private static final String RESOURCE = "src/test/resources/day17_test.txt";

    public void testPartOneAnswer() throws FileNotFoundException {
        String actualAnswer = day17.partOneAnswer(RESOURCE);
        String expectedAnswer = "45";
        assertEquals(expectedAnswer, actualAnswer);
    }

    public void testPartTwoAnswer() throws FileNotFoundException {
        String actualAnswer = day17.partTwoAnswer(RESOURCE);
        String expectedAnswer = "112";
        assertEquals(expectedAnswer, actualAnswer);
    }

    public void testParseTargetCoordinates(){
        String input = "target area: x=20..30, y=-10..-5";
        HashMap<String,Integer> expectedCoordinates = new HashMap<String,Integer>();
        expectedCoordinates.put("xMin",20);
        expectedCoordinates.put("xMax",30);
        expectedCoordinates.put("yMin",-10);
        expectedCoordinates.put("yMax",-5);
        HashMap<String,Integer> actualCoordinates = day17.parseTargetCoordinates(input);
        assertEquals(expectedCoordinates, actualCoordinates);
    }

    public void testGetHighestTrajectory(){
        HashMap<String,Integer> targetCoordinates = new HashMap<String,Integer>();
        targetCoordinates.put("xMin",20);
        targetCoordinates.put("xMax",30);
        targetCoordinates.put("yMin",-10);
        targetCoordinates.put("yMax",-5);
        Integer actualAnswer = day17.getHighestTrajectory(targetCoordinates);
        Integer expectedAnswer = 45;
        assertEquals(expectedAnswer, actualAnswer);


    }

    public void testLargestYPositionFromTrajectory(){
        HashMap<String,Integer> targetCoordinates = new HashMap<String,Integer>();
        targetCoordinates.put("xMin",20);
        targetCoordinates.put("xMax",30);
        targetCoordinates.put("yMin",-10);
        targetCoordinates.put("yMax",-5);
        Integer actualAnswer = day17.largestYPositionFromTrajectory(targetCoordinates,6,3);
        Integer expectedAnswer = 6;
        assertEquals(expectedAnswer, actualAnswer);
    }

    public void testLargestYPositionFromTrajectory2(){
        HashMap<String,Integer> targetCoordinates = new HashMap<String,Integer>();
        targetCoordinates.put("xMin",20);
        targetCoordinates.put("xMax",30);
        targetCoordinates.put("yMin",-10);
        targetCoordinates.put("yMax",-5);
        Integer actualAnswer = day17.largestYPositionFromTrajectory(targetCoordinates,9,0);
        Integer expectedAnswer = 0;
        assertEquals(expectedAnswer, actualAnswer);
    }

    public void testLargestYPositionFromTrajectory3(){
        HashMap<String,Integer> targetCoordinates = new HashMap<String,Integer>();
        targetCoordinates.put("xMin",20);
        targetCoordinates.put("xMax",30);
        targetCoordinates.put("yMin",-10);
        targetCoordinates.put("yMax",-5);
        Integer actualAnswer = day17.largestYPositionFromTrajectory(targetCoordinates,17,-4);
        Integer expectedAnswer = null;
        assertEquals(expectedAnswer, actualAnswer);
    }
}
