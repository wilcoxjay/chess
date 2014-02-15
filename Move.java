package chess;

import java.util.*;

public abstract class Move {

    protected int color;
    protected Position from;
    protected Position to;
    private Vector<SideEffect> effects;

    public Move(int color, Position from, Position to, SideEffect... effects) {
	this.color = color;

	this.from = from;
	this.to = to;
	
	this.effects = new Vector<SideEffect>();
	for (SideEffect effect : effects) {
	    this.effects.add(effect);
	}
    }

    public abstract boolean isValid(Board board);

    public boolean nextIsValid(Board board) {
	return true;
    }

    public void apply(Board board) {
	board.move(from, to);
	for (SideEffect effect : effects) {
	    effect.apply(board);
	}
    }

    public Position getFrom() {
	return from;
    }

    public Position getTo() {
	return to;
    }

    public void addSideEffects(SideEffect... effects) {
	for (SideEffect effect : effects) {
	    this.effects.add(effect);
	}
    }

    public Promotion getPromotion() {
	for (SideEffect effect : effects) {
	    if (effect instanceof Promotion) {
		return (Promotion) effect;
	    }
	}
	return null;
    }

    public String toString() {
	return to.toString();
    } 

}