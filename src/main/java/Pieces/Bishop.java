package Pieces;

public class Bishop extends Piece {

    /***
     * Bishop constructor
     * @param n name
     * @param c color
     * @param i image path
     */
    public Bishop(String n, char c, String i) {
        // Calling the Piece constructor
        super(n, c, i);

        // Creating the move array
        int[][] m = new int[][]{
                {1, -1},
                {1, 1},
                {-1, 1},
                {-1, -1}
        };
        super.setMove(m);
    }
}
