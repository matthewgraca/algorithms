package com.mgraca.algorithms.graphs.undirected;

import com.mgraca.algorithms.fundamentals.LinkedStack;

public class DepthFirstPaths{
  private boolean[] marked;
  private int[] edgeTo;
  private final int s;

  /**
   * Uses depth-first search to find paths from the source vertex to 
   * every other vertex connected to the source
   * @param g the graph
   * @param s the source vertex
   * @throws IllegalArgumentException if the source vertex is out of range
   */
  public DepthFirstPaths(Graph g, int s){
    marked = new boolean[g.V()];
    edgeTo = new int[g.V()];
    validateVertex(s);
    this.s = s;
    dfs(g, s);
  }

  // uses dfs to find paths connected to v
  private void dfs(Graph g, int v){
    marked[v] = true;
    for (int w : g.adj(v)){
      if (!marked[w]){
        edgeTo[w] = v;
        dfs(g, w);
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

  // ensure v is within range
  private void validateVertex(int v){
    int V = marked.length;
    if (v < 0 || v >= V)
      throw new IllegalArgumentException("vertex must be [0,"+ V + ")");
  }

  /**
   * Creates an iterable showing the path from the source to the vertex v, 
   * null if no such path exists
   * @param v the vertex
   * @return an iterable of the path to v from the source in dfs visit order
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
