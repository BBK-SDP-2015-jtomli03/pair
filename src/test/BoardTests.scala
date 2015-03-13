package test

import org.scalatest._
import connectfour._

class BoardTests extends FlatSpec with Matchers {
  
  "An empty board" should "allow a player to make the first move and correctly report its location" in {
    val board = new Board()
    val move = new Move(RED, 0)
    board.makeMove(move)
    board.getPlayer(Board.NUM_ROWS - 1, 0) should be (RED)
  }
  
  it should "report that there are Board.NUM_COLS possible moves" in {
    val board = new Board()
    val moves = board.getPossibleMoves(RED)
    moves.length should be (Board.NUM_COLS)
  }
  
  "A board with one token in each column" should "report that there are Board.NUM_COLS possible moves" in {
    val board = new Board()
    for (i <- 0 until Board.NUM_COLS) {
      if (i % 2 == 0) board.makeMove(new Move(RED, i)) else board.makeMove(new Move(YELLOW, i))
    }
    val moves = board.getPossibleMoves(RED)
    moves.length should be (Board.NUM_COLS)
  }
    
  "A board with a full column" should "report there are Board.NUM_COLS - 1 possible moves" in {
    val board = new Board()
    for (i <- 1 to Board.NUM_ROWS) {
      if (i % 2 == 1) board.makeMove(new Move(RED, 0)) else board.makeMove(new Move(YELLOW, 0))
    }
    val moves = board.getPossibleMoves(YELLOW)
    moves.length should be (Board.NUM_COLS - 1)
  }

  it should "report there are 0 possible moves if the column has a Connect Four" in {
    val board = new Board()
    for (i <- 1 to 4) {
      board.makeMove(new Move(RED, 0))
    }
    val moves = board.getPossibleMoves(YELLOW)
    moves.length should be (0)
  }
  
  ignore should "not allow a further move in the same column" in {
    val board = new Board()
    val move = new Move(RED, 0)
    for (i <- 1 to Board.NUM_ROWS) {
      board.makeMove(move)
    }
  }
  
  "A board with a horizontal Connect Four" should "report there are 0 possible moves" in {
    val board = new Board()
    for (i <- 0 to 3) {
      board.makeMove(new Move(RED, i))
    }
    val moves = board.getPossibleMoves(YELLOW)
    moves.length should be (0)
  }  
 
}