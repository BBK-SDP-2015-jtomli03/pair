package test

import org.scalatest._
import connectfour._

class AITests extends FlatSpec with Matchers {

  // Suite of tests for the class AI
  /*
   * tests needed for:
   * - AI.createGameTree(s: State, d: Int): Unit
   * - AI.minimax(ai: AI, s: State): Unit
   * - AI.getMoves(b: Board): Array[Move]
   */
  
  "An AI player" should "have at least one possible move on a new board for any d up to 4" in {
    for (i <- 1 to 5) new AI(RED, i).getMoves(new Board()).length should be > (0)
  }
  
  it should "have at least one possible move on a board with one previous move for any d up to 4" in {
    val board = new Board()
    val move = new Move(RED, 0)
    board.makeMove(move)

    for (i <- 1 to 5) new AI(YELLOW, i).getMoves(board).length should be > (0)
  }
}