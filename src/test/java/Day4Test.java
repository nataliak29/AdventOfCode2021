import javafx.util.Pair;
import junit.framework.TestCase;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;


public class Day4Test extends TestCase {
    private static final String RESOURCE = "src/test/resources/day4_test.txt";
    Day4 day4 = new Day4();

    public void testGetNumbersDrawn() {
        ArrayList<String> inputArrayString = new ArrayList<String>(
                Arrays.asList("2,4","","1 2 3", "4 5 6", "7 8 9", "",
                        "11 12 13", "14 15 16", "17 18 19", ""));
        ArrayList<String> expectedList = new ArrayList<String>(Arrays.asList("2", "4"));
        assertEquals(day4.getNumbersDrawn(inputArrayString), expectedList);
    }

    public void testGetGameBoardMatrix() {
        ArrayList<String> inputArrayString = new ArrayList<String>(
                Arrays.asList("2 4","","1 2 3", "4 5 6", "7 8 9", "",
                        "11 12 13", "14 15 16", "17 18 19", ""));
        ArrayList<String[][]> actualGameBoards = day4.getGameBoardMatrix(inputArrayString);
        String[][] matrix1 = {{"1","2","3"},{"4","5","6"},{"7","8","9"}};
        String[][] matrix2 = {{"11","12","13"},{"14","15","16"},{"17","18","19"}};
        ArrayList<String[][]> expectedGameBoards = new ArrayList<String[][]>();
        expectedGameBoards.add(matrix1);
        expectedGameBoards.add(matrix2);
        for (int i =0; i<actualGameBoards.size(); i++){
            String[][] expectedMatrix = expectedGameBoards.get(i);
            String[][] actualMatrix = actualGameBoards.get(i);
            assertTrue(Arrays.deepEquals(actualMatrix,
                    expectedMatrix));
        }
    }

    public void testGetOneBoardFromStringArray() {
        ArrayList<String> inputArray = new ArrayList<String>(Arrays.asList("1 2 3","4   5 6", " 7 8 9"));
        String[][] actualMatrix = day4.getOneBoardFromStringArray(inputArray);
        String[][] expectedMatrix = {{"1","2","3"},{"4","5","6"},{"7","8","9"}};
        assertTrue(Arrays.deepEquals(actualMatrix,
                expectedMatrix));
    }

    public void testCrossOutMatchingNumberOnBoard() {
        String[][] inputMatrix = {{"1","2","3"},{"4","5","3"},{"7","8","9"}};
        String[][] actualMatrix = day4.crossOutMatchingNumberOnBoard(inputMatrix, 3);
        String[][] expectedMatrix = {{"1","2","-1"},{"4","5","-1"},{"7","8","9"}};
        assertTrue(Arrays.deepEquals(actualMatrix,
                expectedMatrix));
    }

    public void testCheckIfMatrixWonFalse() {
        String[][] inputMatrix = {{"1","2","3"},{"4","5","3"},{"7","8","9"}};
        boolean checkIfMatrixWon = day4.checkIfMatrixWon(inputMatrix);
        assertEquals(checkIfMatrixWon,false);
    }

    public void testCheckIfMatrixWonRowTrue() {
        String[][] inputMatrix = {{"-1","-1","-1"},{"4","5","3"},{"7","8","9"}};
        boolean checkIfMatrixWon = day4.checkIfMatrixWon(inputMatrix);
        assertEquals(checkIfMatrixWon,true);
    }

    public void testCheckIfMatrixWonColTrue() {
        String[][] inputMatrix = {{"-1","2","3"},{"-1","5","3"},{"-1","8","9"}};
        boolean checkIfMatrixWon = day4.checkIfMatrixWon(inputMatrix);
        assertEquals(checkIfMatrixWon,true);
    }

    public void testCheckIfMatrixWonSecondColTrue() {
        String[][] inputMatrix = {{"1","-1","3"},{"-1","-1","3"},{"-1","-1","9"}};
        boolean checkIfMatrixWon = day4.checkIfMatrixWon(inputMatrix);
        assertEquals(checkIfMatrixWon,true);
    }

    public void testCheckIfMatrixWonSecondColFalse() {
        String[][] inputMatrix = {{"1","-1","3"},{"-1","3","3"},{"-1","-1","9"}};
        boolean checkIfMatrixWon = day4.checkIfMatrixWon(inputMatrix);
        assertEquals(checkIfMatrixWon,false);
    }

    public void testFindBingoBoardWinner() {
        String[][] matrix1 = {{"1","2","3"},{"4","5","6"},{"7","8","9"}};
        String[][] matrix2 = {{"11","12","13"},{"14","15","16"},{"17","18","19"}};
        String[][] winningStateOfMatrix = {{"11","-1","13"},{"14","-1","16"},{"17","-1","19"}};
        ArrayList<String[][]> gameBoards = new ArrayList<String[][]>();
        gameBoards.add(matrix1);
        gameBoards.add(matrix2);
        ArrayList<String> numbersDrawn = new ArrayList<String>(Arrays.asList("2", "5","12","15","18","9"));
        Pair<String[][],Integer> expectedWinnerAndWinningNumber = new Pair<>(winningStateOfMatrix,18);
        Pair<String[][],Integer> actualWinnerAndWinningNumber = day4.findBingoBoardWinner(numbersDrawn,gameBoards);

        assertTrue(Arrays.deepEquals(expectedWinnerAndWinningNumber.getKey(), actualWinnerAndWinningNumber.getKey()));
        assertEquals(expectedWinnerAndWinningNumber.getValue(),actualWinnerAndWinningNumber.getValue());

    }

    public void testSumOfNumbersOnTheBoard(){
        String[][] gameBoard = {{"1","-1","1"},{"-1","-1","-1"},{"2","0","1"}};
        int actualSum = day4.sumOfNumbersOnTheBoard(gameBoard);
        int expectedSum = 5;
        assertEquals(expectedSum,actualSum);
    }

    public void testPartOneAnswer() throws FileNotFoundException {
       String actualAnswer = day4.partOneAnswer(RESOURCE);
       String expectedAnswer = "4512";
       assertEquals(expectedAnswer,actualAnswer);
    }
}