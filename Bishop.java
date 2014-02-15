package chess;

import java.util.*;

public class Bishop extends Rider {

    public MoveList getMoves(int color, Position pos) {
	MoveList moves = new MoveList();
	
	moves.add(getRiderMoves(color, pos, new Position(1, 1)));
	moves.add(getRiderMoves(color, pos, new Position(-1, 1)));
	moves.add(getRiderMoves(color, pos, new Position(-1, -1)));
	moves.add(getRiderMoves(color, pos, new Position(1, -1)));
	
	return moves;
    }

    public int getValue() {
	return BISHOP_VALUE;
    }

    public int getIndex() {
	return BISHOP;
    }

}