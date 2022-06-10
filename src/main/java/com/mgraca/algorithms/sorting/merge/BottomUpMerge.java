package com.mgraca.algorithms.sorting.merge;

/*
 * Cannot use T as a static variable in this context, so we defer to the
 * book's implementaiton of generics and suppress warnings for unchecked 
 * call to compareTo(), since we know only Comparable objects will be 
 * passed
 */
public class BottomUpMerge{
  private static Comparable[] aux;

  /**
   * Sorts the contents of a given array
   * @param a The array being sorted
   */
  public static void sort(Comparable[] a){
    int n = a.length;
    aux = new Comparable[n];
    for (int len = 1; len < n; len *= 2){
      for (int lo = 0; lo < n-len; lo += len+len){
        merge(a, lo, lo+len-1, Math.min(lo+len+len-1, n-1));
      }
    }
  }

  /**
   * Takes two ordered arrays and combines them into one ordered array
   * @param a The array containing the two ordered arrays
   * @param lo  The lower index of the first array
   * @param mid The upper index of the first and lower index of the second array
   * @param hi  The upper index of the second array
   */
  public static void merge(Comparable[] a, int lo, int mid, int hi){
    int i = lo, j = mid + 1;
    for (int k = lo; k <= hi; k++){
      aux[k] = a[k];
    }
    for (int k = lo; k <= hi; k++){
      if (i > mid){     // if the lower array is depleted, take from the upper
        a[k] = aux[k++];
      }
      else if (j > hi){ // if the upper array is depleted, take from the lower
        a[k] = aux[i++];
      }
      else if (less(aux[j], aux[i])){  // if neither are depleted, compare
        a[k] = aux[j++];
      }
      else{
        a[k] = aux[i++];
      }
    }
  }

  /**
   * Checks if one item is smaller than another
   * @param v An item being checked
   * @param w The other item being checked against
   * @return  True if v is smaller than w
   */
  private static boolean less(Comparable v, Comparable w){
    // guaranteed to be Comparable objects at this point
    @SuppressWarnings("unchecked")
    boolean comp = v.compareTo(w) < 0;
    return comp;
  }
}
