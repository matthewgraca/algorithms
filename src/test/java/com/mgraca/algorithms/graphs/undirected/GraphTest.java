package com.mgraca.algorithms.graphs.undirected;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GraphTest{
  /****************************************************************************
   * Int Constructor Tests
   ***************************************************************************/
  
  @Test(expected=IllegalArgumentException.class)
  public void constructorWithNegativeVerticesThrowsException(){
    Graph g = new Graph(-1);
  }

  @Test
  public void constructorInitializesGraph(){
    Graph g = new Graph(10);
    assertTrue(g.V() == 10);
  }

  /****************************************************************************
   * Scanner constructor tests
   ***************************************************************************/

  @Test(expected=IllegalArgumentException.class)
  public void nullArgumentThrowsException(){
    Graph g = new Graph(null);
  }

  @Test(expected=IllegalArgumentException.class)
  public void readVertexIsNegativeThrowsException(){
    try{
      File path = new File("data/graphTest/tinyGBadVertex.txt");
      Scanner in = new Scanner(path);
      Graph g = new Graph(in);
      in.close();
    }
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }

  @Test(expected=IllegalArgumentException.class)
  public void readEdgeIsNegativeThrowsException(){
    try{
      File path = new File("data/graphTest/tinyGBadEdge.txt");
      Scanner in = new Scanner(path);
      Graph g = new Graph(in);
      in.close();
    }
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }

  @Test(expected=IllegalArgumentException.class)
  public void readAddEdgeIsOutOfRangeThrowsException(){
    try{
      File path = new File("data/graphTest/tinyGBadAddEdge.txt");
      Scanner in = new Scanner(path);
      Graph g = new Graph(in);
      in.close();
    }
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void badFormatThrowsException(){
    try{
      File path = new File("data/graphTest/tinyGBadInputFormat.txt");
      Scanner in = new Scanner(path);
      Graph g = new Graph(in);
      in.close();
    }
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }

  @Test
  public void constructorAcceptsWellFormattedFileWithGoodInputs(){
    try{
      File path = new File("data/graphTest/tinyG.txt");
      Scanner in = new Scanner(path);
      Graph g = new Graph(in);
      in.close();
      assertTrue(g.V() == 13 && g.E() == 13);
    }
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }

  /****************************************************************************
   * Add edge Tests
   ***************************************************************************/

  @Test(expected=IllegalArgumentException.class)
  public void addEdgeThrowsExceptionForVertexAOutOfRange(){
    Graph g = new Graph(4);
    g.addEdge(5, 0);
  }

  @Test(expected=IllegalArgumentException.class)
  public void addEdgeThrowsExceptionForVertexBOutOfRange(){
    Graph g = new Graph(4);
    g.addEdge(0, 5);
  }

  @Test
  public void addingValidEdgeAddsIt(){
    Graph g = new Graph(4);
    g.addEdge(0,3);
    assertTrue(g.E() == 1);
  }

  /****************************************************************************
   * Adjacency Tests
   ***************************************************************************/

  @Test(expected=IllegalArgumentException.class)
  public void adjacentUnderRangeVertexThrowsException(){
    Graph g = new Graph(4);
    g.addEdge(0,3);
    g.adj(-1);
  }

  @Test(expected=IllegalArgumentException.class)
  public void adjacentOverRangeVertexThrowsException(){
    Graph g = new Graph(4);
    g.addEdge(0,3);
    g.adj(5);
  }

  @Test
  public void adjacentOfEmptyGraphWithinRangeOutputsCorrectly(){
    Graph g = new Graph(4);
    for (Integer i : g.adj(3))
      assertTrue(false);
    assertTrue(true);
  }

  @Test
  public void adjacentOfFilledGraphWithinRangeOutputsCorrectly(){
    Graph g = new Graph(5);
    g.addEdge(0,1);
    g.addEdge(0,2);
    g.addEdge(0,4);
    g.addEdge(3,4);
    int j = 4;
    for (Integer i : g.adj(0)){
      assertTrue(i == j);
      j /= 2;
    }
  }

  /****************************************************************************
   * Degree Tests
   ***************************************************************************/

  @Test(expected=IllegalArgumentException.class)
  public void degreeUnderRangeVertexThrowsException(){
    Graph g = new Graph(4);
    g.degree(-1);
  }

  @Test(expected=IllegalArgumentException.class)
  public void degreeOverRangeVertexThrowsException(){
    Graph g = new Graph(4);
    g.degree(5);
  }

  @Test
  public void degreeOfEmptyGraphOutputsCorrectly(){
    Graph g = new Graph(4);
    assertTrue(g.degree(0) == 0);
  }

  @Test
  public void degreeOfFilledGraphWithinRangeOutputsCorrectly(){
    Graph g = new Graph(5);
    g.addEdge(0,1);
    g.addEdge(0,2);
    g.addEdge(0,4);
    g.addEdge(3,4);
    assertTrue(g.degree(0) == 3);
  }

  /****************************************************************************
   * To String Tests
   ***************************************************************************/

  @Test
  public void toStringTest(){
    Graph g = null;
    try{
      File path = new File("data/graphTest/tinyG.txt");
      Scanner in = new Scanner(path);
      g = new Graph(in);
      in.close();
      assertTrue(g.V() == 13 && g.E() == 13);
    }
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
    String gString = g.V() + " vertices, " + g.E() + " edges\n";
    gString += "0: 6 2 1 5 \n";
    gString += "1: 0 \n";
    gString += "2: 0 \n";
    gString += "3: 5 4 \n";
    gString += "4: 5 6 3 \n";
    gString += "5: 3 4 0 \n";
    gString += "6: 0 4 \n";
    gString += "7: 8 \n";
    gString += "8: 7 \n";
    gString += "9: 11 10 12 \n";
    gString += "10: 9 \n";
    gString += "11: 9 12 \n";
    gString += "12: 11 9 \n";
    assertTrue( "\nExpected:\n" + gString + "\nReturned:\n" + g.toString(), 
                gString.equals(g.toString()));
  }
}
