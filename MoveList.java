package chess;

import java.util.*;

public class MoveList {

    public List<Move> independent;
    public List<List<Move>> dependent;

    public MoveList() {
	independent = new Vector<Move>();
	dependent = new Vector<List<Move>>();
    }

    public void add(Move move) {
	if (move.getTo().inRange()) {
	    independent.add(move);
	}
    }

    public void add(List<Move> moves) {
	List<Move> inRange = new Vector<Move>();
	for (int i = 0; i < moves.size() && moves.get(i).getTo().inRange(); 
	     i++) {
	    inRange.add(moves.get(i));
	}
	dependent.add(inRange);
    }

    public List<Move> getValid(Board board) {
	List<Move> valid = new Vector<Move>();

	for (Move move : independent) {
	    if (move.isValid(board)) {
		valid.add(move);
	    }
	}

	for (List<Move> moves : dependent) {
	    boolean nextIsValid = true;
	    
	    for (int i = 0; nextIsValid && i < moves.size(); i++) {
		Move move = moves.get(i);
		
		if (move.isValid(board)) {
		    valid.add(move);
		    nextIsValid = move.nextIsValid(board);
		} else {
		    nextIsValid = false;
		}
	    }
	}

	return valid;
    }

    public String toString() {
	return independent + " " + dependent;
    }

}