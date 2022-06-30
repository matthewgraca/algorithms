package com.mgraca.algorithms.searching.symboltable;

import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value>{
  private Node root;

  private class Node{
    private Key key;
    private Value val;
    private Node left, right;
    private int n;  // # of nodes in subtree rooted here

    public Node(Key key, Value val, int n){
      this.key = key;
      this.val = val;
      this.n = n;
    }
  }

  /**
   * Gets the size of the tree at the root
   * @return the size of the tree at the root
   */
  public int size(){
    return size(root);
  }

  // get the number of nodes rooted at a given node
  private int size(Node node){
    if (node == null)
      return 0;
    else
      return node.n;
  }

  /**
   * Gets the value of a given key in the tree
   * @param key The key being searched for
   * @return  the value associated with the given key, null if the key is 
   *          not in the table
   * @throws IllegalArgumentException if the given key is null
   */
  public Value get(Key key){
    return get(root, key);
  }

  // get the key rooted at a given node
  private Value get(Node node, Key key){
    if (key == null)
      throw new IllegalArgumentException("Cannot get null key from symbol table");
    if (node == null) // base case 1: search miss
      return null;
    int cmp = key.compareTo(node.key);
    if (cmp < 0)      // search left subtree
      return get(node.left, key);
    else if (cmp > 0) // search right subtree
      return get(node.right, key);
    else              // base case 2: seach hit
      return node.val;
  }

  /**
   * Adds the given key-value pair into the symbol table
   * @param key the key being added
   * @param val the value being added
   * @throws IllegalArgumentException if the given key is null
   */
  public void put(Key key, Value val){
    if (key == null)
      throw new IllegalArgumentException("Cannot put null key into symbol table");
    if (val == null){
      delete(key);
      return;
    }
    root = put(root, key, val);
  }

  // puts the key rooted at a given node
  private Node put(Node node, Key key, Value val){
    if (node == null) // base case 1: add new leaf
      return new Node(key, val, 1);
    int cmp = key.compareTo(node.key);
    if (cmp < 0)      // put in left subtree
      node.left = put(node.left, key, val);
    else if (cmp > 0) // put in right subtree
      node.right = put(node.right, key, val);
    else              // base case 2: put at current node (update val)
      node.val = val;
    // update size
    node.n = size(node.left) + size(node.right) + 1;
    return node;
  }

  /**
   * Gets the max key value in the symbol table
   * @return the max key value in the symbol table
   */
  public Key max(){
    return null;
  }

  /**
   * Gets the min key value in the symbol table
   * @return the min key value in the symbol table
   */
  public Key min(){
    return null;
  }

  /**
   * Gets the smallest key equal to or greater than the given key
   * @param key the key being compared to
   * @return the smallest key equal to or greater than the given key
   * @throws IllegalArgumentException if the given key is null
   */
  public Key ceiling(Key key){
    return null;
  }

  /**
   * Gets the largest key equal to or less than the given key
   * @param key the key being compared to
   * @return the largest key equal to or less than the given key
   * @throws IllegalArgumentException if the given key is null
   */
  public Key floor(Key key){
    return null;
  }

  /**
   * Gets the key in the symbol table of a given rank 
   * @param rank the relative order of a key
   * @return  the key in the symbol table of a given rank   
   * @throws IllegalArgumentException if the given rank is not between 0 and n-1
   */
  public Key select(int rank){
    return null;
  }

  /**
   * Gets the number of keys in the symbol table less than the given key
   * @param key the key
   * @return the number of keys in the symbol table less than the given key
   * @throws IllegalArgumentException if the given key is null
   */
  public int rank(Key key){
    return 0;
  }

  /**
   * Removes a given key and its associated value from the symbol table
   * @param key the key
   * @throws IllegalArgumentException if the given key is null
   */
  public void delete(Key key){

  }

  /**
   * Removes the minimum key and its associated value from the symbol table
   * @throws NoSuchElementException if the symbol table is empty
   */
  public void deleteMin(){

  }

  /**
   * Removes the maximum key and its associated value from the symbol table
   * @throws NoSuchElementException if the symbol table is empty
   */
  public void deleteMax(){

  }
}
