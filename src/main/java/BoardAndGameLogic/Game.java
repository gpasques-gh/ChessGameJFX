package BoardAndGameLogic;

import java.util.Arrays;
import Pieces.*;

public class Game {

    private Board board;
    private int[][] possibleMoves;
    private int[][] playerMove;
    private Case[] playerMoveCases;

    private boolean check;

    public Game() {
        board = new Board();
        playerMove = new int[][]{
                null,
                null
        };
        playerMoveCases = new Case[]{
                null,
                null
        };
    }

    public void click(String s) {
        Case c = board.getCase(s);
        int[] indexes = board.getIndexes(s);
        if (playerMove[0] == null) {
            playerMove[0] = indexes;
            playerMoveCases[0] = c;
        }
        else {
            playerMove[1] = indexes;
            playerMoveCases[1] = c;
            System.out.println(playerMove[0][0] + " " + playerMove[0][1]);
            System.out.println(playerMove[1][0] + " " + playerMove[1][1]);
            System.out.println(playerMove[0][0] - playerMove[1][0]);
            System.out.println(playerMove[0][1] - playerMove[1][1]);
            movePiece(playerMoveCases[0], playerMoveCases[1]);
        }
    }

    public void movePiece(Case a, Case b) {
        Piece p = a.getPiece();
        a.setPiece(null);
        b.setPiece(p);
        resetPlayerMove();
    }

    public void resetPlayerMove() {
        this.playerMove[0] = null;
        this.playerMove[1] = null;
    }

    public Board getBoard() {
        return board;
    }

    public int[][] getPlayerMove() {
        return playerMove;
    }
}
