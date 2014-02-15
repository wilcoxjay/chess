package chess;

import java.io.*;
import java.util.*;

public class PieceSquareTable {

    private static final int ENDGAME = 1300;

    private static final int[][][] table = 
	new int[Type.COUNT + 1][Board.LENGTH][Board.LENGTH];

    static {
	String prefix = "piece-square-tables" + File.separator;
	table[Type.PAWN] = readTable(prefix + "pawn.txt");
	table[Type.KNIGHT] = readTable(prefix + "knight.txt");
	table[Type.BISHOP] = readTable(prefix + "bishop.txt");
	table[Type.ROOK] = readTable(prefix + "rook.txt");
	table[Type.QUEEN] = readTable(prefix + "queen.txt");
	table[Type.KING] = readTable(prefix + "king-middle.txt");
	table[Type.KING + 1] = readTable(prefix + "king-end.txt");
    }

    private static int[][] readTable(String file) {
	Scanner in = null;
	try {
	    in = new Scanner(new File(file));
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	    System.exit(1);
	}
	int[][] table = new int[Board.LENGTH][Board.LENGTH];
	for (int i = 0; i < table.length; i++) {
	    for (int j = 0; j < table[0].length; j++) {
		table[i][j] = in.nextInt();
	    }
	}
	return table;
    }

    public static int getValue(int color, Type type, Position pos, 
			       int maxMaterial) {
	Position relative = pos.relativeTo(color);
	int index = type.getIndex();
	if (index == Type.KING && maxMaterial - Type.KING_VALUE < ENDGAME) {
	    index++;
	}
	return table[index][relative.getRow()][relative.getColumn()];
    }

}
