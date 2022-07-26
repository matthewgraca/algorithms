package com.mgraca.algorithms.searching.searchapps;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.util.NoSuchElementException;
import java.util.ArrayList;

public class SETTest{

  /*****************************************************************************
   * Constructor Tests
   ****************************************************************************/
  
  @Test
  public void defaultConstructorCreatesSet(){
    SET<Integer> set = new SET<>();
    assertTrue(set.size() == 0);
  }

  /*****************************************************************************
   * Add Tests
   ****************************************************************************/

  @Test(expected=IllegalArgumentException.class)
  public void addingNullKeyToSetThrowsException(){
    SET<Integer> set = new SET<>();
    set.add(null);
  }

  @Test
  public void addProperlyAddsToSet(){
    SET<Integer> set = new SET<>();
    set.add(3);
    assertTrue("size() not properly incremented", set.size() == 1);
    assertTrue("Element not added to the set", set.contains(3));
  }

  /*****************************************************************************
   * Delete Tests
   ****************************************************************************/

  @Test(expected=IllegalArgumentException.class)
  public void removingNullKeyThrowsException(){
    SET<Integer> set = new SET<>();
    set.remove(null);
  }

  @Test
  public void removingFromEmptySetDoesNothing(){
    SET<Integer> set = new SET<>();
    set.remove(3);
    assertTrue("Size decreased despite remove from empty set", set.size() == 0);
  }

  @Test
  public void removingKeyFromSetRemovesIt(){
    SET<Integer> set = new SET<>();
    Integer key = 3;
    set.add(key);
    set.remove(key);
    assertTrue("Size not decremented", set.size() == 0);
    assertTrue("Key not removed from set", !set.contains(key));
  }

  @Test
  public void removingAKeyNotInTheSetDoesNothing(){
    SET<Integer> set = new SET<>();
    set.add(3);
    set.remove(2);
    assertTrue("Size decremented without removal", set.size() == 1);
    assertTrue("Key removed from set", set.contains(3));
  }

  /*****************************************************************************
   * Contains Tests
   ****************************************************************************/
  
  @Test(expected=IllegalArgumentException.class)
  public void searchingForNullKeyThrowsException(){
    SET<Integer> set = new SET<>();
    set.contains(null);
  }

  @Test
  public void searchingForKeyInEmptySetReturnsFalse(){
    SET<Integer> set = new SET<>();
    assertTrue("Key found despite empty set", !set.contains(3));
  }

  @Test
  public void searchingForKeyInSetThatIsInTheSetReturnsTrue(){
    SET<Integer> set = new SET<>();
    set.add(3);
    assertTrue("Key in the set not found", set.contains(3));
  }

  /*****************************************************************************
   * Empty Tests
   ****************************************************************************/

  @Test
  public void filledThenEmptiedSetIsEmpty(){
    SET<Integer> set = new SET<>();
    set.add(3);
    set.add(2);
    set.add(1);
    set.remove(1);
    set.remove(2);
    set.remove(3);
    assertTrue("Set not emptied", set.isEmpty());
  }

  /*****************************************************************************
   * Min Tests
   ****************************************************************************/

  @Test(expected=NoSuchElementException.class)
  public void minOfEmptySetThrowsException(){
    SET<Integer> set = new SET<>();
    set.min();
  }

  @Test
  public void minGetsSmallestKeyInSet(){
    SET<Integer> set = new SET<>();
    set.add(1);
    set.add(5);
    set.add(-1);
    set.add(7);
    assertTrue("Minimum value not retrieved", set.min() == -1);
  }

  /*****************************************************************************
   * Max Tests
   ****************************************************************************/

  @Test(expected=NoSuchElementException.class)
  public void maxOfEmptySetThrowsException(){
    SET<Integer> set = new SET<>();
    set.max();
  }

  @Test
  public void maxGetsSmallestKeyInSet(){
    SET<Integer> set = new SET<>();
    set.add(1);
    set.add(5);
    set.add(-1);
    set.add(7);
    assertTrue("Maximum value not retrieved", set.max() == 7);
  }

  /*****************************************************************************
   * Floor Tests
   ****************************************************************************/

  @Test(expected=IllegalArgumentException.class)
  public void nullFloorThrowsException(){
    SET<Integer> set = new SET<>();
    set.floor(null);
  }

