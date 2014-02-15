package chess;

import static chess.Type.*;
import static chess.Piece.*;
import java.util.*;

public class Board {

    public static final int LENGTH = 8;

    private static final String ANSI_LIGHT = (char) 27 + "[0m";
    private static final String ANSI_DARK = (char) 27 + "[47m";

    private static final int[] setup = 
	new int[] {ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK}; 
    
    private Piece[][] board;
    private boolean canCastle[][];
    private Position enPassant;

    private BoardAnalyzer analyzer;

    public Board() {
	board = new Piece[LENGTH][LENGTH];
	
	for (int i = 0; i < board.length; i++) {
	    board[0][i] = new Piece(setup[i], BLACK);
	    board[1][i] = new Piece(PAWN, BLACK);
	    board[6][i] = new Piece(PAWN, WHITE);
	    board[7][i] = new Piece(setup[i], WHITE);
	}

	canCastle = new boolean[][] {{true, true},
				     {true, true}};

	analyzer = new BoardAnalyzer(this);
    }

    private Board(Board toCopy) {
	board = new Piece[LENGTH][LENGTH];
	for (int i = 0; i < board.length; i++) {
	    System.arraycopy(toCopy.board[i], 0, board[i], 0, board[0].length); 
	}
	
	canCastle = 
	    new boolean[][] {{toCopy.canCastle[0][0], toCopy.canCastle[0][1]},
			     {toCopy.canCastle[1][0], toCopy.canCastle[1][1]}};
	enPassant = toCopy.enPassant;

	analyzer = toCopy.analyzer;
    }

    public Board apply(Move move) {
	Board copy = new Board(this);
	copy.applyHere(move);
	return copy;
    }

    public void applyHere(Move move) {
	enPassant = null;
	move.apply(this);
	analyzer = new BoardAnalyzer(this);
    }

    public void set(Position pos, Piece piece) {
	board[pos.getRow()][pos.getColumn()] = piece;
    }
    
    public void move(Position from, Position to) {
	set(to, get(from));
	remove(from);
    }

    public void remove(Position pos) {
	set(pos, null);
    }

    public boolean isEmpty(Position pos) {
	return board[pos.getRow()][pos.getColumn()] == null;
    }

    public Piece get(Position pos) {
	return board[pos.getRow()][pos.getColumn()];
    } 

    public boolean isAlly(int color, Position pos) {
	return !isEmpty(pos) && color == get(pos).getColor();
    }

    public boolean isEnemy(int color, Position pos) {
	return !isEmpty(pos) && color != get(pos).getColor();
    }

    public void prohibitCastling(int color, int side) {
	canCastle[Piece.colorIndex(color)][CastlingMove.castlingIndex(side)] 
	    = false;
    }

    public boolean canCastle(int color, int side) {
	return canCastle[Piece.colorIndex(color)]
	    [CastlingMove.castlingIndex(side)];
    }

    public void setEnPassant(Position pos) {
	enPassant = pos;
    }

    public Position getEnPassant() {
	return enPassant;
    }

    public List<Move> getLegalMoves(int color) {
	return analyzer.getLegalMoves(color, this);
    }

    public List<Move> getLegalMoves(int color, Position pos) {
	return analyzer.getLegalMoves(color, pos, this);
    }
    
    public boolean gameOver(int color) {
	return inCheckmate(color) || inStalemate(color);
    }

    public boolean inCheckmate(int color) {
	return analyzer.inCheckmate(color, this);
    }

    public boolean inStalemate(int color) {
	return analyzer.inStalemate(color, this);
    }

    public boolean inCheck(int color) {
	return analyzer.inCheck(color);
    }

    public double getValue(int color) {
	return analyzer.getValue(color, this);
    }

    public String toString() {
	StringBuilder builder = new StringBuilder();

	builder.append("  ");
	for (char c = 'a'; c <= 'h'; c++) {
	    builder.append(c + " ");
	}
	builder.append("\n");

	boolean dark = false;
	for (int i = 0; i < Board.LENGTH; i++) {
	    Piece[] row = board[i];
	    
	    builder.append((Board.LENGTH - i) + " ");

	    for (Piece p : row) {
		if (dark) {
		    builder.append(ANSI_DARK);
		}
		builder.append((p == null ? " " : p) + " " + ANSI_LIGHT);
		dark = !dark;
	    }

	    builder.append(" " + (Board.LENGTH - i) + "\n");
	    dark = !dark;
	}

	builder.append("  ");
	for (char c = 'a'; c <= 'h'; c++) {
	    builder.append(c + " ");
	}

	return builder.toString();
    }

}