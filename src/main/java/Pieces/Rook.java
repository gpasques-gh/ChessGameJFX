package Pieces;

public class Rook extends Piece {

    /***
     * boolean hasMoved representing if the Rook moved during the game
     * useful to check if it can be used to castle
     */
    private boolean hasMoved;

    /***
     * Rook constructor
     * @param n name
     * @param c color
     * @param i image path
     */
    public Rook(String n, char c, String i) {
        // Calling the Piece constructor
        super(n, c, i);
    }

    /***
     * moved method
     * change hasMoved to true
     */
    public void moved() {
        hasMoved = true;
    }

    /***
     * getMoved method
     * @return if the King has moved yet
     */
    public boolean getMoved() {
        return hasMoved;
    }
}
