package com.mgraca.algorithms.fundamentals;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.NoSuchElementException;

public class LinkedQueueTest{
  @Test
  public void defaultConstructorHasProperSize(){
    LinkedQueue<Integer> queue = new LinkedQueue<>();
    Integer expected = 0;
    Integer actual = queue.size();
    String msg = "Expected " + expected + "; returned " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void peekIntoEmptyQueue(){
    LinkedQueue<Integer> queue = new LinkedQueue<>();
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
    LinkedQueue<Integer> queue = new LinkedQueue<>();
    assertTrue("Expected empty queue", queue.isEmpty());
  }

  @Test
  public void enqueueIntoEmptyQueue(){
    LinkedQueue<Integer> queue = new LinkedQueue<>();
    queue.enqueue(13);
    Integer expected = 13;
    Integer actual = queue.peek();
    String msg = "Expected " + expected + "; returned " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void nonEmptyQueueIsNotEmpty(){
    LinkedQueue<Integer> queue = new LinkedQueue<>();
    queue.enqueue(13);
    assertTrue("Expected queue to be not empty", !queue.isEmpty());
  }

  @Test
  public void dequeueFromEmptyQueue(){
    LinkedQueue<Integer> queue = new LinkedQueue<>();
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
    LinkedQueue<Integer> queue = new LinkedQueue<>();
    queue.enqueue(13);
    Integer expected = 13;
    Integer actual = queue.dequeue();
    String msg = "Expected " + expected + "; returned " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void dequeueFromQueueWithOneItemMakesItEmpty(){
    LinkedQueue<Integer> queue = new LinkedQueue<>();
    queue.enqueue(13);
    Integer expected = 13;
    Integer actual = queue.dequeue();
    assertTrue("Expected queue to be empty", queue.isEmpty());
  }

  @Test
  public void clearEmptiesTheQueue(){
    LinkedQueue<Integer> queue = new LinkedQueue<>();
    queue.enqueue(13);
    queue.clear();
    assertTrue("Expected queue to be empty", queue.isEmpty());
  }

  @Test
  public void queueResizesSafely(){
    LinkedQueue<Integer> queue = new LinkedQueue<>();
    queue.enqueue(13);
    queue.enqueue(15);
    int actual = queue.dequeue();
    assertTrue("Expected 13; returned " + actual, actual == 13);
    actual = queue.dequeue();
    assertTrue("Expected 15; returned " + actual, actual == 15);
  }

  @Test
  public void multipleEnqueuesAndDequeuesMaintainsIntegrityOfQueue(){
    LinkedQueue<Integer> queue = new LinkedQueue<>();
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
