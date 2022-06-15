package com.mgraca.algorithms.sorting.sortapps;

import java.util.Arrays;
import java.util.Comparator;
import java.net.URL;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;


public class California{
  private static final Comparator<String> CANDIDATE_ORDER = new CandidateComparator();
  /*
   * get test file from booksite
   */
  private static class CandidateComparator implements Comparator<String>{
    // index of order string = new order of letters
    String order = "RWQOJMVAHBSGZXNTCIEKUPDYFL";

    @Override
    public int compare(String a, String b){
      int n = Math.min(a.length(), b.length());
      // compare each character of a and b to the order string
      for (int i = 0; i < n; i++){
        int aindex = order.indexOf(a.charAt(i));
        int bindex = order.indexOf(b.charAt(i));
        if (aindex < bindex)
          return -1;
        else if (aindex > bindex) 
          return 1;
      }
      // if a > b, returns positive; elseif a < b, returns negative
      // if a = b, for loop exits and this returns 0
      return a.length() - b.length();
    }
  }
  public static void main(String[] args)
  {
    ArrayList<String> fileInput = new ArrayList<>();
    String[] candidates;
    try{
      URL url = new URL("https://introcs.cs.princeton.edu/java/data/california-gov.txt");
      Scanner in =  new Scanner(url.openStream());
      while (in.hasNextLine()){
        fileInput.add(in.nextLine().toUpperCase());
      }
      in.close();

    }
    catch(IOException e){
      e.printStackTrace();
    }
    candidates = fileInput.toArray(new String[fileInput.size()]);
    Arrays.sort(candidates, California.CANDIDATE_ORDER);
    for (String i : candidates){
      System.out.println(i);
    }
  }
}
