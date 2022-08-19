package com.mgraca.algorithms.graphs.directed;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DigraphTest{
  /****************************************************************************
   * Int Constructor Tests
   ***************************************************************************/
  
  @Test(expected=IllegalArgumentException.class)
  public void constructorWithNegativeVerticesThrowsException(){
    Digraph g = new Digraph(-1);
  }

  @Test
  public void constructorInitializesDigraph(){
    Digraph g = new Digraph(10);
    assertTrue(g.V() == 10);
  }

  /****************************************************************************
   * Scanner constructor tests
   ***************************************************************************/

  @Test(expected=IllegalArgumentException.class)
  public void nullArgumentThrowsException(){
    Digraph g = new Digraph(null);
  }

  @Test(expected=IllegalArgumentException.class)
  public void readVertexIsNegativeThrowsException(){
    try{
      File path = new File("data/graphTest/tinyGBadVertex.txt");
      Scanner in = new Scanner(path);
      Digraph g = new Digraph(in);
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
      Digraph g = new Digraph(in);
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
      Digraph g = new Digraph(in);
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
      Digraph g = new Digraph(in);
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
      Digraph g = new Digraph(in);
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
    Digraph g = new Digraph(4);
    g.addEdge(5, 0);
  }

  @Test(expected=IllegalArgumentException.class)
  public void addEdgeThrowsExceptionForVertexBOutOfRange(){
    Digraph g = new Digraph(4);
    g.addEdge(0, 5);
  }

  @Test
  public void addingValidEdgeAddsIt(){
    Digraph g = new Digraph(4);
    g.addEdge(0,3);
    assertTrue(g.E() == 1);
  }

  /****************************************************************************
   * Adjacency Tests
   ***************************************************************************/

  @Test(expected=IllegalArgumentException.class)
  public void adjacentUnderRangeVertexThrowsException(){
    Digraph g = new Digraph(4);
    g.addEdge(0,3);
    g.adj(-1);
  }

  @Test(expected=IllegalArgumentException.class)
  public void adjacentOverRangeVertexThrowsException(){
    Digraph g = new Digraph(4);
    g.addEdge(0,3);
    g.adj(5);
  }

  @Test
  public void adjacentOfEmptyDigraphWithinRangeOutputsCorrectly(){
    Digraph g = new Digraph(4);
    for (Integer i : g.adj(3))
      assertTrue(false);
    assertTrue(true);
  }

  @Test
  public void adjacentOfFilledDigraphWithinRangeOutputsCorrectly(){
    Digraph g = new Digraph(5);
    g.addEdge(0,1);
    g.addEdge(0,2);
    g.addEdge(0,4);
    g.addEdge(3,0);
    g.addEdge(3,4);
    int j = 4;
    for (Integer i : g.adj(0)){
      assertTrue(i == j);
      j /= 2;
    }
  }

  /****************************************************************************
   * To String Tests
   ***************************************************************************/

  @Test
  public void toStringTest(){
    Digraph g = null;
    try{
      File path = new File("data/graphTest/tinyDG.txt");
      Scanner in = new Scanner(path);
      g = new Digraph(in);
      in.close();
    }
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
    String gString = g.V() + " vertices, " + g.E() + " edges\n";
    gString += "0: 5 1 \n";
    gString += "1: \n";
    gString += "2: 0 3 \n";
    gString += "3: 5 2 \n";
    gString += "4: 3 2 \n";
    gString += "5: 4 \n";
    gString += "6: 9 4 8 0 \n";
    gString += "7: 6 9 \n";
    gString += "8: 6 \n";
    gString += "9: 11 10 \n";
    gString += "10: 12 \n";
    gString += "11: 4 12 \n";
    gString += "12: 9 \n";
    assertTrue( "\nExpected:\n" + gString + "\nReturned:\n" + g.toString(), 
                gString.equals(g.toString()));
  }

  /****************************************************************************
   * Reverse Tests
   ***************************************************************************/

  @Test
  public void reverseProperlyReversesDigraph(){
    Digraph g = null;
    try{
      File path = new File("data/graphTest/tinyDG.txt");
      Scanner in = new Scanner(path);
      g = new Digraph(in);
      g = g.reverse();
      in.close();
    }
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
    String gString = g.V() + " vertices, " + g.E() + " edges\n";
    gString += "0: 6 2 \n";
    gString += "1: 0 \n";
    gString += "2: 4 3 \n";
    gString += "3: 4 2 \n";
    gString += "4: 11 6 5 \n";
    gString += "5: 3 0 \n";
    gString += "6: 8 7 \n";
    gString += "7: \n";
    gString += "8: 6 \n";
    gString += "9: 12 7 6 \n";
    gString += "10: 9 \n";
    gString += "11: 9 \n";
    gString += "12: 11 10 \n";
    assertTrue( "\nExpected:\n" + gString + "\nReturned:\n" + g.toString(), 
                gString.equals(g.toString()));
  }

}
