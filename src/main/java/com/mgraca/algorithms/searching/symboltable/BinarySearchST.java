package com.mgraca.algorithms.searching.symboltable;

import java.util.NoSuchElementException;

public class BinarySearchST<Key extends Comparable<Key>, Value>{
  private Key[] keys;
  private Value[] vals;
  private int n;

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
    return null;
  }

  /**
   * Gets the number of keys less than the given key
   * @param key The key being checked
   * @return  The number of keys less than the given key
   */
  public int rank(Key key){
    return 0;
  }

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
  }

  /**
   * Removes the given key and its value from the table
   * @param key The key being removed from the table
   * @throws IllegalArgumentException if the argument passed is null
   */
  public void delete(Key key){
    if (key == null)
      throw new IllegalArgumentException("Null argument not allowed");
    //head = delete(head, key);
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
    return null;
  }

  /**
   * Gets the smallest key greater than or equal to the given key
   * @param key The key being checked
   * @return The smallest key greater than or equal to the given key
   * @throws NoSuchElementException if there is no such key
   * @throws IllegalArgumentException if the given key is null
   */
  public Key ceiling(Key key){
    return null;
  }

  /**
   * Gets the largest key less than or equal to the given key
   * @param key The key being checked
   * @return The largest key less than or equal to the given key
   * @throws NoSuchElementException if there is no such key
   * @throws IllegalArgumentException if the given key is null
   */
  public Key floor(Key key){
    return null;
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
 * Array resizing methods
 *****************************************************************************/

  /**
   * Checks if the table is empty
   * @return true if the table is empty, false if otherwise
   */
  private boolean isEmpty(){
    return true;
  }

  /**
   * Checks if the table size is 1/4th of its capacity
   * @return  True if the table size is 1/4th of its capacity
   */
  private boolean sizeIsOneFourthOfCapacity(){
    return true;
  }

  /**
   * Resizes the capacity of the stack
   * @param n The capacity of the new stack
   */
  private void resize(int n){
    @SuppressWarnings("unchecked")
    Key[] tempKeys = (Key[]) new Comparable[n];
    @SuppressWarnings("unchecked")
    Value[] tempValues = (Value[]) new Object[n];
    for (int i = 0; i < n; i++){
      tempKeys[i] = keys[i];
      tempValues[i] = vals[i];
    }
    keys = tempKeys;
    vals = tempValues;
  }

}
