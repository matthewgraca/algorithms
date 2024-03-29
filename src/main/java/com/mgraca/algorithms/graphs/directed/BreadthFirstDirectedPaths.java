package com.mgraca.algorithms.graphs.directed;

import com.mgraca.algorithms.fundamentals.LinkedQueue;
import com.mgraca.algorithms.fundamentals.LinkedStack;
import java.util.NoSuchElementException;

public class BreadthFirstDirectedPaths{
  private boolean[] marked;
  private int[] edgeTo;
  private int[] distTo;
  private final int s;

  /**
   * Initializes and executes breadth-first search
   * @param g the graph
   * @param s the source vertex
   * @throws IllegalArgumentException if the source is out of range
   */
  public BreadthFirstDirectedPaths(Digraph g, int s){
    marked = new boolean[g.V()];
    edgeTo = new int[g.V()];
    distTo = new int[g.V()];
    validateVertex(s);
    this.s = s;
    bfs(g, s);
  }

  // uses bfs to find shortest paths connected to s
  private void bfs(Digraph g, int s){
    validateVertex(s);
    LinkedQueue<Integer> queue = new LinkedQueue<>();
    marked[s] = true;
    queue.enqueue(s);
    while (!queue.isEmpty()){
      int v = queue.dequeue();
      for (int w : g.adj(v)){
        if (!marked[w]){
          edgeTo[w] = v;
          distTo[w] = distTo[v] + 1;
          marked[w] = true;
          queue.enqueue(w);
        }
      }
    }
  }

  /**
   * Checks if v has been visited (if a path exists between source and v)
   * @param v the vertex
   * @return true if a given vertex was visited, false if not
   * @throws IllegalArgumentException if the vertex is out of range
   */
  public boolean hasPathTo(int v){
    validateVertex(v);
    return marked[v];
  }

  /**
   * Finds the distance between the source and a given vertex, in constant time
   * @param v the vertex
   * @return the distance between the source and a given vertex
   */
  public int distTo(int v){
    validateVertex(v);
    String msg = "Cannot find distance to a vertex not connected to the source";
    if (!hasPathTo(v))
      throw new NoSuchElementException(msg);
    return distTo[v];
  }

  // ensure v is within range
  private void validateVertex(int v){
    int V = marked.length;
    if (v < 0 || v >= V)
      throw new IllegalArgumentException("vertex must be [0,"+ V + ")");
  }

  /**
   * Creates an iterable showing the shortest path from the source to v
   * @param v the vertex
   * @return an iterable of the path to v from the source in bfs visit order;
   * null if no such path exists
   * @throws IllegalArgumentException if the vertex if out of range
   */
  public Iterable<Integer> pathTo(int v){
    validateVertex(v);
    if (!hasPathTo(v))
      return null;
    LinkedStack<Integer> path = new LinkedStack<>();
    for (int x = v; x != s; x = edgeTo[x])
      path.push(x);
    path.push(s);
    return path;
  }
}
