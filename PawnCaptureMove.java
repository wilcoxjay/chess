package chess;

public class PawnCaptureMove extends Move {

    public PawnCaptureMove(int color, Position from, Position to,
			   SideEffect... effects) {
	super(color, from, to, effects);
    }

    public boolean isValid(Board board) {
	return board.isEnemy(color, to);
    }

}