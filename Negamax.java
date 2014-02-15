package chess;

import java.util.*;

public class Negamax extends Player {
    
    public Negamax(int color) {
	super(color);
    }

    public Move getMove(Board board) {
	return iterativeDeepening(board, 100);
    }

    private Move iterativeDeepening(Board board, int millis) {
	long end = System.currentTimeMillis() + millis;
	int target = 1; 
        List<Move> pv = new Vector<Move>();
	do {
	    pv.add(null);
	    alphaBeta(board, target, pv);
	    target++;
	} while (System.currentTimeMillis() < end);
	return pv.get(0);
    }

    private double alphaBeta(Board board, int target, List<Move> pv) {
	return color * alphaBeta(board, color, 0, target, 
				 color * Double.NEGATIVE_INFINITY, 
				 color * Double.POSITIVE_INFINITY, pv);
    }

    private double alphaBeta(Board board, int color, int depth, int target, 
			     double alpha, double beta, List<Move> pv) {
	if (depth == target || board.gameOver(color)) {
	    return color * board.getValue(color);
	}
		
	double bestValue = Double.NEGATIVE_INFINITY;

	List<Move> moves = board.getLegalMoves(color);
	Collections.shuffle(moves);
	if (depth < target - 1) {
	    moves.add(0, pv.get(depth));
	}
	for (Move move : moves) {
	    double value = -alphaBeta(board.apply(move), -color, depth + 1,
				      target, -beta, -alpha, pv);
	    if (value > bestValue) {
		bestValue = value;
		pv.set(depth, move);
	    }
	    alpha = Math.max(alpha, value);
	    if (alpha >= beta) {
		return bestValue;
	    }
	}
	return bestValue;
    }

}

