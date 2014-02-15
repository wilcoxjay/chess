package chess;

public class LeaperMove extends Move {

    public LeaperMove(int color, Position from, Position to,
		      SideEffect... effects) {
	super(color, from, to, effects);
    }

    public boolean isValid(Board board) {
	return !board.isAlly(color, to);
    }

}