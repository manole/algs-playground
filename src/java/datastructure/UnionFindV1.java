package datastructure;

/**
 * Reference https://algs4.cs.princeton.edu/15uf/.
 */
public class UnionFindV1 {
    private int[] root;
    private int count;

    public UnionFindV1(int size) {
        count = size;
        this.root = new int[size];
        for (int i = 0; i < root.length; i++) {
            root[i] = i;
        }
    }

    public void union(int a, int b) {
        if (isConnected(a, b)) {
            return;
        }
        int idb = root[b];
        int ida = root[a];
        for (int i = 0; i < root.length; i++) {
            if (root[i] == idb) {
                root[i] = ida;
            }
        }
        count--;
    }

    public int find(int a) {
        return root[a];
    }

    public boolean isConnected(int a, int b) {
        return root[a] == root[b];
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        UnionFindV1 uf = new UnionFindV1(10);
        uf.union(4,3);
        uf.union(3,8);
        uf.union(6,5);
        uf.union(9,4);
        uf.union(2,1);
        uf.union(8,9);
        uf.union(5,0);
        uf.union(7,2);
        uf.union(6,1);
        uf.union(1,0);
        uf.union(6,7);

        System.out.println("Connected components: " + uf.getCount());
    }
}
