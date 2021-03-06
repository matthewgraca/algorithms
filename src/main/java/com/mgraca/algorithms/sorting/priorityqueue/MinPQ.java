package com.mgraca.algorithms.sorting.priorityqueue;

import java.util.NoSuchElementException;

public class MinPQ<Key extends Comparable<Key>>{
  private Key[] pq;
  private int n;

  /**
   * Creates a priority queue
   */
  public MinPQ(){
    this(1);
  }

  /**
   * Creates a priority queue of a given initial capacity
   * @param max Initial capacity size
   */
  public MinPQ(int max){
    @SuppressWarnings("unchecked")
    Key[] temp = (Key[]) new Comparable[max+1];
    pq = temp;
    n = 0;
  }

  /**
   * Creates a priority queue from the keys in a given array
   * @param a Array containing the keys used to create the priority queue
   */
  public MinPQ(Key[] a){
    n = a.length;
    @SuppressWarnings("unchecked")
    Key[] temp = (Key[]) new Comparable[n+1];
    for (int i = 0; i < n; i++){
      temp[i+1] = a[i];
    }
    pq = temp;
    // last node that's not a leaf
    for (int k = n/2; k >= 1; k--){
      sink(k);
    }
  }

  /**
   * Inserts a key into the priority queue
   * @param v The key to be inserted into the priority queue
   */
  public void insert(Key v){
    if (isFull())
      resize(2*pq.length);
    pq[++n] = v;
    swim(n);
  }

  /**
   * Returns the smallest key
   * @return  The smallest key
   * @throws  NoSuchElementException if the priority queue is empty
   */
  public Key min(){
    if (isEmpty())
      throw new NoSuchElementException("Cannot acquire min key of an empty priority queue.");
    return pq[1];
  }

  /**
   * Removes the smallest key
   * @return  The key that was removed
   */
  public Key delMin(){
    Key min = min();
    exchange(1, n);
    pq[n] = null;
    n--;
    sink(1);
    if ((n > 0) && (n == (pq.length - 1) / 4))
      resize(pq.length/2);
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
    return pq[i].compareTo(pq[j]) > 0;
  }

  /**
   * Swaps the contents of two elements in an array
   * @param i The element whose data will be swapped with j
   * @param j The element whose data will be swapped with i
   */
  private void exchange(int i, int j){
    Key temp = pq[i];
    pq[i] = pq[j];
    pq[j] = temp;
  }

  /**
   * Bottom-up reheapify
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
   * Checks if the heap is full
   * @return  True if the heap is full
   */
  private boolean isFull(){
    return n == pq.length - 1;
  }

  /**
   * Resizes the heap
   * @param capacity  The new capacity of the heap
   */
  private void resize(int capacity){
    @SuppressWarnings("unchecked")
    Key[] temp = (Key[]) new Comparable[capacity];
    for (int i = 1; i <= n; i++){
      temp[i] = pq[i];
    }
    pq = temp;
  }

  /**
   * Checks if a heap centered at the root is a min heap
   * @return  True if the array is a min heap
   */
  protected boolean isMinHeap(){
    // ensure null values are where they belong
    for (int i = 1; i <= n; i++){
      if (pq[i] == null)
        return false;
    }
    for (int i = n+1; i < pq.length; i++){
      if (pq[i] != null)
        return false;
    }
    if (pq[0] != null)
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
