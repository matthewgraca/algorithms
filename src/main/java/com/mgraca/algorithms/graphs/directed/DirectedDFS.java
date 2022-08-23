package com.mgraca.algorithms.graphs.directed;

import java.util.Arrays;

public class DirectedDFS{
  private boolean[] marked; // is there an s-v path?

  /**
   * Conducts depth-first search on a given graph and source vertex
   * @param G the graph
   * @param s the source vertex
   * @throws IllegalArgumentException if the source vertex is out of range
   */
  public DirectedDFS(Digraph G, int s){
    marked = new boolean[G.V()];
    validateVertex(s);
    dfs(G, s);
  }

  /**
   * Conducts depth-first search on a given graph and gets the vertices 
   * that are reachable from source
   * @param G the graph
   * @param sources the source vertices
   * @throws IllegalArgumentException if any of the source vertices are out of 
   * range
   */
  public DirectedDFS(Digraph G, Iterable<Integer> sources){
    marked = new boolean[G.V()];
    for (int s : sources){
      validateVertex(s);
      if (!marked[s])
        dfs(G, s);
    }
  }

  // conducts recursive depth-first search on a given graph and source vertex
  private void dfs(Digraph G, int v){
    marked[v] = true;
    for (int w : G.adj(v)){
      if (!marked[w])
        dfs(G, w);
    }
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
}
