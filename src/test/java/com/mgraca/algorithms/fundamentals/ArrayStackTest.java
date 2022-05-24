package com.mgraca.algorithms.fundamentals;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.EmptyStackException;

public class ArrayStackTest{
  @Test
  public void defaultConstructorHasProperSize(){
    ArrayStack<Integer> stack = new ArrayStack<>();
    Integer expected = 0;
    Integer actual = stack.size();
    String msg = "Expected " + expected + "; returned " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void peekIntoEmptyStack(){
    ArrayStack<Integer> stack = new ArrayStack<>();
    String partialMsg = "Expected EmptyStackException thrown; returned ";
    Integer num = -1;
    try{
      num = stack.peek();
      assertTrue(partialMsg + "no exception thrown", false);
    }
    catch (EmptyStackException e){
      assertTrue(true);
    }
    catch (Exception e){
      assertTrue(partialMsg + e.toString(), false);
    }
  }

  @Test
  public void defaultConstructorMakesEmptyStack(){
    ArrayStack<Integer> stack = new ArrayStack<>();
    assertTrue("Expected empty stack", stack.isEmpty());
  }

  @Test
  public void pushIntoEmptyStack(){
    ArrayStack<Integer> stack = new ArrayStack<>();
    stack.push(13);
    Integer expected = 13;
    Integer actual = stack.peek();
    String msg = "Expected " + expected + "; returned " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void nonEmptyStackIsNotEmpty(){
    ArrayStack<Integer> stack = new ArrayStack<>();
    stack.push(13);
    assertTrue("Expected stack to be not empty", !stack.isEmpty());
  }

  @Test
  public void popFromEmptyStack(){
    ArrayStack<Integer> stack = new ArrayStack<>();
    String partialMsg = "Expected EmptyStackException thrown; returned ";
    Integer num = -1;
    try{
      num = stack.pop();
      assertTrue(partialMsg + "no exception thrown", false);
    }
    catch (EmptyStackException e){
      assertTrue(true);
    }
    catch (Exception e){
      assertTrue(partialMsg + e.toString(), false);
    }
  }

  @Test
  public void popFromNonEmptyStack(){
    ArrayStack<Integer> stack = new ArrayStack<>();
    stack.push(13);
    Integer expected = 13;
    Integer actual = stack.pop();
    String msg = "Expected " + expected + "; returned " + actual;
    assertTrue(msg, expected == actual);
  }

  @Test
  public void popFromStackWithOneItemMakesItEmpty(){
    ArrayStack<Integer> stack = new ArrayStack<>();
    stack.push(13);
    Integer expected = 13;
    Integer actual = stack.pop();
    assertTrue("Expected stack to be empty", stack.isEmpty());
  }

  @Test
  public void clearEmptiesTheStack(){
    ArrayStack<Integer> stack = new ArrayStack<>();
    stack.push(13);
    stack.clear();
    assertTrue("Expected stack to be empty", stack.isEmpty());
  }

  @Test
  public void stackResizesSafely(){
    ArrayStack<Integer> stack = new ArrayStack<>();
    stack.push(13);
    stack.push(15);
    int actual = stack.pop();
    assertTrue("Expected 15; returned " + actual, actual == 15);
    actual = stack.pop();
    assertTrue("Expected 13; returned " + actual, actual == 13);
  }

  @Test
  public void multiplePushesAndPopsMaintainsIntegrityOfStack(){
    ArrayStack<Integer> stack = new ArrayStack<>();
    int[] actual = {0, 2, 4, 6, 8, 10, 1, 3, 5, 7, 9};
    int n = actual.length;
    for (int i = 0; i < n; i++){
      stack.push(actual[i]);
    }
    int j;
    for (int i = 0; i < n; i++){
      j = stack.pop();
      assertTrue("Expected " + actual[n-i-1] + "; returned " + j, actual[n-i-1] == j); 
    }
  }
}
