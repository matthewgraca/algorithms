package com.mgraca.algorithms.searching.searchapps;

import com.mgraca.algorithms.fundamentals.LinkedQueue;
import com.mgraca.algorithms.searching.searchapps.SET;

/**
 * Implements a unique queue, where items that were inserted into the queue 
 * can never be reinserted. Uses a symbol table set to keep track of what 
 * items were put into the queue.
 */

public class UniQueue<Key extends Comparable<Key>>{
  private LinkedQueue<Key> queue;
  private SET<Key> set;

  /**
   * Initializes an empty uniqueue
   */
  public UniQueue(){
    queue = new LinkedQueue<Key>();
    set = new SET<Key>();
  }

  /**
   * Adds a given item to the front of the queue;
   * if the queue has/used to have that item, ignore the request 
   * @param item the item being added to the queue
   * @throws IllegalArgumentException if null is passed as an argument
   */
  public void enqueue(Key item){
    if (item == null)
      throw new IllegalArgumentException("Cannot add null items to the queue");
    if (!set.contains(item)){
      queue.enqueue(item);
      set.add(item);
    }
  }

  /**
   * Removes an item from the front of the queue
   * @return the item that was removed
   */
  public Key dequeue(){
    return queue.dequeue();
  }

  /**
   * Checks if the queue contains a given item
   * @param item the item being searched for
   * @return true if the item is in the queue, false if not
   */
  public boolean contains(Key item){
    for (Key data : queue){
      if (data == item)
        return true;
    }
    return false;
  }

  /**
   * Checks if the queue either has or at one point had a given item
   * @param item the item being searched for
   * @return true if the item has of had been in the queue, false if not
   */
  public boolean contained(Key item){
    return set.contains(item);
  }

  /**
   * Gets the number of items in the queue
   * @return the number of items in the queue
   */
  public int size(){
    return queue.size();
  }
}
