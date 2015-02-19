package connectfour

import java.util.concurrent.Semaphore

/** NOTHING FOR YOU TO DO HERE. */
/**
 * A Solver that takes human input from a GUI to determine moves.
 */
class Human(private var player: Player) extends Solver {

  private var waitSema: Semaphore = new Semaphore(0)

  private var nextColumn: Int = _

  override def getMoves(b: Board): Array[Move] = {
    try {
      waitSema.acquire()
    } catch {
      case e: InterruptedException => e.printStackTrace()
    }
    Array(new Move(player, nextColumn))
  }

  /**
   * Signal to this Human that the user wants to place
   * a piece in column c.
   */
  def columnClicked(c: Int) {
    nextColumn = c
    waitSema.release()
  }
}

