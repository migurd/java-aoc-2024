package Days.Day05_Queue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class day05_p2 {
    public static void main(String[] args) throws IOException {
        String input = "";
        int ans = 0;

        try {
            input = Files.readString(Path.of("src/Days/Day05_Queue/input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String[] lines = input.trim().split("\n");

        HashMap<Integer, List<Integer>> rulesHash = new HashMap<>();

        List<List<Integer>> updates = new ArrayList<>();

        // Find the separator (blank line)
        int separatorIndex = -1;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].trim().isEmpty()) {
                separatorIndex = i;
                break;
            }
        }

        // Parse the rules
        for (int i = 0; i < separatorIndex; i++) {
            
            String[] parts = lines[i].split("\\|");

            int x = Integer.parseInt(parts[0].trim());
            int y = Integer.parseInt(parts[1].trim());

            if (!rulesHash.containsKey(y)) {
                rulesHash.put(y, new ArrayList<>());
            }

            rulesHash.get(y).add(x);

        }

        // Parse the updates
        for (int i = separatorIndex + 1; i < lines.length; i++) {

            String[] parts = lines[i].split(",");

            List<Integer> updateList = new ArrayList<>();

            for (String part : parts) {
                updateList.add(Integer.parseInt(part.trim()));
            }

            updates.add(updateList);

        }

        for (List<Integer> update : updates) {

            if (!isValidOrder(update, rulesHash)) {
                List<Integer> sortedUpdate = topologicalSort(update, rulesHash);
                ans += sortedUpdate.get(sortedUpdate.size() / 2);
            }

        }

        System.out.println("Answer: " + ans);
    }

    private static boolean isValidOrder(List<Integer> update, Map<Integer, List<Integer>> rulesHash) {
        Map<Integer, Integer> pageIndices = new HashMap<>();
        for (int i = 0; i < update.size(); i++) {
            pageIndices.put(update.get(i), i);
        }

        for (Integer y : rulesHash.keySet()) {
            if (pageIndices.containsKey(y)) {
                for (Integer x : rulesHash.get(y)) {
                    if (pageIndices.containsKey(x) && pageIndices.get(x) >= pageIndices.get(y)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static List<Integer> topologicalSort(List<Integer> update, Map<Integer, List<Integer>> rulesHash) {
        // Build adjacency list
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();

        for (int page : update) {
            graph.put(page, new ArrayList<>());
            inDegree.put(page, 0);
        }

        // Add edges based on rules
        for (int page : update) {
            if (rulesHash.containsKey(page)) {
                for (int dep : rulesHash.get(page)) {
                    if (update.contains(dep)) {
                        graph.get(dep).add(page);
                        inDegree.put(page, inDegree.get(page) + 1);
                    }
                }
            }
        }

        // Perform topological sort
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a); // Higher numbers first
        for (int page : update) {
            if (inDegree.get(page) == 0) {
                queue.offer(page);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(current);

            for (int neighbor : graph.get(current)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return result;
    }
}
