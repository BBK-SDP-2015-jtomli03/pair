package connectfour


object GameGUI extends App {
  /* -------------------------- Change these to play game differently. -------------------------- */

  /* p1 is the first player, p2 is the second player. A Solver
         * can be a Human, AI, or Dummy. Human and Dummy constructors have
         * a player parameter; the AI constructor has a player and depth
         * as parameters, with the depth used to recurse when searching the
         * game space. */
  //Solver p1= new AI(Player.RED, 6); //note in java version the depth = 5
  //Solver p2 = new AI(Player.YELLOW, 6);
  val p1 = new Human(RED);
  val p2= new Human(YELLOW);
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

  /* --------------------------------- Do not change below here. --------------------------------- */

  val game = new Game(p1, p2);
  game.setGUI(new GUI(game, Board.NUM_COLS, Board.NUM_ROWS));
  game.runGame();

}