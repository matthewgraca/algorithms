package com.mgraca.algorithms.searching.searchapps;

/**
 * Uses a .csv file to associate columns together like a key-value pair.
 * Allows the user to specify two columns to associate together, then 
 * perform a lookup using the key to find its associated value.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import com.mgraca.algorithms.searching.hashtable.SeparateChainingHashST;

public class LookupCSV{
  public static void main(String[] args){
    try{
      // get file path, key column, and val column
      Scanner in = new Scanner(System.in);
      String filePath = args[0];
      int keyField = Integer.parseInt(args[1]);
      int valField = Integer.parseInt(args[2]);
      File file = new File(filePath);
      Scanner sc = new Scanner(file);

      // read file, add key-val pairs to the symbol table
      SeparateChainingHashST<String, String> st = new SeparateChainingHashST<>();
      while (sc.hasNextLine()){
        String line = sc.nextLine();
        String[] tokens = line.split(",");
        String key = tokens[keyField];
        String val = tokens[valField];
        st.put(key, val);
      }

      // read user input for key, then get val associated with the key
      while (in.hasNextLine()){
        String query = in.next();
        if (st.contains(query))
          System.out.println(st.get(query));
      }
      in.close();
      sc.close();
    }
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }
}
