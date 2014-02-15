package chess;

import java.util.*;

public class Rook extends Rider {

    public MoveList getMoves(int color, Position pos) {
	MoveList moves = new MoveList();
	
	moves.add(makeMoves(color, pos, new Position(1, 0)));
	moves.add(makeMoves(color, pos, new Position(0, 1)));
	moves.add(makeMoves(color, pos, new Position(-1, 0)));
	moves.add(makeMoves(color, pos, new Position(0, -1)));
	
	return moves;
    }

    private List<Move> makeMoves(int color, Position from, Position delta) {
	List<Move> moves = getRiderMoves(color, from, delta);

	if (from.relativeTo(color).equals(new Position("a1"))) {
	    for (Move move : moves) {
		move.addSideEffects(
		    new ProhibitCastling(color, CastlingMove.QUEENSIDE));
	    }
	} else if (from.relativeTo(color).equals(new Position("h1"))) {
	    for (Move move : moves) {
		move.addSideEffects(
		    new ProhibitCastling(color, CastlingMove.KINGSIDE));
	    }
	}

	return moves;
    }

    public int getValue() {
	return ROOK_VALUE;
    }

    public int getIndex() {
	return ROOK;
    }

}