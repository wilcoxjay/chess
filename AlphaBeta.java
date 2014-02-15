package chess;

import java.util.*;

public class AlphaBeta extends Player {
    
    public AlphaBeta(int color) {
	super(color);
    }

    public Move getMove(Board board) {
	return iterativeDeepening(board, 10000);
    }

    private Move iterativeDeepening(Board board, int millis) {
	Move move;
	long start = System.currentTimeMillis();
	int depth = 1; 
	do {
	    move = alphaBeta(board, depth);
	    depth++;
	} while (System.currentTimeMillis() < start + millis);
	System.out.println();
	return move;
    }

    private Move alphaBeta(Board board, int depth) {
	List<Move> bestMove = new Vector<Move>();
	bestMove.add(null);
	alphaBeta(board, depth, Double.NEGATIVE_INFINITY, 
		  Double.POSITIVE_INFINITY, color, true, bestMove);
	return bestMove.get(0);
    }

    private double alphaBeta(Board board, int depth, double alpha, double beta,
			     int color, boolean isRoot, List<Move> bestMove) {
	if (depth == 0 || board.gameOver(color)) {
	    return board.getValue(color);
	}	

	if (color == Piece.WHITE) {
	    List<Move> moves = board.getLegalMoves(color);
	    Collections.shuffle(moves);
	    for (Move move : moves) {
		double result = alphaBeta(board.apply(move), depth - 1, alpha, 
					  beta, -color, false, bestMove);
		if (result > alpha) {
		    alpha = result;
		    if (isRoot) {
			bestMove.set(0, move);
		    }
		}

		if (alpha >= beta) {
		    return alpha;
		}
	    }
	    return alpha;
	} else {
	    List<Move> moves = board.getLegalMoves(color);
	    Collections.shuffle(moves);
	    for (Move move : moves) {
		double result = alphaBeta(board.apply(move), depth - 1, alpha, 
					 beta, -color, false, bestMove);
		if (result < beta) {
		    beta = result;
		    if (isRoot) {
			bestMove.set(0, move);
		    }
		}

		if (beta <= alpha) {
		    return beta;
		}
	    }
	    return beta;
	}
    }

}

