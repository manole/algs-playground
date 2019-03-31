package misc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Q26 {

  // Q: implement the Prim's algorithm

  private int size;
  private int V;
  private int E;
  private List<Edge>[] adj;
  private Edge edgeTo[];
  private List<Edge> edgeList;
  private double distTo[];
  private boolean hasCycle;

  class Edge {
    int from;
    int to;
    double weight;

    public Edge(int from, int to, double weight) {
      this.from = from;
      this.to = to;
      this.weight = weight;
    }
  }

  class Node implements Comparable<Node> {
    double weight;
    int vertex;

    public Node(int vertex, double weight) {
      this.weight = weight;
      this.vertex = vertex;
    }

    @Override
    public int compareTo(Node o) {
      return Double.compare(weight, o.weight);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Node node = (Node) o;
      return vertex == node.vertex;
    }

    @Override
    public int hashCode() {
      return Objects.hash(vertex);
    }
  }


  public Q26(int V, int E) {
    if (V < 1) throw new IllegalArgumentException("Size should be positive");
    this.V = V;
    this.E = E;
    this.size = V + 1;
    this.edgeList = new ArrayList<>(E);
    adj = new List[size];
    for (int i = 0; i < size; i++) {
      adj[i] = new ArrayList<>();
    }
  }

  public void addEdge(int v, int w, double weight) {
    Edge e = new Edge(v, w, weight);
    adj[v].add(e);
    edgeList.add(e);
  }


  public Edge[] dijkstra(int s) {
    Queue<Node> q = new PriorityQueue<>();

    distTo = new double[size];
    edgeTo = new Edge[size];

    for (int i = 0; i < size; i++) {
      distTo[i] = Double.POSITIVE_INFINITY;
      edgeTo[i] = null;
    }

    distTo[s] = 0;
    q.add(new Node(s, 0));

    while (!q.isEmpty()) {
      Node e = q.poll();

      for (Edge w : adj[e.vertex]) {
        if (e.weight + w.weight < distTo[w.to]) {
          edgeTo[w.to] = w;
          distTo[w.to] = e.weight + w.weight;
          Node n = new Node(w.to, distTo[w.to]);
          q.remove(n);
          q.add(n);
        }
      }
    }
    return edgeTo;
  }

  public Edge[] bellmanFord(int s) {
    distTo = new double[size];
    edgeTo = new Edge[size];

    for (int i = 0; i < size; i++) {
      distTo[i] = Double.POSITIVE_INFINITY;
      edgeTo[i] = null;
    }
    distTo[s] = 0;

    for (int i = 0; i < V; i++) {
      for (Edge e : edgeList) {
        if (distTo[e.from] != Double.POSITIVE_INFINITY
            && e.weight + distTo[e.from] < distTo[e.to]) {
          distTo[e.to] = e.weight + distTo[e.from];
          edgeTo[e.to] = e;
        }
      }
    }

    return edgeTo;
  }

  static void printPath(Edge[] res, int V, int s) {
    for (int i = 0; i < V; i++) {
      System.out.printf("[%d to %d]", s, i);
      for (Edge e = res[i]; e != null; e = res[e.from]) {
        System.out.print("  (" + e.to + "->" + e.from + ") ");
      }
      System.out.println();
    }
  }

  static void printMst(Edge[] res) {
    for (int i = 0; i < res.length; i++) {
     if(res[i] != null) {
        System.out.print("  (" + res[i].to + "->" + res[i].from + ") ");
      }
    }
    System.out.println();
  }

  public Edge[] primsMST(int s) {
    Queue<Node> queue = new PriorityQueue<>();
    boolean marked[] = new boolean[V];
    for (int i = 0; i < size; i++) {
      distTo[i] = Double.POSITIVE_INFINITY;
      edgeTo[i] = null;
    }
    distTo[s] = 0;

    queue.add(new Node(s, 0));
    marked[0] = true;

    while (!queue.isEmpty()) {
      Node e = queue.poll();
      marked[e.vertex] = true;

      for (Edge w : adj[e.vertex]) {
        if(marked[w.to]) continue;
        if (e.weight < distTo[w.to]) {
          distTo[w.to] = e.weight;
          edgeTo[w.to] = w;
          Node n = new Node(w.to, e.weight);
          queue.remove(n);
          queue.add(n);
        }
      }
    }
    return edgeTo;
  }

  public boolean hasCyclesDAG(int s) {
    boolean[] marked = new boolean[V];
    boolean[] stacked = new boolean[V];
    hasCycle = false;
    Set<Integer> set = new HashSet<>();
    hasCyclesDAG(stacked, marked, s);
    return hasCycle;
  }

  private void hasCyclesDAG(boolean[] stacked, boolean[] marked, int v) {
    marked[v] = true;
    stacked[v] = true;

    for (Edge e : adj[v]) {
      if (!marked[e.to]) {
        hasCyclesDAG(stacked, marked, e.to);
      } else if(stacked[e.to]) {
        hasCycle = true;
        return;
      }
    }

    stacked[v] = false;
    return;
  }

  // isBST
  public static void main(String[] args) {
    Q26 graph = new Q26(8, 15);
    graph.addEdge(4, 5, 0.35);
    graph.addEdge(5, 4, 0.35);
    graph.addEdge(4, 7, 0.37);
    graph.addEdge(5, 7, 0.28);
    graph.addEdge(7, 5, 0.28);
    graph.addEdge(5, 1, 0.32);
    graph.addEdge(0, 4, 0.38);
    graph.addEdge(0, 2, 0.26);
    graph.addEdge(7, 3, 0.39);
    graph.addEdge(1, 3, 0.29);
    graph.addEdge(2, 7, 0.34);
    graph.addEdge(6, 2, 0.40);
    graph.addEdge(3, 6, 0.52);
    graph.addEdge(6, 0, 0.58);
    graph.addEdge(6, 4, 0.93);

    System.out.println("Compute shortest path using Dijkstra");
    printPath(graph.dijkstra(0), 8, 0);

    System.out.println("Compute shortest path using Bellman-Ford");
    printPath(graph.bellmanFord(0), 8,  0);

    System.out.println("Compute minimum spanning tree using Prim's algorithm");
    printMst(graph.primsMST(0));

//    graph.addEdge(4, 6, 0.93);
    System.out.println(graph.hasCyclesDAG(0));
  }
}
