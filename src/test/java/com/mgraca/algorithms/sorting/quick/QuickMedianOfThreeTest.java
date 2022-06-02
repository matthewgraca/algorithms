package com.mgraca.algorithms.sorting.quick;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.Random;
import java.util.Arrays;

public class QuickMedianOfThreeTest{
  @Test
  public void getsMedianFromPermutation1(){
    Integer[] arr = {0,1,2};
    int actual = QuickMedianOfThree.getMedian(arr, 0, 1, 2);
    int expected = 1;
    String msg = "Expected " + expected + ", returned " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void getsMedianFromPermutation2(){
    Integer[] arr = {0,2,1};
    int actual = QuickMedianOfThree.getMedian(arr, 0, 1, 2);
    int expected = 2;
    String msg = "Expected " + expected + ", returned " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void getsMedianFromPermutation3(){
    Integer[] arr = {1,2,0};
    int actual = QuickMedianOfThree.getMedian(arr, 0, 1, 2);
    int expected = 0;
    String msg = "Expected " + expected + ", returned " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void getsMedianFromPermutation4(){
    Integer[] arr = {1,0,2};
    int actual = QuickMedianOfThree.getMedian(arr, 0, 1, 2);
    int expected = 0;
    String msg = "Expected " + expected + ", returned " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void getsMedianFromPermutation5(){
    Integer[] arr = {2,0,1};
    int actual = QuickMedianOfThree.getMedian(arr, 0, 1, 2);
    int expected = 2;
    String msg = "Expected " + expected + ", returned " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void getsMedianFromPermutation6(){
    Integer[] arr = {2,1,0};
    int actual = QuickMedianOfThree.getMedian(arr, 0, 1, 2);
    int expected = 1;
    String msg = "Expected " + expected + ", returned " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void alreadySorted(){
    Integer[] actual = new Integer[1000];
    Integer[] expected = new Integer[1000];
    for (int i = 0; i < actual.length; i++){
      actual[i] = expected[i] = i;
    }
    Arrays.sort(expected);
    QuickMedianOfThree.sort(actual);
    assertTrue(Arrays.equals(actual, expected));
  }

  @Test
  public void reverseSorted(){
    Integer[] actual = new Integer[1000];
    Integer[] expected = new Integer[1000];
    int n = actual.length;
    for (int i = 0; i < n; i++){
      actual[n-i-1] = expected[n-i-1] = i;
    }
    Arrays.sort(expected);
    QuickMedianOfThree.sort(actual);
    assertTrue(Arrays.equals(actual, expected));
  }

  @Test
  public void randomListA(){
    Random rng = new Random(69420);
    Integer[] actual = new Integer[1000];
    Integer[] expected = new Integer[1000];
    for (int i = 0; i < actual.length; i++){
      actual[i] = expected[i] = rng.nextInt();
    }
    Arrays.sort(expected);
    QuickMedianOfThree.sort(actual);
    assertTrue(Arrays.equals(actual, expected));
  }

  @Test
  public void randomListB(){
    Random rng = new Random(11111);
    Integer[] actual = new Integer[1000];
    Integer[] expected = new Integer[1000];
    for (int i = 0; i < actual.length; i++){
      actual[i] = expected[i] = rng.nextInt();
    }
    Arrays.sort(expected);
    QuickMedianOfThree.sort(actual);
    assertTrue(Arrays.equals(actual, expected));
  }

  @Test
  public void randomListC(){
    Random rng = new Random(49051);
    Integer[] actual = new Integer[1000];
    Integer[] expected = new Integer[1000];
    for (int i = 0; i < actual.length; i++){
      actual[i] = expected[i] = rng.nextInt();
    }
    Arrays.sort(expected);
    QuickMedianOfThree.sort(actual);
    assertTrue(Arrays.equals(actual, expected));
  }

  @Test
  public void randomListD(){
    Random rng = new Random(57840);
    Integer[] actual = new Integer[1000];
    Integer[] expected = new Integer[1000];
    for (int i = 0; i < actual.length; i++){
      actual[i] = expected[i] = rng.nextInt();
    }
    Arrays.sort(expected);
    QuickMedianOfThree.sort(actual);
    assertTrue(Arrays.equals(actual, expected));
  }
}
