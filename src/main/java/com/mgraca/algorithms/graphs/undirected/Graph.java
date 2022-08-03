package com.mgraca.algorithms.graphs.undirected;

import com.mgraca.algorithms.fundamentals.Bag;
import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 * An implementation of an undirected graph using an adjacency list, 
 * where each list is a multiset (bag)
 */
public class Graph{
  private int V;
  private int E;
  private Bag<Integer>[] adj;

  /**
   * Initializes a graph with V vertices
   * @param V the number of vertices
   * @throws IllegalArgumentException if the number of vertices is negative
   */
  public Graph(int V){
    if (V < 0){
      String msg = "Cannot initialize a graph with negative vertices";
      throw new IllegalArgumentException(msg);
    }
    this.V = V;
    this.E = 0;
    @SuppressWarnings("unchecked")
    Bag<Integer>[] temp = (Bag<Integer>[]) new Bag[V];
    adj = temp;
    for (int v = 0; v < V; v++)
      adj[v] = new Bag<Integer>();
  }

  /**
   * Initializes a graph using standard input
   * @param in standard input stream
   * @throws IllegalArgumentException if the argument is null
   * @throws IllegalArgumentException if the read vertex or edge is negative
   * @throws IllegalArgumentException if the edge endpoint is out of range
   * @throws IllegalArgumentException if the input is in the wrong format
   */
  public Graph(Scanner in){
    if (in == null)
      throw new IllegalArgumentException("Argument is null");
    try{
      // read vertex number
      checkNextInt(in);
      this.V = in.nextInt();
      if (V < 0){
        String msg = "Cannot initialize a graph with negative vertices";
        throw new IllegalArgumentException(msg);
      }
      // create adjacency list
      @SuppressWarnings("unchecked")
      Bag<Integer>[] temp = (Bag<Integer>[]) new Bag[V];
      adj = temp;
      for (int v = 0; v < V; v++)
        adj[v] = new Bag<Integer>();

      // initialize bags w/in the adj list
      checkNextInt(in);
      int E = in.nextInt();
      if (E < 0){
        String msg = "Cannot initialize a graph with negative edges";
        throw new IllegalArgumentException(msg);
      }
      for (int i = 0; i < E; i++){
        checkNextInt(in);
        int v = in.nextInt();
        checkNextInt(in);
        int w = in.nextInt();
        addEdge(v, w);
      }
    }
    catch (NoSuchElementException e){
      throw new IllegalArgumentException("Input is in the wrong format");
    }
  }

  // throws IllegalArgumentException if an int can't be read from std in
  private void checkNextInt(Scanner in){
    if (!in.hasNextInt())
      throw new IllegalArgumentException("Wrong format");
  }

  /**
   * Gets the number of vertices in the graph
   * @return the number of vertices in the graph
   */
  public int V(){
    return V;
  }

  /**
   * Gets the number of edges in the graph
   * @return the number of edges in the graph
   */
  public int E(){
    return E;
  }

  /**
   * Addes edge v-w to this graph
   * @param v a vertex
   * @param e the other vertex 
   * @throws IllegalArgumentException if any vertex is out of range
   */
  public void addEdge(int v, int w){
    validateVertex(v);
    validateVertex(w);
    E++;
    adj[v].add(w);
    adj[w].add(v);
  }

  /**
   * Computes the degree of v (number of edges incident to v)
   * @param v the vertex
   * @return the degree of v
   */
  public int degree(int v){
    validateVertex(v);
    return adj[v].size();
  }

  // throws IllegalArgumentException if the vertex v is not in [0, V-1]
  private void validateVertex(int v){
    if (v < 0 || v > V-1)
      throw new IllegalArgumentException("Vertex must be [0, " + (V-1) + "]");
  }

  /**
   * Gets the vertices directly adjacent to vertex v
   * @param v the vertex
   * @return the vertices adjacent to v as an iterable
   * @throws IllegalArgumentException if the vertex is out of range
   */
  public Iterable<Integer> adj(int v){
    validateVertex(v);
    return adj[v];
  }

  /**
   * The string representation of this graph
   * @return the number of vertices, followed by the number of edges, followed 
   *          by the adjacency lists
   */
  public String toString(){
    String s = V + " vertices, " + E + " edges\n";
    for (int v = 0; v < V; v++){
      s += v + ": ";
      for (int w : this.adj(v))
        s += w + " ";
      s += "\n";
    }
    return s;
  }

}
