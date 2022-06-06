package com.mgraca.algorithms.sorting.priorityqueue;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.NoSuchElementException;

public class MaxPQTest{
  @Test
  public void defaultConstructorCreatesPQ(){
    MaxPQ<Integer> maxpq = new MaxPQ<>();
    assertTrue(maxpq.size() == 0);
  }

  @Test
  public void singleIntConstructorCreatesPQ(){
    MaxPQ<Integer> maxpq = new MaxPQ<>(10);
    assertTrue(maxpq.size() == 0);
  }

  @Test
  public void arrayConstructorCreatesProperPQ(){
    Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    MaxPQ<Integer> maxpq = new MaxPQ<>(arr);
    assertTrue(maxpq.isMaxHeap());
  }

  @Test(expected = NoSuchElementException.class)
  public void maxOfEmptyPQThrowsException(){
    MaxPQ<Integer> maxpq = new MaxPQ<>();
    Integer max = maxpq.max();
  }

  @Test
  public void midValueInsertMaintainsHeapInvariant(){
    Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    MaxPQ<Integer> maxpq = new MaxPQ<>(arr);
    maxpq.insert(5);
    assertTrue(maxpq.isMaxHeap());
  }

  @Test
  public void maxValueInsertMaintainsHeapInvariant(){
    Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    MaxPQ<Integer> maxpq = new MaxPQ<>(arr);
    maxpq.insert(11);
    assertTrue(maxpq.isMaxHeap());
  }

  @Test
  public void lowerValueInsertMaintainsHeapInvariant(){
    Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    MaxPQ<Integer> maxpq = new MaxPQ<>(arr);
    maxpq.insert(0);
    assertTrue(maxpq.isMaxHeap());
  }

  @Test
  public void deleteMaxMaintainsHeapInvariant(){
    Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    MaxPQ<Integer> maxpq = new MaxPQ<>(arr);
    maxpq.delMax();
    assertTrue(maxpq.isMaxHeap());
  }

  @Test
  public void insertResizingMaintainsHeapInvariant(){
    MaxPQ<Integer> maxpq = new MaxPQ<>();
    maxpq.insert(5);
    maxpq.insert(12);
    maxpq.insert(154);
    maxpq.insert(2);
    maxpq.insert(0);
    assertTrue(maxpq.isMaxHeap());
  }

  @Test
  public void deleteResizingMaintiansHeapInvariant(){
    Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    MaxPQ<Integer> maxpq = new MaxPQ<>(arr);
    for (int i = 0; i < arr.length-arr.length/4; i++){
      maxpq.delMax();
    }
    assertTrue(maxpq.isMaxHeap());
  }

  @Test(expected = NoSuchElementException.class)
  public void deleteFromEmptyPQThrowsException(){
    MaxPQ<Integer> maxpq = new MaxPQ<>();
    maxpq.delMax();
  }
}
