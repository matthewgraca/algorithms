package com.mgraca.algorithms.fundamentals;

import java.util.EmptyStackException;

public class ArrayStack<T>{
  private T[] stack;
  private int top;

  public ArrayStack(){
    @SuppressWarnings("unchecked")
    T[] temp = (T[])new Object[1];
    stack = temp;
    top = -1;
  }

  /**
   * Checks if the stack is empty
   * @return  True if the stack is empty
   */
  public boolean isEmpty(){
    return top == -1;
  }

  /**
   * Shows the amount of items in the stack
   * @return  The number of items in the stack
   */
  public int size(){
    return top + 1;
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
      return stack[top];
    }
  }

  /**
   * Adds an item to the top of the stack
   * @param entry The item being added
   */
  public void push(T entry){
    if (isFull()){
      resize(stack.length*2);
    }
    stack[++top] = entry;
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
      T item = stack[top];
      stack[top--] = null;
      if (sizeIsOneFourthOfCapacity()){
        resize(stack.length/2);
      }
      return item;
    }
  }

  /**
   * Removes all entries from the stack
   */
  public void clear(){
    @SuppressWarnings("unchecked")
    T[] temp = (T[])new Object[1];
    stack = temp;
    top = -1;
  }

  /**
   * Checks if the stack is full
   * @return  True if the stack if full
   */
  private boolean isFull(){
    return top == stack.length - 1;
  }

  /**
   * Checks if the stack size is 1/4th of its capacity
   * @return  True if the stack size is 1/4th of its capacity
   */
  private boolean sizeIsOneFourthOfCapacity(){
    return top + 1 > 0 && top + 1 == stack.length/4;
  }

  /**
   * Resizes the capacity of the stack
   * @param n The capacity of the new stack
   */
  private void resize(int n){
    @SuppressWarnings("unchecked")
    T[] temp = (T[])new Object[n];
    for (int i = 0; i < top+1; i++){
      temp[i] = stack[i];
    }
    stack = temp;
  }
}
