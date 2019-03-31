package misc;

public class Q20<Value> {

  // Q: Get the kth element form the end of a linked list.

  private static final String ERR = "The list has fewer than k elements";
  private Node first;
  private Node last;

  private class Node {
    Value value;
    Node next;

    public Node(Value value) {
      this.value = value;
    }
  }

  public void add(Value a) {
    if(first == null) {
      first = new Node(a);
      last = first;
    } else {
      last.next = new Node(a);
      last = last.next;
    }
  }

  public Value getKthElement(int k) {
    if(k < 0) throw new IllegalArgumentException("Can not be negative");
    if(first == null)
      throw new RuntimeException(ERR);

    Node iterator = first;
    Node kth = first;
    int count = 0;

    while (iterator != null && k >= 0) {
      k--;
      iterator = iterator.next;
    }

    while (iterator != null && k != 0) {
      kth = kth.next;
      iterator = iterator.next;
    }

    if(k>0)
       throw new RuntimeException(ERR);
    else
      return kth.value;
  }

  public Value getKthElementRec(int k) {
    return getKthElementRec(first, first, k);
  }

    private Value getKthElementRec(Node iter, Node kth, int k) {
      if (iter.next == null && k == 0) {
        return kth.value;
      } else if (iter.next == null) {
        throw new RuntimeException("less than k elements");
      }
      return getKthElementRec(iter.next, k == 0 ? kth.next : kth, k > 0 ? k - 1 : 0);
    }

  public static void main(String[] args) {
    Q20<Integer> kth = new Q20<>();
    kth.add(1);
    kth.add(2);
    kth.add(3);
    int k = 3;
    System.out.println(kth.getKthElement(k));
    System.out.println(kth.getKthElementRec(k));
  }
}
