package Days.Day04_CeresSearch;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class day04_p2 {

    public static void main(String args[]) {
        List<String> lines = new ArrayList<>();

        // Directions stores the positions of the values of the cross values
        int[][] diagonalDirections = new int[][] {
            {-1, -1},
            {1, 1},
            {1, -1},
            {-1, 1}
        };

        try {
            lines = Files.readAllLines(Paths.get("src/Days/Day04_CeresSearch/input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        int m = lines.size();
        int n = lines.get(0).length();
        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (lines.get(i).charAt(j) != 'A') {
                    continue;
                }

                char[] diagonalChars = new char[4];
                int count = 0;

                // Collect characters from all four diagonals
                for (int k = 0; k < 4; k++) {
                    int x = i + diagonalDirections[k][0];
                    int y = j + diagonalDirections[k][1];

                    if (x < 0 || x >= m || y < 0 || y >= n) {
                        break;
                    }

                    diagonalChars[k] = lines.get(x).charAt(y);
                    count++;
                }

                if (count < 4) {
                    continue;
                }

                // Check if diagonals form 'M' and 'S' in any order
                String diag1 = "" + diagonalChars[0] + diagonalChars[1];
                String diag2 = "" + diagonalChars[2] + diagonalChars[3];

                if ((diag1.contains("M") && diag1.contains("S") && diag2.contains("M") && diag2.contains("S"))
                    || (diag1.contains("M") && diag1.contains("S") && diag2.contains("S") && diag2.contains("M"))) {
                    ans++;
                }
            }
        }

        System.out.println("Total: " + ans);

    }

}
