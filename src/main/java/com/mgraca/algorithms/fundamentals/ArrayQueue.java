package com.mgraca.algorithms.fundamentals;

import java.util.NoSuchElementException;

public class ArrayQueue<T>{
  private T[] queue;
  private int top;
  private int bottom;
  private int size;

  public ArrayQueue(){
    @SuppressWarnings("unchecked")
    T[] temp = (T[])new Object[2];
    queue = temp;
    top = 0;
    bottom = 1;
    size = 0;
  }

  /**
   * Adds a new entry to the back of the queue
   * @param entry The item being added
   */
  public void enqueue(T entry){
    if (isFull()){
      resize(2*queue.length);
    }
    bottom = (bottom + 1) % queue.length;
    queue[bottom] = entry;
    size++;
  }

  /**
   * Removes the entry at the front of the queue
   * @return  The item at the front of the queue
   * @throws  NoSuchElementException if the queue is empty before the operation
   */
  public T dequeue(){
    if (isEmpty()){
      throw new NoSuchElementException("Cannot dequeue from empty queue");
    }
    else{
      T temp = queue[top];
      queue[top] = null;
      top = (top + 1) % queue.length;
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
      return queue[top];
    }
  }

  /**
   * Checks if the queue is empty
   * @return  True is the queue is empty
   */
  public boolean isEmpty(){
    return top == (bottom + 1) % queue.length;
  }

  /**
   * Checks if the queue is full
   */
  public boolean isFull(){
    return top == (bottom + 2) % queue.length;
  }

  /**
   * Resizes the queue
   * @param n The capacity of the new queue
   */
  private void resize(int n){
    @SuppressWarnings("unchecked")
    T[] temp = (T[])new Object[n];
    for (int i = 0; i < size; i++){
      temp[i] = queue[top];
      top = (top + 1) % queue.length;
    }
    top = 0;
    bottom = size - 1;
    queue = temp;
  }

  /**
   * Removes all entries from the queue
   */
  public void clear(){
    @SuppressWarnings("unchecked")
    T[] temp = (T[])new Object[queue.length];
    queue = temp;
    top = 0;
    bottom = 1;
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
