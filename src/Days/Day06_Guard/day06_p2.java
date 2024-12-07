package Days.Day06_Guard;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class day06_p2 {
    enum Direction {
        UP, RIGHT, DOWN, LEFT
    }

    static class Coordinate {
        int x, y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Coordinate move(Direction d) {
            return switch (d) {
                case UP -> new Coordinate(x, y - 1);
                case RIGHT -> new Coordinate(x + 1, y);
                case DOWN -> new Coordinate(x, y + 1);
                case LEFT -> new Coordinate(x - 1, y);
            };
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static Direction turnRight(Direction d) {
        return switch (d) {
            case UP -> Direction.RIGHT;
            case RIGHT -> Direction.DOWN;
            case DOWN -> Direction.LEFT;
            case LEFT -> Direction.UP;
        };
    }

    static Coordinate findGuardPosition(List<String> lines) {
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == '^') {
                    return new Coordinate(x, y);
                }
            }
        }
        return null;
    }

    static Set<Coordinate> findObstacles(List<String> lines) {
        Set<Coordinate> obstacles = new HashSet<>();
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == '#') {
                    obstacles.add(new Coordinate(x, y));
                }
            }
        }
        return obstacles;
    }

    static boolean isInBounds(Coordinate pos, List<String> lines) {
        return pos.x >= 0 && pos.y >= 0 && pos.y < lines.size() && pos.x < lines.get(pos.y).length();
    }

    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(Path.of("src/Days/Day06_Guard/input.txt"));
        
        Coordinate guardPos = findGuardPosition(lines);
        List<Map.Entry<Coordinate, Direction>> positionList = new ArrayList<>();
        positionList.add(Map.entry(guardPos, Direction.UP));
        Direction guardDir = Direction.UP;
        Set<Coordinate> obstacles = findObstacles(lines);

        // Track initial path
        while (isInBounds(guardPos, lines)) {
            Coordinate nextPos = guardPos.move(guardDir);
            if (obstacles.contains(nextPos)) {
                guardDir = turnRight(guardDir);
            } else {
                guardPos = nextPos;
                if (isInBounds(guardPos, lines)) {
                    positionList.add(Map.entry(guardPos, guardDir));
                }
            }
        }

        // Find potential obstacle positions
        List<Coordinate> potentialObstacles = positionList.stream()
            .filter(e -> !e.getKey().equals(findGuardPosition(lines)))
            .map(Map.Entry::getKey)
            .distinct()
            .toList();

        // Check each position for loop creation
        int posCount = 0;
        for (Coordinate pos : potentialObstacles) {
            List<Map.Entry<Coordinate, Direction>> trace = new ArrayList<>();
            for (Map.Entry<Coordinate, Direction> entry : positionList) {
                if (entry.getKey().equals(pos)) break;
                trace.add(entry);
            }
            guardDir = trace.get(trace.size() - 1).getValue();

            while (isInBounds(trace.get(trace.size() - 1).getKey(), lines)) {
                Coordinate nextPos = trace.get(trace.size() - 1).getKey().move(guardDir);
                if (obstacles.contains(nextPos) || nextPos.equals(pos)) {
                    guardDir = turnRight(guardDir);
                } else {
                    Map.Entry<Coordinate, Direction> newEntry = Map.entry(nextPos, guardDir);
                    if (trace.contains(newEntry)) {
                        posCount++;
                        break;
                    } else {
                        trace.add(newEntry);
                    }
                }
            }
        }
        System.out.println("Result: " + posCount);
    }
}