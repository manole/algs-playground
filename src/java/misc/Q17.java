package misc;

public class Q17<Key extends Comparable, Value> {
  // Q: Implement the self balancing Red Black BST

  private Node root;

  private enum Color {
    RED, BLACK
  }
  
  private class Node {
    Key key;
    Value value;
    int size;
    int height;
    Node left, right;
    Color color;

    public Node(Key key, Value value, Color c) {
      this.key = key;
      this.value = value;
      this.height =  1;
      this.color = c;
    }
  }

  public void put(Key key, Value value) {
    if(key == null) throw new IllegalArgumentException("The can not be null");

    //TODO: validate "value"
    root = put(root, key, value);
  }

  private Node put(Node node, Key key, Value value) {
    if(node == null) return new Node(key, value, Color.RED);

    int cmp = key.compareTo(node.key);

    if(cmp < 0) node.left = put(node.left, key, value);
    else if(cmp > 0) node.right = put(node.right, key, value);
    else {
      node.value = value;
    }

    if(isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
    if(isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
    if(isRed(node.left) && isRed(node.right)) flipColor(node);

    node.size = size(node.left) + size(node.right) + 1;
    return node;
  }

  public Value get(Key key) {
    if(key == null) throw new IllegalArgumentException("The key can not be null");
    Node node = get(root, key);

    return node == null ? null : node.value;
  }

  private Node get(Node node, Key key) {
    if(node == null) return null;

    int cmp = key.compareTo(node.key);
    if(cmp < 0) return get(node.left, key);
    else if(cmp > 0) return get(node.right, key);
    else return node;
  }

  private Node rotateLeft(Node node) {
    Node top = node.right;
    node.right = top.left;
    top.left = node;

    node.height = Math.max(height(node.right), height(node.left)) + 1;
    top.height = Math.max(height(top.right), height(top.left)) + 1;

    top.color = node.color;
    node.color = Color.RED;

    return top;
  }

  private Node rotateRight(Node node) {
    Node top = node.left;
    node.left = top.right;
    top.right = node;

    node.height = Math.max(height(node.right), height(node.left)) + 1;
    top.height = Math.max(height(top.right), height(top.left)) + 1;

    top.color = node.color;
    node.color = Color.RED;

    return top;
  }

  private void flipColor(Node node) {
    node.color = Color.RED;
    node.left.color = Color.BLACK;
    node.right.color = Color.BLACK;
  }

  private int size(Node node) {
    return node == null ? 0 : node.size;
  }

  private int height(Node node) {
    return node == null ? 0 : node.height;
  }

  private boolean isRed(Node node) {
    return node != null && node.color.equals(Color.RED);
  }

  public static void main(String[] args) {
    Q17<Integer, String> rbt = new Q17<>();
    rbt.put(1, "Hellow1");
    rbt.put(2, "Hellow2");
    rbt.put(1, "Hellow11");
    rbt.put(0, "Hellow0");
    rbt.put(4, "Hellow4");

    System.out.println(rbt.get(1));
    System.out.println(rbt.get(2));
    System.out.println(rbt.get(4));
    System.out.println(rbt.get(0));
    System.out.println(rbt.get(0));
    System.out.println(rbt.get(0));
  }
}