package lc;

/**
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1.
 * In other words, one of the first string's permutations is the substring of the second string.
 * "adc", "dcda" -> true;
 **/
class Q567 {

    public static void main(String[] args) {
        System.out.println(checkInclusion("adc", "dcda"));
    }

    private static boolean checkInclusion(String s1, String s2) {
        short[] frequency = new short[26];
        short[] window = new short[26];
        char[] input = s1.toCharArray();
        char[] text = s2.toCharArray();
        int i;

        if (input.length > text.length) {
            return false;
        }
        for (char c : input) {
            frequency[c - 'a']++;
        }
        for (i = 0; i < input.length; i++) {
            window[text[i] - 'a']++;
        }
        if (compare(frequency, window)) {
            return true;
        }
        while (i < text.length) {
            window[text[i -input.length] - 'a']--;
            window[text[i] - 'a']++;
            if (compare(frequency, window)) {
                return true;
            }
            i++;
        }
        return false;
    }

    private static boolean compare(short[] a, short[] b) {
        for(int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }
}