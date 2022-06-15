package com.mgraca.algorithms.sorting.priorityqueue;

import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;

public class IndexMinPQ<Key extends Comparable<Key>>{
  private int[] pq;   // indeces of keys in binary heap order
  private int[] qp;   // inverse of pq[] 
  private Key[] keys; // keys ordered by index
  private int n;      // number of elements in the priority queue
  private int max;

  /**
   * Creates a priority queue of a given initial capacity
   * @param max Initial capacity size
   */
  public IndexMinPQ(int max){
    @SuppressWarnings("unchecked")
    Key[] temp = (Key[]) new Comparable[max+1];
    keys = temp;
    pq = new int[max+1];
    qp = new int[max+1];
    for (int i = 0; i <= max; i++)
      qp[i] = -1;
    n = 0;
    this.max = max;
  }

  /**
   * Inserts a key into the priority queue
   * @param i The index associated with the key
   * @param key The key to be inserted into the priority queue
   * @throws IllegalArgumentException if i surpasses capacity
   */
  public void insert(int i, Key key){
    validateIndex(i);
    if (contains(i))
      throw new IllegalArgumentException("Priority queue already contains index: " + i);
    n++;
    qp[i] = n;
    pq[n] = i;
    keys[i] = key;
    swim(n);
  }

  /**
   * Changes the key associated with i to key
   * @param i The index the key is being associated with
   * @param key The key being associated with i
   * @throws IllegalArgumentException if i is out of bounds
   * @throws NoSuchElementException if no key is associated with i
   */
  public void changeKey(int i, Key key){
    validateIndex(i);
    if (!contains(i))
      throw new NoSuchElementException("Priority queue does not contain the index: " + i);
    keys[i] = key;
    swim(qp[i]);
    sink(qp[i]);
  }

  /**
   * Checks if the index i is associated with some key
   * @return  True if the index is associated with some key
   */
  public boolean contains(int i){
    validateIndex(i);
    return qp[i] != -1;
  }

  /**
   * Removes i and its associated key
   * @param i The index and its associated key to be removed
   * @throws IllegalArgumentException if i is out of bounds
   * @throws NoSuchElementException if no key is associated with i
   */
  public void delete(int i){
    validateIndex(i);
    if (!contains(i))
      throw new NoSuchElementException("Priority queue does not contain the index: " + i);
    int index = qp[i];
    exchange(index, n--);
    swim(index);
    sink(index);
    keys[i] = null;
    qp[i] = -1;
  }

  /**
   * Returns the smallest key
   * @return  The smallest key
   * @throws  NoSuchElementException if the priority queue is empty
   */
  public Key minKey(){
    if (isEmpty())
      throw new NoSuchElementException("Cannot acquire min key of an empty priority queue.");
    return keys[pq[1]];
  }

  /**
   * Returns the minimal key's index
   * @return  The minmal key's index
   * @throws  NoSuchElementException if the priority queue is empty
   */
  public int minIndex(){
    if (isEmpty())
      throw new NoSuchElementException("Cannot acquire min index of an empty priority queue.");
    return pq[1];
  }

  /**
   * Removes the smallest key
   * @return  The index of the smallest key that was removed
   * @throws  NoSuchElementException if the priority queue is empty
   */
  public int delMin(){
    if (isEmpty())
      throw new NoSuchElementException("Cannot delete the min key of an empty priority queue.");
    int min = pq[1];
    exchange(1, n--);
    sink(1);
    qp[min] = -1;
    keys[min] = null;
    pq[n+1] = -1;
    return min;
  }

  /**
   * Checks if the priority queue is empty
   * @return  True if the priority queue is empty
   */
  public boolean isEmpty(){
    return n == 0;
  }

  /**
   * Gives the number of keys in the priority queue
   * @return  The number of keys in the priority queue
   */
  public int size(){
    return n;
  }

  /**
   * Returns the key associated with index i
   * @param i The index associated with a key in the priority queue
   * @return  The key associated with i
   * @throws IllegalArgumentException if i is out of bounds
   * @throws NoSuchElementException if no key is associated with i
   */
  public Key keyOf(int i){
    validateIndex(i);
    if (!contains(i))
      throw new NoSuchElementException("Priority queue does not contain the index: " + i);
    return keys[i];
  }
/******************************************************************************
 * Helper functions for comparing, swapping, and checking for heap invariant
 *****************************************************************************/

  /**
   * Checks if one item is larger than another
   * @param i Index of the key being checked 
   * @param j Index of the key i is being checked against
   * @return  True if pq[i] is smaller than pq[j] 
   */
  private boolean more(int i, int j){
    return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
  }

  /**
   * Swaps the contents of two elements in an array
   * @param i The element whose data will be swapped with j
   * @param j The element whose data will be swapped with i
   */
  private void exchange(int i, int j){
    int swap = pq[i];
    pq[i] = pq[j];
    pq[j] = swap;
    qp[pq[i]] = i;
    qp[pq[j]] = j;
  }

  /**
   * Bottum-up reheapify
   * @param k The index of the current node
   */
  private void swim(int k){
    // while k is not the root and its parent is larger,
    // swap with parent
    while (k > 1 && more(k/2, k)){
      exchange(k/2, k);
      k = k/2;
    }
  }

  /**
   * Top-down reheapify
   * @param k The index of the current node
   */
  private void sink(int k){
    // while k's child is a node that exists 
    while (2*k <= n){
      int j = 2*k;
      if (j < n && more(j, j+1))  // prepare a swap with smallest child 
        j++;
      if(!more(k, j)) // however, if that swap would make the parent > child, stop
        break;
      // swap, prepare to check children
      exchange(k, j);
      k = j;
    }
  }

  /**
   * Out of bounds checking for a given index
   * @param i The index being checked
   * @throws IllegalArgumentException if i is an invalid index
   */
  private void validateIndex(int i){
    if (i < 0)
      throw new IllegalArgumentException("Index cannot be negative: " + i);
    if (i >= max)
      throw new IllegalArgumentException("Index >= capacity: " + i + ">=" + max);
  }

  /**
   * Checks if a heap centered at the root is a min heap
   * @return  True if the array is a min heap
   */
  protected boolean isMinHeap(){
    // ensure null values are where they belong
    for (int i = 1; i <= n; i++){
      if (keys[pq[i]] == null)
        return false;
    }
    for (int i = n+1; i < pq.length; i++){
      if (keys[pq[i]] != null)
        return false;
    }
    if (keys[pq[0]] != null)
      return false;
    // check order of keys in heap
    return isMinHeapOrdered(1);
  }

  /**
   * Checks if the heap at a given root is a min heap
   * @param k The index of the root
   * @return  True if the array is ordered as a min heap
   */
  private boolean isMinHeapOrdered(int k){
    if (k > n)
      return true;
    int left = 2*k;
    int right = 2*k + 1;
    if (left <= n && more(k, left))
      return false;
    if (right <= n && more(k, right))
      return false;
    return isMinHeapOrdered(left) && isMinHeapOrdered(right);
  }
}
