package com.mgraca.algorithms.searching.hashtable;

import java.lang.IllegalArgumentException;

public class LinearProbingHashST<Key, Value>{
  // best be a power of 2
  private static final int INIT_CAPACITY = 4;
  private int n;  // num of key-value pairs in the table
  private int m;  // size of the table
  private Key[] keys;
  private Value[] vals;

  /**
   * Initializes an empty symbol table
   */
  public LinearProbingHashST(){
    this(INIT_CAPACITY);
  }

  /**
   * Initializes an empty symbol table with a given capacity
   * @param capacity the initial capacity
   */
  public LinearProbingHashST(int capacity){
    m = capacity;
    n = 0;

    @SuppressWarnings("unchecked")
    Key[] tempKeys = (Key[]) new Object[m];
    keys = tempKeys;

    @SuppressWarnings("unchecked")
    Value[] tempVals = (Value[]) new Object[m];
    vals = tempVals;
  }

  /**
   * Gets the number of key-value pairs in the table
   * @return the number of key-value pairs in the table
   */
  public int size(){
    return n;
  }

  // hash function for keys; returns values in the range [0, m-1]
  private int hash(Key key){
    return (key.hashCode() & 0x7fffffff) % m;
  }

  // resizes the table and re-hashes all the keys
  private void resize(int capacity){
    // make new list
    LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<>(capacity);
    // rehash
    for (int i = 0; i < m; i++){
      if (keys[i] != null)
        temp.put(keys[i], vals[i]);
    }
    keys = temp.keys;
    vals = temp.vals;
    m = temp.m;
  }

  /**
   * Inserts the given key-value pair into the table.
   * Overwrites the old value with the new value if the table contains the key.
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
    // if the number of keys exceeds 50% of the table capacity
    if (n >= m/2)
      resize(2*m);

    int i;
    for (i = hash(key); keys[i] != null; i = (i + 1) % m){
      if (keys[i].equals(key)){ // perfect place found
        vals[i] = val;
        return;
      }
      // else linear probe until open space found
    }
    keys[i] = key;
    vals[i] = val;
    n++;
  }

  /**
   * Removes the specified key and its associated value from the table
   * @param key the key
   * @throws IllegalArgumentException if the key is null
   */
  public void delete(Key key){
    if (key == null)
      throw new IllegalArgumentException("Cannot delete a null key");
    if (!contains(key))
      return;

    // find position of the key
    int i = hash(key);
    while (!key.equals(keys[i]))
      i = (i + 1) % m;

    // delete
    keys[i] = null;
    vals[i] = null;

    // rehash cluster of keys to the right of the deleted pair
    i = (i + 1) % m;
    while (keys[i] != null){
      Key keyToRehash = keys[i];
      Value valToRehash = vals[i];
      keys[i] = null;
      vals[i] = null;
      n--;
      put(keyToRehash, valToRehash);
      i = (i + 1) % m;
    }

    n--;

    // ensure the table is always never less than 1/8th full
    if (n > 0 && n <= m/8)
      resize(m/2);
  }

  /**
   * Checks if the table contains a given key
   * @param key the key
   * @return true if the table contains the key, false if not
   * @throws IllegalArgumentException if the key is null
   */
  public boolean contains(Key key){
    if (key == null)
      throw new IllegalArgumentException("Cannot search for a null key");
    return get(key) != null;
  }

  /**
   * Retrieves the value associated with a specified key
   * @param key the key
   * @return the value associated with the key; null if not found
   * @throws IllegalArgumentException if the key is null
   */
  public Value get(Key key){
    if (key == null)
      throw new IllegalArgumentException("Cannot get a value from a null key");
    for (int i = hash(key); keys[i] != null; i = (i + 1) % m){
      if (keys[i].equals(key))  // search hit
        return vals[i];
    }
    return null;  // search miss
  }
}
