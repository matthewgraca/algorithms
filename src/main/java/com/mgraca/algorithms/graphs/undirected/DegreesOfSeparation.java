package com.mgraca.algorithms.graphs.undirected;

import java.util.Scanner;

public class DegreesOfSeparation{
  public static void main(String[] args){
    // construct symbol graph
    String filename = args[0];
    String delimiter = args[1];
    String source = args[2];
    SymbolGraph sg = new SymbolGraph(filename, delimiter);

    // determine if source input is valid
    if (!sg.contains(source)){
      System.out.println("Source vertex not in graph.");
      return;
    }

    // get graph and perform bfs from source to every vertex
    Graph G = sg.graph();
    int s = sg.indexOf(source);
    BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);

    // read user input for destination
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()){
      String sink = in.nextLine();
      if (sg.contains(sink)){
        int t = sg.indexOf(sink);
        if (bfs.hasPathTo(t)){
          // contains destination and path exists - print out path
          for (int v : bfs.pathTo(t))
            System.out.println("   " + sg.nameOf(v));
        }
        else // contains destination but not connected
          System.out.println("The source is not connected to the destination.");
      }
      else  // does not contain destination
        System.out.println("Graph does not contain the destination.");
    }
  }
}
