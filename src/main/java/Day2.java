import javafx.util.Pair;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2 extends Day {
    static String RESOURCE = "src/main/resources/day2_input.txt";

    public Day2() {
        super();
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Part1: " + new Day2().partOneAnswer(RESOURCE));
        System.out.println("Part2: "+ new Day2().partTwoAnswer(RESOURCE));
    }

    public Pair parseInstruction(String instruction) {
        String direction = instruction.split(" ")[0].trim();
        Integer coordinateChange = Integer.parseInt(instruction.split(" ")[1].trim());
        Pair<String, Integer> instructionParsed = new Pair<String, Integer>(direction, coordinateChange);
        return instructionParsed;
    }

    public List<Integer> moveByInstruction(List<Integer> startCoordinates, String instruction){
        int xCoordinate = startCoordinates.get(0);
        int yCoordinate = startCoordinates.get(1);
        Pair<String, Integer> instructionParsed = parseInstruction(instruction);
        String direction = instructionParsed.getKey();
        Integer coordinateChange = instructionParsed.getValue();
        if (direction.equals("forward")) {
            xCoordinate = xCoordinate + coordinateChange;
        }
        else if (direction.equals("up")) {
            yCoordinate = yCoordinate - coordinateChange;
        }
        else if (direction.equals("down")) {
            yCoordinate = yCoordinate + coordinateChange;
        }
        List<Integer> endCoordinates = new ArrayList<Integer>(Arrays.asList(xCoordinate, yCoordinate));
        return endCoordinates;

    }

    public List<Integer> moveByInstructionWithAim(List<Integer> startCoordinates, String instruction){
        int xCoordinate = startCoordinates.get(0);
        int yCoordinate = startCoordinates.get(1);
        int aimCoordinate = startCoordinates.get(2);
        Pair<String, Integer> instructionParsed = parseInstruction(instruction);
        String direction = instructionParsed.getKey();
        Integer coordinateChange = instructionParsed.getValue();
        if (direction.equals("forward")) {
            xCoordinate = xCoordinate + coordinateChange;
            yCoordinate = yCoordinate + aimCoordinate * coordinateChange;
        }
        else if (direction.equals("up")) {
            aimCoordinate = aimCoordinate - coordinateChange;
        }
        else if (direction.equals("down")) {
            aimCoordinate = aimCoordinate + coordinateChange;
        }
        List<Integer> endCoordinates = new ArrayList<Integer>(Arrays.asList(xCoordinate, yCoordinate, aimCoordinate));
        return endCoordinates;

    }

    public List<Integer> finalCoordinates(ArrayList<String> instructionsArray) {
        int startYCoordinate = 0;
        int startXCoordinate = 0;
        List<Integer> coordinates = new ArrayList<Integer>(Arrays.asList(startXCoordinate, startYCoordinate));
        for (String instr: instructionsArray) {
            coordinates = moveByInstruction(coordinates, instr);
        }
        return coordinates;
    }

    public List<Integer> finalCoordinatesWithAim(ArrayList<String> instructionsArray) {
        int startYCoordinate = 0;
        int startXCoordinate = 0;
        int startAimCoordinate = 0;
        List<Integer> coordinates = new ArrayList<Integer>(Arrays.asList(startXCoordinate, startYCoordinate, startAimCoordinate));
        for (String instr: instructionsArray) {
            coordinates = moveByInstructionWithAim(coordinates, instr);
        }
        return coordinates;
    }

    @Override
    public String partOneAnswer(String resource) throws FileNotFoundException {
        ArrayList<String> array = getResourceAsStringArray(resource);
        List<Integer> coordinates = finalCoordinates(array);
        int answer = coordinates.get(0) * coordinates.get(1);
        return String.valueOf(answer);
    }

    @Override
    public String partTwoAnswer(String resource) {
        ArrayList<String> array = getResourceAsStringArray(resource);
        List<Integer> coordinates = finalCoordinatesWithAim(array);
        int answer = coordinates.get(0) * coordinates.get(1);
        return String.valueOf(answer);
    }


}
