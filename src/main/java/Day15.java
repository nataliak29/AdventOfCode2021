import javafx.util.Pair;

import java.io.FileNotFoundException;
import java.util.*;

public class Day15 extends Day{
    static String RESOURCE = "src/main/resources/day15_input.txt";

    public Day15() {
        super();
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Part1: " + new Day15().partOneAnswer(RESOURCE));
        System.out.println("Part2: " + new Day15().partTwoAnswer(RESOURCE));
    }

    @Override
    public String partOneAnswer(String resource) throws FileNotFoundException {
        ArrayList<String> inputArray = getResourceAsStringArray(resource);
        Grid grid = createGrid(inputArray);
        Pair<Integer,Integer> start = new Pair(0,0);
        grid.dijkstra(start);
        HashMap<Pair<Integer, Integer>, Integer> distanceMap = grid.getDistanceMap();
        Pair<Integer,Integer> end = new Pair(grid.getTotalRows() -1 ,grid.getTotalColumn()-1);
        int answer = distanceMap.get(end);
        return String.valueOf(answer);
    }

    @Override
    public String partTwoAnswer(String resource) {
        ArrayList<String> inputArray = getResourceAsStringArray(resource);
        Grid grid = createGridLarge(inputArray);
        Pair<Integer,Integer> start = new Pair(0,0);
        grid.dijkstra(start);
        HashMap<Pair<Integer, Integer>, Integer> distanceMap = grid.getDistanceMap();
        Pair<Integer,Integer> end = new Pair(grid.getTotalRows() -1 ,grid.getTotalColumn()-1);
        int answer = distanceMap.get(end);
        return String.valueOf(answer);
    }

    public Grid createGrid(ArrayList<String> inputArray) {
        int numberOrRows = inputArray.size();
        int numberOfColumns = inputArray.get(0).length();
        int[][] inputs = new int[numberOrRows][numberOfColumns];
        for (int i = 0; i < numberOrRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                inputs[i][j] = Integer.parseInt(inputArray.get(i).substring(j, j + 1));
            }
        }
        Grid grid = new Grid(inputs);
        return grid;
    }

    public Grid createGridLarge(ArrayList<String> inputArray) {
        int numberOrRows = inputArray.size();
        int numberOfColumns = inputArray.get(0).length();
        int[][] inputs = new int[numberOrRows * 5][numberOfColumns * 5];
        for (int i = 0; i < numberOrRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                inputs[i][j] = Integer.parseInt(inputArray.get(i).substring(j, j + 1));
            }
        }

        for (int i = numberOrRows; i < numberOrRows * 5; i++) {
            for (int j = 0; j < numberOfColumns; j++) {

                int newValue = inputs[i - numberOrRows][j] + 1;
                if (newValue >= 10) {
                    newValue = 1;
                }
                inputs[i][j] = newValue;
            }
        }

        for (int j = numberOfColumns; j < numberOfColumns * 5; j++) {
            for (int i = 0; i < numberOrRows; i++) {
                int newValue = inputs[i][j - numberOfColumns] + 1;
                if (newValue >= 10) {
                    newValue = 1;
                }
                inputs[i][j] = newValue;
            }
        }

        for (int i = numberOrRows; i < numberOrRows * 5; i++) {
            for (int j = numberOrRows; j < numberOfColumns * 5; j++) {
                int newValue = inputs[i - numberOrRows][j] + 1;
                if (newValue >= 10) {
                    newValue = 1;
                }
                inputs[i][j] = newValue;
            }
        }

        Grid grid = new Grid(inputs);
        return grid;
    }

    public HashMap<Pair<Integer,Integer>,Integer> setUp(Grid grid){
        HashMap<Pair<Integer,Integer>,Integer> distanceFromSource = new HashMap<Pair<Integer,Integer>,Integer>();
        for (int i = 0; i < grid.getTotalRows(); i++) {
            for (int j = 0; j < grid.getTotalColumn(); j++) {
                Pair coordinates = new Pair<>(i, j);
                if (i == 0 && j == 0 ){
                    distanceFromSource.put(coordinates,0);
                }
                else {
                    distanceFromSource.put(coordinates,Integer.MAX_VALUE);
                }
            }
        }
        return distanceFromSource;
    }






}
