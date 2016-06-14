public class Q25 {
  // Q: Convert a BST into a DLL in place.

  static class Node {
    int value;
    Node left, right;

    public Node(int value) {
      this.value = value;
    }
  }

  // needs to be encapsulated
  static Node result = null;

  public static void simpleNotInPlace(Node node) {
    if (node == null)
      return;
    Node tmp;
    simpleNotInPlace(node.left);

    tmp = new Node(node.value);
    tmp.right = result;
    if (result != null) result.left = tmp;
    result = tmp;

    simpleNotInPlace(node.right);
  }


  public static Node notInPlace(Node result, Node node) {
    if (node == null)
      return result;
    Node tmp;
    result = notInPlace(result, node.left);

    tmp = new Node(node.value);
    tmp.right = result;
    if (result != null) result.left = tmp;
    result = tmp;

    result = notInPlace(result, node.right);
    return result;
  }


  public static Node inPlace(Node result, Node node) {
    if (node == null)
      return result;
    Node tmp;
    result = inPlace(result, node.left);

    tmp = node.right;
    node.right = result;
    if (result != null) result.left = node;
    result = node;

    result = inPlace(result, tmp);
    return result;
  }

  public static void print(Node node) {
    while (node != null) {
      System.out.print(node.value + " ");
      node = node.right;
    }
    System.out.println();
  }


  public static void main(String[] args) {
    Node node = new Node(1);
    node.left = new Node(2);
    node.left.left = new Node(4);
    node.left.right = new Node(5);
    node.left.right .left= new Node(7);
    node.right = new Node(3);
    node.right.right = new Node(8);

    simpleNotInPlace(node);
    Node result2 = notInPlace(null, node);
    Node result3 = inPlace(null, node);

    print(result);
    print(result2);
    print(result3);
  }
}
