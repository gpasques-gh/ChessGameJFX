package BoardAndGameLogic;

import java.util.ArrayList;
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
    private ArrayList<Case> playerMoveCases;
    private ArrayList<ArrayList<Case>> possibleMoves;
    private char turn;
    private boolean check;

    /***
     * Game constructor
     */
    public Game() {
        board = new Board();
        turn = 'w';
        playerMoveCases = new ArrayList<>();
        generatePossibleMoves();
    }

    /***
     * click method
     * @param s name of the clicked Rectangle/Case
     */
    public void click(String s) {
        // Get the case of the clicked rectangle
        Case c = board.getCase(s);
        System.out.println(s);
        // Get indexes of the case
        // Adding the case and indexes to the move arrays
        if (playerMoveCases.isEmpty()) {
            playerMoveCases.add(c);
        } else {
            playerMoveCases.add(c);
            // Checking Piece type and moving accordingly
            if (possibleMoves.contains(playerMoveCases)) {
                movePiece(playerMoveCases.get(0), playerMoveCases.get(1));
            } else {
                resetPlayerMove();
            }
        }
    }

    public void generatePossibleMoves() {
        // Variables
        possibleMoves = new ArrayList<>(); // Moves
        Case[][] cases = board.getCases(); // Board
        Case c1, c2; // Cases
        int i1, j1; // Indexes for movement
        Piece piece;
        // Going through the board for Pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                c1 = cases[i][j]; // First case
                piece = c1.getPiece(); // First case Piece
                if (piece != null && piece.getColor() == turn) {
                    // Going through the move array
                    for (int[] move : piece.getMove()) {
                        i1 = i + move[1]; // Adding the row movement
                        j1 = j + move[0]; // Adding the column movement
                        if (i1 > -1 && i1 < 8 && j1 > -1 && j1 < 8) {
                            c2 = cases[i1][j1]; // Second case
                            if (isValidMove(piece, c1, c2)) {
                                // Adding the move to the ArrayList
                                ArrayList<Case> moveArrList = new ArrayList<>();
                                moveArrList.add(c1);
                                moveArrList.add(c2);
                                possibleMoves.add(moveArrList);
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean isValidMove(Piece piece, Case c1, Case c2) {
        boolean isValidMove = false;
        if (piece instanceof Pawn)
            isValidMove = pawnMove(c1, c2);
        else if (piece instanceof Night)
            isValidMove = nightMove(c1, c2);
        else
            resetPlayerMove();
        return isValidMove;
    }

    /***
     * pawnMove method
     * handle the pawn movements
     */
    public boolean pawnMove(Case c1, Case c2) {
        boolean isValidMove = false;
        // Get the two movement cases
        // Get the pawn
        Pawn pawn = (Pawn) c1.getPiece();
        // Get the second case piece
        Piece piece = c2.getPiece();
        // Get movement arrays
        int[][] pawnMoves = pawn.getMove();
        int[] playerMoveDiff = new int[]{
                c1.getColIndex() - c2.getColIndex(),
                c1.getRowIndex() - c2.getRowIndex()
        };

        // Conditions & Logic
        if (piece == null) { // If the piece on the second case is null
            // If the case is one row above or below
            if (Arrays.equals(playerMoveDiff, pawnMoves[0]))
                isValidMove = true; // Moving the pawn
                // If the case is two rows above or below and the pawn hasn't moved yet
            else if (!pawn.getMoved() && Arrays.equals(playerMoveDiff, pawnMoves[1]))
                isValidMove = true; // Moving the pawn
        } else { // If the piece on the second case isn't null
            // If the colors don't match and the case is one row above/below and one column to the right
            if (pawn.getColor() != piece.getColor() && Arrays.equals(playerMoveDiff, pawnMoves[2]))
                isValidMove = true; // Moving the pawn
                // If the colors don't match and the case is one row above/below and one column to the left
            else if (pawn.getColor() != piece.getColor() && Arrays.equals(playerMoveDiff, pawnMoves[3]))
                isValidMove = true; // Moving the pawn
        }
        // Checking if the pawn moved
        return isValidMove;
    }

    /***
     * nightMove method
     * handle the Night movements
     */
    public boolean nightMove(Case c1, Case c2) {
        boolean isValidMove = false;
        // Get the Night
        Night night = (Night) c1.getPiece();
        // Get the second case piece
        Piece piece = c2.getPiece();
        // Get movement arrays
        int[][] nightMoves = night.getMove();
        int[] playerMoveDiff = new int[]{
                c1.getColIndex() - c2.getColIndex(),
                c1.getRowIndex() - c2.getRowIndex()
        };

        // Conditions & Logic
        for (int[] move : nightMoves) // Going through the moves
            if (Arrays.equals(playerMoveDiff, move)) // If the player move is valid
                if (piece == null) // If the piece is null
                    isValidMove = true; // Move the Night
                else if (piece.getColor() != night.getColor()) // Else if the piece is of an opposite color
                    isValidMove = true; // Move the Night

        return isValidMove;
    }

//    public boolean rookMove(Case c1, Case c2) {
//        boolean isValidMove = false;
//        boolean isStopped = false;
//        Rook rook = (Rook)c1.getPiece();
//        Piece piece = c2.getPiece();
//        int i = 0;
//        int j = 0;
//        int[][] rookMoves = rook.getMove();
//        int[] moveDiff = new int[]{
//                c1.getColIndex() - c2.getColIndex(),
//                c1.getRowIndex() - c2.getRowIndex()
//        };
//
//        while (!isStopped && i < rookMoves.length) {
//            j = Math.max(, moveDiff[1]);
//            while (j > 0) {
//
//            }
//        }
//    }
//
//    public void kingMove() {
//
//        // Get the two movement cases
//        Case c1 = playerMoveCases[0];
//        Case c2 = playerMoveCases[1];
//        // Get the pawn
//        King king = (King)c1.getPiece();
//        // Get the second case piece
//        Piece piece = c2.getPiece();
//        // Get movement arrays
//        int[][] kingMoves = king.getMove();
//        int[] playerMoveDiff = new int[]{
//                playerMove[0][0] - playerMove[1][0],
//                playerMove[0][1] - playerMove[1][1]
//        };
//
//        // Conditions & Logic
//        for (int[] move : kingMoves) { // Going through the moves
//            if(Arrays.equals(playerMoveDiff, move)) // If the player move is in the array
//                if(piece == null) // If the piece is null
//                    movePiece(c1, c2); // Moving the King
//                else if (piece.getColor() != king.getColor()) // If the piece is from an opposite color
//                    movePiece(c1, c2); // Moving the King
//        }
//
//        // Checking if the King moved
//        if (c1.getPiece() == king)
//            resetPlayerMove();
//        else
//            king.moved(); // Change the hasMoved boolean to true
//    }

    public void movePiece(Case a, Case b) {
        Piece p = a.getPiece();
        a.setPiece(null);
        b.setPiece(p);
        if (p instanceof Pawn) {
            ((Pawn) p).moved();
        } else if (p instanceof King) {
            ((King) p).moved();
        } else if (p instanceof Rook) {
            ((Rook) p).moved();
        }
        if (turn == 'w')
            turn = 'b';
        else if (turn == 'b')
            turn = 'w';
        resetPlayerMove();
        generatePossibleMoves();

    }


    public void resetPlayerMove() {
        playerMoveCases.clear();
    }

    public Board getBoard() {
        return board;
    }
}
