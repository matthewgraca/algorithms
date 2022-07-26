package com.mgraca.algorithms.searching.searchapps;

/**
 * Another classic filter, where the set is used to determine which keys from 
 * the input stream get passed to the output stream.
 * A blacklist defines keys which are not good to pass.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BlackFilter{
  public static void main(String[] args){
    SET<String> set = new SET<>();
    try{
      // open blacklist, store into the set
      File file = new File("data/list.txt");
      Scanner sc = new Scanner(file);
      while (sc.hasNext()){
        set.add(sc.next());
      }
      sc.close();

      // open input file, check it against the blacklist 
      file = new File("data/tinyTale.txt");
      sc = new Scanner(file);
      while (sc.hasNext()){
        String key = sc.next();
        if (!set.contains(key))
          System.out.print(key + " ");
      }
      System.out.println();
      sc.close();
    }
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }
}
