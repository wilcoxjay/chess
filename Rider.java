package chess;

import java.util.*;

public abstract class Rider extends Type {

    public List<Move> getRiderMoves(int color, Position from, Position delta) {
	List<Move> moves = new Vector<Move>();
	Position to = from.offset(color, delta);
	for (int i = 1; i < Board.LENGTH; i++) {
	    moves.add(new RiderMove(color, from, to));
	    to = to.offset(color, delta);
	}
	return moves;
    }

}