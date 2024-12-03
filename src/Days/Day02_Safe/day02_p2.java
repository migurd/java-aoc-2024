package Days.Day02_Safe;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class day02_p2 {
    public static boolean checkSafe(List<Integer> numbers) {
        Boolean isIncreasing = null;
        boolean isSafe = true;

        for (int i = 1; i < numbers.size(); i++) {

            int lastNumber = numbers.get(i - 1);
            int currentNumber = numbers.get(i);
            int diff = currentNumber - lastNumber;

            if (isIncreasing == null)
                isIncreasing = diff > 0;

            if (isIncreasing && (diff <= 0 || diff > 3)) {
                isSafe = false;
                break;
            }

            if (!isIncreasing && (diff >= 0 || Math.abs(diff) > 3)) {
                isSafe = false;
                break;
            }
        }
        return isSafe;
    }

    public static boolean checkSafeWithDampener(List<Integer> numbers) {
        if (checkSafe(numbers)) {
            return true;
        }

        for (int i = 0; i < numbers.size(); i++) {
            List<Integer> temp = new ArrayList<>(numbers);
            temp.remove(i);
            if (checkSafe(temp)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        try {
            // Obtain contents of file
            List<String> lines = Files.readAllLines(Paths.get("src/Days/Day02_Safe/input.txt"));

            // Answer
            int answer = 0;

            // Get every number of each line, each line has n numbers
            for (String line : lines) {
                List<Integer> numbers = new ArrayList<Integer>();

                for (String number : line.split("\\s+")) {
                    numbers.add(Integer.parseInt(number));
                }

                if (checkSafeWithDampener(numbers)) {
                    answer++;
                }

            } // END FOR BLOCK

            System.out.println(answer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
