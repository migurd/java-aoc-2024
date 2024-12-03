package Days.Day03_MemoryCorrupt;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day03 {
    public static void main(String args[]) {

        String input = "";

        try {
            input = Files.readString(Paths.get("src/Days/Day03_MemoryCorrupt/input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
        Matcher matcher = pattern.matcher(input);

        int totalSum = 0;
        while (matcher.find()) {
            int num1 = Integer.parseInt(matcher.group(1));
            int num2 = Integer.parseInt(matcher.group(2));
            System.out.println(num1 + " * " + num2 + " = " + num1 * num2);
            totalSum += num1 * num2;
        }

        System.out.println(totalSum);

    }
}
