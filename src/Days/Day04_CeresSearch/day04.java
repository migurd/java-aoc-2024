package Days.Day04_CeresSearch;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class day04 {
    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();

        int[][] directions = new int[][] 
        {
            // Left and right
            {0, 1},
            {0, -1},
            // Up and down
            {1, 0},
            {-1, 0},
            // Diagonals
            {1, 1},
            {1, -1},
            {-1, 1},
            {-1, -1}
        };

        try {
            lines = Files.readAllLines(Paths.get("src/Days/Day04_CeresSearch/input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String goal = "XMAS";

        int m = lines.size();
        int n = lines.get(0).length();
        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (lines.get(i).charAt(j) != 'X') // Avoid combinations that don' start with X
                    continue;

                for (int[] direction : directions) {
                    int x = i;
                    int y = j;
                    int k = 0;

                    while (k < goal.length()) {
                        if (x < 0 || x >= m || y < 0 || y >= n) {
                            break;
                        }
                        if (lines.get(x).charAt(y) != goal.charAt(k)) {
                            break;
                        }
                        x += direction[0];
                        y += direction[1];
                        k++;
                    }

                    if (k == goal.length()) {
                        ans++;
                    }
                }
            }
        }

        System.out.println("Total: " + ans);

    }
}