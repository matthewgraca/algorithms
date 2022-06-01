package com.mgraca.algorithms.sorting.sortcompare;

import java.util.Random;
import com.mgraca.algorithms.sorting.elementary.*;
import com.mgraca.algorithms.sorting.merge.*;
import com.mgraca.algorithms.sorting.quick.*;

/* The purpose of this class is to compare two different sorting algorithms
 */
public class SortCompare
{
  /**
   * This method checks the time it takes to run a particular sorting algorithm
   * @param alg The algorithm the user wants to test
   * @param a   The array being sorted
   * @return    The time it took (in ms) to sort the array
   */
  public static <T extends Comparable<? super T>> double time(String alg, T[] a)
  {
    Stopwatch timer = new Stopwatch();
    switch(alg)
    {
      case "Insertion":
        Insertion.sort(a);
        break;
      case "Selection":
        Selection.sort(a);
        break;
      case "Shell":
        Shell.sort(a);
        break;
      case "Merge":
        Merge.sort(a);
        break;
      case "Quick":
        Quick.sort(a);
        break;
      case "QuickThreeWay":
        QuickThreeWay.sort(a);
        break;
      case "QuickInsertion":
        QuickInsertion.sort(a);
        break;
      case "QuickMedianOfThree":
        QuickMedianOfThree.sort(a);
        break;
      case "Heap":
        //Heap.sort(a);
        break;
      default:
        throw new RuntimeException("Invalid algorithm. Run with arguments: " + 
        "<alg1> <alg2> <array size> <trials>");
    }
    return timer.elapsedTime();
  }

  /**
   * This method initializes the array to sort with a bunch of random double values
   * @param alg     The algorithm the user wants to test
   * @param n       The number of items in the array
   * @param trials  The number of experiments the user wants to attempt
   * @return the total time a particular algorithm took to complete all the experiments
   */
  public static double timeRandomInput(String alg, int n, int trials)
  {
    double total = 0.0;
    Double[] a = new Double[n];
    Random rng = new Random();
    // perform the experiment as many time as specified by trials
    for (int t = 0; t < trials; t++)
    {
      // initialize the array with a random double value b/t 0.0 and 1.0 every experiment
      for (int i = 0; i < n; i++)
      {
        a[i] = rng.nextDouble();
      }
      total += time(alg, a);
    }
    return total;
  }

  public static void main(String[] args)
  {
    // command line: java SortCompare alg1 alg2 n trials
    String alg1 = args[0];
    String alg2 = args[1];
    int n = Integer.parseInt(args[2]);
    int trials = Integer.parseInt(args[3]);

    // experiments
    double time1 = timeRandomInput(alg1, n, trials);
    double time2 = timeRandomInput(alg2, n, trials);
    double ratio = time2 / time1;

    // output
    System.out.printf(alg1 + " took %.4fs, while " + alg2 + " took %.4fs.\n", time1, time2);
    System.out.printf("For %d random Doubles\n  %s is", n, alg1);
    System.out.printf(" %.1f times faster than %s\n", ratio, alg2);
  }
}
