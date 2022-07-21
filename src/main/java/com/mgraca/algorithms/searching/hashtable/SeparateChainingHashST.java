package com.mgraca.algorithms.searching.hashtable;

import com.mgraca.algorithms.searching.symboltable.SequentialSearchST;

public class SeparateChainingHashST<Key extends Comparable<Key>, Value>{
  private static final int DEFAULT_CAPACITY = 997;  // make this 4 if resizing
  private int n;  // num of key-value pairs
  private int m;  // hash table size
  private SequentialSearchST<Key, Value>[] st;

  /**
   * Creates a hash table
   */
  public SeparateChainingHashST(){
    this(DEFAULT_CAPACITY);
  }

  /**
   * Creates a hash table with a given capacity
   * @param m the capacity of the hash table
   */
  public SeparateChainingHashST(int m){
    this.m = m;
    @SuppressWarnings("unchecked")
    SequentialSearchST<Key, Value>[] temp = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
    st = temp;
    for (int i = 0; i < m; i++)
      st[i] = new SequentialSearchST<Key, Value>();
  }

  // hash function for keys; returns [0, m-1]
  private int hash(Key key){
    // hashCode() may return negative; flip the negative sign bit
    return (key.hashCode() & 0x7fffffff) % m;
  }

  /**
   * Retrieves the key from the table
   * @param key the key
   * @return the value associated with the key; null if it isn't in the table
   * @throws IllegalArgumentException if the key is null
   */
  public Value get(Key key){
    if (key == null)
      throw new IllegalArgumentException("Cannot get the value of a null key");
    return st[hash(key)].get(key);
  }

  /**
   * Inserts a key-value pair into the table.
   * Overwrites old value if table already contains the key.
   * Deletes the key-value pair if the value is null.
   * @param key the key
   * @param val the value
   * @throws IllegalArgumentException if the key is null
   */
  public void put(Key key, Value val){
    if (key == null)
      throw new IllegalArgumentException("Cannot put a null key into the table");
    if (val == null){
      delete(key);
      return;
    }

    // array resizing if chains get too large, but not in this implementation
    // if (n >= 10*m) resize(2*m)

    if (!st[hash(key)].contains(key))
      n++;
    st[hash(key)].put(key, val);
  }

  /**
   * Removes a specified key-value pair from the table
   * @param key the key
   * @throws IllegalArgumentException if the key is null
   */
  public void delete(Key key){
    if (key == null)
      throw new IllegalArgumentException("Cannot delete a null key");
    if (st[hash(key)].contains(key))
      n--;
    st[hash(key)].delete(key);

    // array resizing
    // if (m > DEFAULT_CAPACITY && n < 2*m) resize(m/2);
  }

  /**
   * Gets the number of items in the table
   * @return  the number of items in the table
   */
  public int size(){
    return n;
  }
}
