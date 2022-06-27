package com.mgraca.algorithms.searching.symboltable;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class BinarySearchSTTest{
  @Test
  public void defaultConstructorTest(){
    BinarySearchST<String, Integer> st = new BinarySearchST<>();
    assertTrue(st.size() == 0);
  }

  @Test
  public void initialCapacityConstructorTest(){
    BinarySearchST<String, Integer> st = new BinarySearchST<>(30);
    assertTrue(st.size() == 0);
  }

  @Test
  public void getFromEmpty(){
    BinarySearchST<String, Integer> st = new BinarySearchST<>();
    assertTrue(st.get("Key") == null);
  }

  @Test(expected=IllegalArgumentException.class)
  public void getUsingNullThrowsException(){
    BinarySearchST<String, Integer> st = new BinarySearchST<>();
    st.get(null);
  }

  @Test
  public void putAddsToTable(){
    BinarySearchST<String, Integer> st = new BinarySearchST<>();
    st.put("Apple", 3);
    Integer expected = 3;
    Integer actual = st.get("Apple");
    String msg = "Expected " + expected + ", returned " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test(expected=IllegalArgumentException.class)
  public void putNullKeyThrowsException(){
    BinarySearchST<String, Integer> st = new BinarySearchST<>();
    st.put(null, 3);
  }

  @Test
  public void putNullValueDeletesKeyValuePair(){
    BinarySearchST<String, Integer> st = new BinarySearchST<>();
    st.put("Apple", 3);
    st.put("Apple", null);
    assertTrue(st.get("Apple") == null);
  }

  @Test
  public void putNullValueWithKeyNotInTableDeletesNothing(){
    BinarySearchST<String, Integer> st = new BinarySearchST<>();
    st.put("Apple", 3);
    st.put("Banana", null);
    assertTrue(st.get("Apple") == 3);
  }

  @Test(expected=IllegalArgumentException.class)
  public void getWithNullThrowsException(){
    BinarySearchST<String, Integer> st = new BinarySearchST<>();
    st.get(null);
  }

  @Test
  public void getFromEmptyReturnsNull(){
    BinarySearchST<String, Integer> st = new BinarySearchST<>();
    st.get("Meatball");
  }

  @Test
  public void getFromKeyNotInTableReturnsNull(){
    BinarySearchST<String, Integer> st = new BinarySearchST<>();
    st.put("Apple", 4);
    assertTrue(st.get("Meatball") == null);
  }

  @Test
  public void getFromKeyInTable(){
    BinarySearchST<String, Integer> st = new BinarySearchST<>();
    st.put("Apple", 4);
    st.put("Banana", 3);
    assertTrue(st.get("Apple") == 4);
  }

  @Test
  public void getFromKeyDeletedFromTableReturnsNull(){
    BinarySearchST<String, Integer> st = new BinarySearchST<>();
    st.put("Apple", 4);
    st.delete("Apple");
    assertTrue(st.get("Apple") == null);
  }

  @Test
  public void deleteFromEmptyDoesNothing(){
    BinarySearchST<String, Integer> st = new BinarySearchST<>();
    st.delete("Apple");
    assertTrue(st.size() == 0);
  }

  @Test(expected=IllegalArgumentException.class)
  public void deleteWithNullThrowsException(){
    BinarySearchST<String, Integer> st = new BinarySearchST<>();
    st.delete(null);
  }

  @Test
  public void deleteKeyNotInTableDoesNothing(){
    BinarySearchST<String, Integer> st = new BinarySearchST<>();
    st.put("Banana", 4);
    st.delete("Apple");
    assertTrue(st.size() == 1);
  }

  @Test
  public void deleteKeyInTableRemovesIt(){
    BinarySearchST<String, Integer> st = new BinarySearchST<>();
    st.put("Banana", 4);
    st.delete("Banana");
    assertTrue(st.size() == 0 && st.get("Banana") == null);
  }

  @Test
  public void multipleDeletesAndPutsMaintainsExpectedBehavior(){
    BinarySearchST<String, Integer> st = new BinarySearchST<>(10);
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
  public void resizingMaintainsSTThroughManyPuts(){
    BinarySearchST<String, Integer> st = new BinarySearchST<>();
    st.put("Apple", 3);
    st.put("Banana", 4);
    st.put("Cantaloupe", 1);
    st.put("Durian", 2);
    assertTrue("Put not properly filling table; size != 4", st.size() == 4);
    assertTrue( "Table not properly filled after resizing",
                st.get("Apple") == 3 &&
                st.get("Banana") == 4 &&
                st.get("Cantaloupe") == 1 &&
                st.get("Durian") == 2);
  }

  @Test
  public void resizingMaintainsSTThroughManyPutsAndDeletes(){
    BinarySearchST<String, Integer> st = new BinarySearchST<>();
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
}
