package chess;

import java.util.*;

public class Queen extends Rider {

    public MoveList getMoves(int color, Position pos) {
	MoveList moves = new MoveList();
	
	moves.add(getRiderMoves(color, pos, new Position(1, 0)));
	moves.add(getRiderMoves(color, pos, new Position(0, 1)));
	moves.add(getRiderMoves(color, pos, new Position(-1, 0)));
	moves.add(getRiderMoves(color, pos, new Position(0, -1)));
	
	moves.add(getRiderMoves(color, pos, new Position(1, 1)));
	moves.add(getRiderMoves(color, pos, new Position(-1, 1)));
	moves.add(getRiderMoves(color, pos, new Position(-1, -1)));
	moves.add(getRiderMoves(color, pos, new Position(1, -1)));

	return moves;
    }

    public int getValue() {
	return QUEEN_VALUE;
    }

    public int getIndex() {
	return QUEEN;
    }

}