package com.mgraca.algorithms.sorting.sortapps;

import com.mgraca.algorithms.sorting.priorityqueue.IndexMinPQ;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class SPT{
  /**
   * Reads job names and processing times from std input and prints a schedule 
   *  that minimizes average completion time using shortest processing time 
   *  first rule.
   */
  public static void main(String[] args)
  {
    try {
      // read processes and their names from a file
      File file = new File("data/process-list.txt");
      Scanner in = new Scanner(file);
      int count = 0;

      // add them to priority queue
      //PriorityQueue<Integer> pq = new PriorityQueue<>();
      IndexMinPQ<Integer> pq = new IndexMinPQ<>(10);
      while (in.hasNextLine()){
        int index = in.nextInt();
        int value = in.nextInt();
        pq.insert(index, value);
        in.nextLine();
        count++;
      }
      in.close();
      // sjf schedule
      /*while (pq.peek() != null){
        System.out.print(pq.poll() + " ");
      }*/
      for (int i = 0; i < count; i++){
        System.out.println("Process " + pq.minIndex() + ", Burst time " + pq.minKey());
        pq.delMin();
      }
    }
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }
}

