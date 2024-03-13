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
            cases[i][0] = new Case("a" + (8 - i), 8 - i, 1);
            cases[i][1] = new Case("b" + (8 - i), 8 - i, 2);
            cases[i][2] = new Case("c" + (8 - i), 8 - i, 3);
            cases[i][3] = new Case("d" + (8 - i), 8 - i, 4);
            cases[i][4] = new Case("e" + (8 - i), 8 - i, 5);
            cases[i][5] = new Case("f" + (8 - i), 8 - i, 6);
            cases[i][6] = new Case("g" + (8 - i), 8 - i, 7);
            cases[i][7] = new Case("h" + (8 - i), 8 - i, 8);
        }
    }

    /***
     * initializeBoard method
     * initializing the Pieces on the Cases from the Case array
     */
    public void initializeBoard() {
        // Initializing the black Pieces
        cases[0][0].setPiece(new Rook("bR", 'b', "file:src/main/images/black-rook.png"));
        cases[0][1].setPiece(new Night("bN", 'b', "file:src/main/images/black-knight.png"));
        cases[0][2].setPiece(new Bishop("bB", 'b', "file:src/main/images/black-bishop.png"));
        cases[0][3].setPiece(new Queen("bQ", 'b', "file:src/main/images/black-queen.png"));
        cases[0][4].setPiece(new King("bK", 'b', "file:src/main/images/black-king.png"));
        cases[0][5].setPiece(new Bishop("bB", 'b', "file:src/main/images/black-bishop.png"));
        cases[0][6].setPiece(new Night("bN", 'b', "file:src/main/images/black-knight.png"));
        cases[0][7].setPiece(new Rook("bR", 'b', "file:src/main/images/black-rook.png"));
        for (int i = 0; i < 8; i++) {
            cases[1][i].setPiece(new Pawn("bP", 'b', "file:src/main/images/black-pawn.png"));
        }

        // Initializing the white Pieces
        cases[7][0].setPiece(new Rook("wR", 'w', "file:src/main/images/white-rook.png"));
        cases[7][1].setPiece(new Night("wN", 'w', "file:src/main/images/white-knight.png"));
        cases[7][2].setPiece(new Bishop("wB", 'w', "file:src/main/images/white-bishop.png"));
        cases[7][3].setPiece(new Queen("wQ", 'w', "file:src/main/images/white-queen.png"));
        cases[7][4].setPiece(new King("wK", 'w', "file:src/main/images/white-king.png"));
        cases[7][5].setPiece(new Bishop("wB", 'w', "file:src/main/images/white-bishop.png"));
        cases[7][6].setPiece(new Night("wN", 'w', "file:src/main/images/white-knight.png"));
        cases[7][7].setPiece(new Rook("wR", 'w', "file:src/main/images/white-rook.png"));
        for (int i = 0; i < 8; i++) {
            cases[6][i].setPiece(new Pawn("wP", 'w', "file:src/main/images/white-pawn.png"));
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
                c.getColIndex(),
                c.getRowIndex()
        };
    }
}
