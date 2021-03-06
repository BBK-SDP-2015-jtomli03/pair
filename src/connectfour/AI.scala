package connectfour

import scala.collection.mutable.ArrayBuffer


object AI {
  /**
   * Generate the game tree with root s of depth d.
   * The game tree's nodes are State objects that represent the state of a game
   * and whose children are all possible States that can result from the next move.
   * <p/>
   * NOTE: this method runs in exponential time with respect to d.
   * With d around 5 or 6, it is extremely slow and will start to take a very
   * long time to run.
   * <p/>
   * Note: If s has a winner (four in a row), it should be a leaf.
   */
  def createGameTree(s: State, d: Int): Unit = {
    if(d > 0){
      s.initializeChildren()
      s.getChildren foreach (childState => createGameTree(childState, d-1))
    }
  }

  /**
   * Call minimax in ai with state s.
   */
  def minimax(ai: AI, s: State) {
    ai.minimax(s)
  }
}

class AI(private var player: Player, private var depth: Int) extends Solver {

  /**
   * See Solver.getMoves for the specification. Could return null.
   */
  override def getMoves(b: Board): Array[Move] = {
    val moves = ArrayBuffer[Move]()
    val newState = new State(player, b, null)
    minimax(newState)
    newState.getChildren
      .filter(_.getValue == newState.getValue)
      .foreach(child => moves.append(child.lastMove))
    moves.toArray
  }

  /**
   * State s is a node of a game tree (i.e. the current State of the game).
   * Use the Minimax algorithm to assign a numerical value to each State of the
   * tree rooted at s, indicating how desirable that java.State is to this player.
   */
  def minimax(s: State): Unit  = {
    AI.createGameTree(s, depth)
    max(s)
  }

  /**
   * If State s has no children then set its value and return that value
   * If State s has children then return the maximum value of its child states
   */
  def max(s: State): Int = {
    if(!s.hasChildren){
      s.setValue(evaluateBoard(s.getBoard))
      s.getValue
    }
    else{
      var max = Integer.MIN_VALUE
      s.getChildren foreach(child => {
        val score = min(child)
        if(score > max) max = score
      })
      s.setValue(max)
      max
    }
  }

  /**
   * If State s has no children then set its value and return that value
   * If State s has children then return the minimum value of its child states
   */
  def min(s:State): Int = {
    if(!s.hasChildren){
      s.setValue(evaluateBoard(s.getBoard))
      s.getValue
    }
    else{
      var min = Integer.MAX_VALUE
      s.getChildren foreach(child => {
        val score = max(child)
        if(score < min) min = score
      })
      s.setValue(min)
      min
    }
  }


  /**
   * Evaluate the desirability of Board b for this player
   * Precondition: b is a leaf node of the game tree (because that is most
   * effective when looking several moves into the future).
   */
  def evaluateBoard(b: Board): Int = {
    val winner = b.hasConnectFour()
    var value = 0
    if (winner == null) {
      val locs = b.winLocations()
      for (loc <- locs; p <- loc) {
        value += (if (p == player) 1 else if (p != null) -1 else 0)
      }
    } else {
      var numEmpty = 0
      var r = 0
      while (r < Board.NUM_ROWS) {
        var c = 0
        while (c < Board.NUM_COLS) {
          if (b.getTile(r, c) == null) numEmpty += 1
          c = c + 1
        }
        r = r + 1
      }
      value = (if (winner == player) 1 else -1) * 10000 * numEmpty
    }
    value
  }
}

