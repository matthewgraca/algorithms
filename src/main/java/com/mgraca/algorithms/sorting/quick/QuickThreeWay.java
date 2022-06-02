package com.mgraca.algorithms.sorting.quick;

import java.util.Random;

public class QuickThreeWay{
  /**
   * Sorts the contents of a given array
   * @param a The array being sorted
   */
  public static <T extends Comparable<? super T>> void sort(T[] a){
    shuffle(a);
    sort(a, 0, a.length-1);
  }

  /**
   * Method responsible for the recursive calls of sort
   * @param a The array being sorted
   * @param lo  The lower index of the array being sorted
   * @param hi  The upper index of the array being sorted
   */
  private static <T extends Comparable<? super T>> void sort(T[] a, int lo, int hi){
    if (hi <= lo){
      return;
    }
    int lt = lo;
    int i = lo+1;
    int gt = hi;
    T v = a[lo];
    
    while (i <= gt){
      int compare = a[i].compareTo(v);
      if (compare < 0){
        exchange(a, i++, lt++);
      }
      else if (compare > 0){
        exchange(a, i, gt--);
      }
      else{
        i++;
      }
    }
                        // a[lt..gt] = v
    sort(a, lo, lt-1);  // a[lo..lt-1] < v
    sort(a, gt+1, hi);  // a[gt+1..hi] > v
  }

  /**
   * Checks if one item is smaller than another
   * @param v An item being checked
   * @param w The other item being checked against
   * @return  True if v is smaller than w
   */
  private static <T extends Comparable<? super T>> boolean less(T v, T w){
    return v.compareTo(w) < 0;
  }

  /**
   * Swaps the contents of two elements in an array
   * @param a The array containing the elements to be swapped
   * @param i The element whose data will be swapped with j
   * @param j The element whose data will be swapped with i
   */
  private static <T extends Comparable<? super T>> void exchange(T[] a, int i, int j){
    T temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  /**
   * Shuffles the contents of an array, using Fisher-Yates for a uniform distribution
   * @param a The array being shuffled
   */
  private static <T extends Comparable<? super T>> void shuffle(T[] a){
    int n = a.length;
    Random rng = new Random();
    for (int i = n-1; i > 0; i--){
      int j = rng.nextInt(i+1); // [0, n-1], [0, n-2], ... , [0, 2], [0, 1]
      exchange(a, i, j);
    }
  }
}
