package com.mgraca.algorithms.graphs.undirected;

/**
 * Checks for cycles; assumes no self-loops or parallel edges
 */
public class Cycle{
  private boolean[] marked;
  private boolean hasCycle;

  /**
   * Conducts depth-first search to find if a graph contains a cycle
   * @param g the graph
   * @throws IllegalArgumentException if the source vertex is out of range
   */
  public Cycle(Graph g){
    marked = new boolean[g.V()];
    for (int s = 0; s < g.V(); s++){
      if (!marked[s])
        dfs(g, -1, s);
    }
  }

  // conducts recursive depth-first search on a given graph and source vertex
  private void dfs(Graph g, int u, int v){
    marked[v] = true;
    for (int w : g.adj(v)){
      if (!marked[w])
        dfs(g, v, w);
      else if (w != u)
        hasCycle = true;
    }
  }

  /**
   * Checks if the graph has been marked as cyclic
   * @return true if the graph has a cycle, false if not
   */
  public boolean hasCycle(){
    return hasCycle;
  }
}
