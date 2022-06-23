package com.mgraca.algorithms.searching.symboltable;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class SequentialSearchSTTest{
  @Test
  public void sizeForEmptyST(){
    SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
    assertTrue(st.size() == 0);
  }

  @Test
  public void sizeForFilledST(){
    SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
    String[] list = {"Catfish", "Bass", "Abalone"};
    st.put("Abalone", 0);
    st.put("Bass", 1);
    st.put("Catfish", 2);
    assertTrue(st.size() == 3);
  }

  @Test
  public void sizeForFilledThenEmptiedST(){
    SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
    String[] list = {"Catfish", "Bass", "Abalone"};
    st.put("Abalone", 0);
    st.put("Bass", 1);
    st.put("Catfish", 2);
    st.delete("Abalone");
    st.delete("Bass");
    st.delete("Catfish");
    assertTrue("Expected 0, returned " + st.size(), st.size() == 0);
  }
  @Test
  public void putAddsToTable(){
    SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
    String[] list = {"Catfish", "Bass", "Abalone"};
    st.put("Abalone", 0);
    st.put("Bass", 1);
    st.put("Catfish", 2);
    int j = 0;
    for (String i : st){
      assertTrue("Expected " + list[j] + ", returned " + i, i.equals(list[j]));
      j++;
    }
  }

  @Test
  public void getPullsFromTable(){
    SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
    String[] list = {"Catfish", "Bass", "Abalone"};
    st.put("Abalone", 0);
    st.put("Bass", 1);
    st.put("Catfish", 2);
    for (int j = 0; j < list.length; j++){
      assertTrue( "Expected " + list[j] + ", returned " + st.get(list[j]), 
                  st.get(list[j]) == list.length-1-j);
    }
  }

  @Test
  public void getNonExistentKeyReturnsNull(){
    SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
    String[] list = {"Catfish", "Bass", "Abalone"};
    st.put("Abalone", 0);
    st.put("Bass", 1);
    st.put("Catfish", 2);
    assertTrue(st.get("Shark") == null);
  }

  @Test
  public void putDuplicateUpdatesValue(){
    SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
    st.put("Abalone", 0);
    st.put("Bass", 1);
    st.put("Catfish", 2);
    st.put("Bass", 4);
    assertTrue("Expected 4, returned " + st.get("Bass"), st.get("Bass") == 4);
  }
}
