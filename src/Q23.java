public class Q23 {
  // Q: Reverse a linked list in groups of size k.

  private Node head;

  static class Node {
    int value;
    Node next;

    public Node(int value) {
      this.value = value;
    }
  }

  public void reverse(int k) {
    if (k < 1) throw new IllegalArgumentException("Illegal Argument exception");
    printList(reverse(head, k));
  }

  private Node reverse(Node head, int k) {
    if (head == null)
      return null;
    int i = 1;
    Node tmp, last, first;

    first = last = new Node(head.value);
    head = head.next;

    while (head != null && i++ < k) {
      tmp = new Node(head.value);
      tmp.next = last;
      last = tmp;
      head = head.next;
    }

    if (last != null)
      first.next = reverse(head, k);

    return last;
  }

  static void printList(Node node) {
    while (node != null) {
      System.out.print(node.value + " ");
      node = node.next;
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Q23 q = new Q23();

    q.head = new Node(1);
    q.head.next = new Node(2);
    q.head.next.next = new Node(3);
    q.head.next.next.next = new Node(4);
    q.head.next.next.next.next = new Node(5);
    q.head.next.next.next.next.next = new Node(6);
    q.head.next.next.next.next.next.next = new Node(7);
    q.head.next.next.next.next.next.next.next = new Node(8);
    printList(q.head);
    q.reverse(2);
  }
}
