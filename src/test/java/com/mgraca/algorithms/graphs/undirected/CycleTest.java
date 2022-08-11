package com.mgraca.algorithms.graphs.undirected;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class CycleTest{
  @Test
  public void treeHasNoCycle(){
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
    Cycle c = new Cycle(g);
    assertTrue(!c.hasCycle());
  }

  @Test
  public void unbalancedTreeHasNoCycle(){
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
    Cycle c = new Cycle(g);
    assertTrue(!c.hasCycle());
  }

  @Test
  public void generalGraphIsNotBipartite(){
    try{
      Graph g = new Graph(new Scanner(new File("data/graphTest/tinyCG.txt")));
      Cycle c = new Cycle(g);
      assertTrue(c.hasCycle());
    }
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }
}
