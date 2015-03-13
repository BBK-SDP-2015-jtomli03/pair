package connectfour

import java.io.{FileNotFoundException, PrintWriter, UnsupportedEncodingException}

import connectfour.State._

import scala.beans.BeanProperty
import scala.collection.mutable.ArrayBuffer

/**
 * An instance represents the state of a game of Connect Four.
 */
object State {

  /**
   * A State array of length 0.
   */
  val length0 = Array[State]()
}

/**
 * A game state. It is player's turn to play, board = current Board layout, lastmove = most recent move taken --null
 * if the user does not care what the last move made was.
 */
class State(@BeanProperty var player: Player, @BeanProperty var board: Board, @BeanProperty var lastMove: Move)
  extends Comparable[Any] {
  /**
   * All possible game States that can result from the next player's Move.
   * The length of the array equals the number of States.
   * It is an array of length 0 if there are no possible moves
   * (once it has been set; initially, it is an array of length0)
   */
  @BeanProperty
  var children: Array[State] = length0

  /**
   * How desirable this State is.
   */
  @BeanProperty
  var value: Int = 0

  /**
   * Checks if a state has children
   * @return Boolean true if has children, false if not
   */
  def hasChildren: Boolean ={
    children.length match {
      case 0 => false
      case _ => true
    }
  }

  /**
   * Retrieves the possible moves and initializes this State's children.
   * The result is that this State's children reflect the possible
   * States that can exist after the next move. Remember, in the
   * children it is the opposite player's turn. This method
   * initializes only this State's children; it does not recursively
   * initialize all descendants.
   */
  def initializeChildren(): Unit = {
    val states = ArrayBuffer[State]()
    board.getPossibleMoves(player) foreach (move => {
      val newBoard = board.copy()
      newBoard.makeMove(move)
      player match{
        case RED => states.append(new State(YELLOW, newBoard, move))
        case YELLOW => states.append(new State(RED, newBoard, move))
      }
    })
    setChildren(states.toArray)
  }

  /**
   * Write this State to a file called "output.txt", including its
   * children, their children, etc.. This method allows the State to
   * be viewed in a file even when it is too large to print to console.
   * Beep when printing is done.
   */
  def writeToFile() {
    try {
      var writer = new PrintWriter("output.txt", "UTF-8")
      writer.println(this)
      java.awt.Toolkit.getDefaultToolkit.beep()
    } catch {
      case e @ (_: FileNotFoundException | _: UnsupportedEncodingException) => e.printStackTrace()
    }
  }

  /**
   * Return a representation of this State.
   */
  override def toString(): String = {
    println("State.toString printing")
    toStringHelper(0, "")
  }

  /**
   * Return a string that contains a representation of this board indented
   * with string ind (expected to be a string of blank characters) followed
   * by a similar representation of all its children,
   * indented an additional ind characters. d is the depth of this state.
   */
  private def toStringHelper(d: Int, ind: String): String = {
    println("in string helper")
    var str = ind + player + " to play\n"
    str = str + ind + "Value: " + value + "\n"
    str = str + board.toString(ind) + "\n"
    if (children != null && children.length > 0) {
      str = str + ind + "Children at depth " + (d + 1) + ":\n" + ind +
        "----------------\n"
      for (s <- children) {
        str = str + s.toStringHelper(d + 1, ind + "   ")
      }
    }
    str
  }

  /**
   * = -1, 0, or 1 depending on whether this object is <, = , or > than ob
   */
  override def compareTo(ob: Any): Int = 0
  // NOTE: this does not work! If you wish to use any sorting
  // method from the Java API, you must implement this.

}

