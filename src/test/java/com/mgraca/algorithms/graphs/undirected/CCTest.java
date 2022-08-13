package com.mgraca.algorithms.graphs.undirected;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class CCTest{
  @Test(expected=IllegalArgumentException.class)
  public void idVertexBelowRangeThrowsException(){
    Graph g = new Graph(3);
    CC cc = new CC(g);
    cc.id(-1);
  }

  @Test(expected=IllegalArgumentException.class)
  public void idVertexAboveRangeThrowsException(){
    Graph g = new Graph(3);
    CC cc = new CC(g);
    cc.id(3);
  }

  @Test(expected=IllegalArgumentException.class)
  public void connectedVertexAboveRangeArg1ThrowsException(){
    Graph g = new Graph(3);
    CC cc = new CC(g);
    cc.connected(3, 0);
  }

  @Test(expected=IllegalArgumentException.class)
  public void connectedVertexAboveRangeArg2ThrowsException(){
    Graph g = new Graph(3);
    CC cc = new CC(g);
    cc.connected(0, 3);
  }

  @Test(expected=IllegalArgumentException.class)
  public void connectedVertexAboveRangeBothArgsThrowsException(){
    Graph g = new Graph(3);
    CC cc = new CC(g);
    cc.connected(3, 3);
  }

  @Test(expected=IllegalArgumentException.class)
  public void connectedVertexBelowRangeArg1ThrowsException(){
    Graph g = new Graph(3);
    CC cc = new CC(g);
    cc.connected(-1, 0);
  }

  @Test(expected=IllegalArgumentException.class)
  public void connectedVertexBelowRangeArg2ThrowsException(){
    Graph g = new Graph(3);
    CC cc = new CC(g);
    cc.connected(0, -1);
  }

  @Test(expected=IllegalArgumentException.class)
  public void connectedVertexBelowRangeBothArgsThrowsException(){
    Graph g = new Graph(3);
    CC cc = new CC(g);
    cc.connected(-1, -1);
  }

  @Test
  public void connectedTreeIsConnected(){
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
    CC cc = new CC(g);
    assertTrue(cc.connected(0, 9));
    assertTrue(cc.count() == 1);
  }

  @Test
  public void properConnectedDetectionOnNotConnectredGraph(){
    try{
      Graph g = new Graph(new Scanner(new File("data/graphTest/tinyG.txt")));
      CC cc = new CC(g);
      assertTrue(cc.connected(0, 4));
      assertTrue(!cc.connected(0, 7));
      assertTrue(!cc.connected(7, 12));
      assertTrue(cc.count() == 3);
    }
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }
}
