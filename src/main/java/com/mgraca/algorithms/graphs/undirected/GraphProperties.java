package com.mgraca.algorithms.graphs.undirected;

import java.util.NoSuchElementException;
import com.mgraca.algorithms.fundamentals.LinkedQueue;

/**
 * Determines and computes various graph properties
 * Note: eccentricity of a graph is the longest length of all the shortest 
 * paths between any two vertices in a graph
 */
public class GraphProperties{
  private Graph G;
  /**
   * Constructor
   * @throws IllegalArgumentException if G is not connected
   * @param G the graph
   */
  public GraphProperties(Graph G){
    this.G = G;
    CC cc = new CC(G);
    if (cc.count() != 1)
      throw new IllegalArgumentException("G is not connected");
  }

  // computes the eccentricity of vertex v; 
  // the longest length of the shortest path from v to every other vertex
  private int eccentricity(int v){
    int max = 0;
    BreadthFirstPaths bfs = new BreadthFirstPaths(G, v);
    for (int w = 0; w < G.V(); w++){
      if (max < bfs.distTo(w))
        max = bfs.distTo(w);
    }
    return max;
  }

  /**
   * Finds the max eccentricity of any vertex
   * @return the max eccentricity of the graph
   */
  public int diameter(){
    int max = 0;
    // perform bfs on every vertex v
    for (int v = 0; v < G.V(); v++){
      BreadthFirstPaths bfs = new BreadthFirstPaths(G, v);
      int eccentricityV = eccentricity(v);
      if (max < eccentricityV)
        max = eccentricityV;
    }
    return max;
  }

  /**
   * Finds the smallest eccentricity of any vertex
   * @return the minimum eccentricity of the graph
   */
  public int radius(){
    int min = Integer.MAX_VALUE;
    // perform bfs on every vertex v
    for (int v = 0; v < G.V(); v++){
      BreadthFirstPaths bfs = new BreadthFirstPaths(G, v);
      int eccentricityV = eccentricity(v);
      if (min > eccentricityV)
        min = eccentricityV;
    }
    return min;
  }

  /**
   * Gets the vertex whose eccentricity is the radius
   * @return the vertex whose eccentricity is the radius
   */
  public int center(){
    int radius = radius();
    for (int v = 0; v < G.V(); v++){
      if (radius == eccentricity(v))
        return v;
    }
    throw new NoSuchElementException("No radius found");
  }

  /**
   * Computes the sum of the lengths of the shortest paths between all pairs of 
   * vertices; the Wiener index
   * @return the sum of the lengths of the shortest paths between all pairs of 
   * vertices
   */
  public int wiener(){
    int sum = 0;
    for (int v = 0; v < G.V(); v++){
      BreadthFirstPaths bfs = new BreadthFirstPaths(G, v);
      for (int w = v+1; w < G.V(); w++)
        sum += bfs.distTo(w);
    }
    return sum;
  }

  /**
   * Gets the length of the shortest cycle in a graph
   * @return the length of the shortest cycle
   */
  public int girth(){
    int min = Integer.MAX_VALUE;
    for (int v = 0; v < G.V(); v++){
      int cycleLength = bfsCycle(v);
      if (min > cycleLength)
        min = cycleLength;
    }
    return min;
  }

  // bfs with added cycle detection and length tracking
  // returns max int if no cycle found
  private int bfsCycle(int s){
    // using edgeTo[] parent-link tree, find the min depth which a vertex 
    // appears for the second time when bfs is run at a vertex v
    boolean[] marked = new boolean[G.V()];
    int[] edgeTo = new int[G.V()];
    int[] distTo = new int[G.V()];
    LinkedQueue<Integer> queue = new LinkedQueue<>();
    queue.enqueue(s);
    marked[s] = true;
    while (!queue.isEmpty()){
      int v = queue.dequeue();
      for (int w : G.adj(v)){
        if (!marked[w]){
          marked[w] = true;
          edgeTo[w] = v;
          distTo[w] = distTo[v] + 1;
          queue.enqueue(w);
        }
        else if (w != edgeTo[v]){
          return distTo[w] + distTo[v] + 1;
        }
      }
    }
    return Integer.MAX_VALUE;
  }
}
