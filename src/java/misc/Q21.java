package misc;

public class Q21 {

  // Q: A list of n string, each of length n, is sorted into lexicographic order using
  //    the merge-sort algorithm.

  static int compare(String a, String b) {
    if (a == null) a = "";
    if (b == null) b = "";

    int i = 0;
    while (i < a.length() && i < b.length()) {
      char x = a.charAt(i), y = b.charAt(i);
      if (x != y) return x - y;
      i++;
    }
    return a.length() - b.length();
  }


  public static void main(String[] args) {
    System.out.println(compare("zbz", "zbz"));
  }

}
