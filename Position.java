package chess;

public class Position {

    private int row;
    private int column;

    public Position(int row, int column) {
	this.row = row;
	this.column = column;
    }

    public Position(String str) {
	this.row = Board.LENGTH - (str.charAt(1) - '0');
	this.column = str.charAt(0) - 'a';
    }

    public Position relativeTo(int color) {
	return new Position(color == Piece.WHITE ? row : Board.LENGTH - 1 - row,
			    column);
    }

    public int getRow() {
	return row;
    }

    public int getRank(int color) {
	return color == Piece.WHITE ? Board.LENGTH - row : row + 1;
    }

    public int getColumn() {
	return column;
    }

    public int getFile() {
	return column + 1;
    }

    public Position offset(int color, Position delta) {
	return offset(color, delta.row, delta.column);
    }

    public Position offset(int color, int rowDelta, int columnDelta) {
	return new Position(row + -color * rowDelta, column + columnDelta);
    }

    public boolean inRange() {
	return 0 <= row && row < Board.LENGTH && 
	    0 <= column && column < Board.LENGTH;
    }

    public String toString() {
	return (char) (column + 'a') + "" + (Board.LENGTH - row);
    }

    public boolean equals(Object other) {
	if (other instanceof Position) {
	    Position o = (Position) other;
	    return row == o.row && column == o.column;
	}
	return false;
    }

    public int hashCode() {
	return (row * 31) ^ column;
    }

}