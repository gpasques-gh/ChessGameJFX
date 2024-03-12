package Pieces;

public class Pawn extends Piece {

    /***
     * boolean representing if the Pawn moved during the game
     */
    private boolean hasMoved;

    /***
     * Pawn constructor
     * @param n name
     * @param c color
     * @param i image path
     */
    public Pawn(String n, char c, String i) {
        // Calling Piece constructor
        super(n, c, i);

        // Determining if white or black
        // and choosing which move
        if (c == 'w') {
            int[][] m = new int[][]{
                    {0, 1},
                    {0, 2},
                    {-1, 1},
                    {1, 1}
            };
            super.setMove(m);
        } else if (c == 'b') {
            int[][] m = new int[][]{
                    {0, -1},
                    {0, -2},
                    {-1, -1},
                    {1, -1}
            };
            super.setMove(m);
        }
        hasMoved = false;
    }

    /***
     * moved method
     * change the hasMoved variable to true
     */
    public void moved() {
        hasMoved = true;
    }


    /***
     * getMoved method
     * @return if the Pawn has moved yet
     */
    public boolean getMoved() {
        return hasMoved;
    }

}
