package chess;

import java.util.*;

public class Pawn extends Type {

    public MoveList getMoves(int color, Position pos) {
	MoveList moves = new MoveList();

	if (pos.getRank(color) == 2) {
	    moves.add(Arrays.asList(new Move[] 
		{new PawnMove(color, pos, pos.offset(color, 1, 0)), 
		 new PawnMove(color, pos, pos.offset(color, 2, 0),
			      new SetEnPassant(pos.offset(color, 1, 0)))}));
	} else if (pos.getRank(color) == 7) {
	    for (Move move : getPromotions(color, pos, pos.offset(color, 1, 0),
					   false)) {
		moves.add(move);
	    }
	} else {
	    moves.add(new PawnMove(color, pos, pos.offset(color, 1, 0)));
	}
	
	if (pos.getRank(color) == 7) {
	    for (Move move : getPromotions(color, pos, 
					   pos.offset(color, 1, 1), true)) {
		moves.add(move);
	    }
	    for (Move move : getPromotions(color, pos, 
					   pos.offset(color, 1, -1), true)) {
		moves.add(move);
	    }
	} else {
	    moves.add(new PawnCaptureMove(color, pos, pos.offset(color, 1, 1)));
	    moves.add(new PawnCaptureMove(color, pos, 
					  pos.offset(color, 1, -1)));
	}

	if (pos.getRank(color) == 5)  {
	    moves.add(new EnPassantMove(color, pos, pos.offset(color, 1, 1)));
	    moves.add(new EnPassantMove(color, pos, pos.offset(color, 1, -1)));
	}

	return moves;
    }

    private List<Move> getPromotions(int color, Position from, Position to, 
				     boolean capture) {
	List<Move> promotions = new Vector<Move>();

	for (int i = Type.KNIGHT; i <= Type.QUEEN; i++) {
	    if (capture) {
		promotions.add(
		    new PawnCaptureMove(color, from, to, 
					new Promotion(color, to, 
						      Type.fromIndex(i))));
	    } else {
		promotions.add(
		    new PawnMove(color, from, to, 
				 new Promotion(color, to, Type.fromIndex(i))));
	    }
	}

	return promotions;
    }

    public int getValue() {
	return PAWN_VALUE;
    }

    public int getIndex() {
	return PAWN;
    }

}