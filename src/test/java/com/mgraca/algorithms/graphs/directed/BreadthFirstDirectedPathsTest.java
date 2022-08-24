package com.mgraca.algorithms.graphs.directed;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public class BreadthFirstDirectedPathsTest{
  @Test(expected=IllegalArgumentException.class)
  public void vertexBelowRangeThrowsException(){
    Digraph g = new Digraph(3);
    BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(g, -1);
  }

  @Test(expected=IllegalArgumentException.class)
  public void vertexAboveRangeThrowsException(){
    Digraph g = new Digraph(3);
    BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(g, 3);
  }

  @Test
  public void properVisitOrderOnABalancedTree(){
    int[] expected = {0, 1, 4, 9};
    Digraph g = new Digraph(10); 
    g.addEdge(0, 2);
    g.addEdge(0, 1);
    g.addEdge(1, 4);
    g.addEdge(1, 3);
    g.addEdge(2, 6);
    g.addEdge(2, 5);
    g.addEdge(3, 8);
    g.addEdge(3, 7);
    g.addEdge(4, 9);
    BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(g, 0);
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
    Digraph g = new Digraph(10);
    g.addEdge(0, 9);
    g.addEdge(0, 1);
    g.addEdge(1, 2);
    g.addEdge(2, 3);
    g.addEdge(3, 4);
    g.addEdge(4, 5);
    g.addEdge(5, 6);
    g.addEdge(6, 7);
    g.addEdge(7, 8);
    BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(g, 0);
    int i = 0;
    for (int v : bfs.pathTo(9)){
      assertTrue( "Expected " + expected[i] + ", returned " + v, 
                  v == expected[i]);
      i++;
    }
  }

  @Test
  public void properVisitOrderOnAGeneralDigraph1(){
    int[] expected = {0, 5, 4, 3};
    try{
      Digraph g = new Digraph(new Scanner(new File("data/graphTest/tinyDG.txt")));
      BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(g, 0);
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

  @Test(expected=NullPointerException.class)
  public void properVisitOrderOnAGeneralDigraph2(){
    int[] expected = {};
    try{
      Digraph g = new Digraph(new Scanner(new File("data/graphTest/tinyDG.txt")));
      BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(g, 0);
      int i = 0;
      for (int v : bfs.pathTo(8)){
        assertTrue( "Expected " + expected[i] + ", returned " + v, 
                    v == expected[i]);
        i++;
      }
    }
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }

  @Test
  public void properVisitOrderOnAGeneralDigraph3(){
    int[] expected = {6, 9, 11, 12};
    try{
      Digraph g = new Digraph(new Scanner(new File("data/graphTest/tinyDG.txt")));
      BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(g, 6);
      int i = 0;
      for (int v : bfs.pathTo(12)){
        assertTrue( "Expected " + expected[i] + ", returned " + v, 
                    v == expected[i]);
        i++;
      }
    }
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }

  @Test
  public void properDistanceFoundForBalancedTree(){
    Digraph g = new Digraph(10); 
    g.addEdge(0, 2);
    g.addEdge(0, 1);
    g.addEdge(1, 4);
    g.addEdge(1, 3);
    g.addEdge(2, 6);
    g.addEdge(2, 5);
    g.addEdge(3, 8);
    g.addEdge(3, 7);
    g.addEdge(4, 9);
    BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(g, 0);
    int expected = 3;
    int actual = bfs.distTo(9);
    String msg = "Expected: " + expected + ", returned: " + actual; 
    assertTrue(msg, expected == actual);
  }

  @Test
  public void properDistanceFoundForBalancedTreeAtRoot(){
    Digraph g = new Digraph(10); 
    g.addEdge(0, 2);
    g.addEdge(0, 1);
    g.addEdge(1, 4);
    g.addEdge(1, 3);
    g.addEdge(2, 6);
    g.addEdge(2, 5);
    g.addEdge(3, 8);
    g.addEdge(3, 7);
    g.addEdge(4, 9);
    BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(g, 0);
    int expected = 0;
    int actual = bfs.distTo(0);
    String msg = "Expected: " + expected + ", returned: " + actual; 
    assertTrue(msg, expected == actual);
  }

  @Test
  public void properDistanceOnAGeneralDigraph(){
    try{
      Digraph g = new Digraph(new Scanner(new File("data/graphTest/tinyDG.txt")));
      BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(g, 0);
      int expected = 3;
      int actual = bfs.distTo(2);
      String msg = "Expected: " + expected + ", returned: " + actual; 
      assertTrue(msg, expected == actual);
    }
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }
}
