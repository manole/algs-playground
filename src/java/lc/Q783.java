/* Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved. */
package lc;

/**
 * 783. Minimum Distance Between BST Nodes.
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Q783 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    private int minDiff;
    private int previousMax;

    public int minDiffInBST(TreeNode root) {
        minDiff = Integer.MAX_VALUE;
        previousMax = Integer.MAX_VALUE;
        findMinDiff(root);
        return minDiff;
    }

    private void findMinDiff(TreeNode root) {
        if (root == null) {
            return;
        }
        findMinDiff(root.left);
        minDiff = Math.min(Math.abs(root.val - previousMax), minDiff);
        previousMax = root.val;
        findMinDiff(root.right);
    }
}
