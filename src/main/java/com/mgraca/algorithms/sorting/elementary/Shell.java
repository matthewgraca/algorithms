package com.mgraca.algorithms.sorting.elementary;

public class Shell{
  /**
   * Sorts the contents of a given array
   * @param a The array being sorted
   */
  public static <T extends Comparable<? super T>> void sort(T[] a){
    int n = a.length;
    int h = 1;
    // finds the smallest increment <= floor(n/3)
    while (h < n/3){
      h = 3 * h + 1;  // 1, 4, 13, 40, ... 
    }
    // continuously h-sort the array ..., 40-sort, 13-sort, 4-sort, 1-sort
    while (h >= 1){
      for (int i = h; i < n; i++){
        for (int j = i; j >= h && less(a[j], a[j-h]); j -= h){
          exchange(a, j, j-h);
        }
      }
      h = h / 3;
    }
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
  private static void exchange(Object[] a, int i, int j){
    Object temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }
}
