package chess;

import java.util.*;

public class Chess {
    
    private Board board;
    private Player[] players;
    private int color; 

    private Chess() {
	board = new Board();
	players = new Player[] {new PrincipalVariation(Piece.WHITE), 
				new PrincipalVariation(Piece.BLACK)};
	color = Piece.WHITE;
    }

    private void nextPly() {
	System.out.println(this + "\n");
	players[Piece.colorIndex(color)].takeTurn(board);
	color *= -1;
    }

    private void setBoard(Board board) {
	this.board = board;
    }

    private boolean gameOver() {
	return board.inStalemate(color) || board.inCheckmate(color);
    }

    public String toString() {
	return board.toString() + "\n";
    }

    public static void main(String[] args) {
	Chess game = new Chess();
	while (!game.gameOver()) {
	    game.nextPly();
	}
	System.out.println(game);
    }

}