package com.mgraca.algorithms.graphs.directed;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.mgraca.algorithms.fundamentals.Bag;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class DirectedDFSTest{
  @Test(expected=IllegalArgumentException.class)
  public void vertexBelowRangeThrowsException(){
    Digraph g = new Digraph(3);
    DirectedDFS dfs = new DirectedDFS(g, -1);
  }

  @Test(expected=IllegalArgumentException.class)
  public void vertexAboveRangeThrowsException(){
    Digraph g = new Digraph(3);
    DirectedDFS dfs = new DirectedDFS(g, 3);
  }

  @Test
  public void properReachabilityOnABalancedTree(){
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
    DirectedDFS dfs = new DirectedDFS(g, 0);
    for (int i = 0; i < 10; i++){
      String msg = "Expected " + i + " to be reachable from 0";
      assertTrue(msg, dfs.marked(i));
    }
    dfs = new DirectedDFS(g, 7);
  }
  
  @Test
  public void properReachabilityOnUnbalancedTree(){
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
    DirectedDFS dfs = new DirectedDFS(g, 0);
    for (int i = 0; i < 10; i++){
      String msg = "Expected " + i + " to be reachable from 0";
      assertTrue(msg, dfs.marked(i));
    }
    dfs = new DirectedDFS(g, 1);
    String msg = "9 should not be reachable from 1";
    assertTrue(msg, !dfs.marked(9));
  }

  @Test
  public void properReachablilityOnAGeneralDigraphFromVertex1(){
    int[] reachableFrom1 = {1};
    int[] unreachableFrom1 = {2,3,4,5,6,7,8,9,10,11,12};
    try{
      Digraph g = new Digraph(new Scanner(new File("data/graphTest/tinyDG.txt")));
      DirectedDFS dfs = new DirectedDFS(g, 1);
      for (int i : reachableFrom1){
        String msg = "Expected " + i + " to be reachable from 1";
        assertTrue(msg, dfs.marked(i));
      }
      for (int i : unreachableFrom1){
        String msg = "Expected " + i + " to be unreachable from 1";
        assertTrue(msg, !dfs.marked(i));
      }
    }
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }

  @Test
  public void properReachablilityOnAGeneralDigraphFromVertex2(){
    int[] reachableFrom2 = {0,1,2,3,4,5};
    int[] unreachableFrom2 = {6,7,8,9,10,11,12};
    try{
      Digraph g = new Digraph(new Scanner(new File("data/graphTest/tinyDG.txt")));
      DirectedDFS dfs = new DirectedDFS(g, 2);
      for (int i : reachableFrom2){
        String msg = "Expected " + i + " to be reachable from 2";
        assertTrue(msg, dfs.marked(i));
      }
      for (int i : unreachableFrom2){
        String msg = "Expected " + i + " to be unreachable from 2";
        assertTrue(msg, !dfs.marked(i));
      }
    }
    catch (FileNotFoundException e){
      e.printStackTrace();
    }

  }

  @Test
  public void manySourcesOnGeneralDigraphCorrectlyMarksReachableVertices(){
    int[] reachableFrom126 = {0,1,2,3,4,5,6,8,9,10,11,12};
    int[] unreachableFrom126 = {7};
    Bag<Integer> bag = new Bag<>();
    bag.add(1);
    bag.add(2);
    bag.add(6);
    try{
      Digraph g = new Digraph(new Scanner(new File("data/graphTest/tinyDG.txt")));
      DirectedDFS dfs = new DirectedDFS(g, 1);
      dfs = new DirectedDFS(g, bag);
      for (int i : reachableFrom126){
        String msg = "Expected " + i + " to be reachable from 1, 2, or 6";
        assertTrue(msg, dfs.marked(i));
      }
      for (int i: unreachableFrom126){
        String msg = "Expected " + i + " to be unreachable from 1, 2, or 6";
        assertTrue(msg, !dfs.marked(i));
      }
    }
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }
}
