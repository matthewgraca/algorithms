package com.mgraca.algorithms.searching.searchapps;

/**
 * An example of an inverted index, where values are used to locate keys
 * Associates each word in a text with the set of positions in the text where 
 * that word occurs.
 * Makes use of a table of lists, where the key is the word and the values 
 * are the set of positions in the text where the word occurs.
 *
 * Usage: java -jar target/(name of jar) data/(name of file)
 */

import com.mgraca.algorithms.searching.hashtable.SeparateChainingHashST;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Concordance{
  public static void main(String[] args){
    final int CONTEXT = 5;
    try{
      // read strings from file
      Scanner sc = new Scanner(new File(args[0]));
      Scanner in = new Scanner(System.in);

      // associate each string with an integer, counting up
      int count = 0;
      SeparateChainingHashST<String, ArrayList<Integer>> st = new SeparateChainingHashST<>();
      ArrayList<String> words = new ArrayList<>();
      while (sc.hasNext()){
        // ignore punctuation and case
        String word = sc.next().toLowerCase().replaceAll("[\\W]", "");
        words.add(word);
        // add the word-integer pair, or just add the integer to an existing word 
        if (!st.contains(word))
          st.put(word, new ArrayList<Integer>());
        st.get(word).add(count);
        count++;
      }
      sc.close();

      // read user input; get string and output the associated positions
      in = new Scanner(System.in);
      while (in.hasNextLine()){
        String query = in.next();
        ArrayList<Integer> indecies = st.get(query);
        // print out the context of the query word
        if (indecies != null){
          // iterate through each word that appears 
          for (int k = 0; k < indecies.size(); k++){
            int j = indecies.get(k);
            // print left context; if left context < actual context, start from 0
            for (int i = Math.max(0, j-CONTEXT+1); i < j; i++)
              System.out.print(words.get(i) + " ");
            System.out.print("**" + words.get(j) + "** ");
            // print right context; if right context > actual context, end at end
            for (int i = j+1; i < Math.min(j+CONTEXT, words.size()); i++)
              System.out.print(words.get(i) + " ");
            System.out.println();
          }
        }
      }
      System.out.println();
      in.close();
    }
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }
}
