import java.util.Arrays;

public class Q22 {

  //1) K largest elements from a big file or array.

  //2)Find a triplet a, b, c such that a^2 = b^2 + c^2. Variations of this problem like find a triplet
  //  with sum equal to 0. Find a pair with given sum. All such questions are efficiently solved using hashing.

  //3) Binary tree traversal questions like left view, right view, top view, bottom view,    (REVIEW)
  //   maximum of a level, minimum of a level, children sum property, diameter etc.

  //4) Convert a Q18 into a DLL and DLL to Q18 in place.

  //5) Vertical traversal of a Binary Tree.

  //6) Lowest Common ancestor in a Bianry Search Tree and Binary Tree.



  static void largestElement(int a[]) {
    if (a == null || a.length == 0)
      throw new IllegalArgumentException("array should not be null or empty ");
    int max = a[0];
    for (int i = 0; i < a.length; i++) {
      if (a[i] > max) max = a[i];
    }

    System.out.println("Max element :" + max);
  }

  static void findTriplet1(int b[]) {
    int x;
    int a[] = Arrays.copyOf(b, b.length);
    Arrays.sort(a);

    for (int i = 0; i < a.length; i++) {
      a[i] *= a[i];
    }

    for (int i = 0; i < a.length; i++) {
      for (int j = i; j < a.length; j++) {
        x = Arrays.binarySearch(a, a[i] + a[j]);
        if (x > 0) {
          System.out.printf("%d + %d = %d\n", a[i], a[j], a[i] + a[j]);
        }
      }
    }
  }

  static void findTriplet2(int b[]) {
    int lo, hi, sum;

    int a[] = Arrays.copyOf(b, b.length);

    Arrays.sort(a);

    for (int i = 0; i < a.length; i++) {
      a[i] *= a[i];
    }

    for (int i = a.length - 1; i > -0; i--) {

      hi = i - 1;
      lo = 0;

      while (lo < hi) {
        sum = a[lo] + a[hi] - a[i];
        if (sum == 0) {
          System.out.printf("%d + %d = %d\n", a[lo], a[hi], a[i]);
        }

        if (sum > 0) hi--;
        else lo++;
      }
    }
  }

  // in-place conversion
  static void rotateMatrixRight(int[][] a) {
    int tmp;
    int m = a.length / 2;
    int n = a.length - 1;
    printMatrix(a);

    // ring rotation
    for (int i = 0; i < m; i++) {
      for (int j = i; j < m + 1; j++) {
        tmp = a[i][j];
        a[i][j] = a[n - j][i];
        a[n - j][i] = a[n - i][n - j];
        a[n - i][n - j] = a[j][n - i];
        a[j][n - i] = tmp;
      }
    }

    printMatrix(a);
  }

  static void printMatrix(int a[][]) {
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < a.length; j++) {
        System.out.print(a[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    largestElement(new int[]{1, 2, 3, 4, 0, 5, -1, 3, 1, 99, 45});
    int a[] = {1, 4, 5, 6, 10, 8};
    findTriplet1(a);
    findTriplet2(a);
    rotateMatrixRight(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
    System.out.println();
    rotateMatrixRight(new int[][]{{1, 2}, {3, 4}});
  }
}
