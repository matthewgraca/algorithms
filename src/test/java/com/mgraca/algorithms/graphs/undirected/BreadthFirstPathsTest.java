package com.mgraca.algorithms.graphs.undirected;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class BreadthFirstPathsTest{
  @Test(expected=IllegalArgumentException.class)
  public void vertexBelowRangeThrowsException(){
    Graph g = new Graph(3);
    BreadthFirstPaths bfs = new BreadthFirstPaths(g, -1);
  }

  @Test(expected=IllegalArgumentException.class)
  public void vertexAboveRangeThrowsException(){
    Graph g = new Graph(3);
    BreadthFirstPaths bfs = new BreadthFirstPaths(g, 3);
  }

  @Test
  public void properVisitOrderOnABalancedTree(){
    int[] expected = {0, 1, 4, 9};
    Graph g = new Graph(10); 
    g.addEdge(0, 2);
    g.addEdge(0, 1);
    g.addEdge(1, 4);
    g.addEdge(1, 3);
    g.addEdge(2, 6);
    g.addEdge(2, 5);
    g.addEdge(3, 8);
    g.addEdge(3, 7);
    g.addEdge(4, 9);
    BreadthFirstPaths bfs = new BreadthFirstPaths(g, 0);
    int i = 0;
    for (int v : bfs.pathTo(9)){
      assertTrue( "Expected " + expected[i] + ", returned " + v, 
                  v == expected[i]);
      i++;
    }
  }
  
  @Test
  public void properVisitOrderOnUnbalancedTree(){
    int[] expected = {0, 9};
    Graph g = new Graph(10);
    g.addEdge(0, 9);
    g.addEdge(0, 1);
    g.addEdge(1, 2);
    g.addEdge(2, 3);
    g.addEdge(3, 4);
    g.addEdge(4, 5);
    g.addEdge(5, 6);
    g.addEdge(6, 7);
    g.addEdge(7, 8);
    BreadthFirstPaths bfs = new BreadthFirstPaths(g, 0);
    int i = 0;
    for (int v : bfs.pathTo(9)){
      assertTrue( "Expected " + expected[i] + ", returned " + v, 
                  v == expected[i]);
      i++;
    }
  }

  @Test
  public void properVisitOrderOnAGeneralGraph(){
    int[] expected = {0, 2, 3};
    try{
      Graph g = new Graph(new Scanner(new File("data/graphTest/tinyCG.txt")));
      BreadthFirstPaths bfs = new BreadthFirstPaths(g, 0);
      int i = 0;
      for (int v : bfs.pathTo(3)){
        assertTrue( "Expected " + expected[i] + ", returned " + v, 
                    v == expected[i]);
        i++;
      }
    }
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }
}
