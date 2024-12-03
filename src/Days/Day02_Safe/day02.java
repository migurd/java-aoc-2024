package Days.Day02_Safe;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class day02 {
    public static void main(String args[]) {
        try {

            // Obtain contents of file
            List<String> lines = Files.readAllLines(Paths.get("src/Days/Day02_Safe/input.txt"));

            // Answer
            int answer = 0;

            // Get every number of each line, each line has n numbers
            for (String line : lines) {

                String[] numbers = line.split("\\s+");

                Boolean isIncreasing = null;
                int isSafe = 1;

                for (int i = 1; i < numbers.length; i++) {
                
                    int lastNumber = Integer.parseInt(numbers[i - 1]);
                    int currentNumber = Integer.parseInt(numbers[i]);
                    int diff = currentNumber - lastNumber;

                    if (isIncreasing == null)
                        isIncreasing = diff > 0;

                    if (isIncreasing && (diff <= 0 || diff > 3)) 
                    {
                        isSafe = 0;
                        break;
                    }

                    if (!isIncreasing && (diff >= 0 || Math.abs(diff) > 3)) 
                    {
                        isSafe = 0;
                        break;
                    }
                }

                answer += isSafe;

            }

            System.out.println(answer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}