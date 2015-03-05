package connectfour

/**
 * Runs the Connect Four game.
 */

object Game extends App {

  private val SLEEP_INTERVAL = 100

  val p1 = new Dummy(RED)
  val p2 = new Dummy(YELLOW)
  val game = new Game(p1, p2)
  game.runGame()
}

class Game(private var activePlayer: Solver, private var player2: Solver) {

  private var board: Board = new Board()

  private var gui: GUI = _

  private var winner: Player = _

  /**
   * Construct a new Game with p1 as the first player, p2 as the second player,
   * with b as the current Board state, and with it being p's turn to play
   * true means player 1, false means player 2).
   */
  def this(p1: Solver,
    p2: Solver,
    b: Board,
    p: Boolean) {
    this(p1, p2)
    board = b
    activePlayer = (if (p) p1 else p2)
  }

  /**
   * Attach GUI gui to this Game model.
   */
  def setGUI(gui: GUI) {
    this.gui = gui
  }

  /**
   * Notify this Game that column col has been clicked by a user.
   */
  def columnClicked(col: Int) {
    if (activePlayer.isInstanceOf[Human]) {
      activePlayer.asInstanceOf[Human].columnClicked(col)
    }
  }

  /**
   * Run the game until finished. If GUI is not initialized, the output will be
   * sent to the console.
   */
  def runGame() {
    while (!isGameOver) {
      var moveIsSafe = false
      var nextMove: Move = null
      while (!moveIsSafe) {
        val bestMoves = activePlayer.getMoves(board)
        if (bestMoves.length == 0) {
          gui.setMsg("Game cannot continue until a Move is produced.")
          //continue
        } else {
          nextMove = bestMoves(0)
        }
        if (board.getTile(0, nextMove.column) == null) {
          moveIsSafe = true
        } else {
          gui.setMsg("Illegal Move: Cannot place disc in full column. Try again.")
        }
      }
      board.makeMove(nextMove)
      if (gui == null) {
        println(nextMove)
        println(board)
      } else {
        gui.updateGUI(board, nextMove)
      }
      val temp = activePlayer
      activePlayer = player2
      player2 = temp
      // The following code causes a delay so that you can easily view the plays
      // being made by the AIs
      try {
        Thread.sleep(Game.SLEEP_INTERVAL)
      } catch {
        case e: InterruptedException => e.printStackTrace()
      }
    }
    if (gui == null) {
      if (winner == null) {
        println("Tie game!")
      } else {
        println(winner + " won the game!!!")
      }
    } else {
      gui.notifyGameOver(winner)
    }
  }

  /**
   * Return true if this game is over, false if not. If the game
   * is over, set the winner field to the winner; if no winner
   * set the winner to null.
   */
  def isGameOver(): Boolean = {
    winner = board.hasConnectFour()
    if (winner != null) return true
    var r = 0
    while (r < Board.NUM_ROWS) {
      var c = 0
      while (c < Board.NUM_COLS) {
        if (board.getTile(r, c) == null) return false
        c = c + 1
      }
      r = r + 1
    }
    true
  }
}

