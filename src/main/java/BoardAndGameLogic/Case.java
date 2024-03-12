package BoardAndGameLogic;
import Pieces.*;

public class Case {

    /***
     * String name
     * the Piece on the case
     * the index of the row
     * the index of the column
     */
    private String name;
    private Piece piece;
    private int rowIndex;
    private int colIndex;

    /***
     * Case constructor
     * @param n name
     */
    public Case(String n, int r, int c) {
        name = n;
        rowIndex = r;
        colIndex = c;
        piece = null;
    }

    public void setPiece(Piece p){
        piece = p;
    }

    /***
     * getName method
     * @return name of the Case
     */
    public String getName() {
        return name;
    }

    /***
     * getPiece method
     * @return the Piece on the Case
     */
    public Piece getPiece() {
        return piece;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }
}