  @Test(expected=NoSuchElementException.class)
  public void floorOfEmptySetThrowsException(){
    SET<Integer> set = new SET<>();
    set.floor(3);
  }

  @Test(expected=NoSuchElementException.class)
  public void floorOfSetWithAllKeysGreaterThanItThrowsException(){
    SET<Integer> set = new SET<>();
    set.add(1);
    set.add(2);
    set.add(3);
    set.floor(0);
  }

  @Test
  public void floorOfSetWithSomeKeysGreaterThanItGetsProperKey(){
    SET<Integer> set = new SET<>();
    set.add(1);
    set.add(2);
    set.add(4);
    set.add(5);
    assertTrue("Expected 2; returned " + set.floor(3), set.floor(3) == 2);
  }

  @Test
  public void floorOfSetWithAllKeysSmallerThanItGetsProperKey(){
    SET<Integer> set = new SET<>();
    set.add(1);
    set.add(2);
    set.add(4);
    set.add(5);
    assertTrue("Expected 5; returned " + set.floor(10), set.floor(10) == 5);
  }

  @Test
  public void floorOfSetWithSameKeyReturnsThatKey(){
    SET<Integer> set = new SET<>();
    set.add(1);
    set.add(2);
    set.add(4);
    assertTrue("Expected 2; returned " + set.floor(2), set.floor(2) == 2);
  }

  /*****************************************************************************
   * Ceiling Tests
   ****************************************************************************/

  @Test(expected=IllegalArgumentException.class)
  public void nullCeilingThrowsException(){
    SET<Integer> set = new SET<>();
    set.ceiling(null);
  }

  @Test(expected=NoSuchElementException.class)
  public void ceilingOfEmptySetThrowsException(){
    SET<Integer> set = new SET<>();
    set.ceiling(3);
  }

  @Test(expected=NoSuchElementException.class)
  public void ceilingOfSetWithAllKeysLessThanItThrowsException(){
    SET<Integer> set = new SET<>();
    set.add(1);
    set.add(2);
    set.add(3);
    set.ceiling(4);
  }

  @Test
  public void ceilingOfSetWithSomeKeysLessThanItGetsProperKey(){
    SET<Integer> set = new SET<>();
    set.add(1);
    set.add(2);
    set.add(4);
    set.add(5);
    assertTrue("Expected 2; returned " + set.ceiling(3), set.ceiling(3) == 4);
  }

  @Test
  public void ceilingOfSetWithAllKeysGreaterThanItGetsProperKey(){
    SET<Integer> set = new SET<>();
    set.add(1);
    set.add(2);
    set.add(4);
    set.add(5);
    assertTrue("Expected 5; returned " + set.ceiling(0), set.ceiling(0) == 1);
  }

  @Test
  public void ceilingOfSetWithSameKeyReturnsThatKey(){
    SET<Integer> set = new SET<>();
    set.add(1);
    set.add(2);
    set.add(4);
    assertTrue("Expected 2; returned " + set.ceiling(2), set.ceiling(2) == 2);
  }

  /****************************************************************************
   * Equals Tests
   ***************************************************************************/
  
  @Test
  public void equalityReflexiveTest(){
    SET<Integer> set = new SET<>();
    set.add(1);
    set.add(2);
    set.add(4);
    assertTrue(set.equals(set));
  }

  @Test
  public void equalitySymmetricTest(){
    SET<Integer> thisSet = new SET<>();
    thisSet.add(1);
    thisSet.add(2);
    thisSet.add(4);

    SET<Integer> thatSet = new SET<>();
    thatSet.add(1);
    thatSet.add(2);
    thatSet.add(4);

    assertTrue(thisSet.equals(thatSet));
    assertTrue(thatSet.equals(thisSet));
  }

  @Test
  public void equalityTransitiveTest(){
    SET<Integer> x = new SET<>();
    x.add(1);
    x.add(2);
    x.add(4);

    SET<Integer> y = new SET<>();
    y.add(1);
    y.add(2);
    y.add(4);

    SET<Integer> z = new SET<>();
    z.add(1);
    z.add(2);
    z.add(4);

    assertTrue(x.equals(y));
    assertTrue(y.equals(z));
    assertTrue(x.equals(z));
  }

