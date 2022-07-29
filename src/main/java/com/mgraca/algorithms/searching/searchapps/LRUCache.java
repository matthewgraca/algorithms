package com.mgraca.algorithms.searching.searchapps;

import com.mgraca.algorithms.searching.hashtable.LinearProbingHashST;
import java.util.NoSuchElementException;

/**
 * Emulates a Least Recently Used Cache using a symbol table and a doubly 
 * linked list. Symbol table effectively handles lookup, while the linked
 * list comprises the actual cache.
 *
 * Purpose: Within a constant factor of O(1) time complexity for 
 * contains(), access(), and remove(), with the tradeoff of memory overhead 
 * for the hash table and doubly linked list
 */

public class LRUCache<Key>{
  private int n;
  private Node head;
  private Node tail;
  private LinearProbingHashST<Key, Node> st;

  /**
   * Initializes an empty cache
   */
  public LRUCache(){
    n = 0;
    head = null;
    tail = null;
    st = new LinearProbingHashST<Key, Node>();
  }

  /**
   * Inserts the given item if it's not already present, then moves the item
   * to the top of the cache
   * @param item the item being inserted
   * @throws IllegalArgumentException if the item is null
   */
  public void access(Key item){
    if (item == null)
      throw new IllegalArgumentException("Cannot access a null item");
    remove(item);
    insert(item);
  }

  // inserts the item into the head of the cache and the symbol table
  private void insert(Key item){
    Node temp = new Node(item, head, null);
    if (head == null) // list is empty case
      tail = temp;
    else
      head.prev = temp;
    head = temp;
    st.put(item, head);
    n++;
  }

  // deletes the item from the cache and the symbol table
  // returns item if search hit, null if search miss
  private Key remove(Key item){
    Node temp = st.get(item);
    if (temp == null) // case a: search miss
      return null;
    // case b: search hit
    // case b1: single node
    if (temp.next == null && temp.prev == null)
      head = tail = null;
    // case b2: only prev nodes
    else if (temp.next == null){
      tail = temp.prev;
      tail.next = null;
    }
    // case b3: only next nodes
    else if (temp.prev == null){
      head = head.next;
      head.prev = null;
    }
    // case b4: both prev and next nodes exist
    else{
      temp.prev.next = temp.next;
      temp.next.prev = temp.prev;
    }
    temp = null;
    st.delete(item);
    n--;
    return item;
  }

  /**
   * Deletes and returns the item least recently accessed 
   * (longest time unaccessed), at the bottom of the cache
   * @return the item least recently accessed
   * @throws NoSuchElementException if there is no item to remove 
   */
  public Key remove(){
    if (n == 0)
      throw new NoSuchElementException("Cannot remove from an empty cache");
    return remove(tail.item); // guaranteed to not be null since size > 0
  }

  /**
   * Gets the number of items in the cache
   * @return the number of items in the cache
   */
  public int size(){
    return n;
  }

  /**
   * Checks if an item is in the cache; is not considered accessing
   * @return true if the item is in the cache, false if not
   * @throws IllegalArgumentException if the item is null
   */
  public boolean contains(Key item){
    /*
    boolean contains = false;
    for (Node i = head; i != null; i = i.next){
      if (i.item == item)
        contains = true;
    }*/
    return st.contains(item) /*&& contains*/;
  }

  /****************************************************************************
   * Doubly linked list subclass
   ***************************************************************************/

  private class Node{
    private Key item;
    private Node next;
    private Node prev;

    public Node(Key item, Node next, Node prev){
      this.item = item;
      this.next = next;
      this.prev = prev;
    }
  }
}
