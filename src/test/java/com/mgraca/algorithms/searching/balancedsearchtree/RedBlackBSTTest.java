package com.mgraca.algorithms.searching.balancedsearchtree;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.util.NoSuchElementException;
import com.mgraca.algorithms.searching.binarysearchtree.BST;

public class RedBlackBSTTest{
  @Test
  public void defaultConstructorTest(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    assertTrue(st.size() == 0);
  }

  @Test
  public void getFromEmpty(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    assertTrue(st.get("Key") == null);
  }

  @Test(expected=IllegalArgumentException.class)
  public void getUsingNullThrowsException(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.get(null);
  }

  @Test
  public void putAddsToTable(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.put("Apple", 3);
    Integer expected = 3;
    Integer actual = st.get("Apple");
    String msg = "Expected " + expected + ", returned " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test(expected=IllegalArgumentException.class)
  public void putNullKeyThrowsException(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.put(null, 3);
  }

  @Test
  public void putNullValueDeletesKeyValuePair(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.put("Apple", 3);
    st.put("Apple", null);
    assertTrue(st.get("Apple") == null);
  }

  @Test
  public void putNullValueWithKeyNotInTableDeletesNothing(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.put("Apple", 3);
    st.put("Banana", null);
    assertTrue(st.get("Apple") == 3);
  }

  @Test(expected=IllegalArgumentException.class)
  public void getWithNullThrowsException(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.get(null);
  }

  @Test
  public void getFromEmptyReturnsNull(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.get("Meatball");
  }

  @Test
  public void getFromKeyNotInTableReturnsNull(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.put("Apple", 4);
    assertTrue(st.get("Meatball") == null);
  }

  @Test
  public void getFromKeyInTable(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.put("Apple", 4);
    st.put("Banana", 3);
    assertTrue(st.get("Apple") == 4);
  }

  @Test
  public void getFromKeyDeletedFromTableReturnsNull(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.put("Apple", 4);
    st.delete("Apple");
    assertTrue(st.get("Apple") == null);
  }

  @Test
  public void deleteFromEmptyDoesNothing(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.delete("Apple");
    assertTrue(st.size() == 0);
  }

  @Test(expected=IllegalArgumentException.class)
  public void deleteWithNullThrowsException(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.delete(null);
  }

  @Test
  public void deleteKeyNotInTableDoesNothing(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.put("Banana", 4);
    st.delete("Apple");
    assertTrue("Expected size = 1; returned size = " + st.size(), st.size() == 1);
  }

  @Test
  public void deleteKeyInTableRemovesIt(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.put("Banana", 4);
    st.delete("Banana");
    assertTrue("Item was not removed", st.get("Banana") == null);
    assertTrue("Expected size = 0, returned size = " + st.size(), st.size() == 0);
  }

  @Test
  public void multipleDeletesAndPutsMaintainsExpectedBehavior(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
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
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
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
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.put("Apple", 3);
    st.put("Banana", 4);
    st.put("Cantaloupe", 1);
    st.put("Durian", 2);
    assertTrue("Expected Apple, returned " + st.min(), st.min() == "Apple");
  }

  @Test
  public void minOfTreeWithOneNodeReturnsThatNode(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.put("Apple", 3);
    assertTrue(st.min() == "Apple");
  }

  @Test(expected=NoSuchElementException.class)
  public void minOfEmptyTreeThrowsException(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.min();
  }

  @Test
  public void maxGetsMaximumOfManyItems(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.put("Apple", 3);
    st.put("Banana", 4);
    st.put("Cantaloupe", 1);
    st.put("Durian", 2);
    assertTrue("Expected Durian, returned " + st.max(), st.max() == "Durian");
  }

  @Test
  public void maxOfTreeWithOneNodeReturnsThatNode(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.put("Apple", 3);
    assertTrue(st.max() == "Apple");
  }

  @Test(expected=NoSuchElementException.class)
  public void maxOfEmptyTreeThrowsException(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.max();
  }

  @Test
  public void getCeilingOfManyItems(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
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
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.ceiling("Apple");
  }

  @Test(expected=IllegalArgumentException.class)
  public void ceilingOfNullKeyThrowsException(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.ceiling(null);
  }

  @Test(expected=NoSuchElementException.class)
  public void ceilingOfTableWithItemsOnlyLessThanItThrowsException(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.put("Apple", 3);
    st.put("Banana", 4);
    st.put("Cantaloupe", 1);
    st.put("Durian", 2);
    st.ceiling("Eggplant");
  }

  @Test
  public void getFloorOfManyItems(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
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
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.floor("Apple");
  }

  @Test(expected=IllegalArgumentException.class)
  public void floorOfNullKeyThrowsException(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.floor(null);
  }

  @Test(expected=NoSuchElementException.class)
  public void floorOfTableWithItemsOnlyGreaterThanItThrowsException(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.put("Apple", 3);
    st.put("Banana", 4);
    st.put("Cantaloupe", 1);
    st.put("Durian", 2);
    st.floor("Aardvark");
  }

  @Test(expected=IllegalArgumentException.class)
  public void selectBelowRangeThrowsException(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.put("Apple", 3);
    st.put("Banana", 4);
    st.put("Cantaloupe", 1);
    st.put("Durian", 2);
    st.select(-1);
  }

  public void selectAfterRangeThrowsException(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.put("Apple", 3);
    st.put("Banana", 4);
    st.put("Cantaloupe", 1);
    st.put("Durian", 2);
    st.select(5);
  }
  @Test
  public void selectGetsProperKey(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.put("Apple", 3);
    st.put("Banana", 4);
    st.put("Cantaloupe", 1);
    st.put("Durian", 2);
    assertTrue(st.select(2) == "Cantaloupe");
  }

  @Test
  public void rankOfEmptyTableReturns0(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    assertTrue(st.rank("A") == 0);
  }

  @Test
  public void rankOfKeyNotInTableReturns0(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.put("Banana", 4);
    assertTrue(st.rank("A") == 0);
  }

  @Test
  public void rankOfFilledTableReturnsProperValue(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.put("Apple", 3);
    st.put("Banana", 4);
    st.put("Cantaloupe", 1);
    st.put("Durian", 2);
    assertTrue(st.rank("Cantaloupe") == 2);
  }

  @Test(expected=NoSuchElementException.class)
  public void deleteMinFromEmptyTableThrowsException(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.deleteMin();
  }

  @Test
  public void deleteMinFromFilledTableSuccessfully(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.put("Apple", 3);
    st.put("Banana", 4);
    st.put("Cantaloupe", 1);
    st.put("Durian", 2);
    st.deleteMin();
    assertTrue(st.min() != "Apple");
  }

  @Test(expected=NoSuchElementException.class)
  public void deleteMaxFromEmptyTableThrowsException(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.deleteMax();
  }

  @Test
  public void deleteMaxFromFilledTableSuccessfully(){
    RedBlackBST<String, Integer> st = new RedBlackBST<>();
    st.put("Apple", 3);
    st.put("Banana", 4);
    st.put("Cantaloupe", 1);
    st.put("Durian", 2);
    st.deleteMax();
    assertTrue(st.max() != "Durian");
  }

  @Test
  public void RedBlackBSTIsFasterThanBST(){
    BST<Integer, Integer> bst = new BST<>();
    RedBlackBST<Integer, Integer> rbbst = new RedBlackBST<>();
    int n = 10000;

    // bst should be O(n) linear time
    long bstTime = System.currentTimeMillis();
    for (int i = 0; i < n; i++){
      bst.put(i, i);
    }
    bstTime = System.currentTimeMillis() - bstTime;

    // rbbst should be O(lgn) logarithmic time
    long rbbstTime = System.currentTimeMillis();
    for (int i = 0; i < n; i++){
      rbbst.put(i, i);
    }
    rbbstTime = System.currentTimeMillis() - rbbstTime;

    // hopefully, 10k items should have lg beat n every time
    String msg = "Expected RedBlackBST to search and put faster. " +
      "BST: " + bstTime + ", RedBlackBST: " + rbbstTime;
    assertTrue(msg, bstTime > rbbstTime);
  }
}
