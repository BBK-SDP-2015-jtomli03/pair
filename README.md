# Game of Connect Four

Connect Four is a two player game where each player takes a turn in dropping a coloured disc from the top of the game board. The pieces fall straight down, occupying the next available space in the column. The winner of the game is the first player to get a row of four of your own colour discs next to each other (either vertically, horizontally, or diagonally). This version has a graphical GUI to enable play.

#The Players Available in this Version

There are 3 types of player available;
  
  **Human** - a human player makes a move of their choosing on the board.
  
  **Dummy** - a dummy player makes a random move on the board.
  
  **AI** - a version of artificial intelligence that makes its next move based on the best move returned from the minimax algorithm calculated to a chosen depth.
  
Each type of player can play against its own type or one of the other types of player.

#How to play

  1) In class GameGui you can uncomment the types of players you wish to use for players one and two, and comment out the other types. 
  
  2) If playing with AI, then in its constructor you can amend the depth of the game tree for use with the minimax algorithm. The higher the value, the more accurate the AI is in making its best possible move, but the slower it is in making a move. From depth 6 you will find that the game slows significantly. 
  
  3) Once the players have been set just simply run GameGUI to start play.



  

