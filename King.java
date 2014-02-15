package chess;

import java.util.*;

public class King extends Type {

    public MoveList getMoves(int color, Position pos) {
	MoveList moves = new MoveList();

	moves.add(makeMove(color, pos, pos.offset(color, 1, 0)));
	moves.add(makeMove(color, pos, pos.offset(color, 0, 1)));
	moves.add(makeMove(color, pos, pos.offset(color, -1, 0)));
	moves.add(makeMove(color, pos, pos.offset(color, 0, -1)));
	
	moves.add(makeMove(color, pos, pos.offset(color, 1, 1)));
	moves.add(makeMove(color, pos, pos.offset(color, -1, 1)));
	moves.add(makeMove(color, pos, pos.offset(color, -1, -1)));
	moves.add(makeMove(color, pos, pos.offset(color, 1, -1)));

	if (pos.relativeTo(color).equals(new Position("e1"))) {
	    moves.add(new CastlingMove(color, pos, CastlingMove.KINGSIDE));
	    moves.add(new CastlingMove(color, pos, CastlingMove.QUEENSIDE));
	}

	return moves;
    }
    
    private Move makeMove(int color, Position from, Position to) {
	Move move = new LeaperMove(color, from, to);
	if (from.relativeTo(color).equals(new Position("e1"))) {
	    move.addSideEffects(new ProhibitCastling(color, 
						     CastlingMove.KINGSIDE),
				new ProhibitCastling(color, 
						     CastlingMove.QUEENSIDE));
	}
	return move;
    } 

    public int getValue() {
	return KING_VALUE;
    }

    public int getIndex() {
	return KING;
    }

}