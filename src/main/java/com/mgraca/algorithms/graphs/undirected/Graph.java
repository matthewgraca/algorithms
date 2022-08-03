package com.mgraca.algorithms.graphs.undirected;

import com.mgraca.algorithms.fundamentals.Bag;
import java.util.Scanner;

/**
 * An implementation of an undirected graph using an adjacency list, 
 * where each list is a multiset (bag)
 */
public class Graph{

  /**
   * Initializes a graph with V vertices
   * @param V the number of vertices
   */
  public Graph(int V){

  }

  /**
   * Initializes a graph using standard input
   * @param in standard input stream
   */
  public Graph(Scanner in){

  }

  /**
   * Gets the number of vertices in the graph
   * @return the number of vertices in the graph
   */
  public int V(){
    return 0;
  }

  /**
   * Gets the number of edges in the graph
   * @return the number of edges in the graph
   */
  public int E(){
    return 0;
  }

  /**
   * Addes edge v-w to this graph
   * @param v a vertex
   * @param e the other vertex 
   */
  public void addEdge(int v, int w){

  }

  /**
   * Computes the degree of v (number of edges incident to v)
   * @param v the vertex
   * @return the degree of v
   */
  public int degree(int v){
    return 0;
  }

  // throws IllegalArgumentException if the vertex v is not in [0, V-1]
  private void validateVertex(int v){

  }

  /**
   * Gets the vertices directly adjacent to vertex v
   * @param v the vertex
   * @return the vertices adjacent to v as an iterable
   */
  public Iterable<Integer> adj(int v){
    return null;
  }

  /**
   * The string representation of this graph
   * @return the number of vertices, followed by the number of edges, followed 
   *          by the adjacency lists
   */
  public String toString(){
    return null;
  }

}
