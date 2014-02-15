package chess;

public class RookCastling implements SideEffect {

    private Position from;
    private Position to;

    public RookCastling(int color, int side) {
	int fromColumn = side == CastlingMove.KINGSIDE ? 7 : 0;
	from = new Position(7, fromColumn).relativeTo(color);
	to = from.offset(color, 0, side == CastlingMove.KINGSIDE ? -2 : 3);
    }

    public void apply(Board board) {
	board.move(from, to);
    }

}