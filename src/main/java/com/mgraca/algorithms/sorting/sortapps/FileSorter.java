package com.mgraca.algorithms.sorting.sortapps;

import java.io.File;
import java.util.Arrays;

public class FileSorter{
  /**
   * Prints files in a given directory, sorted by file name
   */
  public static void main(String[] args)
  {
    File directory = new File(args[0]);
    if (!directory.exists()){
      System.out.println(args[0] + " does not exist.");
      return;
    }
    if (!directory.isDirectory()){
      System.out.println(args[0] + " is not a directory.");
      return;
    }

    File[] files = directory.listFiles();
    if (files == null){
      System.out.println("Could not read files.");
      return;
    }

    Arrays.sort(files);
    for (File i : files){
      System.out.println(i.getName());
    }
  }
}
