package lc;

public class Q8 {
    static int myAtoi(String str) {
        char[] chars = str.toCharArray();
        int result = 0;
        int sign = 1;
        int i = 0;
        if (chars.length < 1) {
            return 0;
        }
        while (i < chars.length && chars[i] == ' ') {
            i++;
        }
        if (i < chars.length && chars[i] == '-') {
            sign = -1;
            i++;
        } else if (i < chars.length && chars[i] == '+') {
            i++;
        }
        int previous;
        while (i < chars.length && chars[i] >='0' && chars[i] <= '9') {
            previous = result;
            result = (sign) * (chars[i] - '0') + result * 10;
            if (result - previous > 0) {
                return Integer.MAX_VALUE;
            }
            if (result - previous < 0) {
                return Integer.MIN_VALUE;
            }
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("2147483648"));
    }
}