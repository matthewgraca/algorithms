package com.mgraca.algorithms.sorting.sortcompare;

/* This class tracks the amount of seconds passed from when the object was created, to the call of 
 *  elapsedTime(). Primarily used for timing algorithms
 */
public class Stopwatch
{
  private final long start;

  public Stopwatch()
  {
    start = System.currentTimeMillis();
  }

  /**
   * Determines the amount of time that has passed between the creation of the Stopwatch object and 
   *  the call of elapsedTime()
   * @return the amount time between the creation of Stopwatch and the call of elapsedTime(), in 
   *  seconds
   */ 
  public double elapsedTime()
  {
    long now = System.currentTimeMillis();
    return (now - start) / 1000.0;
  }
}
