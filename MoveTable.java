package chess; 

import java.util.*;

public class MoveTable {

    private static final MoveList[][][][] moves = 
	new MoveList[2][Type.COUNT][Board.LENGTH][Board.LENGTH];

    static {
	add(Piece.WHITE);
	add(Piece.BLACK);
    }

    private static void add(int color) {
	add(color, new Pawn());
	add(color, new Knight());
	add(color, new Bishop());
	add(color, new Rook());
	add(color, new Queen());
	add(color, new King());
    }

    private static void add(int color, Type type) {
	for (int i = 0; i < Board.LENGTH; i++) {
	    for (int j = 0; j < Board.LENGTH; j++) {
		moves[Piece.colorIndex(color)][type.getIndex()][i][j] = 
		    type.getMoves(color, new Position(i, j));
	    }
	}
    }

    public static MoveList get(int color, Type type, Position pos) {
	return moves[Piece.colorIndex(color)][type.getIndex()]
	    [pos.getRow()][pos.getColumn()];
    }

}