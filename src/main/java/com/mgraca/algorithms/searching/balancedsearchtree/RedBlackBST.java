package com.mgraca.algorithms.searching.balancedsearchtree;

import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;

public class RedBlackBST<Key extends Comparable<Key>, Value>{
  private Node root;
  private static final boolean RED = true;
  private static final boolean BLACK = false;

  // Node helper class
  private class Node{
    private Key key;
    private Value val;
    private Node left, right;
    private int n;          // # of nodes in subtree rooted here
    private boolean color;  // color of link between parent and this node

    public Node(Key key, Value val, int n, boolean color){
      this.key = key;
      this.val = val;
      this.n = n;
      this.color = color;
    }
  }

  /****************************************************************************
   * Node helper methods
   ***************************************************************************/

  /**
   * Gets the size of the tree at the root
   * @return the size of the tree at the root
   */
  public int size(){
    return size(root);
  }

  // get the number of nodes rooted at a given node
  private int size(Node node){
    return node == null ? 0 : node.n;
  }

  // check if the parent of x linking to x is red or not
  private boolean isRed(Node x){
    return x == null ? false : x.color == RED;
  }

  /**
   * Checks if the table is empty
   * @return true if the table is empty, false if not
   */
  public boolean isEmpty(){
    return root == null;
  }

  /****************************************************************************
   * Standard BST iterative search
   ***************************************************************************/
  /**
   * Returns the value associated with the given key
   * @param key the key
   * @return the value associated with the given key if the key is in the table
   *    and null if the key is not in the table
   * @throws IllegalArgumentException if the key is null
   */
  public Value get(Key key){
    if (key == null)
      throw new IllegalArgumentException("Cannot get a null key");
    return get(root, key);
  }

  // iterative search for key
  private Value get(Node x, Key key){
    while (x != null){
      int cmp = key.compareTo(x.key);
      if (cmp < 0)      // if the key to find is smaller, go left
        x = x.left;
      else if (cmp > 0) // if it's larger, go right
        x = x.right;
      else              // if found, return value
        return x.val;
    }
    return null;  // search miss; return null
  }

  /**
   * Checks if the symbol table contains a given key
   * @param key the key being searched
   * @return true if the table contains the key, false if not
   * @throws IllegalArgumentException if the key is null
   */
  public boolean contains(Key key){
    return get(key) != null;
  }

  /****************************************************************************
   * Insertion
   ***************************************************************************/
  /**
   * Inserts a key-value pair into the tree, deletes key if value is null
   * @param key the key being added
   * @param val the value being added
   * @throws IllegalArgumentException if the given key is null
   */
  public void put(Key key, Value val){
    if (key == null)
      throw new IllegalArgumentException("Cannot put null key into tree");
    if (val == null){
      delete(key);
      return;
    }
    root = put(root, key, val);
    root.color = BLACK;
  }

  // recursive put
  private Node put(Node h, Key key, Value val){
    // base case: insert at null leaf
    if (h == null)
      return new Node(key, val, 1, RED);

    // search for position to insert
    int cmp = key.compareTo(h.key);
    if (cmp < 0)
      h.left = put(h.left, key, val);
    else if (cmp > 0)
      h.right = put(h.right, key, val);
    else
      h.val = val;

    // clean up right-leaning links
    if (isRed(h.right) && !isRed(h.left))
      h = rotateLeft(h);
    if (isRed(h.left) && isRed(h.left.left))
      h = rotateRight(h);
    if (isRed(h.right) && isRed(h.left))
      flipColors(h);

    // update size
    h.n = size(h.left) + size(h.right) + 1;
    return h;
  }

  /****************************************************************************
   * Deletion
   ***************************************************************************/

  /**
   * Removes a given key and its associated value from the symbol table
   * @param key the key
   * @throws IllegalArgumentException if the given key is null
   */
  public void delete(Key key){
    if (key == null)
      throw new IllegalArgumentException("Cannot delete null key");
    if (!contains(key))
      return;

    // if both children are black, set root to red
    if (!isRed(root.left) && !isRed(root.right))
      root.color = RED;

    root = delete(root, key);
    if (!isEmpty())
      root.color = BLACK;
  }

  // delete the key-value pair at the key rooted at h
  private Node delete(Node h, Key key){
    if (key.compareTo(h.key) < 0){
      if (!isRed(h.left) && !isRed(h.left.left))
        h = moveRedLeft(h);
      h.left = delete(h.left, key);
    }
    else{
      if (isRed(h.left))
        h = rotateRight(h);
      if (key.compareTo(h.key) == 0 && (h.right == null))
        return null;
      if (!isRed(h.right) && !isRed(h.right.left))
        h = moveRedRight(h);
      if (key.compareTo(h.key) == 0){
        Node x = min(h.right);
        h.key = x.key;
        h.val = x.val;
        //h.val = get(h.right, min(h.right).key);
        //h.key = min(h.right).key;
        h.right = deleteMin(h.right);
      }
      else
        h.right = delete(h.right, key);
    }
    return balance(h);
  }

