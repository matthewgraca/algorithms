package com.mgraca.algorithms.sorting.priorityqueue;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.NoSuchElementException;

public class MinPQTest{
  @Test
  public void defaultConstructorCreatesPQ(){
    MinPQ<Integer> minpq = new MinPQ<>();
    assertTrue(minpq.size() == 0);
  }

  @Test
  public void singleIntConstructorCreatesPQ(){
    MinPQ<Integer> minpq = new MinPQ<>(10);
    assertTrue(minpq.size() == 0);
  }

  @Test
  public void arrayConstructorCreatesProperPQ(){
    Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    MinPQ<Integer> minpq = new MinPQ<>(arr);
    assertTrue(minpq.isMinHeap());
  }

  @Test(expected = NoSuchElementException.class)
  public void minOfEmptyPQThrowsException(){
    MinPQ<Integer> minpq = new MinPQ<>();
    Integer min = minpq.min();
  }

  @Test
  public void midValueInsertMaintainsHeapInvariant(){
    Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    MinPQ<Integer> minpq = new MinPQ<>(arr);
    minpq.insert(5);
    assertTrue(minpq.isMinHeap());
  }

  @Test
  public void maxValueInsertMaintainsHeapInvariant(){
    Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    MinPQ<Integer> minpq = new MinPQ<>(arr);
    minpq.insert(11);
    assertTrue(minpq.isMinHeap());
  }

  @Test
  public void lowerValueInsertMaintainsHeapInvariant(){
    Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    MinPQ<Integer> minpq = new MinPQ<>(arr);
    minpq.insert(0);
    assertTrue(minpq.isMinHeap());
  }

  @Test
  public void deleteMinMaintainsHeapInvariant(){
    Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    MinPQ<Integer> minpq = new MinPQ<>(arr);
    minpq.delMin();
    assertTrue(minpq.isMinHeap());
  }

  @Test
  public void insertResizingMaintainsHeapInvariant(){
    MinPQ<Integer> minpq = new MinPQ<>();
    minpq.insert(5);
    minpq.insert(12);
    minpq.insert(154);
    minpq.insert(2);
    minpq.insert(0);
    assertTrue(minpq.isMinHeap());
  }

  @Test
  public void deleteResizingMaintiansHeapInvariant(){
    Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    MinPQ<Integer> minpq = new MinPQ<>(arr);
    for (int i = 0; i < arr.length-arr.length/4; i++){
      minpq.delMin();
    }
    assertTrue(minpq.isMinHeap());
  }

  @Test(expected = NoSuchElementException.class)
  public void deleteFromEmptyPQThrowsException(){
    MinPQ<Integer> minpq = new MinPQ<>();
    minpq.delMin();
  }
}
