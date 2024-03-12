package Pieces;

public class Queen extends Piece {

    /***
     * Queen constructor
     * @param n name
     * @param c color
     * @param i image path
     */
    public Queen(String n, char c, String i) {
        // Calling the Piece constructor
        super(n, c, i);

        // Creating the move array
        int[][] m = new int[][]{
                {0, 1},
                {0, -1},
                {1, 1},
                {1, -1},
                {-1, -1},
                {-1, 0},
                {-1, 1}
        };
        super.setMove(m);
    }
}
