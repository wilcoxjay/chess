package chess;

public class EnPassantCapture implements SideEffect {

    private Position toRemove;

    public EnPassantCapture(Position toRemove) {
	this.toRemove = toRemove;
    }

    public void apply(Board board) {
	board.remove(toRemove);
    }

}