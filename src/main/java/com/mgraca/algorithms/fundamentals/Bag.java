package com.mgraca.algorithms.fundamentals;

import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * Linked implementation of the multiset (set with duplicates allowed)
 */
public class Bag<T> implements Iterable<T>{
  private Node head;
  private int size;
  
  /**
   * Initializes empty bag
   */
  public Bag(){
    size = 0;
    head = null;
  }

  /**
   * Adds the item to the list
   * @throws IllegalArgumentException if null is an argument
   */
  public void add(T item){
    Node temp = new Node();
    temp.data = item;
    temp.next = head;
    head = temp;
    size++;
  }

  /**
   * Gets the number of items in the bag 
   * @return the number of items in the bag
   */
  public int size(){
    return size;
  }

  /*****************************************************************************
   * Nested Node class
   ****************************************************************************/
  private class Node{
    T data;
    Node next;
  }


  /*****************************************************************************
   * Iterator overloading 
   ****************************************************************************/
  @Override
  public Iterator<T> iterator(){
    return new ListIterator();
  }

  private class ListIterator implements Iterator<T>{
    private Node current = head;     
    public boolean hasNext(){
      return current != null;
    }

    public T next(){
      T item = current.data;
      current = current.next;
      return item;
    }

    public void remove(){}
  }
}
