package com.mgraca.algorithms.searching.hashtable;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class LinearProbingHashSTTest{
  /****************************************************************************
   * Constructor Tests
   ***************************************************************************/

  @Test
  public void defaultConstructorCreatesST(){
    LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
    assertTrue(st.size() == 0);
  }

  @Test
  public void sizeConstructorCreatesST(){
    LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>(20);
    assertTrue(st.size() == 0);
  }

  /****************************************************************************
   * Get tests
   ***************************************************************************/

  @Test(expected=IllegalArgumentException.class)
  public void getNullKeyThrowsException(){
    LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
    st.get(null);
  }

  @Test
  public void getKeyNotInTableReturnsNull(){
    LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
    assertTrue(st.get("String") == null);
  }

  /****************************************************************************
   * Put tests
   ***************************************************************************/

  @Test(expected=IllegalArgumentException.class)
  public void putNullKeyThrowsException(){
    LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
    st.put(null, 3);
  }

  @Test
  public void putIncrementsSize(){
    LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
    st.put("Apple", 1);
    assertTrue(st.size() == 1);
  }

  /****************************************************************************
   * Put/get mixing tests
   ***************************************************************************/
  
  @Test
  public void putNullValueDeletesKeyValue(){
    LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
    st.put("Apple", 1);
    st.put("Apple", null);
    assertTrue("put() did not delete key-value pair", st.get("Apple") == null);
  }

  @Test
  public void putNullValueNotInTableRemovesNothing(){
    LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
    st.put("Apple", null);
    assertTrue(st.size() == 0);
  }

  @Test
  public void putSameKeyUpdatesToNewValue(){
    LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
    st.put("Apple", 1);
    st.put("Apple", 2);
    assertTrue("put() did not update value", st.get("Apple") == 2);
  }

  @Test
  public void manyPutsAndGetsValid(){
    LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
    st.put("Apple", 1);
    st.put("Banana", 2);
    st.put("Crepe", 3);
    st.put("Durian", 4);
    st.put("Egg", 5);
    st.put("Fruit", 6);
    assertTrue(st.get("Apple") == 1);
    assertTrue(st.get("Banana") == 2);
    assertTrue(st.get("Crepe") == 3);
    assertTrue(st.get("Durian") == 4);
    assertTrue(st.get("Egg") == 5);
    assertTrue(st.get("Fruit") == 6);
  }

  /****************************************************************************
   * Delete tests
   ***************************************************************************/

  @Test(expected=IllegalArgumentException.class)
  public void deleteNullKeyThrowsException(){
    LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
    st.delete(null);
  }

  @Test
  public void deleteFromEmptyTableDoesNothing(){
    LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
    st.delete("Apple");
    assertTrue(st.size() == 0);
  }

  @Test
  public void deletingKeyProperlyRemovesIt(){
    LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
    st.put("Apple", 1);
    st.delete("Apple");
    assertTrue("Key still in the table", st.get("Apple") == null);
    assertTrue("Delete not properly decrementing", st.size() == 0);
  }

  @Test
  public void manyPutsAndDeletesValid(){
    LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
    st.put("Apple", 1);
    st.put("Banana", 2);
    st.put("Crepe", 3);
    st.put("Durian", 4);
    st.delete("Apple");
    assertTrue(st.get("Apple") == null);
    st.delete("Banana");
    assertTrue(st.get("Banana") == null);
    st.delete("Durian");
    assertTrue(st.get("Durian") == null);
    st.delete("Crepe");
    assertTrue(st.get("Crepe") == null);
  }

}
