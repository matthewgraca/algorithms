package com.mgraca.algorithms.searching.searchapps;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class UniQueueTest{

  /****************************************************************************
   * Constructor Tests
   ***************************************************************************/
  @Test
  public void defaultConstructorCreatesUniQueue(){
    UniQueue<Integer> uq = new UniQueue<>();
    assertTrue(uq.size() == 0);
  }

  /****************************************************************************
   * Enqueue Tests
   ***************************************************************************/
  @Test(expected=IllegalArgumentException.class)
  public void enqueuingWithNullThrowsException(){
    UniQueue<Integer> uq = new UniQueue<>();
    uq.enqueue(null);
  }

  @Test
  public void enqueuingAddsItemToTheQueue(){
    UniQueue<Integer> uq = new UniQueue<>();
    uq.enqueue(1);
    assertTrue(uq.size() == 1);
    assertTrue(uq.contains(1));
    assertTrue(uq.contained(1));
  }

  @Test
  public void enqueuingSameItemsDoesntAddToQueue(){
    UniQueue<Integer> uq = new UniQueue<>();
    uq.enqueue(1);
    uq.enqueue(1);
    assertTrue(uq.size() == 1);
    assertTrue(uq.contains(1));
    assertTrue(uq.contained(1));
  }

  /****************************************************************************
   * Dequeue Tests
   ***************************************************************************/
  @Test
  public void dequeueItemRemovesItFromQueue(){
    UniQueue<Integer> uq = new UniQueue<>();
    uq.enqueue(1);
    assertTrue(uq.dequeue() == 1);
    assertTrue(uq.size() == 0);
    assertTrue(!uq.contains(1));
    assertTrue(uq.contained(1));
  }
}