  /**
   * Removes the smallest key and its associated value from the table
   * @throws NoSuchElementException if the table is empty
   */
  public void deleteMin(){
    if (isEmpty())
      throw new NoSuchElementException("Cannot delete from empty table");

    // if both children are black, set root to red
    if (!isRed(root.left) && !isRed(root.right))
      root.color = RED;

    root = deleteMin(root);
    if (isEmpty())
      root.color = BLACK;
  }

  // delete the smallest key-value pair at the node rooted at h
  private Node deleteMin(Node h){
    if (h.left == null) // search miss
      return null;

    // if left child and grandchild are black, transform
    if (!isRed(h.left) && !isRed(h.left.left))
      h = moveRedLeft(h);

    h.left = deleteMin(h.left);
    return balance(h);
  }

  /**
   * Removes the largest key and its associated value from the table
   * @throws NoSuchElementException if the table is empty
   */
  public void deleteMax(){
    if (isEmpty())
      throw new NoSuchElementException("Cannot delete from empty table");

    // if both children are black, set root to red
    if (!isRed(root.left) && !isRed(root.right))
      root.color = RED;

    root = deleteMax(root);
    if (isEmpty())
      root.color = BLACK;
  }

  // delete the largest key-value pair at the node rooted at h
  private Node deleteMax(Node h){
    if (isRed(h.left))
      h = rotateRight(h);
    if (h.right == null)  // search miss
      return null;

    if (!isRed(h.right) && !isRed(h.right.left))
      h = moveRedRight(h);

    h.right = deleteMax(h.right);

    return balance(h);
  }
  /****************************************************************************
    * red-black balancing helper functions
    ***************************************************************************/
  // rotate the node rooted at h left
  private Node rotateLeft(Node h){
    // exchange links
    Node x = h.right;
    h.right = x.left;
    x.left = h;
    // set link colors
    x.color = h.color;
    h.color = RED;
    // set new size
    x.n = h.n;
    h.n = size(h.left) + size(h.right) + 1;
    return x;
  }

  // rotate the node rooted at h right
  private Node rotateRight(Node h){
    // exchange links
    Node x = h.left;
    h.left = x.right;
    x.right= h;
    // set link colors
    x.color = h.color;
    h.color = RED;
    // set new size
    x.n = h.n;
    h.n = size(h.left) + size(h.right) + 1;
    return x;
  }

  // make the links from h black and the link into h red
  private void flipColors(Node h){
    // generalized for insertion and deletion
    h.color = !h.color;
    h.left.color = !h.left.color;
    h.right.color = !h.right.color;
    //h.color = RED;
    //h.left.color = BLACK;
    //h.right.color = BLACK;
  }

  // assuming h is red and both h.left and h.lef.left are black,
  // make h.left or one of its children red
  private Node moveRedLeft(Node h){
    flipColors(h);
    if (isRed(h.right.left)){
      h.right = rotateRight(h.right);
      h = rotateLeft(h);
      flipColors(h);
    }
    return h;
  }

  // assuming h is red and both h.right and h.right.left are black,
  // make h.right or one of its children red
  private Node moveRedRight(Node h){
    flipColors(h);
    if (isRed(h.left.left)){
      h = rotateRight(h);
      flipColors(h);
    }
    return h;
  }

  // restores red-black tree invariant
  private Node balance(Node h){
    if (isRed(h.right) && !isRed(h.left))
      h = rotateLeft(h);
    if (isRed(h.left) && isRed(h.left.left))
      h = rotateRight(h);
    if (isRed(h.left) && isRed(h.right))
      flipColors(h);
    h.n = size(h.left) + size(h.right) + 1;
    return h;
  }

  /****************************************************************************
   * Ordered symbol table methods
   ***************************************************************************/
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
    if (rank < 0 || rank >= size())
      throw new IllegalArgumentException("Rank parameter must be [0," + size() + "-1]");
    Node x = select(root, rank);
    return x.key;
  }

  private Node select(Node x, int k){
    if (x == null)
      return null;
    int t = size(x.left); // num of keys smaller than the current key
    if (t > k)
      return select(x.left, k);
    else if (t < k)
      return select(x.right, k-t-1);  // k-t-1 = rank to find in right subtree
    else
      return x;
  }

  /**
   * Gets the number of keys in the symbol table less than the given key
   * @param key the key
   * @return the number of keys in the symbol table less than the given key
   * @throws IllegalArgumentException if the given key is null
   */
  public int rank(Key key){
    return rank(key, root);
  }

  private int rank(Key key, Node x){
    if (x == null)
      return 0;
    int cmp = key.compareTo(x.key);
    if (cmp < 0)
      return rank(key, x.left);
    else if (cmp > 0)
      return 1 + size(x.left) + rank(key, x.right);
    else
      return size(x.left);
  }

}
