package test

import org.scalatest._
import connectfour._

class StateTests extends FlatSpec with Matchers {
  
  //Suite of tests for the class State
  
  "The initial state" should "have Board.NUM_COLS children, all with player YELLOW (assuming RED goes first)" in {
    val initialState = new State(RED, new Board(), null)
    initialState.initializeChildren()
    val children = initialState.getChildren()
    children.length should be (Board.NUM_COLS)
  }
  
  "Children of the initial state" should "have player YELLOW, assuming RED goes first" in {
	  val initialState = new State(RED, new Board(), null)
	  initialState.initializeChildren()
	  val children = initialState.getChildren()
	  for (child <- children) {
		  child.getPlayer() should be (YELLOW)
	  }
  }
  
  it should "report that the correct player made the previous move" in {
    val initialState = new State(RED, new Board(), null)
    initialState.initializeChildren()
    val children = initialState.getChildren()
    (children) foreach (child => child.getLastMove().player should be (RED))    
  }
}