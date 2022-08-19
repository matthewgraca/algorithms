package com.mgraca.algorithms.graphs.directed;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class SymbolDigraphTest{
  @Test
  public void constructorReadsFile(){
    String filename = "data/routes.txt";
    String delimiter = " ";
    SymbolDigraph sg = new SymbolDigraph(filename, delimiter);
    assertTrue(sg.digraph().V() == 10);
    assertTrue(sg.digraph().E() == 18);
  }

  // creates a symbol graph using the routes dataset
  private SymbolDigraph initSGRoutes(){
    String filename = "data/routes.txt";
    String delimiter = " ";
    return new SymbolDigraph(filename, delimiter);
  }

  @Test
  public void graphContainsAllItems(){
    SymbolDigraph sg = initSGRoutes();
    String[] vertices = { "JFK", "MCO", "ORD", "DEN", "HOU", 
                          "DFW", "PHX", "ATL", "LAX", "LAS"};
    for (String s : vertices)
      assertTrue(sg.contains(s));
  }

  @Test
  public void graphIndexOfAndNameOfAreInverse(){
    SymbolDigraph sg = initSGRoutes();
    String[] vertices = { "JFK", "MCO", "ORD", "DEN", "HOU", 
                          "DFW", "PHX", "ATL", "LAX", "LAS"};
    for (String s : vertices)
      assertTrue(sg.nameOf(sg.indexOf(s)).equals(s));
  }

  @Test
  public void underlyingDigraphIsCorrectlySetUp(){
    SymbolDigraph sg = initSGRoutes();
    String expected = sg.digraph().toString();
    String actual = "10 vertices, 18 edges\n" +
      "0: 2 7 1 \n" +
      "1: \n" +
      "2: 7 6 5 4 3 \n" +
      "3: 9 6 \n" +
      "4: 1 \n" +
      "5: 4 6 \n" +
      "6: 8 \n" + 
      "7: 1 4 \n" +
      "8: \n" +
      "9: 6 8 \n";
    String msg = "\nExpected " + expected + "\nActual " + actual; 
    assertTrue(msg, expected.equals(actual));
  }
}
