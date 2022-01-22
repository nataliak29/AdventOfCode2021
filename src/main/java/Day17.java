import javafx.util.Pair;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day17 extends Day{
    static String RESOURCE = "src/main/resources/day17_input.txt";

    public Day17() {
        super();
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Part1: " + new Day17().partOneAnswer(RESOURCE));
        System.out.println("Part2: " + new Day17().partTwoAnswer(RESOURCE));
    }

    @Override
    public String partOneAnswer(String resource) throws FileNotFoundException {
        ArrayList<String> inputArray = getResourceAsStringArray(resource);
        HashMap<String,Integer> targetCoordinates = parseTargetCoordinates(inputArray.get(0));
        int answer = getHighestTrajectory(targetCoordinates);;
        return String.valueOf(answer);
    }

    @Override
    public String partTwoAnswer(String resource) {
        ArrayList<String> inputArray = getResourceAsStringArray(resource);
        HashMap<String,Integer> targetCoordinates = parseTargetCoordinates(inputArray.get(0));
        int answer = numberOfVelocityValuesHittingTarget(targetCoordinates);
        return String.valueOf(answer);
    }

    public HashMap<String,Integer> parseTargetCoordinates(String input){
        HashMap<String,Integer> targetCoordinates = new HashMap<String,Integer>();
        input = input.replaceAll("[^-?0-9]+", " ");
        List<String> coordinates = Arrays.asList(input.trim().split(" "));
        targetCoordinates.put("xMin", Integer.valueOf(coordinates.get(0)));
        targetCoordinates.put("xMax", Integer.valueOf(coordinates.get(1)));
        targetCoordinates.put("yMin", Integer.valueOf(coordinates.get(2)));
        targetCoordinates.put("yMax", Integer.valueOf(coordinates.get(3)));
        return targetCoordinates;
    }

    public Integer getHighestTrajectory(HashMap<String,Integer> targetCoordinates){
        Integer highestTrajectory = -Integer.MAX_VALUE;

        for (Integer xVelocity = 0; xVelocity <= 1000; xVelocity++){
            for (Integer yVelocity = -500; yVelocity <= 1000; yVelocity++){
                Integer largestYPos = -Integer.MAX_VALUE;
                try {
                largestYPos = largestYPositionFromTrajectory(targetCoordinates,
                        xVelocity,yVelocity);

                    if (largestYPos >= highestTrajectory){
                        highestTrajectory = largestYPos;
                    }}
                catch (NullPointerException e){
                    //System.out.println("Out of range");
                }


            }
        }

        return highestTrajectory;
    }


    public Integer numberOfVelocityValuesHittingTarget(HashMap<String,Integer> targetCoordinates){
        List<Pair<Integer,Integer>> velocitiesHittingTarget = new ArrayList<Pair<Integer,Integer>>();

        for (Integer xVelocity = 0; xVelocity <= 500; xVelocity++){
            for (Integer yVelocity = -500; yVelocity <= 500; yVelocity++){
                    if ( largestYPositionFromTrajectory(targetCoordinates,
                            xVelocity,yVelocity) != null){
                        Pair<Integer,Integer> velocities = new Pair<>(xVelocity,yVelocity);
                        velocitiesHittingTarget.add(velocities);
                    }
            }
        }

        return velocitiesHittingTarget.size();
    }

    public Integer largestYPositionFromTrajectory(HashMap<String,Integer> targetCoordinates,
                                                  Integer xVelocity, Integer yVelocity){
        Integer xMin = targetCoordinates.get("xMin");
        Integer xMax = targetCoordinates.get("xMax");
        Integer yMax = targetCoordinates.get("yMax");
        Integer yMin = targetCoordinates.get("yMin");
        Integer largestYPosition = 0;

        Integer steps = 0;
        Integer xPos = xIncrement(0, xVelocity);
        Integer yPos = yIncrement(0, yVelocity);
        while (true){
            //System.out.println("x "+xPos + " y "+ yPos);

            if ( isTargetHit (xPos, yPos, xMin, xMax, yMin, yMax)){
                //System.out.println("target hit");
                break;
            }
            if (isOutOfRange(xPos, yPos, xMax, yMin)){
                //System.out.println("out of range");
                return null;
            }
                xVelocity = xVelocityChange(xVelocity);
                yVelocity = yVelocityChange(yVelocity);
                xPos = xIncrement(xPos, xVelocity);
                yPos = yIncrement(yPos, yVelocity);
            steps += 1;
            if (largestYPosition < yPos) {
                largestYPosition = yPos;
            }


        }
        return largestYPosition;

    }
    public boolean isTargetHit (Integer xPos, Integer yPos,
                                Integer xMin, Integer xMax,
                                Integer yMin, Integer yMax){
       if ( xPos >= xMin & xPos <= xMax & yPos >= yMin & yPos <= yMax) {
           return true;
       }
       else {
           return false;
       }
    }

    public boolean isOutOfRange(Integer xPos, Integer yPos , Integer xMax , Integer yMin){
        if (xPos > xMax || yPos < yMin){
            return true;
        }
        else {
            return false;
        }
    }

    public Integer xVelocityChange(Integer xVelocity){
        if ( xVelocity == 0){
            return 0;
        }
        if ( xVelocity > 0){
            return xVelocity - 1;
        }
        else {
            return xVelocity + 1;
        }
    }

    public Integer yVelocityChange(Integer yVelocity){
        return yVelocity - 1;
    }

    public Integer xIncrement(Integer xPos, Integer xVelocity){
        return xPos + xVelocity;
    }

    public Integer yIncrement(Integer yPos,Integer yVelocity){
        return yPos + yVelocity;
    }
}
