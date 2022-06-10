package com.mgraca.algorithms.sorting.priorityqueue;

public class Heap{
  /**
   * Sorts the contents of a given array
   * @param a The array being sorted
   */
  public static <T extends Comparable<? super T>> void sort(T[] a){
    int n = a.length;
    // heapify
    for (int k = n/2; k >= 1; k--){
      sink(a, k, n);
    }
    // sortdown
    while (n > 1){
      exchange(a, 1, n--);
      sink(a, 1, n);
    }
  }

  /**
   * Top-down reheapify
   * @param a The array to reheapify
   * @param k The index of the current node
   * @param n The index of the last leaf node
   */
  private static <T extends Comparable<? super T>> void sink(T[] a, int k, int n){
    // while k's child is a node that exists 
    while (2*k <= n){
      int j = 2*k;
      if (j < n && less(a, j, j+1))  // prepare a swap with largest child 
        j++;
      if(!less(a, k, j)) // however, if that swap would make the parent < child, stop
        break;
      // swap, prepare to check children
      exchange(a, k, j);
      k = j;
    }
  }

  /**
   * Swaps the contents of two elements in an array
   * @param a The array containing the elements to be swapped
   * @param i The element whose data will be swapped with j
   * @param j The element whose data will be swapped with i
   */
  private static <T extends Comparable<? super T>> void exchange(T[] a, int i, int j){
    // off by 1 to support indexing from 1 instead of 0
    T temp = a[i-1];
    a[i-1] = a[j-1];
    a[j-1] = temp;
  }

  /**
   * Checks if one item is smaller than another
   * @param a The array containing the items being checked
   * @param i Index of an item being checked
   * @param j Index of the other item being checked against
   * @return  True if a[i] is smaller than a[j]
   */
  private static <T extends Comparable<? super T>> boolean less(T[] a, int i, int j){
    // off by 1 to support indexing from 1 instead of 0
    return a[i-1].compareTo(a[j-1]) < 0;
  }
}
