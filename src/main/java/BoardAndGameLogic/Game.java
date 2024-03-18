package BoardAndGameLogic;

import java.util.ArrayList;
import Pieces.*;

public class Game {

    /***
     * board of the game
     * possibleMoves array
     * playerMove array
     * playerMoveCases array
     * check boolean
     */
    private final Board board;
    private final ArrayList<Case> playerMoveCases;
    private ArrayList<ArrayList<Case>> possibleMoves;
    private char turn;
    //private boolean check;

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
        Case c1;
        Piece piece;
        // Going through the board for Pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                c1 = cases[i][j]; // First case
                piece = c1.getPiece(); // First case Piece
                if (piece != null && piece.getColor() == turn) {
                    // Going through the move array
                    possibleMovesPieces(piece, i, j);
                }
            }
        }
    }

    public void possibleMovesPieces(Piece piece, int row, int col) {
        if (piece instanceof Pawn)
            pawnMove(piece, row, col);
        else if (piece instanceof Rook)
            rookMove(piece, row, col);
        else if (piece instanceof Night)
            nightMove(piece, row, col);
        else
            resetPlayerMove();
    }

    public void addMove(Case c1, Case c2) {
        ArrayList<Case> arrayList = new ArrayList<>();
        arrayList.add(c1);
        arrayList.add(c2);
        possibleMoves.add(arrayList);
    }

    /***
     * pawnMove method
     * handle the pawn movements
     */
    public void pawnMove(Piece piece, int row, int col) {
        Case[][] cases = board.getCases();
        Case currentCase = cases[row][col];
        // Get the two movement cases
        // Get the pawn
        Pawn pawn = (Pawn) piece;

        // If the pawn is white
        if (piece.getColor() == 'w') {
            // If it can move forward
            if (row-1 >=  0) {
                // If the case is empty
                if (cases[row-1][col].getPiece()==null) {
                    // Add the move to the possible move ArrayList
                    addMove(currentCase, cases[row-1][col]);
                }

                // If the pawn hasn't moved yet
                if (!pawn.getMoved()) {
                    // If both the first and second cases are empty
                    if (cases[row-1][col].getPiece()==null && cases[row-2][col].getPiece()==null) {
                        // Add the move to the possible move ArrayList
                        addMove(currentCase, cases[row-2][col]);
                    }
                }

                // If it can move left
                if (col-1 >= 0) {
                    // Get the piece on the case
                    Piece secondPiece = cases[row-1][col-1].getPiece();
                    // If the piece isn't null and is black
                    if (secondPiece != null && secondPiece.getColor() != 'w') {
                        // Add the move to the possible move ArrayList
                        addMove(currentCase, cases[row-1][col-1]);
                    }
                }

                // If it can move right
                if (col+1 < cases.length) {
                    // Get the piece on the case
                    Piece secondPiece = cases[row-1][col+1].getPiece();
                    // If the piece isn't null and is black
                    if (secondPiece != null && secondPiece.getColor() != 'w') {
                        // Add the move to the possible move ArrayList
                        addMove(currentCase, cases[row-1][col+1]);
                    }
                }
            }
        } else { // If the pawn is black
            // If it can move forward
            if (row+1 < cases.length) {
                // If the case is empty
                if (cases[row+1][col].getPiece()==null) {
                    // Add the move to the possible move ArrayList
                    addMove(currentCase, cases[row-1][col]);
                }

                // If the pawn hasn't moved yet
                if (!pawn.getMoved()) {
                    // If both the first and second cases are empty
                    if (cases[row+1][col].getPiece()==null && cases[row+2][col].getPiece()==null) {
                        // Add the move to the possible move ArrayList
                        addMove(currentCase, cases[row+2][col]);
                    }
                }

                // If it can move left
                if (col-1 >= 0) {
                    // Get the piece on the case
                    Piece secondPiece = cases[row+1][col-1].getPiece();
                    // If the piece isn't null and is white
                    if (secondPiece != null && secondPiece.getColor() != 'b') {
                        // Add the move to the possible move ArrayList
                        addMove(currentCase, cases[row+1][col-1]);
                    }
                }

                // If it can move right
                if (col+1 < cases.length) {
                    // Get the piece on the case
                    Piece secondPiece = cases[row+1][col+1].getPiece();
                    // If the piece isn't null and is white
                    if (secondPiece != null && secondPiece.getColor() != 'b') {
                        // Add the move to the possible move ArrayList
                        addMove(currentCase, cases[row+1][col+1]);
                    }
                }
            }
        }
    }

    /***
     * nightMove method
     * handle the Night movements
     */
    public void nightMove(Piece piece, int row, int col) {
        Case[][] cases = board.getCases(); // Get the board
        Case currentCase = cases[row][col]; // Get the current case
        Night night = (Night)piece; // Get the Night

        // Test on the case two rows below and one column right
        if (row + 2 < cases.length && col + 1 < cases.length) {
            if (cases[row+2][col+1].getPiece() == null) { // If it's empty
                addMove(currentCase, cases[row+2][col+1]); // Add it to the moves
            // If the piece is opposite color
            } else if (cases[row+2][col+1].getPiece().getColor() != night.getColor()) {
                addMove(currentCase, cases[row+2][col+1]); // Add it to the moves
            }
        }

        // Test on the case two rows below and one column left
        if (row + 2 < cases.length && col - 1 >= 0) {
            if (cases[row+2][col-1].getPiece() == null) { // If it's empty
                addMove(currentCase, cases[row+2][col-1]); // Add it to the moves
            // If the piece is opposite color
            } else if (cases[row+2][col-1].getPiece().getColor() != night.getColor()) {
                addMove(currentCase, cases[row+2][col-1]); // Add it to the moves
            }
        }

        // Test on the case two rows above and one column right
        if (row - 2 >= 0 && col + 1 < cases.length) {
            if (cases[row-2][col+1].getPiece() == null) { // If it's empty
                addMove(currentCase, cases[row-2][col+1]); // Add it to the moves
                // If the piece is opposite color
            } else if (cases[row-2][col+1].getPiece().getColor() != night.getColor()) {
                addMove(currentCase, cases[row-2][col+1]); // Add it to the moves
            }
        }

        // Test on the case two rows above and one column left
        if (row - 2 >= 0 && col - 1 >= 0) {
            if (cases[row-2][col-1].getPiece() == null) { // If it's empty
                addMove(currentCase, cases[row-2][col-1]); // Add it to the moves
            // If the piece is opposite color
            } else if (cases[row-2][col-1].getPiece().getColor() != night.getColor()) {
                addMove(currentCase, cases[row-2][col-1]); // Add it to the moves
            }
        }

        // Test on the case one row above and two column right
        if (row - 1 >= 0 && col + 2 < cases.length) {
            if (cases[row-1][col+2].getPiece() == null) { // If it's empty
                addMove(currentCase, cases[row-1][col+2]); // Add it to the moves
                // If the piece is opposite color
            } else if (cases[row-1][col+2].getPiece().getColor() != night.getColor()) {
                addMove(currentCase, cases[row-1][col+2]); // Add it to the moves
            }
        }

        // Test on the case one row above and two column right
        if (row + 1 < cases.length && col + 2 < cases.length) {
            if (cases[row+1][col+2].getPiece() == null) { // If it's empty
                addMove(currentCase, cases[row+1][col+2]); // Add it to the moves
                // If the piece is opposite color
            } else if (cases[row+1][col+2].getPiece().getColor() != night.getColor()) {
                addMove(currentCase, cases[row+1][col+2]); // Add it to the moves
            }
        }

        // Test on the case one row above and two column left
        if (row - 1 >= 0 && col - 2 >= 0) {
            if (cases[row-1][col-2].getPiece() == null) { // If it's empty
                addMove(currentCase, cases[row-1][col-2]); // Add it to the moves
                // If the piece is opposite color
            } else if (cases[row-1][col-2].getPiece().getColor() != night.getColor()) {
                addMove(currentCase, cases[row-1][col-2]); // Add it to the moves
            }
        }

        // Test on the case one row above and two column left
        if (row + 1 < cases.length && col - 2 >= 0) {
            if (cases[row+1][col-2].getPiece() == null) { // If it's empty
                addMove(currentCase, cases[row+1][col-2]); // Add it to the moves
                // If the piece is opposite color
            } else if (cases[row+1][col-2].getPiece().getColor() != night.getColor()) {
                addMove(currentCase, cases[row+1][col-2]); // Add it to the moves
            }
        }
    }

    public void rookMove(Piece piece, int row, int col) {
        Case[][] cases = board.getCases();
        Case currentCase = cases[row][col];
        Rook rook = (Rook)piece;
        int r1 = row + 1;
        int c1 = col;

        // Going to the top of the board:
        while (r1 < cases.length) {
            // If the piece on the case is null
            if (cases[r1][c1].getPiece() == null) {
                addMove(currentCase, cases[r1][c1]); // Add it to the moves
                r1++; // Increment the row
            // If it's opposite color
            } else if (cases[r1][c1].getPiece().getColor() != rook.getColor()) {
                addMove(currentCase, cases[r1][c1]); // Add it to the move
                break; // Then stop
            // If it's allied piece
            } else {
                break; // Stop
            }
        }

        // Going to the bottom of the board:
        r1 = row - 1;
        while (r1 >= 0) {
            // If the piece on the case is null
            if (cases[r1][c1].getPiece() == null) {
                addMove(currentCase, cases[r1][c1]); // Add it to the moves
                r1--; // Decrement the row
            // If it's opposite color
            } else if (cases[r1][c1].getPiece().getColor() != rook.getColor()) {
                addMove(currentCase, cases[r1][c1]); // Add it to the move
                break; // Then stop
            // If it's allied piece
            } else {
                break; // Stop
            }
        }

        // Going to the right of the board:
        r1 = row;
        c1 = col + 1;
        while (c1 < cases.length) {
            // If the piece on the case is null
            if (cases[r1][c1].getPiece() == null) {
                addMove(currentCase, cases[r1][c1]); // Add it to the moves
                c1++;
            // If it's opposite color
            } else if (cases[r1][c1].getPiece().getColor() != rook.getColor()) {
                addMove(currentCase, cases[r1][c1]); // Add it to the moves
                break; // Then stop
            // If it's allied piece
            } else {
                break; // Stop
            }
        }

        // Going to the left of the board:
        c1 = col - 1;
        while (c1 >= 0) {
            // If the piece on the case is null
            if (cases[r1][c1].getPiece() == null) {
                addMove(currentCase, cases[r1][c1]); // Add it to the moves
                c1--; // Decrement the row
                // If it's opposite color
            } else if (cases[r1][c1].getPiece().getColor() != rook.getColor()) {
                addMove(currentCase, cases[r1][c1]); // Add it to the move
                break; // Then stop
                // If it's allied piece
            } else {
                break; // Stop
            }
        }
    }

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
