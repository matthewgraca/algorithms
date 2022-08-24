package com.mgraca.algorithms.graphs.directed;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;

public class DepthFirstDirectedPathsTest{
  @Test(expected=IllegalArgumentException.class)
  public void vertexBelowRangeThrowsException(){
    Digraph g = new Digraph(3);
    DepthFirstDirectedPaths dfs = new DepthFirstDirectedPaths(g, -1);
  }

  @Test(expected=IllegalArgumentException.class)
  public void vertexAboveRangeThrowsException(){
    Digraph g = new Digraph(3);
    DepthFirstDirectedPaths dfs = new DepthFirstDirectedPaths(g, 3);
  }

  @Test
  public void properPathForABalancedTree(){
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
    DepthFirstDirectedPaths dfp = new DepthFirstDirectedPaths(g, 0);
    int i = 0;
    for (int v : dfp.pathTo(9)){
      assertTrue( "Expected " + expected[i] + ", returned " + v, 
                  v == expected[i]);
      i++;
    }
  }

  @Test
  public void properPathForAnUnbalancedTree1(){
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
    DepthFirstDirectedPaths dfp = new DepthFirstDirectedPaths(g, 0);
    int i = 0;
    for (int v : dfp.pathTo(9)){
      assertTrue( "Expected " + expected[i] + ", returned " + v, 
                  v == expected[i]);
      i++;
    }
  }

  @Test
  public void properPathForAnUnbalancedTree2(){
    int[] expected = {0, 1, 2, 3, 4, 5, 6, 7};
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
    DepthFirstDirectedPaths dfp = new DepthFirstDirectedPaths(g, 0);
    int i = 0;
    for (int v : dfp.pathTo(7)){
      assertTrue( "Expected " + expected[i] + ", returned " + v, 
                  v == expected[i]);
      i++;
    }
  }

  @Test
  public void properPathForAGeneralDigraph1(){
    int[] expected = {0, 5, 4, 3, 2};
    try{
      Digraph g = new Digraph(new Scanner(new File("data/graphTest/tinyDG.txt")));
      DepthFirstDirectedPaths dfp = new DepthFirstDirectedPaths(g, 0);
      int i = 0;
      for (int v : dfp.pathTo(2)){
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
  public void properPathForAGeneralDigraph2(){
    int[] expected = {};
    try{
      Digraph g = new Digraph(new Scanner(new File("data/graphTest/tinyDG.txt")));
      DepthFirstDirectedPaths dfp = new DepthFirstDirectedPaths(g, 0);
      int i = 0;
      for (int v : dfp.pathTo(6)){
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
  public void properPathForAGeneralDigraph3(){
    int[] expected = {6, 9, 11, 12};
    try{
      Digraph g = new Digraph(new Scanner(new File("data/graphTest/tinyDG.txt")));
      DepthFirstDirectedPaths dfp = new DepthFirstDirectedPaths(g, 6);
      int i = 0;
      for (int v : dfp.pathTo(12)){
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
