public class BST<Key extends Comparable, Value> {

  private Node root;

  private class Node {
    Key key;
    Value value;
    int size;
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
    BST<Integer, String> avl = new BST<>();
    avl.put(1, "Hellow1");
    avl.put(2, "Hellow2");
    avl.put(1, "Hellow11");
    avl.put(0, "Hellow0");
    avl.put(4, "Hellow4");

    System.out.println(avl.get(1));
    System.out.println(avl.get(2));
    System.out.println(avl.get(4));
    System.out.println(avl.get(0));
    avl.delete(0);
    System.out.println(avl.get(0));
    avl.delete(0);
    avl.put(0, "Hellow0");
    System.out.println(avl.get(0));
  }

}