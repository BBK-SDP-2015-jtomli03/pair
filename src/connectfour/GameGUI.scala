package connectfour


object GameGUI extends App {
  /* -------------------------- Change these to play game differently. -------------------------- */

  /* p1 is the first player, p2 is the second player. A Solver
         * can be a Human, AI, or Dummy. Human and Dummy constructors have
         * a player parameter; the AI constructor has a player and depth
         * as parameters, with the depth used to recurse when searching the
         * game space. */
  val p1= new AI(RED, 6); //note in java version the depth = 5
  val p2 = new AI(YELLOW, 6);
  //val p1 = new Human(RED);
  //val p2= new Human(YELLOW);
  //val p1 = new Dummy(RED);
  //val p2 = new Dummy(YELLOW);

  /* When testing, you may want to comment out all the above statements
        */

  /* -------------------------------------------------------------- */

  /* ABOUT TESTING.
   * Testing is difficult using a JUnit testing class because it is difficult to
   * get at many of the fields and methods from that class. So you may want to
   * put some testing methods in this class.
   *
   * In testing various methods, you may want to use boards with certain
   * layouts of your choosing. For example, a board with column 0 all filled.
   * To do this, you can
   *
   *     1. Create a board b
   *     2. Call makeMove several times to put pieces where you want them.
   *     3. Play the game.
   *
   * For example, you can write the following to set up an initial board and
   * then play the game
   *
   *      Board b= new Board();
   *      b.makeMove(new Move(Player.RED, 4));
   *      b.makeMove(new Move(Player.YELLOW, 3));
   *      b.makeMove(new Move(Player.RED, 5));
   *      Game game= new Game(p1, p2, b, false);
   *
   * This code places a red piece in column 4, a yellow piece in column 3,
   * and a red piece in column 5. Then it runs the game.
   *
   * We give you also procedure fillColumn at the end of this file to help
   * out in initializing a board. Study it. Note that it is static.
   *
   * Suppose you want to test a method that your wrote, like Board.getPossibleMoves.
   * Thus, you want to do the following.
   *
   *     1. Create a board b
   *     2. Call makeMove several times to put pieces where you want them.
   *     3. Call the method you want to test.
   *     4. Check the result, if any.
   *
   * You can check the result by using println statements to print out things and
   * looking at the output. You are testing by eyeballing the output. This is OK as
   * long as you are careful. Here is how you could print out the results of a
   * call to getPossibleMoves:
   *
   *      Board b= new Board();
   *      fillColumn(b, Player.RED, 0);  // fill column 0
   *      Move[] moves= b.getPossibleMoves(Player.RED);
   *      for (Move m : moves) {
   *           System.out.println(m);
   *
   * If you are having real trouble, the above may not help. Here is what you
   * can do to test the very basics of Board.getPossibleMoves:
   *
   *      Board b= new Board();
   *      fillColumn(b, Player.RED, 0);  // fill column 0
   *      Move[] moves= b.getPossibleMoves(Board.Player.RED);
   *      if (moves.length != Board.NUM_COLS-1) {
   *          System.out.println("Error in getPossibleMoves with 1 col filled. array size is wrong: " +  moves.length);
   *      }
   *      if (moves[0].getColumn() != 1) {
   *          System.out.println(s + "First col is filled, second isn't but moves[0] is " + moves[0]);
   *      }
   *
   * We suggest you write a static method to test getPossibleMoves
   * (and perhaps other methods for other tests).
   * Make it a method so that you can call it or not from method main, depending on
   * your needs. It doesn't have to test ALL possible cases, but it has to check
   * enough that so you are positive the method works.
   *
   *
  }
   *
   * */

  /* ********* Put any testing methods that you write here.  *******
   * *** We will not look at this class anyway ***/

