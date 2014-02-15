package chess;

import java.util.*;
import java.util.regex.*;

public class Human extends Player {

    private Scanner in;

    public Human(int color) {
	super(color);
	in = new Scanner(System.in);
    }

    public Move getMove(Board board) {
	System.out.print(this + ": ");
	String line = in.nextLine();

	if (line.length() == 2) {
	    Position from = new Position(line.substring(0, 2));
	    System.out.println(board.getLegalMoves(color, from));
	} else {
	    Position from = new Position(line.substring(0, 2));
	    Position to = new Position(line.substring(2, 4));
	    Promotion promotion = null;
	    if (line.length() > 4) {
		promotion = new Promotion(color, to, 
					  charToType(line.charAt(4)));
	    }
	    
	    if (from.inRange() && board.isAlly(getColor(), from)) {
		
		for (Move move : board.getLegalMoves(color, from)) {
		    Promotion movePromotion = move.getPromotion();
		    
		    if (move.getFrom().equals(from) && 
			move.getTo().equals(to) &&
			(movePromotion == null ? promotion == null : 
			 movePromotion.equals(promotion))) {
			
			System.out.println();
			return move;
		    }
		}
	    }
	    System.out.println("Illegal move, please try again.");
	}	
	return (getMove(board));	
    }

    private Type charToType(char c) {
	switch (c) {
	case 'n': return new Knight();
	case 'b': return new Bishop();
	case 'r': return new Rook();
	case 'q': return new Queen();
	default: return null;
	}
    }

}