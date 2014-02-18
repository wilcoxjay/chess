package chess;

import java.util.*;

public class PrincipalVariation extends Player {
    
    public PrincipalVariation(int color) {
	super(color);
    }

    public Move getMove(Board board) {
	return iterativeDeepening(board, 100);
    }

    private Move iterativeDeepening(Board board, int millis) {
	long end = System.currentTimeMillis() + millis;
	Stack<Move> prevPV = new Stack<Move>();
        Stack<Move> nextPV;
	do {
	    nextPV = new Stack<Move>();
	    nextPV.setSize(prevPV.size() + 1);
	    alphaBeta(board, prevPV, nextPV, end);
	    prevPV = nextPV;
	} while (System.currentTimeMillis() < end);
	return nextPV.peek();
    }

    private void alphaBeta(Board board, Stack<Move> prevPV, Stack<Move> nextPV,
			   long end) {
	alphaBeta(board, color, nextPV.size(), Double.NEGATIVE_INFINITY, 
		  Double.POSITIVE_INFINITY, prevPV, nextPV, end);
    }

    private double alphaBeta(Board board, int color, int depth, double alpha, 
			     double beta, Stack<Move> prevPV, List<Move> nextPV,
			     long end) {
	if (depth == 0 || board.gameOver(color)) {
	    return board.getValue(color);
	}

	List<Move> moves = board.getLegalMoves(color);
	Collections.shuffle(moves);
	if (!prevPV.empty()) {
	    moves.add(0, prevPV.pop());
	}
	
	if (color == Piece.WHITE) {
	    for (Move move : moves) {
		double result = alphaBeta(board.apply(move), -color, depth - 1,
					  alpha, beta, prevPV, nextPV, end);
		if (result > alpha) {
		    alpha = result;
		    nextPV.set(depth - 1, move);
		}

		if (alpha >= beta) {
		    return alpha;
		}
	    }
	    return alpha;
	} else {
	    for (Move move : moves) {
		double result = alphaBeta(board.apply(move), -color, depth - 1, 
					  alpha, beta, prevPV, nextPV,  end);
		if (result < beta) {
		    beta = result;
		    nextPV.set(depth - 1, move);
		}

		if (beta <= alpha) {
		    return beta;
		}
	    }
	    return beta;
	}
    }

}