  @Test
  public void equalityConsistencyTest(){
    SET<Integer> x = new SET<>();
    x.add(1);
    x.add(2);
    x.add(4);

    SET<Integer> y = new SET<>();
    y.add(1);
    y.add(2);
    y.add(4);

    assertTrue(x.equals(y));
    assertTrue(x.equals(y));
    assertTrue(x.equals(y));
    assertTrue(x.equals(y));
  }

  @Test
  public void equalityNotNullTest(){
    SET<Integer> x = new SET<>();
    x.add(1);
    x.add(2);
    x.add(4);

    assertTrue(!x.equals(null));
  }

  @Test
  public void equalityClassDifferenceTest(){
    SET<Integer> x = new SET<>();
    x.add(1);
    x.add(2);
    x.add(4);

    ArrayList<Integer> y = new ArrayList<>();
    y.add(1);
    y.add(2);
    y.add(4);

    assertTrue(!x.equals(y));
  }

  /****************************************************************************
   * Union Tests
   ***************************************************************************/

  @Test(expected=IllegalArgumentException.class)
  public void unionWithANullSetThrowsException(){
    SET<Integer> set1 = new SET<>();
    set1.add(1);
    set1.union(null);
  }

  @Test
  public void unionWithAnEmptySetReturnsThisSet(){
    SET<Integer> thisSet = new SET<>();
    SET<Integer> thatSet = new SET<>();
    SET<Integer> thenSet;
    thisSet.add(1);
    thisSet.add(2);
    thisSet.add(4);
    thenSet = thisSet.union(thatSet);
    assertTrue(thisSet.equals(thenSet));
  }

  @Test
  public void unionWithTwoDisjointSets(){
    SET<Integer> x = new SET<>();
    x.add(0);
    x.add(2);
    x.add(4);

    SET<Integer> y = new SET<>();
    y.add(1);
    y.add(3);
    y.add(5);

    SET<Integer> z = x.union(y);
    SET<Integer> expected = new SET<>();
    expected.add(0);
    expected.add(1);
    expected.add(2);
    expected.add(3);
    expected.add(4);
    expected.add(5);

    assertTrue(z.equals(expected));
  }

  @Test
  public void unionWithTwoSetsWithSomeIntersectingKeys(){
    SET<Integer> x = new SET<>();
    x.add(0);
    x.add(2);
    x.add(4);

    SET<Integer> y = new SET<>();
    y.add(0);
    y.add(2);
    y.add(5);

    SET<Integer> z = x.union(y);
    SET<Integer> expected = new SET<>();
    expected.add(0);
    expected.add(2);
    expected.add(4);
    expected.add(5);

    assertTrue(z.equals(expected));
  }

  /****************************************************************************
   * Intersection Tests
   ***************************************************************************/

  @Test(expected=IllegalArgumentException.class)
  public void intersectionWithANullSetThrowsException(){
    SET<Integer> set1 = new SET<>();
    set1.add(1);
    set1.intersection(null);
  }

  @Test
  public void intersectionWithAnEmptySetReturnsEmptySet(){
    SET<Integer> x = new SET<>();
    SET<Integer> y = new SET<>();
    x.add(1);
    x.add(2);
    x.add(4);
    SET<Integer> z = x.intersection(y);
    assertTrue(z.isEmpty());
  }

  @Test
  public void intersectionWithTwoDisjointSets(){
    SET<Integer> x = new SET<>();
    x.add(0);
    x.add(2);
    x.add(4);

    SET<Integer> y = new SET<>();
    y.add(1);
    y.add(3);
    y.add(5);

    SET<Integer> z = x.intersection(y);

    assertTrue(z.isEmpty());
  }

  @Test
  public void intersectionWithTwoSetsWithSomeIntersectingKeys(){
    SET<Integer> x = new SET<>();
    x.add(0);
    x.add(2);
    x.add(4);

    SET<Integer> y = new SET<>();
    y.add(0);
    y.add(2);
    y.add(5);

    SET<Integer> z = x.intersection(y);
    SET<Integer> expected = new SET<>();
    expected.add(0);
    expected.add(2);

    assertTrue(z.equals(expected));
  }
}
