package Days.Day05_Queue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class day05 {
    
    public static void main(String args[]) {

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

        // Process the updates
        for (List<Integer> update : updates) {

            boolean isValid = true;
            Map<Integer, Integer> pageIndices = new HashMap<>();

            for (int idx = 0; idx < update.size(); idx++) {
                pageIndices.put(update.get(idx), idx);
            }

            for (Integer y : rulesHash.keySet()) {
                if (pageIndices.containsKey(y)) {
                    for (Integer x : rulesHash.get(y)) {
                        if (pageIndices.containsKey(x)) {
                            if (pageIndices.get(x) >= pageIndices.get(y)) {
                                isValid = false;
                                break;
                            }
                        }
                    }
                }
                if (!isValid)
                    break;
            }

            if (isValid) {
                ans += update.get(update.size() / 2);
            }

        }

        System.out.println("Answer: " + ans);

    }

}
