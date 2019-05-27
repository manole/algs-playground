package datastructure;

/**
 * Reference https://algs4.cs.princeton.edu/15uf/.
 */
public class UnionFindV2 {
    private int[] root;
    private int count;

    public UnionFindV2(int size) {
        count = size;
        this.root = new int[size];
        for (int i = 0; i < root.length; i++) {
            root[i] = i;
        }
    }

    public void union(final int a, final int b) {
        int x = find(a);
        int y = find(b);
        if (x != y) {
            root[y] = x;
            count--;
        }
    }

    public int find(int a) {
        while (root[a] != a) {
            a = root[a];
        }
        return a;
    }

    public boolean isConnected(int a, int b) {
        return find(a) == find(b);
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        UnionFindV2 uf = new UnionFindV2(10);
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
