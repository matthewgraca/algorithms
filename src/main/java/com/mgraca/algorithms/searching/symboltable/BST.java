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
   * @throws NoSuchElementException if the table is empty
   */
  public Key max(){
    if (isEmpty()) 
      throw new NoSuchElementException("Cannot get the max of an empty table");
    Node x = max(root);
    return x.key;
  }

  private Node max(Node x){
    if (x.right == null)
      return x;
    return max(x.right);
  }

  /**
   * Gets the min key value in the symbol table
   * @return the min key value in the symbol table
   * @throws NoSuchElementException if the table is empty
   */
  public Key min(){
    if (isEmpty())
      throw new NoSuchElementException("Cannot get the min of an empty table");
    Node x = min(root);
    return x.key;
  }

  private Node min(Node x){
    if (x.left == null)
      return x;
    return min(x.left);
  }

  /**
   * Gets the smallest key equal to or greater than the given key
   * @param key the key being compared to
   * @return the smallest key equal to or greater than the given key
   * @throws IllegalArgumentException if the given key is null
   * @throws NoSuchElementException if the table is empty or all the keys in the table are smaller 
   */
  public Key ceiling(Key key){
    if (key == null)
      throw new IllegalArgumentException("Cannot get the ceiling of a null key");
    if (isEmpty())
      throw new NoSuchElementException("Cannot get the floor of an empty symbol table");
    Node x = ceiling(root, key);
    if (x == null)
      throw new NoSuchElementException("All keys are less than the given key");
    return x.key;
  }

  private Node ceiling(Node x, Key key){
    if (x == null)  // search miss
      return null;
    int cmp = key.compareTo(x.key);
    if (cmp == 0) // search hit - same key
      return x;
    if (cmp > 0)  // larger key available, keep searching
      return ceiling(x.right, key);
    // overshoot; refine search
    Node t = ceiling(x.left, key);
    if (t != null) return t;
    else return x;
  }

  /**
   * Gets the largest key equal to or less than the given key
   * @param key the key being compared to
   * @return the largest key equal to or less than the given key
   * @throws IllegalArgumentException if the given key is null
   * @throws NoSuchElementException if the table is empty or all the keys in the table are larger 
   */
  public Key floor(Key key){
    if (key == null)
      throw new IllegalArgumentException("Cannot get the floor of a null key");
    if (isEmpty())
      throw new NoSuchElementException("Cannot get the floor of an empty symbol table");
    Node x = floor(root, key);
    if (x == null)
      throw new NoSuchElementException("All keys are greater than the given key");
    return x.key;
  }

  private Node floor(Node x, Key key){
    if (x == null)
      return null;
    int cmp = key.compareTo(x.key);
    if (cmp == 0)
      return x;
    if (cmp < 0)
      return floor(x.left, key);
    Node t = floor(x.right, key);
    if (t != null) return t;
    else return x;
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
    if (key == null)
      throw new IllegalArgumentException("Cannot delete null key");
    root = delete(root, key);
  }

  private Node delete(Node node, Key key){
    if (node == null) // base case 1: node not found
      return null;
    int cmp = key.compareTo(node.key);
    if (cmp < 0)
      node.left = delete(node.left, key);
    else if (cmp > 0)
      node.right = delete(node.right, key);
    else{ // base case 2: node found
      if (node.right == null) // 2a: no right child; replace with left tree 
        return node.left;     
      if (node.left == null)  // 2b: no left child; replace with right tree 
        return node.right;    
      // 2c: has both children - replace with minimum node in right subtree
      Node tree = node;
      node = min(tree.right);
      node.right = deleteMin(tree.right);
      node.left = tree.left;
    }
    node.n = size(node.left) + size(node.right) + 1;
    return node;
  }

  /**
   * Removes the minimum key and its associated value from the symbol table
   * @throws NoSuchElementException if the symbol table is empty
   */
  public void deleteMin(){
    if (isEmpty())
      throw new NoSuchElementException("Canno delete the minimum of an empty table");
    root = deleteMin(root);
  }

  private Node deleteMin(Node x){
    if (x.left == null)
      return x.right;
    x.left = deleteMin(x.left);
    x.n = size(x.left) + size(x.right) + 1;
    return x;
  }

  /**
   * Removes the maximum key and its associated value from the symbol table
   * @throws NoSuchElementException if the symbol table is empty
   */
  public void deleteMax(){

  }

  private boolean isEmpty(){
    return size() == 0;
  }
}
