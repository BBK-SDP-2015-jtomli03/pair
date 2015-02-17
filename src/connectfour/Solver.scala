package connectfour

trait Solver {

  def getMoves(b: Board): Array[Move]
}