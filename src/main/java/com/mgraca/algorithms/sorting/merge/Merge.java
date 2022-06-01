package com.mgraca.algorithms.sorting.merge;

/*
 * Due to Java being unable to use generic variables under static contexts,
 * it is necessary to pass the auxilliary array through the method.
 * The book provides an implementation which doesn't require passing the array; 
 * however, that would require the use of Comparable which would trigger 
 * compiler complaints about unchecked warnings. These warnings are probably 
 * safe to suppress, but I've chosen to maintain a consistent implementation.
 */
public class Merge{
  /**
   * Sorts the contents of a given array
   * @param a The array being sorted
   */
  public static <T extends Comparable<? super T>> void sort(T[] a){
    int n = a.length;
    @SuppressWarnings("unchecked")
    T[] aux = (T[])new Comparable<?>[n];
    sort(a, aux, 0, n-1);
  }

  /**
   * Method responsible for the recursive calls of sort
   * @param a The array being sorted
   * @param aux The auxilliary arrya supporting the sort
   * @param lo  The lower index of the array being sorted
   * @param hi  The upper index of the array being sorted
   */
  private static <T extends Comparable<? super T>> void 
  sort(T[] a, T[] aux, int lo, int hi){
    if (hi <= lo){
      return;
    }
    int mid = lo + (hi-lo) / 2; // overflow safe method of taking an average
    sort(a, aux, lo, mid);      // sort left
    sort(a, aux, mid+1, hi);    // sort right
    merge(a, aux, lo, mid, hi); // merge results
  }

  /**
   * Takes two ordered arrays and combines them into one ordered array
   * @param a The array containing the two ordered arrays
   * @param aux The auxilliary array assisting in the sort
   * @param lo  The lower index of the first array
   * @param mid The upper index of the first and lower index of the second array
   * @param hi  The upper index of the second array
   */
  public static <T extends Comparable<? super T>> void 
  merge(T[] a, T[] aux, int lo, int mid, int hi){
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
  private static <T extends Comparable<? super T>> boolean less(T v, T w){
    return v.compareTo(w) < 0;
  }
}
