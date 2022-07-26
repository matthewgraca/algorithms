package com.mgraca.algorithms.searching.searchapps;

import java.util.Iterator;
import java.util.TreeSet; // self-balancing bst
import java.util.NoSuchElementException;

/**
 * Ordered set of comparable keys
 */
public class SET<Key extends Comparable<Key>> implements Iterable<Key>{
  private TreeSet<Key> set;

  /**
   * Initializes empty set
   */
  public SET(){
    set = new TreeSet<Key>();
  }


  /****************************************************************************
   * Basic symbol table operations
   ***************************************************************************/

  /**
   * Gets the size (cardinality) of the set
   */
  public int size(){
    return set.size();
  }

  /**
   * Adds a key into the set
   * @param key the key to add
   * @throws IllegalArgumentException if the key is null
   */
  public void add(Key key){
    if (key == null)
      throw new IllegalArgumentException("Cannot add null key to the set");
    set.add(key);
  }

  /**
   * Checks if a key is in the set
   * @param key the key to check
   * @return true if the key is in the set, false if not
   * @throws IllegalArgumentException if the key is null
   */
  public boolean contains(Key key){
    if (key == null)
      throw new IllegalArgumentException("Cannot search for a null key");
    return set.contains(key);
  }

  /**
   * Removes a given key from the set
   * @param key the key to remove
   * @throws IllegalArgumentException if the key is null
   */
  public void remove(Key key){
    if (key == null)
      throw new IllegalArgumentException("Cannot remove a null key");
    set.remove(key);
  }

  /**
   * Checks if the set is empty
   * @return true if the set is empty, false if not
   */
  public boolean isEmpty(){
    return set.size() == 0;
  }

  /****************************************************************************
   * Ordered table operations
   ***************************************************************************/

  /**
   * Finds the minimum key in the set
   * @return the minimum key in the set
   * @throws NoSuchElementException if the set is empty
   */
  public Key min(){
    if (isEmpty())
      throw new NoSuchElementException("Cannot get the min of an empty set");
    return set.first();
  }

  /**
   * Finds the maximum key in the set
   * @return the maximum key in the set
   * @throws NoSuchElementException if the set is empty
   */
  public Key max(){
    if (isEmpty())
      throw new NoSuchElementException("Cannot get the max of an empty set");
    return set.last();
  }

  /**
   * Finds the largest key less than or equal to the given key
   * @param key the key
   * @return the largest key less than or equal to the given key
   * @throws IllegalArgumentException if the given key is null
   * @throws NoSuchElementException if the set is empty or there is no such key
   */
  public Key floor(Key key){
    if (key == null)
      throw new IllegalArgumentException("Cannot get the floor of a null key");
    Key k = set.floor(key);
    if (k == null)
      throw new NoSuchElementException("All keys greater than " + key);
    return k;
  }

  /**
   * Finds the smallest key greater than or equal to the given key
   * @param key the key
   * @return the smallest key greater than or equal to the given key
   * @throws IllegalArgumentException if the given key is null
   * @throws NoSuchElementException if the set is empty or there is no such key
   */
  public Key ceiling(Key key){
    if (key == null)
      throw new IllegalArgumentException("Cannot get the ceiling of a null key");
    Key k = set.ceiling(key);
    if (k == null)
      throw new NoSuchElementException("All keys less than " + key);
    return k;
  }

  /****************************************************************************
   * Classic set operations
   ***************************************************************************/

  /**
   * Returns the union of this set and that set
   * @param that the other set
   * @return the union of this set and that set
   * @throws IllegalArgumentException if that set is null
   */
  public SET<Key> union(SET<Key> that){
    if (that == null)
      throw new IllegalArgumentException("Cannot union with a null reference");
    SET<Key> z = new SET<Key>();
    for (Key x : set)
      z.add(x);
    for (Key y : that)
      z.add(y);
    return z;
  }

  /**
   * Returns the intersection of this set and that set
   * @param that the other set
   * @return the intersection of this set and that set
   * @throws IllegalArgumentException if that set is null
   */
  public SET<Key> intersection(SET<Key> that){
    if (that == null)
      throw new IllegalArgumentException("Cannot intersect with null");
    SET<Key> z = new SET<Key>();
    // check using smaller set for less comparisons 
    if (this.size() < that.size()){
      for (Key x : this.set){
        if (that.contains(x))
          z.add(x);
      }
    }
    else{
      for (Key x : that.set){
        if (this.contains(x))
          z.add(x);
      }
    }
    return z;
  }

  /****************************************************************************
   * Overriding methods
   ***************************************************************************/

  /**
   * Returns all the keys in the set as an iterator.
   * Use the foreach notation: for (Key key : set)
   * @return an iterator for all the keys in the set
   */
  @Override
  public Iterator<Key> iterator(){
    return set.iterator();
  }

  /**
   * Compare this set to the other set.
   * Two empty sets of different types are still equal, as per Java's 
   * Collections framework behavior for equals()
   * @param other the other set
   * @return true if this set equals the other set, false if not
   */
  @Override
  public boolean equals(Object other){
    if (other == this)
      return true;
    if (other == null)
      return false;
    if (other.getClass() != this.getClass())
      return false;
    SET that = (SET) other;
    return this.set.equals(that.set);
  }
}
