package com.mgraca.algorithms.sorting;

/*
 * Finds the smallest item in the array and exashnges it with the first entry.
 * Then, find the next smallest item and exhange it with the second entry.
 * Continue in this manner until the entire array is sorted.
 * Heuristic: you repeatedly select the smallest remaining value.
 * 
 * n^2/2 compares
 * n exchanges
 */
public class Selection{
  /**
   * Sorts the contents of a given array
   * @param a The array being sorted
   */
  public static void sort(Comparable[] a){
    int n = a.length;
    for (int i = 0; i < n; i++){
      int minIndex = i;
      // scan the array for the smallest value
      for (int j = i+1; j < n; j++){
        // swap i with the minIndex
        if (less(a[j], a[minIndex])){
          minIndex = j;
        }
      }
      exchange(a, i, minIndex);
    }
  }

  /**
   * Checks if one item is smaller than another
   * @param v An item being checked
   * @param w The other item being checked against
   * @return  True if v is smaller than w
   */
  private static boolean less(Comparable v, Comparable w){
    return v.compareTo(w) < 0;
  }

  /**
   * Swaps the contents of two elements in an array
   * @param a The array containing the elements to be swapped
   * @param i The element whose data will be swapped with j
   * @param j The element whose data will be swapped with i
   */
  private static void exchange(Comparable[] a, int i, int j){
    Comparable temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }
}
