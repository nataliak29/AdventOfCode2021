import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

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
        int answer = -1;
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
            findCorruptedCharacter(line);
            return corruptedCharacter;
        }

        public String findCorruptedCharacter(String line) {
            StringBuffer lineBf = new StringBuffer();
            lineBf.append(line);
            System.out.println("Line " + line);
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
                        System.out.println("Corrupted " + nextString);
                        corruptedCharacter = nextString;
                        return lineBf.toString();
                    }
                }

            }

            if (lineBf.toString().equals(line)) {
                return lineBf.toString();
            } else {
                findCorruptedCharacter(lineBf.toString());
            }
            return "";
        }

    }
}

