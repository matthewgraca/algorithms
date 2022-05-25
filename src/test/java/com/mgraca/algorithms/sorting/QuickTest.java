package com.mgraca.algorithms.sorting;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.Random;
import java.util.Arrays;

public class QuickTest{
  @Test
  public void alreadySorted(){
    Integer[] actual = new Integer[1000];
    Integer[] expected = new Integer[1000];
    for (int i = 0; i < actual.length; i++){
      actual[i] = expected[i] = i;
    }
    Arrays.sort(expected);
    Quick.sort(actual);
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
    Quick.sort(actual);
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
    Quick.sort(actual);
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
    Quick.sort(actual);
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
    Quick.sort(actual);
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
    Quick.sort(actual);
    assertTrue(Arrays.equals(actual, expected));
  }
}
