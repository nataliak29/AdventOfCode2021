import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public abstract class Day {

    public static ArrayList<String> getResourceAsStringArray(String resource) {
        List<String> lines = Collections.emptyList();
        try { lines = Files.readAllLines(Paths.get(resource), StandardCharsets.UTF_8); }
        catch (IOException e) {
            e.printStackTrace();
        }
        return (ArrayList<String>) lines;
    }

    public int[] getIntegersList(ArrayList<String> inputArray){
        ArrayList<String> finalArray  = new ArrayList<>(Arrays.asList(inputArray.get(0).split(",")));
        int[] integerList = new int[finalArray.size()];
        for (Integer i = 0; i < finalArray.size(); i ++) {
            integerList[i] = Integer.parseInt(finalArray.get(i));
        }
        return  integerList;
    }

    public ArrayList<Integer> convertStringArrayToIntegerArray(ArrayList<String> myArray) {
        ArrayList<Integer> finalArray = new ArrayList<Integer>();
        for (String s : myArray) {
            int element = Integer.parseInt(s);
            finalArray.add(element);
        }
        return finalArray;
    }

    public ArrayList<String[]> splitLinesIntoArray(ArrayList<String> array) {
        ArrayList<String[]> finalArray = new ArrayList<String[]>();
        for (String s : array) {
            String[] arraySplit = s.split("");
            finalArray.add(arraySplit);
        }
        return finalArray;
    }

    public int fileFirstLineLength(String resource){
        File f = new File(resource);
        String firstLine = "";
        try {
            Scanner inFile = new Scanner(f);
            firstLine = inFile.nextLine();
            inFile.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] data = firstLine.split("");
        return data.length;
    }

    public int fileLength(String resource) {
        Path path = Paths.get(resource);
        long lines = 0;
        try {
            lines = Files.lines(path).count();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return (int) lines;
    }

    public String partOneAnswer(String resource) throws IOException {
        return "";
    }

    public String partTwoAnswer(String resource) throws IOException {
        return "";
    }

}
