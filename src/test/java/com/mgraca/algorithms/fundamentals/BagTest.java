package com.mgraca.algorithms.fundamentals;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class BagTest{
  /****************************************************************************
   * Constructor Tests
   ***************************************************************************/
  @Test
  public void constructorCreatesBag(){
    Bag<Integer> bag = new Bag<>();
    assertTrue(bag.size() == 0);
  }

  /****************************************************************************
   * Add test
   ***************************************************************************/
  @Test
  public void addingItemAddsItToTheBag(){
    Bag<Integer> bag = new Bag<>();
    bag.add(10);
    for (Integer x : bag)
      assertTrue(x == 10);
    assertTrue(bag.size() == 1);
  }

  @Test
  public void addingManyDistinctItemsToTheBagAddsItToTheBag(){
    Bag<Integer> bag = new Bag<>();
    for (int i = 0; i < 10; i++)
      bag.add(i);
    int j = 10;
    for (Integer x : bag)
      assertTrue(x == --j); 
    assertTrue(bag.size() == 10);
  }

  @Test
  public void addingSameItemsToTheBagAddsIt(){
    Bag<Integer> bag = new Bag<>();
    for (int i = 0; i < 10; i++)
      bag.add(4);
    for (Integer x : bag)
      assertTrue(x == 4); 
    assertTrue(bag.size() == 10);
  }
}
