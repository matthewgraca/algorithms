package com.mgraca.algorithms.searching.symboltable;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.util.NoSuchElementException;

public class BSTTest{
  @Test
  public void defaultConstructorTest(){
    BST<String, Integer> st = new BST<>();
    assertTrue(st.size() == 0);
  }

  @Test
  public void getFromEmpty(){
    BST<String, Integer> st = new BST<>();
    assertTrue(st.get("Key") == null);
  }

  @Test(expected=IllegalArgumentException.class)
  public void getUsingNullThrowsException(){
    BST<String, Integer> st = new BST<>();
    st.get(null);
  }

  @Test
  public void putAddsToTable(){
    BST<String, Integer> st = new BST<>();
    st.put("Apple", 3);
    Integer expected = 3;
    Integer actual = st.get("Apple");
    String msg = "Expected " + expected + ", returned " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test(expected=IllegalArgumentException.class)
  public void putNullKeyThrowsException(){
    BST<String, Integer> st = new BST<>();
    st.put(null, 3);
  }

  @Test
  public void putNullValueDeletesKeyValuePair(){
    BST<String, Integer> st = new BST<>();
    st.put("Apple", 3);
    st.put("Apple", null);
    assertTrue(st.get("Apple") == null);
  }

  @Test
  public void putNullValueWithKeyNotInTableDeletesNothing(){
    BST<String, Integer> st = new BST<>();
    st.put("Apple", 3);
    st.put("Banana", null);
    assertTrue(st.get("Apple") == 3);
  }

  @Test(expected=IllegalArgumentException.class)
  public void getWithNullThrowsException(){
    BST<String, Integer> st = new BST<>();
    st.get(null);
  }

  @Test
  public void getFromEmptyReturnsNull(){
    BST<String, Integer> st = new BST<>();
    st.get("Meatball");
  }

  @Test
  public void getFromKeyNotInTableReturnsNull(){
    BST<String, Integer> st = new BST<>();
    st.put("Apple", 4);
    assertTrue(st.get("Meatball") == null);
  }

  @Test
  public void getFromKeyInTable(){
    BST<String, Integer> st = new BST<>();
    st.put("Apple", 4);
    st.put("Banana", 3);
    assertTrue(st.get("Apple") == 4);
  }

  @Test
  public void getFromKeyDeletedFromTableReturnsNull(){
    BST<String, Integer> st = new BST<>();
    st.put("Apple", 4);
    st.delete("Apple");
    assertTrue(st.get("Apple") == null);
  }

  @Test
  public void deleteFromEmptyDoesNothing(){
    BST<String, Integer> st = new BST<>();
    st.delete("Apple");
    assertTrue(st.size() == 0);
  }

  @Test(expected=IllegalArgumentException.class)
  public void deleteWithNullThrowsException(){
    BST<String, Integer> st = new BST<>();
    st.delete(null);
  }

  @Test
  public void deleteKeyNotInTableDoesNothing(){
    BST<String, Integer> st = new BST<>();
    st.put("Banana", 4);
    st.delete("Apple");
    assertTrue("Expected size = 1; returned size = " + st.size(), st.size() == 1);
  }

  @Test
  public void deleteKeyInTableRemovesIt(){
    BST<String, Integer> st = new BST<>();
    st.put("Banana", 4);
    st.delete("Banana");
    assertTrue("Item was not removed", st.get("Banana") == null);
    assertTrue("Expected size = 0, returned size = " + st.size(), st.size() == 0);
  }

  @Test
  public void multipleDeletesAndPutsMaintainsExpectedBehavior(){
    BST<String, Integer> st = new BST<>();
    st.put("Apple", 3);
    st.put("Banana", 4);
    st.put("Cantaloupe", 1);
    st.put("Durian", 2);
    assertTrue("Put not properly filling table", st.size() == 4);
    st.delete("Apple");
    st.delete("Banana");
    st.delete("Cantaloupe");
    st.delete("Durian");
    assertTrue("Delete not properly emptying table", st.size() == 0);
  }

  @Test
  public void manyPutsAndDeletesMaintainsST(){
    BST<String, Integer> st = new BST<>();
    st.put("Apple", 3);
    st.put("Banana", 4);
    st.put("Cantaloupe", 1);
    st.put("Durian", 2);
    st.delete("Apple");
    st.delete("Cantaloupe");
    st.delete("Banana");
    st.delete("Durian");
    assertTrue("Delete not properly emptying table; size > 0", st.size() == 0);
    assertTrue( "Delete not properly emptying after resizeing",
                st.get("Apple") == null &&
                st.get("Banana") == null &&
                st.get("Cantaloupe") == null &&
                st.get("Durian") == null);
  }

  @Test
  public void minGetsMinimumOfManyItems(){
    BST<String, Integer> st = new BST<>();
    st.put("Apple", 3);
    st.put("Banana", 4);
    st.put("Cantaloupe", 1);
    st.put("Durian", 2);
    assertTrue("Expected Apple, returned " + st.min(), st.min() == "Apple");
  }

  @Test
  public void minOfTreeWithOneNodeReturnsThatNode(){
    BST<String, Integer> st = new BST<>();
    st.put("Apple", 3);
    assertTrue(st.min() == "Apple");
  }

  @Test(expected=NoSuchElementException.class)
  public void minOfEmptyTreeThrowsException(){
    BST<String, Integer> st = new BST<>();
    st.min();
  }

  @Test
  public void maxGetsMaximumOfManyItems(){
    BST<String, Integer> st = new BST<>();
    st.put("Apple", 3);
    st.put("Banana", 4);
    st.put("Cantaloupe", 1);
    st.put("Durian", 2);
    assertTrue("Expected Durian, returned " + st.max(), st.max() == "Durian");
  }

  @Test
  public void maxOfTreeWithOneNodeReturnsThatNode(){
    BST<String, Integer> st = new BST<>();
    st.put("Apple", 3);
    assertTrue(st.max() == "Apple");
  }

  @Test(expected=NoSuchElementException.class)
  public void maxOfEmptyTreeThrowsException(){
    BST<String, Integer> st = new BST<>();
    st.max();
  }

  @Test
  public void getCeilingOfManyItems(){
    BST<String, Integer> st = new BST<>();
    st.put("Apple", 3);
    st.put("Banana", 4);
    st.put("Cantaloupe", 1);
    st.put("Durian", 2);
    String expected = "Durian";
    String actual = st.ceiling("Cranberry");
    String msg = "Expected " + expected + ", returned " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test(expected=NoSuchElementException.class)
  public void ceilingOfEmptyTableThrowsException(){
    BST<String, Integer> st = new BST<>();
    st.ceiling("Apple");
  }

  @Test(expected=IllegalArgumentException.class)
  public void ceilingOfNullKeyThrowsException(){
    BST<String, Integer> st = new BST<>();
    st.ceiling(null);
  }

  @Test(expected=NoSuchElementException.class)
  public void ceilingOfTableWithItemsOnlyLessThanItThrowsException(){
    BST<String, Integer> st = new BST<>();
    st.put("Apple", 3);
    st.put("Banana", 4);
    st.put("Cantaloupe", 1);
    st.put("Durian", 2);
    st.ceiling("Eggplant");
  }

  @Test
  public void getFloorOfManyItems(){
    BST<String, Integer> st = new BST<>();
    st.put("Apple", 3);
    st.put("Banana", 4);
    st.put("Cantaloupe", 1);
    st.put("Durian", 2);
    String expected = "Apple";
    String actual = st.floor("Baa");
    String msg = "Expected " + expected + ", returned " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test(expected=NoSuchElementException.class)
  public void floorOfEmptyTableThrowsException(){
    BST<String, Integer> st = new BST<>();
    st.floor("Apple");
  }

  @Test(expected=IllegalArgumentException.class)
  public void floorOfNullKeyThrowsException(){
    BST<String, Integer> st = new BST<>();
    st.floor(null);
  }

  @Test(expected=NoSuchElementException.class)
  public void floorOfTableWithItemsOnlyGreaterThanItThrowsException(){
    BST<String, Integer> st = new BST<>();
    st.put("Apple", 3);
    st.put("Banana", 4);
    st.put("Cantaloupe", 1);
    st.put("Durian", 2);
    st.floor("Aardvark");
  }
}
