package chess;

import java.util.*;

public abstract class Type {

    protected static final int PAWN_VALUE = 100;
    protected static final int KNIGHT_VALUE = 320;
    protected static final int BISHOP_VALUE = 330;
    protected static final int ROOK_VALUE = 500;
    protected static final int QUEEN_VALUE = 900;
    protected static final int KING_VALUE = 20000;

    public static final int PAWN = 0;
    public static final int KNIGHT = 1;
    public static final int BISHOP = 2;
    public static final int ROOK = 3;
    public static final int QUEEN = 4;
    public static final int KING = 5;

    public static final int COUNT = 6;

    public abstract MoveList getMoves(int color, Position pos);

    public abstract int getValue();

    public abstract int getIndex();

    public boolean equals(Object other) {
	if (other instanceof Type) {
	    return getIndex() == ((Type) other).getIndex();
	}
	return false;
    }

    public static Type fromIndex(int index) {
	switch (index) {
	case PAWN: return new Pawn();
	case KNIGHT: return new Knight();
	case BISHOP: return new Bishop();
	case ROOK: return new Rook();
	case QUEEN: return new Queen();
	case KING: return new King();
	default: return null;
	}
    }

}