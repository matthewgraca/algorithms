package com.mgraca.algorithms.fundamentals;

import java.util.NoSuchElementException;

public class LinkedQueue<T>{
  private Node head;
  private Node tail;
  private int size;

  private class Node{
    T data;
    Node next;
  }

  public LinkedQueue(){
    head = tail = null;
    size = 0;
  }

  /**
   * Adds a new entry to the back of the queue
   * @param entry The item being added
   */
  public void enqueue(T entry){
    Node temp = new Node();
    temp.data = entry;
    if (isEmpty()){
      head = tail = temp;
    }
    else{
      tail.next = temp;
      tail = tail.next;
    }
    size++;
  }

  /**
   * Removes the entry at the front of the queue
   * @return  The item at the front of the queue
   * @throws  NoSuchElementException if the queue is empty before the operation
   */
  public T dequeue(){
    if (isEmpty()){
      throw new NoSuchElementException("Cannot dequeue from an empty queue");
    }
    else{
      T temp = head.data;
      head = head.next;
      if (head == null){
        tail = null;
      }
      size--;
      return temp;
    }
  }

  /**
   * Retrieves the entry at the front of the queue
   * @return  The item at the front of the queue
   * @throws  NoSuchElementException if the queue is empty
   */
  public T peek(){
    if (isEmpty()){
      throw new NoSuchElementException("Cannot peek into empty queue");
    }
    else{
      return head.data;
    }
  }

  /**
   * Checks if the queue is empty
   * @return  True is the queue is empty
   */
  public boolean isEmpty(){
    return head == null;
  }

  /**
   * Removes all entries from the queue
   */
  public void clear(){
    head = tail = null;
    size = 0;
  }

  /**
   * Gets the size of the queue
   * @return  The size of the queue
   */
  public int size(){
    return size;
  }
}
