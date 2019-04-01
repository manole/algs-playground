package lc;

import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')',
 * find the length of the longest valid (well-formed) parentheses substring.
 */
public class Q32 {

    private static int longestValidParentheses(String s) {
        final char[] array = s.toCharArray();
        int length = 0;
        Stack<Integer> positions = new Stack<>();
        positions.push(-1); // solves cases where s starts with ")"

        for (int i = 0; i < array.length; i++) {
            if (array[i] == '(') {
                positions.push(i);
            } else {
                positions.pop();
                if (positions.isEmpty()) {
                    positions.push(i);
                } else {
                    length = Math.max(i - positions.peek(), length);
                }
            }
        }
        return length;
    }

    public static void main(String[] args) {
        System.out.println(longestValidParentheses(")()())"));
    }
}
