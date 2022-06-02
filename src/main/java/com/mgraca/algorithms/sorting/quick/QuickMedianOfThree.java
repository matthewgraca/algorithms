package com.mgraca.algorithms.sorting.quick;

import java.util.Random;

public class QuickMedianOfThree{
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

    int mid = getMedian(a, lo, lo + (hi - lo) / 2, hi);
    exchange(a, lo, mid);

    int j = partition(a, lo, hi); // place partitioning item in proper place
    sort(a, lo, j-1);             // sort left of paritioned item
    sort(a, j+1, hi);             // sort right of partitioned item
  }

  /**
   * Finds the index containing the median of three elements of an array
   * @param lo  The lower index
   * @param mid The middle index
   * @param hi  The upper index
   * @return  The index containing the median value
   */
  protected static <T extends Comparable<? super T>> int getMedian(T[] a, int lo, int mid, int hi){
    if (less(a[lo], a[mid])){ // reduce to 102, 201, 210
      if (less(a[mid], a[hi]))
        return mid;   // 012
      else{
        if (less(a[lo], a[hi]))
          return hi;  // 021
        else 
          return lo;  // 120
      }
    }
    else {  // 102, 201, 210
      if (less(a[lo], a[hi]))
        return lo;    // 102
      else{ 
        if (less(a[mid], a[hi]))
          return hi;  // 201
        else
          return mid; // 210
      }
    }
  }

  /**
   * Partitions an array such that the elements to the left of the item are smaller and 
   * the elements to the right are larger than it
   * @param a The array containing the items to partition
   * @param lo  The lower index of the items to partition
   * @param hi  The upper index of the items to partition
   * @return  The index containing the location of the partitioned item
   */
  private static <T extends Comparable<? super T>> int partition(T[] a, int lo, int hi){
    int i = lo, j = hi+1;
    T v = a[lo];
    while (true){
      while (less(a[++i], v)){  // scan i->hi
        if (i == hi)
          break;
      }
      while (less(v, a[--j])){  // scan lo<-j 
        if (j == lo)
          break;
      }
      if (i >= j)               // check for complete scan
        break;
      exchange(a, i, j);        
    }
    exchange(a, lo, j);
    return j;
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
