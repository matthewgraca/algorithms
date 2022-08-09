package com.mgraca.algorithms.graphs.undirected;

import java.util.ArrayList;

public class DepthFirstSearch{
  private boolean[] marked; // is there an s-v path?
  private int count;        // number of vertices connected to s
  private ArrayList<Integer> visitOrder;  // dfs visit order (for testing)

  /**
   * Conducts depth-first search on a given graph and source vertex
   * @param g the graph
   * @param s the source vertex
   * @throws IllegalArgumentException if the source vertex is out of range
   */
  public DepthFirstSearch(Graph g, int s){
    marked = new boolean[g.V()];
    visitOrder = new ArrayList<Integer>();
    validateVertex(s);
    dfs(g, s);
  }

  // conducts recursive depth-first search on a given graph and source vertex
  private void dfs(Graph g, int v){
    visitOrder.add(v);
    marked[v] = true;
    count++;
    for (int w : g.adj(v)){
      if (!marked[w])
        dfs(g, w);
    }
  }

  /**
   * Gets the number of vertices connected to s 
   * @return the number of vertices visited
   */
  public int count(){
    return count;
  }

  /**
   * Checks if a given vertex has been visited
   * @param v the vertex
   * @return true if a given vertex was visited, false if not
   * @throws IllegalArgumentException if the vertex is out of range
   */
  public boolean marked(int v){
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
   * Returns a string of the visit order of the search
   * @return a string of the visit order of the search
   */
  public String visitOrder(){
    return visitOrder.toString();
  }

}
