package Days.Day01_Historian;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class day01 {
    public static void main(String[] args) {
        try {
            // Obtain contents of file
            List<String> lines = Files.readAllLines(Paths.get("src/Days/Day01_Historian/input.txt"));

            // Parse contents of file
            List<Integer> firstList = new ArrayList<>();
            List<Integer> secondList = new ArrayList<>();
            for (String line : lines) {
                List<Integer> row = new ArrayList<>();
                for (String num : line.split("\\s+")) {
                    row.add(Integer.parseInt(num));
                }
                firstList.add(row.get(0));
                secondList.add(row.get(1));
            }

            // Sort 
            firstList.sort(Integer::compareTo);
            secondList.sort(Integer::compareTo);

            double sum = 0;

            for (int i = 0; i < firstList.size(); i++) {
                sum += Math.abs(firstList.get(i) - secondList.get(i));
            }

            System.out.println(sum);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
