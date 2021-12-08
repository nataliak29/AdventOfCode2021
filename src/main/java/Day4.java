import javafx.util.Pair;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Day4 extends Day{
    static String RESOURCE = "src/main/resources/day4_input.txt";
    static Integer MATRIX_SIZE = 5;

    public Day4() {
        super();
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Part1: " + new Day4().partOneAnswer(RESOURCE));
        System.out.println("Part2: "+ new Day4().partTwoAnswer(RESOURCE));
    }


    public ArrayList<String> getNumbersDrawn (ArrayList<String> inputArrayString) {
        String[] parsedArray = inputArrayString.get(0).split(",");
        ArrayList<String> numberDrawnArray = new ArrayList<String>();
        for (String s: parsedArray){
            numberDrawnArray.add(s);
        }
        return numberDrawnArray;
    }

    public ArrayList<String[][]> getGameBoardMatrix(ArrayList<String> inputArrayString ){
        ArrayList<String[][]> gameBoards = new ArrayList<String[][]>();
        int matrixSize = MATRIX_SIZE;
        for (int i = 1; i < inputArrayString.size(); i=i+1) {
            if (inputArrayString.get(i-1).equals("") & i+matrixSize < inputArrayString.size()+1) {
                String[][] matrix = getOneBoardFromStringArray(new ArrayList<String> (inputArrayString.subList(i,i+matrixSize)));
                gameBoards.add(matrix);
            }

        }
        return gameBoards;
    }

    public String[][] getOneBoardFromStringArray (ArrayList<String> input) {
        int size = input.get(0).trim().split("\\s+").length;
        String[][] board = new String[size][size];
        for (int row=0; row<size; row++){
            String[] boardRow = input.get(row).trim().split("\\s+");
            for (int column = 0; column< boardRow.length; column++){
                board[row][column] = boardRow[column];
            }
        }
        return board;
    }

    public String[][] crossOutMatchingNumberOnBoard(String[][] targetGameBoard, int targetNumber) {
        for (int row = 0; row < targetGameBoard.length; row++) {
            for (int col = 0; col < targetGameBoard.length; col++) {
                int value = Integer.parseInt(targetGameBoard[col][row]);
                if (value == targetNumber) {
                    targetGameBoard[col][row] = "-1";
                }
            }
        }
        return targetGameBoard;
    }

    public boolean checkIfMatrixWon(String[][] targetGameBoard) {
        for (int row = 0; row < targetGameBoard.length; row++) {
            int countOfNumbersLeftInRow = 0;
            for (int col = 0; col < targetGameBoard.length; col++) {
                if (!targetGameBoard[col][row].equals("-1")){
                    countOfNumbersLeftInRow+=1;
                }
            }
            if (countOfNumbersLeftInRow == 0){
                return true;
            }
        }
        for (int col = 0; col < targetGameBoard.length; col++) {
            int countOfNumbersLeftInColumn = 0;
            for (int row = 0; row < targetGameBoard.length; row++) {
                if (!targetGameBoard[col][row].equals("-1")){
                    countOfNumbersLeftInColumn+=1;
                }
            }
            if (countOfNumbersLeftInColumn == 0){
                return true;
            }
        }
        return false;
    }

    public Pair<String[][],Integer> findBingoBoardWinner(ArrayList<String> numbersDrawn, ArrayList<String[][]> gameBoards) {
        int winnerBoard = -1;
        for (int num = 0; num< numbersDrawn.size(); num++){
            int numberDrawn = Integer.parseInt(numbersDrawn.get(num));
            for (int boardIndex = 0; boardIndex < gameBoards.size(); boardIndex++){
                String[][] thisGameBoard = gameBoards.get(boardIndex);
                String[][] processedGameBoard = crossOutMatchingNumberOnBoard(thisGameBoard, numberDrawn);
                if (checkIfMatrixWon(processedGameBoard)){
                    winnerBoard = boardIndex;
                    String[][] winnerGameBoard = gameBoards.get(winnerBoard);
                    return new Pair<>(winnerGameBoard,numberDrawn);
                }
            }
        }
        return null;
    }

    public Pair<String[][],Integer> findLastBingoBoardWinner(ArrayList<String> numbersDrawn, ArrayList<String[][]> gameBoards) {
        int numberOfBoardsWon = 0;
        int totalNumberOfBoards = gameBoards.size();
        for (int num = 0; num< numbersDrawn.size(); num++){
            int numberDrawn = Integer.parseInt(numbersDrawn.get(num));
            for (int boardIndex = gameBoards.size() - 1; boardIndex >= 0; boardIndex--){
                String[][] thisGameBoard = gameBoards.get(boardIndex);
                String[][] processedGameBoard = crossOutMatchingNumberOnBoard(thisGameBoard, numberDrawn);
                if (checkIfMatrixWon(processedGameBoard) && numberOfBoardsWon != totalNumberOfBoards - 1 ){
                    numberOfBoardsWon += 1;
                    gameBoards.remove(boardIndex);
                }
                else {
                    if (checkIfMatrixWon(processedGameBoard) && numberOfBoardsWon == totalNumberOfBoards - 1) {
                        String[][] winnerGameBoard = processedGameBoard;
                        return new Pair<>(winnerGameBoard, numberDrawn);
                    }
                }
            }
        }
        return null;
    }

    public int sumOfNumbersOnTheBoard(String[][] gameBoard) {
        int totalSum = 0;
        for (int row = 0; row < gameBoard.length; row++) {

            for (int column = 0; column < gameBoard.length; column++) {
                if (!gameBoard[row][column].equals("-1")) {
                    totalSum += Integer.parseInt(gameBoard[row][column]);
                }
            }
        }
        return totalSum;
    }

    @Override
    public String partOneAnswer(String resource) throws FileNotFoundException {
        ArrayList<String> inputArrayString =  getResourceAsStringArray(resource);
        ArrayList<String> numbersDrawn = getNumbersDrawn(inputArrayString);
        ArrayList<String[][]> gameBoards= getGameBoardMatrix(inputArrayString);
        Pair<String[][],Integer> winnerAndWinningNumber = findBingoBoardWinner(numbersDrawn, gameBoards);
        int answer =  sumOfNumbersOnTheBoard(winnerAndWinningNumber.getKey()) * winnerAndWinningNumber.getValue() ;
        return String.valueOf(answer);
    }

    @Override
    public String partTwoAnswer(String resource) {
        ArrayList<String> inputArrayString =  getResourceAsStringArray(resource);
        ArrayList<String> numbersDrawn = getNumbersDrawn(inputArrayString);
        ArrayList<String[][]> gameBoards= getGameBoardMatrix(inputArrayString);
        Pair<String[][],Integer> winnerAndWinningNumber = findLastBingoBoardWinner(numbersDrawn, gameBoards);
        int answer =  sumOfNumbersOnTheBoard(winnerAndWinningNumber.getKey()) * winnerAndWinningNumber.getValue() ;
        return String.valueOf(answer);
    }
}






