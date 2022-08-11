package com.mgraca.algorithms.graphs.undirected;

public class Bipartite{
  private boolean[] marked;
  private boolean[] color;
  private boolean isBipartite;

  /**
   * Conducts depth-first search to find if a graph is bipartite
   * @param g the graph
   * @throws IllegalArgumentException if the source vertex is out of range
   */
  public Bipartite(Graph g){
    int n = g.V();
    marked = new boolean[n];
    color = new boolean[n];
    isBipartite = true;
    for (int s = 0; s < n; s++)
      if (!marked[s])
        dfs(g, s);
  }

  // conducts recursive depth-first search on a given graph and source vertex
  private void dfs(Graph g, int v){
    marked[v] = true;
    for (int w : g.adj(v)){
      if (!marked[w]){
        color[w] = !color[v];
        dfs(g, w);
      }
      else if (color[w] == color[v])
        isBipartite = false;
    }
  }

  /**
   * Checks if the graph has been marked as bipartite
   * @return true if the graph is bipartite, false if not
   */
  public boolean isBipartite(){
    return isBipartite;
  }
}
