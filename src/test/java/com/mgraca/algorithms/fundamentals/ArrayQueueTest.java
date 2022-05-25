package com.mgraca.algorithms.fundamentals;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.NoSuchElementException;

public class ArrayQueueTest{
  @Test
  public void defaultConstructorHasProperSize(){
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    Integer expected = 0;
    Integer actual = queue.size();
    String msg = "Expected " + expected + "; returned " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void peekIntoEmptyQueue(){
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    String partialMsg = "Expected NoSuchElementException thrown; returned ";
    Integer num = -1;
    try{
      num = queue.peek();
      assertTrue(partialMsg + "no exception thrown", false);
    }
    catch (NoSuchElementException e){
      assertTrue(true);
    }
    catch (Exception e){
      assertTrue(partialMsg + e.toString(), false);
    }
  }

  @Test
  public void defaultConstructorMakesEmptyQueue(){
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    assertTrue("Expected empty queue", queue.isEmpty());
  }

  @Test
  public void enqueueIntoEmptyQueue(){
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    queue.enqueue(13);
    Integer expected = 13;
    Integer actual = queue.peek();
    String msg = "Expected " + expected + "; returned " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void nonEmptyQueueIsNotEmpty(){
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    queue.enqueue(13);
    assertTrue("Expected queue to be not empty", !queue.isEmpty());
  }

  @Test
  public void dequeueFromEmptyQueue(){
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    String partialMsg = "Expected NoSuchElementException thrown; returned ";
    Integer num = -1;
    try{
      num = queue.dequeue();
      assertTrue(partialMsg + "no exception thrown", false);
    }
    catch (NoSuchElementException e){
      assertTrue(true);
    }
    catch (Exception e){
      assertTrue(partialMsg + e.toString(), false);
    }
  }

  @Test
  public void dequeueFromNonEmptyQueue(){
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    queue.enqueue(13);
    Integer expected = 13;
    Integer actual = queue.dequeue();
    String msg = "Expected " + expected + "; returned " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void dequeueFromQueueWithOneItemMakesItEmpty(){
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    queue.enqueue(13);
    Integer expected = 13;
    Integer actual = queue.dequeue();
    assertTrue("Expected queue to be empty", queue.isEmpty());
  }

  @Test
  public void clearEmptiesTheQueue(){
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    queue.enqueue(13);
    queue.clear();
    assertTrue("Expected queue to be empty", queue.isEmpty());
  }

  @Test
  public void queueResizesSafely(){
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    queue.enqueue(13);
    queue.enqueue(15);
    int actual = queue.dequeue();
    assertTrue("Expected 13; returned " + actual, actual == 13);
    actual = queue.dequeue();
    assertTrue("Expected 15; returned " + actual, actual == 15);
  }

  @Test
  public void multipleEnqueuesAndDequeuesMaintainsIntegrityOfQueue(){
    ArrayQueue<Integer> queue = new ArrayQueue<>();
    int[] actual = {0, 2, 4, 6, 8, 10, 1, 3, 5, 7, 9};
    int n = actual.length;
    for (int i = 0; i < n; i++){
      queue.enqueue(actual[i]);
    }
    int j;
    for (int i = 0; i < n; i++){
      j = queue.dequeue();
      assertTrue("Expected " + actual[i] + "; returned " + j, actual[i] == j); 
    }
  }
}
