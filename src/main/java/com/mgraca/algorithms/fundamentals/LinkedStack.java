package com.mgraca.algorithms.fundamentals;

import java.util.EmptyStackException;
import java.util.Iterator;

public class LinkedStack<T> implements Iterable<T>{
  private Node head;
  private int size;

  private class Node{
    T data;
    Node next;
  }

  /**
   * Checks if the stack is empty
   * @return  True if the stack is empty
   */
  public boolean isEmpty(){
    return head == null;
  }

  /**
   * Shows the amount of items in the stack
   * @return  The number of items in the stack
   */
  public int size(){
    return size;
  }

  /**
   * Retrieves the stack's top entry
   * @return  The object at the top of the stack
   * @throws  EmptyStackException if the stack is empty
   */
  public T peek(){
    if (isEmpty()){
      throw new EmptyStackException();
    }
    else{
      return head.data;
    }
  }

  /**
   * Adds an item to the top of the stack
   * @param entry The item being added
   */
  public void push(T entry){
    Node temp = new Node();
    temp.data = entry;
    temp.next = head;
    head = temp;
    size++;
  }

  /**
   * Removes an item from the top of the stack
   * @return  The item that was removed
   * @throws  EmptyStackException if the stack was empty prior to the operation
   */
  public T pop(){
    if (isEmpty()){
      throw new EmptyStackException();
    }
    else{
      T temp = head.data;
      head = head.next;
      size--;
      return temp;
    }
  }

  /**
   * Removes all entries from the stack
   */
  public void clear(){
    head = null;
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
