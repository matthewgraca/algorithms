package com.mgraca.algorithms.searching.searchapps;

/**
 * Implements the prototypical filter example, de-duplication.
 * Wherein we read a stream of inputs, remove all duplicate keys, and print 
 * the result. We read one string at a time, so memory requirements are light.
 * The result is a string of all the distinct keys from the standard input.
 * 
 * While it would be preferrable to not use files and properly test the output,
 * a key point of this exercise is light memory requirements, using one string 
 * at a time.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DeDup{
  public static void main(String[] args){
    try{
      File file = new File("data/tinyTale.txt");
      Scanner sc = new Scanner(file);
      SET<String> set = new SET<>();
      while (sc.hasNext()){
        String key = sc.next();
        if (!set.contains(key)){
          set.add(key);
          System.out.print(key + " ");
        }
      }
      System.out.println();
    }
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }
}
