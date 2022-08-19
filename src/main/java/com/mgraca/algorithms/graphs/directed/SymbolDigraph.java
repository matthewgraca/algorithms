package com.mgraca.algorithms.graphs.directed;

import com.mgraca.algorithms.searching.balancedsearchtree.RedBlackBST;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Symbol graph that uses a red-black bst to enable strings as vertices
 */
public class SymbolDigraph{
  private RedBlackBST<String, Integer> st;  // string -> index
  private String[] keys;                    // index -> string
  private Digraph G;

  /**
   * Creates a symbol digraph
   * @param filename name of the file being read
   * @param delimiter determines each unit of data to read
   */
  public SymbolDigraph(String filename, String delimiter){
    try{
      File file = new File(filename);
      Scanner fin = new Scanner(file);
      
      // populate symbol table with vertices (st.get(string) = index)
      st = new RedBlackBST<String, Integer>();
      while (fin.hasNextLine()){
        String[] line = fin.nextLine().split(delimiter);
        for (int i = 0; i < line.length; i++)
          if (!st.contains(line[i]))
            st.put(line[i], st.size());
      }

      // populate keys (keys[index] = string)
      keys = new String[st.size()];
      for (String name : st.keys())
        keys[st.get(name)] = name;

      // populate graph
      G = new Digraph(st.size());
      fin = new Scanner(file);
      // for each row: first column is v, rest of the column connect to v
      while (fin.hasNextLine()){
        String[] line = fin.nextLine().split(delimiter);
        int v = st.get(line[0]);
        for (int i = 1; i < line.length; i++)
          G.addEdge(v, st.get(line[i]));
      }
    }
    catch(FileNotFoundException e){
      e.printStackTrace();
    }
  }

  /**
   * Checks if a given key is a vertex
   * @param key the key being searched for
   * @return true if the key is a vertex, false if not
   */
  public boolean contains(String key){
    return st.contains(key);
  }

  /**
   * Finds the index associated with a given key
   * @param key the key whose index is being searched for
   * @return the index associated with the key
   */
  public int indexOf(String key){
    return st.get(key);
  }

  /**
   * Gets the key associated with the given index v
   * @param v the index associated with a given key
   * @throws IllegalArgumentException if the index is out of range [0,V)
   * @return the key associated with the index v
   */
  public String nameOf(int v){
    validateVertex(v);
    return keys[v];
  }

  /**
   * Returns the underlying graph; it is the client's responsibility to not
   * mutate the digraph
   * @return the unerlying digraph
   */
  public Digraph digraph(){
    return G;
  }

  // checks if a vertex is valid
  private void validateVertex(int v){
    String msg = "Vertex out of range [0," + G.V() + ")";
    if (v < 0 || v >= G.V())
      throw new IllegalArgumentException(msg);
  }
}
