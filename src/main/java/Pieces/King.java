package Pieces;

public class King extends Piece {

    /***
     * boolean hasMoved representing if the King moved during the game
     * useful to check if it can castle
     * boolean checked representing if the King is checked
     */
    private boolean hasMoved;
    private boolean checked;

    /***
     * King constructor
     * @param n name
     * @param c color
     * @param i image path
     */
    public King(String n, char c, String i) {
        // Calling Piece constructor
        super(n, c, i);
        hasMoved = false;
        checked = false;
    }

    /***
     * moved method
     * change hasMoved to true
     */
    public void moved() {
        hasMoved = true;
    }

    /***
     * setChecked method
     * change checked to true
     */
    public void setChecked() {
        checked = true;
    }

    /***
     * getMoved method
     * @return if the King has moved yet
     */
    public boolean getMoved() {
        return hasMoved;
    }

    /***
     * isChecked method
     * @return is the King is currently checked
     */
    public boolean isChecked() {
        return checked;
    }


}
