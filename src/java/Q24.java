public class Q24 {
  // Q: Implement a stack with push(), pop() and min() in O(1) time.

  Node head;

  static class Node {
    int value;
    int smaller;
    Node next;

    public Node(int value) {
      this.value = value;
    }
  }

  public void push(int value) {
    Node tmp = new Node(value);
    tmp.smaller = value;
    if(head != null && value > head.smaller) {
        tmp.smaller = head.smaller;
    }
    tmp.next = head;
    head = tmp;
  }

  public int pop() {
    if (head == null) throw new IllegalStateException("you came into a wrong state.");
    int value = head.value;
    head = head.next;
    return value;
  }

  public int min() {
    if(head != null) return head.smaller;
    throw new IllegalStateException("Your stack is empty, and sad");
  }

  public static void main(String[] args) {
    Q24 q = new Q24();
    q.push(2);
    q.push(2);
    q.push(3);
    q.push(1);
    q.push(4);
    System.out.println(q.min());
    q.pop();
    q.pop();
    System.out.println(q.min());
  }
}
