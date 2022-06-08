package com.mgraca.algorithms.sorting.priorityqueue;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;

public class IndexMaxPQTest{
  @Test
  public void singleIntConstructorCreatesPQ(){
    IndexMaxPQ<Integer> maxpq = new IndexMaxPQ<>(10);
    assertTrue(maxpq.size() == 0);
  }

  @Test
  public void insertAddsToEmptyPQ(){
    IndexMaxPQ<String> maxpq = new IndexMaxPQ<>(10);
    maxpq.insert(0, "Apple");
    assertTrue(maxpq.maxKey() == "Apple");
  }

  @Test
  public void insertInReverseOrderReturnsProperMax(){
    IndexMaxPQ<String> maxpq = new IndexMaxPQ<>(10);
    maxpq.insert(0, "Zebra");
    maxpq.insert(1, "Yak");
    maxpq.insert(2, "X-Ray Tetra");
    maxpq.insert(3, "Walrus");
    assertTrue(maxpq.maxKey() == "Zebra");
  }

  @Test
  public void insertInOrderReturnsProperMax(){
    IndexMaxPQ<String> maxpq = initializePQ();
    assertTrue(maxpq.maxKey() == "Durian");
  }

  @Test(expected = IllegalArgumentException.class)
  public void insertIntoFullPQThrowsException(){
    IndexMaxPQ<String> maxpq = new IndexMaxPQ<>(1);
    maxpq.insert(0, "Apple");
    maxpq.insert(1, "Banana");
  }

  @Test(expected = IllegalArgumentException.class)
  public void insertIntoPQThatContainsPreexistingIndexThrowsException(){
    IndexMaxPQ<String> maxpq = new IndexMaxPQ<>(10);
    maxpq.insert(0, "Apple");
    maxpq.insert(0, "Banana");
  }

  @Test(expected = IllegalArgumentException.class)
  public void insertWithBadIndexThrowsException(){
    IndexMaxPQ<String> maxpq = new IndexMaxPQ<>(10);
    maxpq.insert(-1, "Invalid");
  }

  @Test
  public void changeKeyDoesNotSwapMax(){
    IndexMaxPQ<String> maxpq = initializePQ();
    maxpq.changeKey(2, "Blackberry");
    assertTrue(maxpq.maxKey() == "Durian");
  }

  @Test
  public void changeKeySwapsMax(){
    IndexMaxPQ<String> maxpq = initializePQ();
    maxpq.changeKey(3, "Zombie");
    assertTrue(maxpq.maxKey() == "Zombie");
  }

  @Test(expected = NoSuchElementException.class)
  public void changeKeyOnInvalidIndexThrowsException(){
    IndexMaxPQ<String> maxpq = initializePQ();
    maxpq.delete(3);
    maxpq.changeKey(3, "Watermelon");
  }

  @Test(expected = IllegalArgumentException.class)
  public void changeKeyOnBadIndexThrowsException(){
    IndexMaxPQ<String> maxpq = initializePQ();
    maxpq.changeKey(-1, "Invalid");
  }

  @Test
  public void containsFindsIndexInThePQ(){
    IndexMaxPQ<String> maxpq = initializePQ();
    assertTrue(maxpq.contains(0) && maxpq.contains(1) && maxpq.contains(2) && maxpq.contains(3));
  }

  @Test
  public void containsUnableToFindIndexNotInThePQ(){
    IndexMaxPQ<String> maxpq = initializePQ();
    assertTrue(!maxpq.contains(4));
  }

  @Test(expected = IllegalArgumentException.class)
  public void containsGivenBadIndexThrowsException(){
    IndexMaxPQ<String> maxpq = initializePQ();
    maxpq.contains(-1);
  }

  @Test(expected = NoSuchElementException.class)
  public void maxOfEmptyPQThrowsException(){
    IndexMaxPQ<Integer> maxpq = new IndexMaxPQ<>(10);
    Integer max = maxpq.maxKey();
  }

