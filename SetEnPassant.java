package chess;

public class SetEnPassant implements SideEffect {

    private Position toSet;

    public SetEnPassant(Position toSet) {
	this.toSet = toSet;
    }

    public void apply(Board board) {
	board.setEnPassant(toSet);
    }

}