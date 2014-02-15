package chess;

public class PawnMove extends Move {

    public PawnMove(int color, Position from, Position to, 
		    SideEffect... effects) {
	super(color, from, to, effects);
    }

    public boolean isValid(Board board) {
	return board.isEmpty(to);
    }

    public boolean nextIsValid(Board board) {
	return isValid(board);
    }

}