  @Test
  public void deleteMaxFromPQWorks(){
    IndexMaxPQ<String> maxpq = initializePQ();
    maxpq.delete(3);
    assertTrue(maxpq.maxKey() != "Durian");
  }

  @Test
  public void deleteNonMaxStillKeepsMax(){
    IndexMaxPQ<String> maxpq = initializePQ();
    maxpq.delete(1);
    assertTrue(maxpq.maxKey() == "Durian");
  }

  @Test(expected = IllegalArgumentException.class)
  public void deleteWithBadIndexThrowsException(){
    IndexMaxPQ<String> maxpq = initializePQ();
    maxpq.delete(-1);
  }

  @Test(expected = NoSuchElementException.class)
  public void deleteWithInvalidIndexThrowsException(){
    IndexMaxPQ<String> maxpq = initializePQ();
    maxpq.delete(3);
    maxpq.delete(3);
  }

  @Test
  public void maxIndexForUntouchedPQ(){
    IndexMaxPQ<String> maxpq = initializePQ();
    assertTrue(maxpq.maxIndex() == 3);
  }

  @Test
  public void maxIndexFromDeletedPQ(){
    IndexMaxPQ<String> maxpq = initializePQ();
    maxpq.delete(3);
    assertTrue(maxpq.maxIndex() == 2);
  }

  @Test(expected = NoSuchElementException.class)
  public void maxIndexFromEmptyPQThrowsException(){
    IndexMaxPQ<String> maxpq = new IndexMaxPQ<>(10);
    maxpq.maxIndex();
  }

  @Test
  public void delMaxRemovesMax(){
    IndexMaxPQ<String> maxpq = initializePQ();
    assertTrue(maxpq.delMax() == 3);
  }
 
  @Test(expected = NoSuchElementException.class)
  public void deleteFromEmptyPQThrowsException(){
    IndexMaxPQ<Integer> maxpq = new IndexMaxPQ<>(10);
    maxpq.delMax();
  }

  @Test
  public void emptyPQIsEmpty(){
    IndexMaxPQ<Integer> maxpq = new IndexMaxPQ<>(10);
    assertTrue(maxpq.isEmpty());
  }

  @Test
  public void nonEmptyPQIsNotEmpty(){
    IndexMaxPQ<String> maxpq = initializePQ(); 
    assertTrue(!maxpq.isEmpty());
  }

  @Test
  public void sizeOfHalfFilledPQIsCorrect(){
    IndexMaxPQ<String> maxpq = initializePQ(); 
    assertTrue(maxpq.size() == 4);
  }

  @Test
  public void sizeOfEmptyPQIsZero(){
    IndexMaxPQ<Integer> maxpq = new IndexMaxPQ<>(10); 
    assertTrue(maxpq.size() == 0);
  }

  @Test
  public void keyOfReturnsValidKey(){
    IndexMaxPQ<String> maxpq = initializePQ();
    assertTrue(maxpq.keyOf(2) == "Cantaloupe");
  }

  @Test(expected = NoSuchElementException.class)
  public void keyOfInvalidIndexThrowsException(){
    IndexMaxPQ<String> maxpq = initializePQ();
    maxpq.delete(3);
    maxpq.keyOf(3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void keyOfBadIndexThrowsException(){
    IndexMaxPQ<String> maxpq = initializePQ();
    maxpq.keyOf(11);
  }

 /****************************************************************************
   * Helper functions to create a populated priority queue
   ***************************************************************************/
  private IndexMaxPQ<String> initializePQ(){
    IndexMaxPQ<String> maxpq = new IndexMaxPQ<>(10);
    maxpq.insert(0, "Apple");
    maxpq.insert(1, "Banana");
    maxpq.insert(2, "Cantaloupe");
    maxpq.insert(3, "Durian");
    return maxpq;
  }
}
