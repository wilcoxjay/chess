package chess;

public class EnPassantMove extends Move {

    public EnPassantMove(int color, Position from, Position to) {
	super(color, from, to, new EnPassantCapture(to.offset(color, -1, 0)));
    }

    public boolean isValid(Board board) {
	Position enPassant = board.getEnPassant();
	return enPassant != null && enPassant.equals(to);
    }

}