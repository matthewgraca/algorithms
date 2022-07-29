package com.mgraca.algorithms.searching.searchapps;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.util.NoSuchElementException;

public class LRUCacheTest{

  /*****************************************************************************
   * Constructor Tests
   ****************************************************************************/

  @Test
  public void defaultConstructorInitializes(){
    LRUCache<String> cache = new LRUCache<>();
    assertTrue(cache.size() == 0);
  }

  /****************************************************************************
   * Contains Tests
   ***************************************************************************/

  @Test(expected=IllegalArgumentException.class)
  public void containsWithNullThrowsException(){
    LRUCache<String> cache = new LRUCache<>();
    cache.contains(null);
  }

  @Test
  public void containsWithEmptyCacheReturnsFalse(){
    LRUCache<String> cache = new LRUCache<>();
    assertTrue(!cache.contains("p1"));
  }

  /*****************************************************************************
   * Access Tests
   ****************************************************************************/

  @Test(expected=IllegalArgumentException.class)
  public void accessWithNullThrowsException(){
    LRUCache<String> cache = new LRUCache<>();
    cache.access(null);
  }

  @Test
  public void accessWithItemNotInCachePutsItIntoCache(){
    LRUCache<String> cache = new LRUCache<>();
    cache.access("p1");
    assertTrue(cache.contains("p1"));
    assertTrue(cache.size() == 1);
  }

  @Test
  public void accessWithManyDifferentItemsInCachePutsThemInTheCache(){
    LRUCache<String> cache = new LRUCache<>();
    cache.access("p1");
    cache.access("p2");
    cache.access("p3");
    cache.access("p4");
    assertTrue(cache.contains("p1"));
    assertTrue(cache.contains("p2"));
    assertTrue(cache.contains("p3"));
    assertTrue(cache.contains("p4"));
    assertTrue(cache.size() == 4);
  }

  @Test
  public void accessWithItemsAlreadyInCacheKeepsThemInTheCache(){
    LRUCache<String> cache = new LRUCache<>();
    cache.access("p1");
    cache.access("p2");
    cache.access("p1");
    cache.access("p2");
    assertTrue(cache.contains("p1"));
    assertTrue(cache.contains("p2"));
    assertTrue(cache.size() == 2);
  }

  /*****************************************************************************
   * Remove Tests
   ****************************************************************************/

  @Test(expected=NoSuchElementException.class)
  public void removeFromEmptyCacheThrowsException(){
    LRUCache<String> cache = new LRUCache<>();
    cache.remove();
  }

  @Test
  public void removeOneItemActuallyRemovesItem(){
    LRUCache<String> cache = new LRUCache<>();
    cache.access("p1");
    assertTrue(cache.remove() == "p1");
    assertTrue(!cache.contains("p1"));
    assertTrue(cache.size() == 0);
  }

  @Test
  public void removeItemOfManyItemsRemovesTheLRUItem(){
    LRUCache<String> cache = new LRUCache<>();
    cache.access("p1");
    cache.access("p2");
    cache.access("p3");
    cache.access("p4");
    assertTrue(cache.remove() == "p1");
    assertTrue(!cache.contains("p1"));
    assertTrue(cache.size() == 3);
  }

  @Test
  public void interwovenRemoveAndAccessReturnsRightItemWhenRemoved(){
    LRUCache<String> cache = new LRUCache<>();
    cache.access("p1");
    cache.access("p2");
    cache.access("p3");
    cache.access("p4");
    cache.access("p1"); // lru order: p2 p3 p4 p1
    assertTrue(cache.remove() == "p2");
    assertTrue(!cache.contains("p2"));
    assertTrue(cache.size() == 3);
  }
}
