package com.mgraca.algorithms.graphs.undirected;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GraphPropertiesTest{
  private GraphProperties initgp(String filename){
    Graph g = null;
    try{
      File file = new File(filename);
      Scanner fin = new Scanner(file);
      g = new Graph(fin);
    }
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
    return new GraphProperties(g);
  }

  private GraphProperties initgpBalancedTree(){
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
    return new GraphProperties(g);
  }

  private GraphProperties initgpUnbalancedTree(){
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
    return new GraphProperties(g);
  }

  /****************************************************************************
   * Constructor Tests
   ***************************************************************************/
  @Test(expected=IllegalArgumentException.class)
  public void unconnectedGraphThrowsException(){
    GraphProperties gp = initgp("data/graphTest/tinyG.txt");
  }

  @Test
  public void connectedGraphDoesNotThrowException(){
    GraphProperties gp = initgp("data/graphTest/tinyCG.txt");
  }

  /****************************************************************************
   * Diameter Tests
   ***************************************************************************/
  @Test
  public void diameterOfTinyCG(){
    GraphProperties gp = initgp("data/graphTest/tinyCG.txt");
    int expected = 2;
    int actual = gp.diameter();
    String msg = "Expected: " + expected + ", returned: " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void diameterOfUnbalancedTree(){
    GraphProperties gp = initgpUnbalancedTree();
    int expected = 9;
    int actual = gp.diameter();
    String msg = "Expected: " + expected + ", returned: " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void diameterOfBalancedTree(){
    GraphProperties gp = initgpBalancedTree();
    int expected = 5;
    int actual = gp.diameter();
    String msg = "Expected: " + expected + ", returned: " + actual;
    assertTrue(msg, expected == actual);
  }

  /****************************************************************************
   * Radius Tests
   ***************************************************************************/
  @Test
  public void radiusOfTinyCG(){
    GraphProperties gp = initgp("data/graphTest/tinyCG.txt");
    int expected = 2;
    int actual = gp.radius();
    String msg = "Expected: " + expected + ", returned: " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void radiusOfUnbalancedTree(){
    GraphProperties gp = initgpUnbalancedTree();
    int expected = 5;
    int actual = gp.radius();
    String msg = "Expected: " + expected + ", returned: " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void radiusOfBalancedTree(){
    GraphProperties gp = initgpBalancedTree();
    int expected = 3;
    int actual = gp.radius();
    String msg = "Expected: " + expected + ", returned: " + actual;
    assertTrue(msg, expected == actual);
  }

  /****************************************************************************
   * Center Tests
   ***************************************************************************/
  @Test
  public void centerOfTinyCG(){
    GraphProperties gp = initgp("data/graphTest/tinyCG.txt");
    int expected = 0;
    int actual = gp.center();
    String msg = "Expected: " + expected + ", returned: " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void centerOfUnbalancedTree(){
    GraphProperties gp = initgpUnbalancedTree();
    int expected = 3;
    int actual = gp.center();
    String msg = "Expected: " + expected + ", returned: " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void centerOfBalancedTree(){
    GraphProperties gp = initgpBalancedTree();
    int expected = 0;
    int actual = gp.center();
    String msg = "Expected: " + expected + ", returned: " + actual;
    assertTrue(msg, expected == actual);
  }

  /****************************************************************************
   * Wiener Tests
   ***************************************************************************/
  @Test
  public void wienerOfTinyCG(){
    GraphProperties gp = initgp("data/graphTest/tinyCG.txt");
    int expected = 22;
    int actual = gp.wiener();
    String msg = "Expected: " + expected + ", returned: " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void wienerOfUnbalancedTree(){
    GraphProperties gp = initgpUnbalancedTree();
    int expected = 165;
    int actual = gp.wiener();
    String msg = "Expected: " + expected + ", returned: " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void wienerOfBalancedTree(){
    GraphProperties gp = initgpBalancedTree();
    int expected = 127;
    int actual = gp.wiener();
    String msg = "Expected: " + expected + ", returned: " + actual;
    assertTrue(msg, expected == actual);
  }

  /****************************************************************************
   * Girth Tests
   ***************************************************************************/
  @Test
  public void girthOfTinyCG(){
    GraphProperties gp = initgp("data/graphTest/tinyCG.txt");
    int expected = 3;
    int actual = gp.girth();
    String msg = "Expected: " + expected + ", returned: " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void girthOfUnbalancedTree(){
    GraphProperties gp = initgpUnbalancedTree();
    int expected = Integer.MAX_VALUE;
    int actual = gp.girth();
    String msg = "Expected: " + expected + ", returned: " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void girthOfBalancedTree(){
    GraphProperties gp = initgpBalancedTree();
    int expected = Integer.MAX_VALUE;
    int actual = gp.girth();
    String msg = "Expected: " + expected + ", returned: " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void girthOfCycleGraph(){
    Graph g = new Graph(5);
    g.addEdge(0,1);
    g.addEdge(1,2);
    g.addEdge(2,3);
    g.addEdge(3,4);
    g.addEdge(4,0);
    GraphProperties gp = new GraphProperties(g);
    int expected = 5;
    int actual = gp.girth();
    String msg = "Expected: " + expected + ", returned: " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void girthOfSmallCycleWithinLargeCycleGraph(){
    Graph g = new Graph(5);
    g.addEdge(0,1);
    g.addEdge(1,2);
    g.addEdge(2,3);
    g.addEdge(3,4);
    g.addEdge(4,0);
    g.addEdge(4,2);
    GraphProperties gp = new GraphProperties(g);
    int expected = 3;
    int actual = gp.girth();
    String msg = "Expected: " + expected + ", returned: " + actual;
    assertTrue(msg, expected == actual);
  }
}
