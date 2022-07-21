package com.mgraca.algorithms.searching.hashtable;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class SeparateChainingHashSTTest{

  /****************************************************************************
   * Constructor Tests
   ***************************************************************************/

  @Test
  public void defaultConstructorCreatesST(){
    SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>();
    assertTrue(st.size() == 0);
  }

  @Test
  public void sizeConstructorCreatesST(){
    SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>(20);
    assertTrue(st.size() == 0);
  }

  /****************************************************************************
   * Get tests
   ***************************************************************************/

  @Test(expected=IllegalArgumentException.class)
  public void getNullKeyThrowsException(){
    SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>(20);
    st.get(null);
  }

  @Test
  public void getKeyNotInTableReturnsNull(){
    SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>(20);
    assertTrue(st.get("String") == null);
  }

  /****************************************************************************
   * Put tests
   ***************************************************************************/
  
  @Test(expected=IllegalArgumentException.class)
  public void putNullKeyThrowsException(){
    SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>(20);
    st.put(null, 3);
  }

  @Test
  public void putIncrementsSize(){
    SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>(20);
    st.put("Apple", 1);
    assertTrue(st.size() == 1);
  }

  /****************************************************************************
   * Put/get mixing tests
   ***************************************************************************/
  
  @Test
  public void putNullValueDeletesKeyValue(){
    SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>(20);
    st.put("Apple", 1);
    st.put("Apple", null);
    assertTrue("put() did not delete key-value pair", st.get("Apple") == null);
  }

  @Test
  public void putNullValueNotInTableRemovesNothing(){
    SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>(20);
    st.put("Apple", null);
    assertTrue(st.size() == 0);
  }

  @Test
  public void putSameKeyUpdatesToNewValue(){
    SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>(20);
    st.put("Apple", 1);
    st.put("Apple", 2);
    assertTrue("put() did not update value", st.get("Apple") == 2);
  }

  @Test
  public void manyPutsAndGetsValid(){
    SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>(20);
    st.put("Apple", 1);
    st.put("Banana", 2);
    st.put("Crepe", 3);
    st.put("Durian", 4);
    assertTrue(st.get("Apple") == 1);
    assertTrue(st.get("Banana") == 2);
    assertTrue(st.get("Crepe") == 3);
    assertTrue(st.get("Durian") == 4);
  }

  /****************************************************************************
   * Delete tests
   ***************************************************************************/

  @Test(expected=IllegalArgumentException.class)
  public void deleteNullKeyThrowsException(){
    SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>(20);
    st.delete(null);
  }

  @Test
  public void deleteFromEmptyTableDoesNothing(){
    SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>(20);
    st.delete("Apple");
    assertTrue(st.size() == 0);
  }

  @Test
  public void deletingKeyProperlyRemovesIt(){
    SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>(20);
    st.put("Apple", 1);
    st.delete("Apple");
    assertTrue("Key still in the table", st.get("Apple") == null);
    assertTrue("Delete not properly decrementing", st.size() == 0);
  }

  @Test
  public void manyPutsAndDeletesValid(){
    SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>(20);
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
