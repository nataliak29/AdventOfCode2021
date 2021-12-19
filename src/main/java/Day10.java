import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Day10 extends Day {

    static String RESOURCE = "src/main/resources/day10_input.txt";

    public Day10() {
        super();
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Part1: " + new Day10().partOneAnswer(RESOURCE));
        System.out.println("Part2: " + new Day10().partTwoAnswer(RESOURCE));
    }

    @Override
    public String partOneAnswer(String resource) throws FileNotFoundException {
        ArrayList<String> inputArray = getResourceAsStringArray(resource);
        int answer = totalPointsFromCorruptedCharacters(inputArray);
        return String.valueOf(answer);
    }

    @Override
    public String partTwoAnswer(String resource) {
        ArrayList<String> inputArray = getResourceAsStringArray(resource);
        ArrayList<Long> points = pointsFromMissingCharacters(inputArray);
        Collections.sort(points);
        Long answer = points.get(points.size()/2);
        return String.valueOf(answer);
    }

    public int totalPointsFromCorruptedCharacters(ArrayList<String> inputArray) {
        Integer totalPoints = 0;
        for (String line : inputArray) {
            Day10.NavigationSubsystemLine navigationSubsystemLine = new Day10.NavigationSubsystemLine(line);
            String corruptedCharacter = navigationSubsystemLine.getCorruptedCharacter();
            if (corruptedCharacter != null){
                Integer actualPoints = navigationSubsystemLine.getPoints(corruptedCharacter);
                totalPoints += actualPoints;
            }
        }
        return totalPoints;
    }

    public ArrayList<Long> pointsFromMissingCharacters(ArrayList<String> inputArray) {
        ArrayList<Long> points = new ArrayList<Long>();
        for (String line : inputArray) {
            Day10.NavigationSubsystemLine navigationSubsystemLine = new Day10.NavigationSubsystemLine(line);
            String lineToAutoComplete = navigationSubsystemLine.getLineToAutoComplete();
            long score = navigationSubsystemLine.autoCompleteScore(lineToAutoComplete);
            if (score != 0){
                points.add(score);
            }
        }
        return points;
    }

    static class NavigationSubsystemLine {
        ArrayList<String> leftHandSide = new ArrayList<String>(Arrays.asList("{", "(", "[", "<"));
        ArrayList<String> rightHandSide = new ArrayList<String>(Arrays.asList("}", ")", "]", ">"));
        ArrayList<String> legalExpressions = new ArrayList<String>(Arrays.asList("{}", "()", "[]", "<>"));
        String line;
        String corruptedCharacter;

        public NavigationSubsystemLine(String line) {
            this.line = line;
        }

        public int getPoints(String corruptedCharacter) {
            if (corruptedCharacter.equals(")")) {
                return 3;
            }
            if (corruptedCharacter.equals("]")) {
                return 57;
            }
            if (corruptedCharacter.equals("}")) {
                return 1197;
            }
            if (corruptedCharacter.equals(">")) {
                return 25137;
            }
            return -1;
        }

        public String getCorruptedCharacter() {
            findCorruptedAndMissingCharacters(line);
            return corruptedCharacter;
        }

        public String getLineToAutoComplete() {
            String result = findCorruptedAndMissingCharacters(line);
            if (corruptedCharacter == null) {
                return result;
            }
            return "";
        }

        public String findCorruptedAndMissingCharacters(String line) {
            StringBuffer lineBf = new StringBuffer();
            lineBf.append(line);
            for (int i = 0; i <= lineBf.length() - 2; i++) {
                String nextString = String.valueOf(lineBf.charAt(i + 1));
                String thisString = String.valueOf(lineBf.charAt(i));
                if (leftHandSide.contains(thisString) & rightHandSide.contains(nextString)) {
                    StringBuffer expression = new StringBuffer();
                    expression.append(thisString);
                    expression.append(nextString);
                    if (legalExpressions.contains(expression.toString())) {
                        lineBf.replace(i, i + 2, "");
                    } else {
                        corruptedCharacter = nextString;
                        return lineBf.toString();
                    }
                }
            }
            if (lineBf.toString().equals(line)) {
                return lineBf.toString();
            } else {
                return findCorruptedAndMissingCharacters(lineBf.toString());
            }
        }

        public int missingCharacterScore(String character){
            if (character.equals(")")) {
                return 1;
            }
            if (character.equals("]")){
                return 2;
            }
            if (character.equals("}")){
                return 3;
            }
            if (character.equals(">")){
                return 4;
            }
            return 0;
        }

        public long autoCompleteScore(String line){
            String[] lineArray = line.split("");
            long finalScore = 0;
            for (int i = line.length() - 1; i >= 0; i--){
                String s = lineArray[i];
                int index = leftHandSide.indexOf(s);
                String missingCharacter = rightHandSide.get(index);
                long score = missingCharacterScore(missingCharacter);
                finalScore = finalScore * 5 + score;

            }
            return finalScore;

        }

    }
}

