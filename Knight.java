package chess;

import java.util.*;

public class Knight extends Type {

    public MoveList getMoves(int color, Position pos) {
	MoveList moves = new MoveList();
	
	moves.add(new LeaperMove(color, pos, pos.offset(color, 2, 1)));
	moves.add(new LeaperMove(color, pos, pos.offset(color, 1, 2)));
	moves.add(new LeaperMove(color, pos, pos.offset(color, -1, 2)));
	moves.add(new LeaperMove(color, pos, pos.offset(color, -2, 1)));
	moves.add(new LeaperMove(color, pos, pos.offset(color, -2, -1)));
	moves.add(new LeaperMove(color, pos, pos.offset(color, -1, -2)));
	moves.add(new LeaperMove(color, pos, pos.offset(color, 1, -2)));
	moves.add(new LeaperMove(color, pos, pos.offset(color, 2, -1)));

	return moves;
    }

    public int getValue() {
	return KNIGHT_VALUE;
    }

    public int getIndex() {
	return KNIGHT;
    }

}