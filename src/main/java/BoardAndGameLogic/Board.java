package BoardAndGameLogic;
import Pieces.*;

public class Board {

    /**
     * Case array storing current game board
     */
    private Case[][] cases;

    /***
     * Board constructor creating the board and initializing the Pieces
     */
    public Board() {
        // Creating the board
        this.createBoard();

        // Initializing the Pieces
        this.initializeBoard();
    }

    /***
     * Board constructor from a Case array
     * @param c Case array
     */
    public Board(Case[][] c) {
        cases = c;
    }

    /***
     * createBoard() method
     * instancing the Case array
     * filling the Case array with Case objects
     */
    public void createBoard() {
        // Instancing the Case array
        cases = new Case[8][8];

        // Filling the Case array with cases
        for (int i = 0; i < 8; i++) {
            cases[0][i] = new Case("a" + (8 - i), i, 0);
            cases[1][i] = new Case("b" + (8 - i), i, 1);
            cases[2][i] = new Case("c" + (8 - i), i, 2);
            cases[3][i] = new Case("d" + (8 - i), i, 3);
            cases[4][i] = new Case("e" + (8 - i), i, 4);
            cases[5][i] = new Case("f" + (8 - i), i, 5);
            cases[6][i] = new Case("g" + (8 - i), i, 6);
            cases[7][i] = new Case("h" + (8 - i), i, 7);
        }
    }

    /***
     * initializeBoard method
     * initializing the Pieces on the Cases from the Case array
     */
    public void initializeBoard() {
        // Initializing the black Pieces
        cases[0][0].setPiece(new Rook("bR", 'b', "file:src/main/images/black-rook.png"));
        cases[1][0].setPiece(new Night("bN", 'b', "file:src/main/images/black-knight.png"));
        cases[2][0].setPiece(new Bishop("bB", 'b', "file:src/main/images/black-bishop.png"));
        cases[3][0].setPiece(new Queen("bQ", 'b', "file:src/main/images/black-queen.png"));
        cases[4][0].setPiece(new King("bK", 'b', "file:src/main/images/black-king.png"));
        cases[5][0].setPiece(new Bishop("bB", 'b', "file:src/main/images/black-bishop.png"));
        cases[6][0].setPiece(new Night("bN", 'b', "file:src/main/images/black-knight.png"));
        cases[7][0].setPiece(new Rook("bR", 'b', "file:src/main/images/black-rook.png"));
        for (int i = 0; i < 8; i++) {
            cases[i][1].setPiece(new Pawn("bP", 'b', "file:src/main/images/black-pawn.png"));
        }

        // Initializing the white Pieces
        cases[0][7].setPiece(new Rook("wR", 'w', "file:src/main/images/white-rook.png"));
        cases[1][7].setPiece(new Night("wN", 'w', "file:src/main/images/white-knight.png"));
        cases[2][7].setPiece(new Bishop("wB", 'w', "file:src/main/images/white-bishop.png"));
        cases[3][7].setPiece(new Queen("wQ", 'w', "file:src/main/images/white-queen.png"));
        cases[4][7].setPiece(new King("wK", 'w', "file:src/main/images/white-king.png"));
        cases[5][7].setPiece(new Bishop("wB", 'w', "file:src/main/images/white-bishop.png"));
        cases[6][7].setPiece(new Night("wN", 'w', "file:src/main/images/white-knight.png"));
        cases[7][7].setPiece(new Rook("wR", 'w', "file:src/main/images/white-rook.png"));
        for (int i = 0; i < 8; i++) {
            cases[i][6].setPiece(new Pawn("wP", 'w', "file:src/main/images/white-pawn.png"));
        }
    }

    /***
     * getCases() method
     * @return the Case array
     */
    public Case[][] getCases() {
        return cases;
    }


    public Case getCase(String n) {
        Case c = null;
        String s;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                s = cases[i][j].getName();
                if (s.equals(n)) {
                    c = cases[i][j];
                }
            }
        }
        return c;
    }

    public int[] getIndexes(String n) {
        Case c = getCase(n);
        return new int[]{
                c.getRowIndex(),
                c.getColIndex()
        };
    }
}
