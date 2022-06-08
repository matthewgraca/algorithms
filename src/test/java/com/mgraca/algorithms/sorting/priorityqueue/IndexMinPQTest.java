package com.mgraca.algorithms.sorting.priorityqueue;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;

public class IndexMinPQTest{
  @Test
  public void singleIntConstructorCreatesPQ(){
    IndexMinPQ<Integer> minpq = new IndexMinPQ<>(10);
    assertTrue(minpq.size() == 0);
  }

  @Test
  public void insertAddsToEmptyPQ(){
    IndexMinPQ<String> minpq = new IndexMinPQ<>(10);
    minpq.insert(0, "Apple");
    assertTrue(minpq.minKey() == "Apple");
  }

  @Test
  public void insertInReverseOrderReturnsProperMin(){
    IndexMinPQ<String> minpq = new IndexMinPQ<>(10);
    minpq.insert(0, "Zebra");
    minpq.insert(1, "Yak");
    minpq.insert(2, "X-Ray Tetra");
    minpq.insert(3, "Walrus");
    assertTrue(minpq.minKey() == "Walrus");
  }

  @Test
  public void insertInOrderReturnsProperMin(){
    IndexMinPQ<String> minpq = initializePQ();
    assertTrue(minpq.minKey() == "Apple");
  }

  @Test(expected = IllegalArgumentException.class)
  public void insertIntoFullPQThrowsException(){
    IndexMinPQ<String> minpq = new IndexMinPQ<>(1);
    minpq.insert(0, "Apple");
    minpq.insert(1, "Banana");
  }

  @Test(expected = IllegalArgumentException.class)
  public void insertIntoPQThatContainsPreexistingIndexThrowsException(){
    IndexMinPQ<String> minpq = new IndexMinPQ<>(10);
    minpq.insert(0, "Apple");
    minpq.insert(0, "Banana");
  }

  @Test(expected = IllegalArgumentException.class)
  public void insertWithBadIndexThrowsException(){
    IndexMinPQ<String> minpq = new IndexMinPQ<>(10);
    minpq.insert(-1, "Invalid");
  }

  @Test
  public void changeKeyDoesNotSwapMin(){
    IndexMinPQ<String> minpq = initializePQ();
    minpq.changeKey(2, "Grape");
    assertTrue(minpq.minKey() == "Apple");
  }

  @Test
  public void changeKeySwapsMin(){
    IndexMinPQ<String> minpq = initializePQ();
    minpq.changeKey(0, "Alfalfa");
    assertTrue(minpq.minKey() == "Alfalfa");
  }

  @Test(expected = NoSuchElementException.class)
  public void changeKeyOnInvalidIndexThrowsException(){
    IndexMinPQ<String> minpq = initializePQ();
    minpq.delete(3);
    minpq.changeKey(3, "Watermelon");
  }

  @Test(expected = IllegalArgumentException.class)
  public void changeKeyOnBadIndexThrowsException(){
    IndexMinPQ<String> minpq = initializePQ();
    minpq.changeKey(-1, "Invalid");
  }

  @Test
  public void containsFindsIndexInThePQ(){
    IndexMinPQ<String> minpq = initializePQ();
    assertTrue(minpq.contains(0) && minpq.contains(1) && minpq.contains(2) && minpq.contains(3));
  }

  @Test
  public void containsUnableToFindIndexNotInThePQ(){
    IndexMinPQ<String> minpq = initializePQ();
    assertTrue(!minpq.contains(4));
  }

  @Test(expected = IllegalArgumentException.class)
  public void containsGivenBadIndexThrowsException(){
    IndexMinPQ<String> minpq = initializePQ();
    minpq.contains(-1);
  }

  @Test(expected = NoSuchElementException.class)
  public void minOfEmptyPQThrowsException(){
    IndexMinPQ<Integer> minpq = new IndexMinPQ<>(10);
    Integer min = minpq.minKey();
  }

  @Test
  public void deleteMinFromPQWorks(){
    IndexMinPQ<String> minpq = initializePQ();
    minpq.delete(0);
    assertTrue(minpq.minKey() != "Apple");
  }

  @Test
  public void deleteNonMinStillKeepsMin(){
    IndexMinPQ<String> minpq = initializePQ();
    minpq.delete(1);
    assertTrue(minpq.minKey() == "Apple");
  }

  @Test(expected = IllegalArgumentException.class)
  public void deleteWithBadIndexThrowsException(){
    IndexMinPQ<String> minpq = initializePQ();
    minpq.delete(-1);
  }

  @Test(expected = NoSuchElementException.class)
  public void deleteWithInvalidIndexThrowsException(){
    IndexMinPQ<String> minpq = initializePQ();
    minpq.delete(3);
    minpq.delete(3);
  }

  @Test
  public void minIndexForUntouchedPQ(){
    IndexMinPQ<String> minpq = initializePQ();
    assertTrue(minpq.minIndex() == 0);
  }

  @Test
  public void minIndexFromDeletedPQ(){
    IndexMinPQ<String> minpq = initializePQ();
    minpq.delete(0);
    assertTrue(minpq.minIndex() == 1);
  }

  @Test(expected = NoSuchElementException.class)
  public void minIndexFromEmptyPQThrowsException(){
    IndexMinPQ<String> minpq = new IndexMinPQ<>(10);
    minpq.minIndex();
  }

  @Test
  public void delMinRemovesMin(){
    IndexMinPQ<String> minpq = initializePQ();
    assertTrue(minpq.delMin() == 0);
  }
 
  @Test(expected = NoSuchElementException.class)
  public void deleteFromEmptyPQThrowsException(){
    IndexMinPQ<Integer> minpq = new IndexMinPQ<>(10);
    minpq.delMin();
  }

  @Test
  public void emptyPQIsEmpty(){
    IndexMinPQ<Integer> minpq = new IndexMinPQ<>(10);
    assertTrue(minpq.isEmpty());
  }

  @Test
  public void nonEmptyPQIsNotEmpty(){
    IndexMinPQ<String> minpq = initializePQ(); 
    assertTrue(!minpq.isEmpty());
  }

  @Test
  public void sizeOfHalfFilledPQIsCorrect(){
    IndexMinPQ<String> minpq = initializePQ(); 
    assertTrue(minpq.size() == 4);
  }

  @Test
  public void sizeOfEmptyPQIsZero(){
    IndexMinPQ<Integer> minpq = new IndexMinPQ<>(10); 
    assertTrue(minpq.size() == 0);
  }

  @Test
  public void keyOfReturnsValidKey(){
    IndexMinPQ<String> minpq = initializePQ();
    assertTrue(minpq.keyOf(2) == "Cantaloupe");
  }

  @Test(expected = NoSuchElementException.class)
  public void keyOfInvalidIndexThrowsException(){
    IndexMinPQ<String> minpq = initializePQ();
    minpq.delete(3);
    minpq.keyOf(3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void keyOfBadIndexThrowsException(){
    IndexMinPQ<String> minpq = initializePQ();
    minpq.keyOf(11);
  }

 /****************************************************************************
   * Helper functions to create a populated priority queue
   ***************************************************************************/
  private IndexMinPQ<String> initializePQ(){
    IndexMinPQ<String> minpq = new IndexMinPQ<>(10);
    minpq.insert(0, "Apple");
    minpq.insert(1, "Banana");
    minpq.insert(2, "Cantaloupe");
    minpq.insert(3, "Durian");
    return minpq;
  }
}
