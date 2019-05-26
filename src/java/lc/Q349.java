package lc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Q349 {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> result = new HashSet<>();
        int[] tmp;
        if (nums1.length > nums2.length) {
            tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
        }
        Arrays.sort(nums1);
        System.out.println(binarySearch(nums1, 1));

        for (int e: nums2) {
            if (binarySearch(nums1, e) > -1) {
                result.add(e);
            }
        }
        int i = 0;
        int[] array = new int[result.size()];
        for (int e: result) {
            array[i++] = e;
        }
        return array;
    }

    private int binarySearch(int[] a, int value) {
        return binarySearch(a, 0, a.length - 1, value);
    }

    private int binarySearch(int[] a, int lo, int hi, int value) {
        if (lo > hi) {
            return -1;
        }
        int mid = lo + (hi - lo) / 2;
        if (value > a[mid]) {
            return binarySearch(a, mid + 1, hi, value);
        }
        if (value < a[mid]) {
            return binarySearch(a, lo, mid - 1, value);
        }
        return mid;
    }

    public static void main(String[] args) {
        Q349 q = new Q349();
        System.out.println(Arrays.toString(q.intersection(new int[]{4,9,5}, new int[] {9,4,9,8,4})));
    }
}
