package com.mgraca.algorithms.sorting.sortapps;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class SelectKthSmallestItemsTest{
  @Test
  public void selectTest1(){
    Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    int expected = 4;
    int actual = SelectKthSmallestItems.select(a, 3);
    String msg = "Expected " + expected + ", returned " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void selectTest2(){
    Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    int expected = 6;
    int actual = SelectKthSmallestItems.select(a, 5);
    String msg = "Expected " + expected + ", returned " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void selectTest3(){
    Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    int expected = 1;
    int actual = SelectKthSmallestItems.select(a, 0);
    String msg = "Expected " + expected + ", returned " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void selectTest4(){
    Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    int expected = 9;
    int actual = SelectKthSmallestItems.select(a, 8);
    String msg = "Expected " + expected + ", returned " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void selectMedianTest1(){
    Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    int expected = 5;
    int actual = SelectKthSmallestItems.selectMedian(a);
    String msg = "Expected " + expected + ", returned " + actual;
    assertTrue(msg, expected == actual);
  }
}
