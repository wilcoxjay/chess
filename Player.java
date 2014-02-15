package chess;

public abstract class Player {

    protected int color;
    
    public Player(int color) {
	this.color = color;
    }

    public abstract Move getMove(Board board);

    public void takeTurn(Board board) {
	board.applyHere(getMove(board));
    }

    public int getColor() {
	return color;
    }

    public String toString() {
	return color == Piece.WHITE ? "White" : "Black";
    }

}