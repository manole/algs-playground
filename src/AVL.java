public class AVL<Key extends Comparable, Value> {

  private Node root;

  private class Node {
    Key key;
    Value value;
    int size;
    int height;
    Node left, right;

    public Node(Key key, Value value) {
      this.key = key;
      this.value = value;
    }
  }

  public void put(Key key, Value value) {
    if(key == null) throw new IllegalArgumentException("The can not be null");

    //TODO: validate value
    root = put(root, key, value);
  }

  private Node put(Node node, Key key, Value value) {
    if(node == null) return new Node(key, value);

    int cmp = key.compareTo(node.key);

    if(cmp < 0) node.left = put(node.left, key, value);
    else if(cmp > 0) node.right = put(node.right, key, value);
    else {
      node.value = value;
    }

    // positive left heavy, negative right heavy
    int bal = height(node.left) - height(node.right);
    // left heavy
    if (bal > 1 && key.compareTo(node.key) < 0) {
      rotateRight(node);
    }

    if (bal > 1 && key.compareTo(node.key) > 0) {
      node.left = rotateLeft(node.left);
      rotateRight(node);
    }

    // right heavy
    if (bal < -1 && key.compareTo(node.key) > 0) {
      rotateLeft(node);
    }

    // right let
    if (bal < -1 && key.compareTo(node.key) < 0) {
      node.right = rotateRight(node.right);
      rotateLeft(node);
    }
    node.size = size(node.left) + size(node.right) + 1;
    node.height = Math.max(height(node.left), + height(node.right)) + 1;
    return node;
  }

  private Node rotateLeft(Node node) {
    Node top = node.right;
    node.right = top.left;
    top.left = node;

    node.size = Math.max(height(node.right), height(node.left)) + 1;
    top.size = Math.max(height(top.right), height(top.left)) + 1;

    return top;
  }

  private Node rotateRight(Node node) {
    Node top = node.left;
    node.left = top.right;
    top.right = node;

    node.size = Math.max(height(node.right), height(node.left)) + 1;
    top.size = Math.max(height(top.right), height(top.left)) + 1;

    return top;
  }

  private int height(Node node) {
    return node == null ? 0 : node.height;
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
    else {
      return node;
    }
  }

  public void delete(Key key) {
    if(key == null) throw new IllegalArgumentException("The key can not be null");
    root = delete(root, key);
  }

  public Node delete(Node node, Key key) {
    if(node == null) return null;

    int cmp = key.compareTo(node.key);

    if(cmp < 0) node.left = delete(node.left, key);
    else if(cmp > 0) node.right = delete(node.right, key);
    else {
      if(node.left == null) return node.right;
      if(node.right == null) return node.left;

      Node old = node;
      node = findMin(old.right);
      node.right = deleteMin(old.right);
      node.left = old.left;
    }
    node.size = size(node.left) + size(node.right) + 1;
    return node;
  }

  private Node deleteMin(Node node) {
    if (node.left == null) return node.right;
    node.left = deleteMin(node.left);
    node.size = size(node.left) + size(node.right) + 1;
    return node;
  }

  private int size(Node node) {
    return node == null ? 0 : node.size;
  }

  private Node findMin(Node node) {
    if (node.left == null) return node;
    return findMin(node.left);
  }

  public static void main(String[] args) {
    AVL<Integer, String> avl = new AVL<>();
    avl.put(1, "Hellow1");
    avl.put(2, "Hellow2");
    avl.put(3, "Hellow3");
    avl.put(4, "Hellow4");
    avl.put(5, "Hellow5");

    System.out.println(avl.get(1));
    System.out.println(avl.get(2));
    System.out.println(avl.get(3));
    System.out.println(avl.get(4));
    System.out.println(avl.get(5));
  }

}