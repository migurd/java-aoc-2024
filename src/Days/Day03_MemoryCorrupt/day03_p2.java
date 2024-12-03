package Days.Day03_MemoryCorrupt;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day03_p2 {
    public static void main(String[] args) {
        String input = "";

        // Read the input file
        try {
            input = Files.readString(Paths.get("src/Days/Day03_MemoryCorrupt/input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        Pattern pattern = Pattern.compile("do\\(\\)|don't\\(\\)|mul\\((\\d{1,3}),(\\d{1,3})\\)");
        Matcher matcher = pattern.matcher(input);

        boolean isMultiplicationEnabled = true;
        long totalSum = 0;

        while (matcher.find()) {

            String match = matcher.group();

            if (match.equals("do()")) {
                isMultiplicationEnabled = true;
            } else if (match.equals("don't()")) {
                isMultiplicationEnabled = false;
            } else if (matcher.group(1) != null && matcher.group(2) != null) {
                int num1 = Integer.parseInt(matcher.group(1));
                int num2 = Integer.parseInt(matcher.group(2));

                if (isMultiplicationEnabled) {
                    totalSum += (long) num1 * num2;
                }
            }

        }

        System.out.println("Total Sum: " + totalSum);
    }
}