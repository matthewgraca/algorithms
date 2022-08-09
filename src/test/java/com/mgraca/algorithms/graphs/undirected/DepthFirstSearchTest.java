package com.mgraca.algorithms.graphs.undirected;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class DepthFirstSearchTest{
  @Test(expected=IllegalArgumentException.class)
  public void vertexBelowRangeThrowsException(){
    Graph g = new Graph(3);
    DepthFirstSearch dfs = new DepthFirstSearch(g, -1);
  }

  @Test(expected=IllegalArgumentException.class)
  public void vertexAboveRangeThrowsException(){
    Graph g = new Graph(3);
    DepthFirstSearch dfs = new DepthFirstSearch(g, 3);
  }

  @Test
  public void properVisitOrderOnABalancedTree(){
    ArrayList<Integer> visitOrder = 
      new ArrayList<>(Arrays.asList(0, 1, 3, 7, 8, 4, 9, 2, 5, 6));
    Graph g = new Graph(visitOrder.size()); 
    g.addEdge(0, 2);
    g.addEdge(0, 1);
    g.addEdge(1, 4);
    g.addEdge(1, 3);
    g.addEdge(2, 6);
    g.addEdge(2, 5);
    g.addEdge(3, 8);
    g.addEdge(3, 7);
    g.addEdge(4, 9);
    DepthFirstSearch dfs = new DepthFirstSearch(g, 0);
    String msg = 
      "\nExpected " + visitOrder.toString() + 
      "\nReturned " + dfs.visitOrder();
    assertTrue(msg, dfs.visitOrder().equals(visitOrder.toString()));
    assertTrue(dfs.count() == visitOrder.size());
  }
  
  @Test
  public void properVisitOrderOnUnbalancedTree(){
    ArrayList<Integer> visitOrder =
      new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    Graph g = new Graph(visitOrder.size());
    g.addEdge(0, 9);
    g.addEdge(0, 1);
    g.addEdge(1, 2);
    g.addEdge(2, 3);
    g.addEdge(3, 4);
    g.addEdge(4, 5);
    g.addEdge(5, 6);
    g.addEdge(6, 7);
    g.addEdge(7, 8);
    DepthFirstSearch dfs = new DepthFirstSearch(g, 0);
    String msg = 
      "\nExpected " + visitOrder.toString() + 
      "\nReturned " + dfs.visitOrder();
    assertTrue(msg, dfs.visitOrder().equals(visitOrder.toString()));
    assertTrue(dfs.count() == visitOrder.size());
  }

  @Test
  public void properVisitOrderOnAGeneralGraph(){
    ArrayList<Integer> visitOrder = 
      new ArrayList<>(Arrays.asList(0, 2, 1, 3, 5, 4));
    try{
      Graph g = new Graph(new Scanner(new File("data/graphTest/tinyCG.txt")));
      DepthFirstSearch dfs = new DepthFirstSearch(g, 0);
      String msg = 
        "\nExpected " + visitOrder.toString() + 
        "\nReturned " + dfs.visitOrder();
      assertTrue(msg, dfs.visitOrder().equals(visitOrder.toString()));
      assertTrue(dfs.count() == visitOrder.size());
    }
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }
}
