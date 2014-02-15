package chess;

public class ProhibitCastling implements SideEffect {

    private int color;
    private int side;

    public ProhibitCastling(int color, int side) {
	this.color = color;
	this.side = side;
    }

    public void apply(Board board) {
	board.prohibitCastling(color, side);
    }

}