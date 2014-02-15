package chess;

public class RiderMove extends Move {

    public RiderMove(int color, Position from, Position to, 
		     SideEffect... effects) {
	super(color, from, to, effects);
    }

    public boolean isValid(Board board) {
	return !board.isAlly(color, to);
    }

    public boolean nextIsValid(Board board) {
	return board.isEmpty(to);
    }

}