package Days.Day01_Historian;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class day01_p2 {
    public static void main(String[] args) {
        try {
            // Obtain contents of file
            List<String> lines = Files.readAllLines(Paths.get("src/Days/Day01_Historian/input.txt"));

            // Parse contents of file
            List<Integer> firstList = new ArrayList<>();
            HashMap<Integer, Integer> hash = new HashMap<>();
            for (String line : lines) {
                List<Integer> row = new ArrayList<>();
                for (String num : line.split("\\s+")) {
                    row.add(Integer.parseInt(num));
                }
                firstList.add(row.get(0));
                hash.put(row.get(1), hash.getOrDefault(row.get(1), 0) + 1);
            }

            double sum = 0;

            for (int i = 0; i < firstList.size(); i++) {
                if (hash.containsKey(firstList.get(i))) {
                    sum += firstList.get(i) * hash.get(firstList.get(i));
                }
            }

            System.out.println(new BigDecimal(sum).toPlainString());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
