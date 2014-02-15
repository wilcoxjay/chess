package chess;

import java.util.*;

public class PrincipalVariation extends Player {
    
    public PrincipalVariation(int color) {
	super(color);
    }

    public Move getMove(Board board) {
	return iterativeDeepening(board, 10000);
    }

    private Move iterativeDeepening(Board board, int millis) {
	long end = System.currentTimeMillis() + millis;
	int target = 1; 
        List<Move> pv = new Vector<Move>();
	do {
	    pv.add(null);
	    alphaBeta(board, target, pv, end);
	    target++;
	} while (System.currentTimeMillis() < end);
	return pv.get(0);
    }

    private void alphaBeta(Board board, int target, List<Move> pv, long end) {
	alphaBeta(board, color, 0, target, Double.NEGATIVE_INFINITY, 
		  Double.POSITIVE_INFINITY, pv, end);
    }

    private double alphaBeta(Board board, int color, int depth, int target, 
			     double alpha, double beta, List<Move> pv, 
			     long end) {
	if (depth == target || board.gameOver(color)) {
	    return board.getValue(color);
	}	

	List<Move> moves = board.getLegalMoves(color);
	Collections.shuffle(moves);
	if (depth < target - 1) {
	    moves.add(0, pv.get(depth));
	}
	
	if (color == Piece.WHITE) {
	    for (Move move : moves) {
		double result = alphaBeta(board.apply(move), -color, depth + 1,
					  target, alpha, beta, pv, end);
		if (result > alpha) {
		    alpha = result;
		    pv.set(depth, move);
		}

		if (alpha >= beta) {
		    return alpha;
		}
	    }
	    return alpha;
	} else {
	    for (Move move : moves) {
		double result = alphaBeta(board.apply(move), -color, depth + 1, 
					  target, alpha, beta, pv, end);
		if (result < beta) {
		    beta = result;
		    pv.set(depth, move);
		}

		if (beta <= alpha) {
		    return beta;
		}
	    }
	    return beta;
	}
    }

}

