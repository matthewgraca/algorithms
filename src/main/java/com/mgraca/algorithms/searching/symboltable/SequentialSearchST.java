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
   */
  public Value get(Key key){
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
   */
  public void put(Key key, Value val){
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
   */
  public void delete(Key key){
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
