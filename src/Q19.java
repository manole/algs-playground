public class Q19 {

  // Q: You are given a list of numbers 1 -> n
  //    One of the integers is missing in the list. Write an efficient code to find the missing integer.

  static void sumFormula(int a[]) {
    if(a == null || a.length < 1)
      throw new IllegalArgumentException("a cannot be null or empty");

    long n = a.length, sum1 = (n + 1) * (n + 2) / 2, sum2 = 0;
    for (int e : a) {
      sum2 += e;
    }
    System.out.println(sum1 - sum2);
  }

  static void xorFormula(int a[]){
    if(a == null || a.length < 1)
      throw new IllegalArgumentException("a cannot be null or empty");

    int x = 0;
    for (int i = 0; i < a.length; i++) {
      x ^= a[i];
      x ^= i + 1;
    }
    System.out.println(x ^ (a.length +1));
  }

  public static void main(String[] args) {
    int[] a = {1, 2, 3, 4, 5, 6, 8};
    sumFormula(a);
    xorFormula(a);
  }
}
