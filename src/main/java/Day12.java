import java.io.FileNotFoundException;
import java.util.*;

public class Day12 extends Day {

    static String RESOURCE = "src/main/resources/day12_input.txt";

    public Day12() {
        super();
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Part1: " + new Day12().partOneAnswer(RESOURCE));
        System.out.println("Part2: " + new Day12().partTwoAnswer(RESOURCE));
    }

    @Override
    public String partOneAnswer(String resource) throws FileNotFoundException {
        ArrayList<String> inputArray = getResourceAsStringArray(resource);
        Day12.CaveMap myCaveMap = new Day12.CaveMap(inputArray);
        int answer = deepSearch(myCaveMap.caveMap.get("start"), new HashSet<>());
        return String.valueOf(answer);
    }

    @Override
    public String partTwoAnswer(String resource) {
        ArrayList<String> inputArray = getResourceAsStringArray(resource);
        Day12.CaveMap myCaveMap = new Day12.CaveMap(inputArray);
        int answer = deepSearchPart2(myCaveMap.caveMap.get("start"), new HashSet<>(), false);
        return String.valueOf(answer);
    }

    /**https://github.com/Sycix-HK/Advent-of-Code-2021/blob/main/Java/submarine/equipment/sonar/caves/PathFinder.java**/
    static class CaveClass {

        public final List<CaveClass> connectedCaves = new ArrayList<>();
        public final String size;
        private final String name;

        public CaveClass(String name) {
            this.name = name;
            if (Character.isUpperCase(name.charAt(0))){
                size = "Large";
            }
            else {
                size = "Small";
            }
        }

        public String getName() {
            return name;
        }

        public String getSize() {
            return size;
        }

        public static void connectCaves(CaveClass first, CaveClass second) {
            if (!first.connectedCaves.contains(second)) {
                first.connectedCaves.add(second);
                second.connectedCaves.add(first);
            }
        }
    }

    static class CaveMap {

        public final Map<String, CaveClass> caveMap = new HashMap<>();

        public CaveMap(ArrayList<String> inputArray) {
            for (String line : inputArray ) {
                String[] lineArray = line.split("-");
                String firstCave = lineArray[0];
                String secondCave = lineArray[1];
                if (!caveMap.containsKey(firstCave)) {
                    caveMap.put(firstCave, new CaveClass(firstCave));
                }
                if (!caveMap.containsKey(secondCave)) {
                    caveMap.put(secondCave, new CaveClass(secondCave));
                }
                CaveClass.connectCaves(caveMap.get(firstCave), caveMap.get(secondCave));
            }
        }

    }

    static int deepSearch(CaveClass cave, HashSet<String> path) {
        if (cave.getName().equals("end")) {
            return 1;
        }
        HashSet<String> thisPath = (HashSet<String>) path.clone();
        thisPath.add(cave.getName());
        int count = 0;
        for (CaveClass thisCave : cave.connectedCaves) {
            if ( thisCave.getSize().equals("Large") || !path.contains(thisCave.getName()) ) {
                count += deepSearch(thisCave, thisPath);
            }
        }
        return count;
    }


    static int deepSearchPart2(CaveClass cave, HashSet<String> path, boolean smallCaveVisitedTwice) {
        HashSet<String> thisPath = (HashSet<String>) path.clone();
        Boolean thisSmallCaveVisitedTwice = smallCaveVisitedTwice;
        thisPath.add(cave.getName());

        if (cave.getSize().equals("Small") && path.contains(cave.getName()) &&
                smallCaveVisitedTwice == false) {
            smallCaveVisitedTwice = true;
        }

        if (cave.getName().equals("end")) {
            return 1;
        }
        int count = 0;
        for (CaveClass thisCave : cave.connectedCaves) {
            if ( !thisCave.getName().equals("start")
                    &&  thisCave.getSize().equals("Small") && !path.contains(thisCave.getName())) {
                count += deepSearchPart2(thisCave, thisPath,  thisSmallCaveVisitedTwice );
            }
            if ( !thisCave.getName().equals("start")
                    &&  thisCave.getSize().equals("Small") && path.contains(thisCave.getName()) &&
                    smallCaveVisitedTwice == false) {
                count += deepSearchPart2(thisCave, thisPath, true );
            }
            if ( !thisCave.getName().equals("start")
                    &&   thisCave.getSize().equals("Large") ) {
                count += deepSearchPart2(thisCave, thisPath, smallCaveVisitedTwice);
            }
        }
        return count;
    }
}