 /*
* Tests the method getPossibleMoves() in class Board.
* Tests this method works if the board is filled a column at a time without a connect four,
* and tests this method works if there is a connect four - both horizontal and diagonal (ie returns no moves as the game is finished)
 */
  def testGetPossibleMoves() = {
    val b = new Board()
    val firstPlayerInColumn = RED
    val secondPlayerInColumn = YELLOW
    val moves = b.getPossibleMoves(RED);
    println("Board is empty. All columns should be a possible move: ")
    for (m <- moves) System.out.println(m)
    println()
    println("TESTING POSSIBLE MOVES BY FILLING A COLUMN AT A TIME WITHOUT CONNECT FOUR....")
    testGetPossibleMovesWithoutConnectFour(b, firstPlayerInColumn, secondPlayerInColumn)
    println()
    println("TESTING POSSIBLE MOVES BY FILLING ROW 6 WITH A CONNECT FOUR....")
    testGetPossibleMovesWithHorizontalConnectFour(b, firstPlayerInColumn)
    println()
    println("TESTING POSSIBLE MOVES BY CREATING A DIAGONAL CONNECT FOUR....")
    testGetPossibleMovesWithDiagonalConnectFour(b, firstPlayerInColumn, secondPlayerInColumn)
    println()
    println("TESTING COMPLETE.")
  }

  /*
  * Fills the board one column at a time to check the method getPossibleMoves() in class Board,
  * the board is fully filled without a connect four. The possible moves are printed on screen
  * after each column is filled.
   */
  def testGetPossibleMovesWithoutConnectFour(b: Board, firstPlayerInColumn: Player, secondPlayerInColumn: Player) = {
    for(col <- 0 until Board.NUM_COLS) {
      col match{
        case 0 | 1 | 4 | 5 => fillColumnAlternately(b, col, firstPlayerInColumn, secondPlayerInColumn)
        case 2 | 3 | 6 => fillColumnAlternately(b, col, secondPlayerInColumn, firstPlayerInColumn)
      }
      val moves = b.getPossibleMoves(RED); //doesn't matter which colour, we are just checking for possible moves
      println("Filling column: " + col)
      println("Possible moves are now: ")
      for (m <- moves) System.out.println(m)
    }
  }

  /*
  * A connect four is made on row 6, and then the method getPossibleMoves() in class Board
  * is tested. It should return no possible moves as the game is over.
  */
  def testGetPossibleMovesWithHorizontalConnectFour(b: Board, firstPlayerInColumn: Player) = {
    for(c <- 0 until 4){
      b.makeMove(new Move(firstPlayerInColumn, c))
    }
    val moves = b.getPossibleMoves(RED); //doesn't matter which colour, we are just checking for possible moves
    println("There should be no possible moves. Possible moves = ")
    for (m <- moves) println(m)
  }

  def testGetPossibleMovesWithDiagonalConnectFour(b: Board, firstPlayerInColumn: Player, secondPlayerInColumn: Player) = {
    for(col <- 0 until 4) {
      col match{
        case 0 | 2  => fillColumnAlternately(b, col, firstPlayerInColumn, secondPlayerInColumn)
        case 1 | 3  => fillColumnAlternately(b, col, secondPlayerInColumn, firstPlayerInColumn)
      }
    }
    val moves = b.getPossibleMoves(RED); //doesn't matter which colour, we are just checking for possible moves
    println("There should be no possible moves. Possible moves = ")
    for (m <- moves) System.out.println(m)
  }

  /*
   * Fills the stated column with alternate players, ie RED, YELLOW, RED, YELLOW etc.
   */
  def fillColumnAlternately(b: Board, col: Int, firstPlayer: Player, secondPlayer: Player) = {
    for(r <- 0 until Board.NUM_ROWS by 2){      //by 2 because we're filling two rows at a time
      b.makeMove(new Move(firstPlayer, col))
      b.makeMove(new Move(secondPlayer, col))
    }
  }

  def testInitializeChildren() = {
    val b = new Board()
    val firstPlayer = RED
    val secondPlayer = YELLOW
    val state1 = new State(firstPlayer, b, null)
    state1.initializeChildren()
    println("Printing child states for an empty board...")
    state1.getChildren foreach(state => println(state.getBoard.toString()))
    println()
    fillColumnAlternately(b, 0, firstPlayer, secondPlayer) //fill column 0
    state1.initializeChildren()
    println("Printing child states after column 0 filled...")
    state1.getChildren foreach(state => println(state.getBoard.toString()))
    println()
    fillColumnAlternately(b, 6, firstPlayer, secondPlayer) //fill column 0
    state1.initializeChildren()
    println("Printing child states after columns 0 and 6 filled...")
    state1.getChildren foreach(state => println(state.getBoard.toString()))
  }


  /* --------------------------------- Do not change below here. --------------------------------- */

  val game = new Game(p1, p2);
  game.setGUI(new GUI(game, Board.NUM_COLS, Board.NUM_ROWS));
  game.runGame();




}