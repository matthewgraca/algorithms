package com.mgraca.algorithms.searching.symboltable;

import java.util.Iterator;

public class SequentialSearchST<Key extends Comparable<Key>, Value> implements Iterable<Key>{
  private Node head;
  private int n;

  /**
   * Creates a symbol table
   */
  public SequentialSearchST(){
    head = null;
    n = 0;
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
    for (Node current = head; current != null; current = current.next){
      if (key.equals(current.key))
        return current.val; // search hit
    }
    return null;  // search miss
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
    // delete key from table if value is null
    if (val == null){
      delete(key);
      return;
    }
    // search for key; update if found, grow table if new
    for (Node current = head; current != null; current = current.next){
      if (key.equals(current.key)){
        current.val = val;  // search hit; update
        return;
      }
    }
    head = new Node(key, val, head);  // search miss; grow table
    n++;
  }

  /**
   * Gets the number of key-value pairs in the table
   * @return  The number of key-value pairs in the table
   */
  public int size(){
    return n;
  }

  /**
   * Removes the given key and its value from the table
   * @param key The key being removed from the table
   * @throws IllegalArgumentException if the argument passed is null
   */
  public void delete(Key key){
    if (key == null)
      throw new IllegalArgumentException("Null argument not allowed");
    head = delete(head, key);
  }

  /* 
  // iterative solution:
  public void delete(Key key){
    if (key == null)
      throw new IllegalArgumentException("Null argument not allowed");
    if (head == null) // exit if table is empty
      return;
    // check first key
    Node current = head;
    if (key.equals(current.key)){
      current = null;
      n--;
      return;
    }
    // check table with > 1 key
    // search for key; remove if found
    for ( Node prev = head, curr = head.next; 
          curr != null; 
          prev = prev.next, curr = curr.next){
      if (key.equals(curr.key)){
        prev.next = curr.next;
        n--;
        return;
      }
    }
  }
  */

  // deletes key in linked list beginning at node current 
  private Node delete(Node current, Key key){
    if (current == null)          // base case 1: search miss
      return null;
    if (key.equals(current.key)){ // base case 2: search hit
      n--;
      return current.next;
    }
    current.next = delete(current.next, key);
    return current;
  }

/******************************************************************************
 * Internal Node class
 *****************************************************************************/

  private class Node{
    Key key;
    Value val;
    Node next;

    public Node(Key key, Value val, Node next){
      this.key = key;
      this.val = val;
      this.next = next;
    }
  }

/******************************************************************************
 * Iterator implementation
 *****************************************************************************/

  public Iterator<Key> iterator(){
    return new ListIterator();
  }

  private class ListIterator implements Iterator<Key>{
    private Node current = head;

    public boolean hasNext(){
      return current != null;
    }

    public void remove(){}

    public Key next(){
      Key key = current.key;
      current = current.next;
      return key;
    }
  }
}
