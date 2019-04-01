package lc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Q819 {

    private static String mostCommonWord(String paragraph, String[] banned) {
        Set<String> bannedSet = new HashSet<>();
        Map<String, Integer> frequency = new HashMap<>();
        for (String w : banned) {
            bannedSet.add(w.toLowerCase());
        }
        String [] words = paragraph.split("[\\s,\\.\\?;!']");
        String result = words[0].toLowerCase();
        int max = 0;

        for (String w : words) {
            String lw = w.toLowerCase();
            if (lw.equals("") || bannedSet.contains(lw)) {
                continue;
            }
            Integer count = frequency.get(lw);
            if (count == null) {
                frequency.put(lw, 1);
                count = 1;
            } else {
                count++;
                frequency.put(lw, count);
            }
            if (count > max) {
                result = lw;
                max = count;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(mostCommonWord("a, a, a, a, b,b,b,c, c", new String[] {"a"}));
    }
}
