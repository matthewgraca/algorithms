package com.mgraca.algorithms.graphs.undirected;

/**
 * Identifies the connected components of a given graph
 */
public class CC{
  private boolean[] marked; // is there an s-v path?
  private int[] id;
  private int count;        // number of connected components 

  /**
   * Finds the connected components of a graph using depth-first search
   * @param g the graph
   */
  public CC(Graph g){
    int n = g.V();
    marked = new boolean[n];
    id = new int[n];
    for (int s = 0; s < n; s++){
      if (!marked[s]){
        dfs(g, s);
        count++;
      }
    }
  }

  // conducts recursive depth-first search on a given graph and source vertex
  private void dfs(Graph g, int v){
    marked[v] = true;
    id[v] = count;
    for (int w : g.adj(v)){
      if (!marked[w])
        dfs(g, w);
    }
  }

  /**
   * Gets the number of connected components; 1 if graph is connected 
   * @return the number of connected components
   */
  public int count(){
    return count;
  }

  // ensure v is within range
  private void validateVertex(int v){
    int V = marked.length;
    if (v < 0 || v >= V)
      throw new IllegalArgumentException("vertex must be [0,"+ V + ")");
  }

  /**
   * Checks if two vertices are conencted
   * @param v the vertex
   * @param w the other vertex
   * @return true if the vertices are connected, false if not
   * @throws IllegalArgumentException if either v or w are out of range
   */
  public boolean connected(int v, int w){
    validateVertex(v);
    validateVertex(w);
    return id[v] == id[w];
  }

  /**
   * Gets the identity of the connected subgraph the vertex is associated with
   * @param v the vertex
   * @return the id of the connected subgraph the vertex is associated with
   * @throws IllegalArgumentException if v is out of range
   */
  public int id(int v){
    validateVertex(v);
    return id[v];
  }
}
