import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public abstract class Day {

    public ArrayList<String> getResourceAsStringArray(String resource) {
        List<String> lines = Collections.emptyList();
        try { lines = Files.readAllLines(Paths.get(resource), StandardCharsets.UTF_8); }
        catch (IOException e) {
            e.printStackTrace();
        }
        return (ArrayList<String>) lines;
    }

    public ArrayList<Integer> convertStringArrayToIntegerArray(ArrayList<String> myArray) {
        ArrayList<Integer> finalArray = new ArrayList<Integer>();
        for (String s : myArray) {
            int element = Integer.parseInt(s);
            finalArray.add(element);
        }
        return finalArray;
    }

    public String partOneAnswer(String resource) throws FileNotFoundException {
        return "";
    }

    public String partTwoAnswer(String resource) throws FileNotFoundException {
        return "";
    }

}
