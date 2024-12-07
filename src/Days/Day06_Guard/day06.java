package Days.Day06_Guard;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class day06 {

    public static void main(String[] args) {

        String input = "";

        HashMap<String, List<Integer>> directions = new HashMap<>(){
            {
                put("^", List.of(0, -1));
                put(">", List.of(1, 0));
                put("v", List.of(0, 1));
                put("<", List.of(-1, 0));
            }
        };

        try {
            input = Files.readString(Path.of("src/Days/Day06_Guard/input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String[] grid = input.trim().split("\n");

        ArrayList<String> directionKeys = new ArrayList<>(directions.keySet());

        int x = 0, y = 0;
        outerLoop:
        for (y = 0; y < grid.length; y++) {

            String[] line = grid[y].split("");
            for (x = 0; x < line.length; x++) {
                if (directionKeys.contains(line[x])) {
                    break outerLoop;
                }
            }

        }

        // Initialize a set to keep track of visited positions
        Set<String> visited = new HashSet<>();
        visited.add(x + "," + y);

        int currentDirection = directionKeys.indexOf(grid[y].split("")[x]);

        while (true) {
            int dx = directions.get(directionKeys.get(currentDirection)).get(0);
            int dy = directions.get(directionKeys.get(currentDirection)).get(1);
            int nextX = x + dx;
            int nextY = y + dy;

            // Check if the guard has left the grid
            if (nextX < 0 || nextX >= grid[0].length() || nextY < 0 || nextY >= grid.length) {
                break;
            }

            String nextChar = grid[nextY].split("")[nextX];

            if (nextChar.equals("#")) {
                // Obstacle ahead, turn right 90 degrees
                currentDirection = (currentDirection + 1) % 4;
            } else {
                // Move forward
                x = nextX;
                y = nextY;
                visited.add(x + "," + y);
            }
        }

        // Output the number of distinct positions visited
        System.out.println("Positions visited: " + visited.size());

    }

}
