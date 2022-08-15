package com.mgraca.algorithms.graphs.undirected;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class SymbolGraphTest{
  @Test
  public void constructorReadsFile(){
    String filename = "data/routes.txt";
    String delimiter = " ";
    SymbolGraph sg = new SymbolGraph(filename, delimiter);
    assertTrue(sg.graph().V() == 10);
    assertTrue(sg.graph().E() == 18);
  }

  // creates a symbol graph using the routes dataset
  private SymbolGraph initSGRoutes(){
    String filename = "data/routes.txt";
    String delimiter = " ";
    return new SymbolGraph(filename, delimiter);
  }

  @Test
  public void graphContainsAllItems(){
    SymbolGraph sg = initSGRoutes();
    String[] vertices = { "JFK", "MCO", "ORD", "DEN", "HOU", 
                          "DFW", "PHX", "ATL", "LAX", "LAS"};
    for (String s : vertices)
      assertTrue(sg.contains(s));
  }

  @Test
  public void graphIndexOfAndNameOfAreInverse(){
    SymbolGraph sg = initSGRoutes();
    String[] vertices = { "JFK", "MCO", "ORD", "DEN", "HOU", 
                          "DFW", "PHX", "ATL", "LAX", "LAS"};
    for (String s : vertices)
      assertTrue(sg.nameOf(sg.indexOf(s)).equals(s));
  }

  @Test
  public void underlyingGraphIsCorrectlySetUp(){
    SymbolGraph sg = initSGRoutes();
    String expected = sg.graph().toString();
    String actual = "10 vertices, 18 edges\n" +
      "0: 2 7 1 \n" +
      "1: 4 7 0 \n" +
      "2: 7 0 6 5 4 3 \n" +
      "3: 9 6 2 \n" +
      "4: 1 5 7 2 \n" +
      "5: 4 2 6 \n" +
      "6: 9 8 3 2 5 \n" + 
      "7: 1 2 4 0 \n" +
      "8: 9 6 \n" +
      "9: 6 8 3 \n";
    String msg = "\nExpected " + expected + "\nActual " + actual; 
    assertTrue(msg, expected.equals(actual));
  }
}
