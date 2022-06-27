package com.mgraca.algorithms.searching.symboltable;

import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;

public class BinarySearchST<Key extends Comparable<Key>, Value>{
  private static final int INIT_CAPACITY = 2;
  private Key[] keys;
  private Value[] vals;
  private int n;

  /**
   * Creates a symbol table with a default initial capacity
   */
  public BinarySearchST(){
    this(INIT_CAPACITY);
  }

  /**
   * Creates a symbol table with a given capacity
   * @param capacity  Initial capacity of the symbol table
   */
  public BinarySearchST(int capacity){
    @SuppressWarnings("unchecked")
    Key[] tempKeys = (Key[]) new Comparable[capacity];
    keys = tempKeys;
    @SuppressWarnings("unchecked")
    Value[] tempVals = (Value[]) new Object[capacity];
    vals = tempVals;
    n = 0;
  }

  /**
   * Gets the number of key-value pairs in the table
   * @return  The number of key-value pairs in the table
   */
  public int size(){
    return n;
  }

  /**
   * Gets the value paired with a given key, null if the key is absent
   * @param key The key being searched for in the table
   * @return  The value associated with the given key; null if absent
   * @throws IllegalArgumentException if the argument passed is null
   */
  public Value get(Key key){
    if (key == null)
      throw new IllegalArgumentException("Null argument not allowed");
    if (isEmpty())
      return null;
    int i = rank(key);
    if (i < n && keys[i].compareTo(key) == 0)
      return vals[i];
    else
      return null;
  }

  /**
   * Gets the number of keys less than the given key, using binary search
   * @param key The key being checked
   * @return  The number of keys less than the given key
   * @throws IllegalArgumentException if the argument passed is null
   */
  public int rank(Key key){
    if (key == null)
      throw new IllegalArgumentException("Cannot find the rank of a null key");
    int lo = 0, hi = n - 1;
    while (lo <= hi){
      int mid = lo + (hi - lo) / 2;
      int compare = key.compareTo(keys[mid]);
      if (compare < 0)
        hi = mid - 1;
      else if (compare > 0)
        lo = mid + 1;
      else
        return mid;
    }
    return lo;
  }

  /*Recursive version of binary search:
  public int rank(Key key, int lo, int hi){
    if (hi < lo)
      return lo;
    int mid = lo + (hi - lo) / 2;
    int compare = key.compareTo(keys[mid]);
    if (compare < 0)
      return rank(key, lo, mid-1);
    else if (compare > 0)
      return rank(key, mid+1, hi);
    else
      return mid;
  }
  */

  /**
   * Puts the given key-value pair into the table; removes key from table if 
   * value is null
   * @param key The key being added
   * @param val The value being added
   * @throws IllegalArgumentException if the argument passed is null
   */
  public void put(Key key, Value val){
    if (key == null)
      throw new IllegalArgumentException("Null argument not allowed");
    // delete pair if given a null value
    if (val == null){
      delete(key);
      return;
    }

    int i = rank(key);

    // if key is already in table, update value
    if (i < n && keys[i].compareTo(key) == 0){
      vals[i] = val;
      return;
    }

    // otherwise, insert new key-value pair
    if (n == keys.length)
      resize(2*keys.length);
    // shift right, make space for pair insertion
    for (int j = n; j > i; j--){
      keys[j] = keys[j-1];
      vals[j] = vals[j-1];
    }
    keys[i] = key;
    vals[i] = val;
    n++;
  }

  /**
   * Removes the given key and its value from the table
   * @param key The key being removed from the table
   * @throws IllegalArgumentException if the argument passed is null
   */
  public void delete(Key key){
    if (key == null)
      throw new IllegalArgumentException("Null argument not allowed");
    if (isEmpty())
      return;
    
    int i = rank(key);

    // if the key is not in the table, exit 
    if (i == n || keys[i].compareTo(key) != 0)
      return;
    else{
      // shift pairs to the left, overwriting the found key
      for (int j = i; j < n-1; j++){
        keys[j] = keys[j+1];
        vals[j] = vals[j+1];
      }
      n--;
      keys[n] = null;
      vals[n] = null;
      if (sizeIsOneFourthOfCapacity())
        resize(keys.length/2);
    }
  }

  /**
   * Gets the minimum key in the table
   * @return The minimum key in the table
   * @throws NoSuchElementException if the table is empty
   */
  public Key min(){
    if (isEmpty())
      throw new NoSuchElementException("Cannot get the minimum key for an empty symbol table");
    return keys[0];
  }

  /**
   * Gets the maximum key in the table
   * @return the maximum key in the table
   * @throws NoSuchElementException if the table is empty
   */
  public Key max(){
    if (isEmpty())
      throw new NoSuchElementException("Cannot get the maximum key for an empty symbol table");
    return keys[n-1];
  }

  /**
   * Gets the key of a given rank
   * @param k The rank of the key being retrieved
   * @return  The key of rank k
   * @throws IllegalArgumentException if the table does not contain a key of that rank
   */
  public Key select(int k){
    if (k < 0 || k >= n)
      throw new IllegalArgumentException("Argument out of range");
    return keys[k];
  }

  /**
   * Gets the smallest key greater than or equal to the given key
   * @param key The key being checked
   * @return The smallest key greater than or equal to the given key
   * @throws NoSuchElementException if there is no such key
   * @throws IllegalArgumentException if the given key is null
   */
  public Key ceiling(Key key){
    if (key == null)
      throw new IllegalArgumentException("Cannot get the ceiling of a null key");
    int i = rank(key);
    if (i == n)
      throw new NoSuchElementException("Argument is too large to find ceiling");
    else
      return keys[i];
  }

  /**
   * Gets the largest key less than or equal to the given key
   * @param key The key being checked
   * @return The largest key less than or equal to the given key
   * @throws NoSuchElementException if there is no such key
   * @throws IllegalArgumentException if the given key is null
   */
  public Key floor(Key key){
    if (key == null)
      throw new IllegalArgumentException("Cannot get the floor of a null key");
    int i = rank(key);
    // if a key is found that matches the floor, return it
    if (i < n && keys[i].compareTo(key) == 0)
      return keys[i];
    // else return the value below the key
    if (i == 0)
      throw new NoSuchElementException("Argument is too small to find floor");
    else
      return keys[i-1];
  }

  /**
   * Returns all keys in the symbol table in a given range
   * @param lo  lower endpoint
   * @param hi  upper endpoint
   * @return all keys in the table, lo and hi inclusive
   * @throws IllegalArgumentException if either lo and/or hi is null
   */
  public Iterable<Key> keys(Key lo, Key hi){
    return null;
  }

/******************************************************************************
 * Array resizing helper methods
 *****************************************************************************/

  /**
   * Checks if the table is empty
   * @return true if the table is empty, false if otherwise
   */
  private boolean isEmpty(){
    return n == 0;
  }

  /**
   * Checks if the table size is 1/4th of its capacity
   * @return  True if the table size is 1/4th of its capacity
   */
  private boolean sizeIsOneFourthOfCapacity(){
    return n > 0 && n == keys.length / 4;
  }

  /**
   * Resizes the capacity of the stack
   * @param capacity  The capacity of the new stack
   */
  private void resize(int capacity){
    @SuppressWarnings("unchecked")
    Key[] tempKeys = (Key[]) new Comparable[capacity];
    @SuppressWarnings("unchecked")
    Value[] tempValues = (Value[]) new Object[capacity];
    for (int i = 0; i < n; i++){
      tempKeys[i] = keys[i];
      tempValues[i] = vals[i];
    }
    keys = tempKeys;
    vals = tempValues;
  }
}
