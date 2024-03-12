package Pieces;

public class Night extends Piece {

    /***
     * Knight constructor
     * @param n name
     * @param c color
     * @param i image path
     */
    public Night(String n, char c, String i) {
        // Calling the Piece constructor
        super(n, c, i);

        // Creating the move array
        int[][] m = new int[][]{
                {2, -1},
                {2, 1},
                {1, -2},
                {1, 2},
                {-1, -2},
                {-1, 2},
                {-2, -1},
                {-2, 1}
        };
        super.setMove(m);
    }
}
