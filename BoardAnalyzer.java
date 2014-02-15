package chess;

import java.util.*;

public class BoardAnalyzer {

    private static final double WHITE_WIN = Piece.WHITE * 1E10;
    private static final double BLACK_WIN = Piece.BLACK * 1E10;

    private List[][] moveTable;
    private List[] moveList;
    private Position[] kings;
    private int[] material;
    private int[] position;

    private List[][] legalMoveTable;
    private List[] legalMoveList;
    private boolean[] checkedForCheck;
    private boolean[] inCheck;
    
    
    @SuppressWarnings("unchecked")
    public BoardAnalyzer(Board board) {
	moveTable = new Vector[Board.LENGTH][Board.LENGTH];
	moveList = new Vector[] {new Vector<Move>(), new Vector<Move>()};
	kings = new Position[2];
	material = new int[2];

	for (int i = 0; i < Board.LENGTH; i++) {
	    for (int j = 0; j < Board.LENGTH; j++) {
		Position pos = new Position(i, j);
		
		if (!board.isEmpty(pos)) {
		    Piece piece = board.get(pos);
		    int color = piece.getColor();

		    List<Move> moves = MoveTable.get(color, piece.getType(),
						     pos).getValid(board);

		    moveTable[i][j] = moves;
		    moveList[Piece.colorIndex(color)].addAll(moves);

		    if (piece.getType().equals(new King())) {
			kings[Piece.colorIndex(color)] = pos;
		    }

		    material[Piece.colorIndex(color)] += piece.getValue();
		}
	    }
	}

	position = new int[2];
	int maxMaterial = Math.max(material[0], material[1]);
	for (int i = 0; i < Board.LENGTH; i++) {
	    for (int j = 0; j < Board.LENGTH; j++) {
		Position pos = new Position(i, j);
		
		if (!board.isEmpty(pos)) {
		    Piece piece = board.get(pos);
		    int color = piece.getColor();
		    
		    position[Piece.colorIndex(color)] += 
			PieceSquareTable.getValue(color, piece.getType(),
						  pos, maxMaterial);
		}
	    }
	}

	legalMoveTable = new Vector[Board.LENGTH][Board.LENGTH];
	legalMoveList = new Vector[2];
	checkedForCheck = new boolean[2];
	inCheck = new boolean[2];
    }

    public boolean inCheckmate(int color, Board board) {
	return inCheck(color) && getLegalMoves(color, board).isEmpty();
    }

    public boolean inStalemate(int color, Board board) {
	return !inCheck(color) && getLegalMoves(color, board).isEmpty();
    }

    public boolean inCheck(int color) {
	if (!checkedForCheck[Piece.colorIndex(color)]) {
	    inCheck[Piece.colorIndex(color)] =
		underAttack(color, kings[Piece.colorIndex(color)]);
	    checkedForCheck[Piece.colorIndex(color)] = true;
	}
	return inCheck[Piece.colorIndex(color)];
    }

    private boolean underAttack(int color, Position pos) {
	for (Object move : moveList[Piece.colorIndex(-color)]) {
	    if (((Move) move).getTo().equals(pos)) {
		return true;
	    } 
	}
	return false;
    }

    @SuppressWarnings("unchecked")
    public List<Move> getLegalMoves(int color, Board board) {
	if (legalMoveList[Piece.colorIndex(color)] == null) {
	    legalMoveList[Piece.colorIndex(color)] = 
		getLegalMoves(color, moveList[Piece.colorIndex(color)], board);
	}
	return legalMoveList[Piece.colorIndex(color)];
    }

    @SuppressWarnings("unchecked")
    public List<Move> getLegalMoves(int color, Position pos, Board board) {
	if (legalMoveTable[pos.getRow()][pos.getColumn()] == null) {
	    List<Move> moves = moveTable[pos.getRow()][pos.getColumn()];
	    if (moves == null) {
		return null;
	    }
	    legalMoveTable[pos.getRow()][pos.getColumn()] =
		getLegalMoves(color, moves, board);
	}
	return legalMoveTable[pos.getRow()][pos.getColumn()];
    }

    private List<Move> getLegalMoves(int color, List<Move> moves, Board board) {
	List<Move> legalMoves = new Vector<Move>();
	for (Move move : moves) {
	    if (!board.apply(move).inCheck(color)) {
		legalMoves.add(move);
	    }
	}
	return legalMoves;
    }

    public double getValue(int color, Board board) {
	if (inCheckmate(color, board)) {
	    return color == Piece.WHITE ? BLACK_WIN : WHITE_WIN; 
	} else if (inStalemate(color, board)) {
	    return 0;
	} else {
	    return material[Piece.colorIndex(Piece.WHITE)] + 
		position[Piece.colorIndex(Piece.WHITE)] -
		material[Piece.colorIndex(Piece.BLACK)] -
		position[Piece.colorIndex(Piece.BLACK)];
	}
    }

}