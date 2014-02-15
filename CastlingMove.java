package chess;

public class CastlingMove extends Move {

    public static final int KINGSIDE = 1;
    public static final int QUEENSIDE = -1;

    private int side;

    public CastlingMove(int color, Position from, int side) {
	super(color, from, from.offset(color, 0, side == KINGSIDE ? 2 : -2), 
	      new RookCastling(color, side),
	      new ProhibitCastling(color, CastlingMove.KINGSIDE),
	      new ProhibitCastling(color, CastlingMove.QUEENSIDE));
	this.side = side;
    }

    public boolean isValid(Board board) {	
	if (!board.canCastle(color, side)) {
	    return false;
	}
	
	int toCheck = side == KINGSIDE ? 2 : 3; 
	for (int i = 1; i <= toCheck; i++) {
	    if (!board.isEmpty(from.offset(color, 0, i * side))) {
		return false;
	    }
	}
	
	for (int i = 0; i < toCheck; i++) {
	    Position to = from.offset(color, 0, i * side);
	    if (board.apply(new LeaperMove(color, from, to)).inCheck(color)) {
		return false;
	    }
	}

	return true;
    }

    public static int castlingIndex(int side) {
	return (side + 1) / 2;
    }

}