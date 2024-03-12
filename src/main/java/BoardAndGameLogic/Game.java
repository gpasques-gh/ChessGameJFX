package BoardAndGameLogic;

import java.util.Arrays;
import Pieces.*;

public class Game {

    /***
     * board of the game
     * possibleMoves array
     * playerMove array
     * playerMoveCases array
     * check boolean
     */
    private Board board;
    private int[][] possibleMoves;
    private int[][] playerMove;
    private Case[] playerMoveCases;

    private boolean check;

    /***
     * Game constructor
     */
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

    /***
     * click method
     * @param s name of the clicked Rectangle/Case
     */
    public void click(String s) {
        // Get the case of the clicked rectangle
        Case c = board.getCase(s);
        // Get indexes of the case
        int[] indexes = board.getIndexes(s);
        // Adding the case and indexes to the move arrays
        if (playerMove[0] == null) {
            playerMove[0] = indexes;
            playerMoveCases[0] = c;
        }
        else {
            playerMove[1] = indexes;
            playerMoveCases[1] = c;
            Piece piece = playerMoveCases[0].getPiece();
            System.out.println(playerMove[0][0] + " " + playerMove[0][1]);
            System.out.println(playerMove[1][0] + " " + playerMove[1][1]);
            System.out.println(playerMove[0][0] - playerMove[1][0]);
            System.out.println(playerMove[0][1] - playerMove[1][1]);
            // Checking Piece type and moving accordingly
            if (piece instanceof Pawn)
                pawnMove(); // If it's a Pawn
            else if (piece instanceof Night)
                nightMove(); // If it's a Night
            else if (piece instanceof King)
                kingMove(); // If it's a King
            else
                movePiece(playerMoveCases[0], playerMoveCases[1]);


        }
    }

    /***
     * pawnMove method
     * handle the pawn movements
     */
    public void pawnMove() {

        // Get the two movement cases
        Case c1 = playerMoveCases[0];
        Case c2 = playerMoveCases[1];
        // Get the pawn
        Pawn pawn = (Pawn)c1.getPiece();
        // Get the second case piece
        Piece piece = c2.getPiece();
        // Get movement arrays
        int[][] pawnMoves = pawn.getMove();
        int[] playerMoveDiff = new int[]{
                playerMove[0][0] - playerMove[1][0],
                playerMove[0][1] - playerMove[1][1]
        };

        // Conditions & Logic
        if (piece == null) { // If the piece on the second case is null
            // If the case is one row above or below
            if (Arrays.equals(playerMoveDiff, pawnMoves[0]))
                movePiece(c1, c2); // Moving the pawn
            // If the case is two rows above or below and the pawn hasn't moved yet
            else if (!pawn.getMoved() && Arrays.equals(playerMoveDiff, pawnMoves[1]))
                movePiece(c1, c2); // Moving the pawn
        } else { // If the piece on the second case isn't null
            // If the colors don't match and the case is one row above/below and one column to the right
            if (pawn.getColor() != piece.getColor() && Arrays.equals(playerMoveDiff, pawnMoves[2]))
                movePiece(c1, c2); // Moving the pawn
            // If the colors don't match and the case is one row above/below and one column to the left
            else if (pawn.getColor() != piece.getColor() && Arrays.equals(playerMoveDiff, pawnMoves[3]))
                movePiece(c1, c2); // Moving the pawn
        }

        // Checking if the pawn moved
        if(c1.getPiece() == pawn)
            resetPlayerMove(); // If not, reset the player move array
        else
            pawn.moved(); // If so, change the hasMoved boolean to true
    }

    /***
     * nightMove method
     * handle the Night movements
     */
    public void nightMove() {

        // Get the two movement cases
        Case c1 = playerMoveCases[0];
        Case c2 = playerMoveCases[1];
        // Get the pawn
        Night night = (Night)c1.getPiece();
        // Get the second case piece
        Piece piece = c2.getPiece();
        // Get movement arrays
        int[][] nightMoves = night.getMove();
        int[] playerMoveDiff = new int[]{
                playerMove[0][0] - playerMove[1][0],
                playerMove[0][1] - playerMove[1][1]
        };

        // Conditions & Logic
        for (int[] move : nightMoves) // Going through the moves
            if(Arrays.equals(playerMoveDiff, move)) // If the player move is valid
                if (piece == null) // If the piece is null
                    movePiece(c1, c2); // Move the Night
                else if(piece.getColor() != night.getColor()) // Else if the piece is of an opposite color
                    movePiece(c1, c2); // Move the Night

        // Check is the Night moved
        if (c1.getPiece() == night)
            resetPlayerMove();
    }

    public void kingMove() {

        // Get the two movement cases
        Case c1 = playerMoveCases[0];
        Case c2 = playerMoveCases[1];
        // Get the pawn
        King king = (King)c1.getPiece();
        // Get the second case piece
        Piece piece = c2.getPiece();
        // Get movement arrays
        int[][] kingMoves = king.getMove();
        int[] playerMoveDiff = new int[]{
                playerMove[0][0] - playerMove[1][0],
                playerMove[0][1] - playerMove[1][1]
        };

        // Conditions & Logic
        for (int[] move : kingMoves) { // Going through the moves
            if(Arrays.equals(playerMoveDiff, move)) // If the player move is in the array
                if(piece == null) // If the piece is null
                    movePiece(c1, c2); // Moving the King
                else if (piece.getColor() != king.getColor()) // If the piece is from an opposite color
                    movePiece(c1, c2); // Moving the King
        }

        // Checking if the King moved
        if (c1.getPiece() == king)
            resetPlayerMove();
        else
            king.moved(); // Change the hasMoved boolean to true
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
