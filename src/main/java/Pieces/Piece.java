package Pieces;

public abstract class Piece {

    /***
     * Constants and Variables
     * name of the Piece
     * color of the Piece ('w' for white and 'b' for black)
     * image icon of the Piece
     * moves of the Piece (index of row and columns)
     */
    private final String name;
    private final char color;
    private final String image;

    /***
     * Piece constructor
     * @param n name
     * @param c color
     */
    public Piece(String n, char c, String i) {
        name = n;
        color = c;
        image = i;
    }

    /***
     * getName method
     * @return name of the Piece
     */
    public String getName() {
        return name;
    }

    /***
     * getColor method
     * @return color of the Piece
     */
    public char getColor() {
        return color;
    }

    /***
     * getImage method
     * @return image path of the Piece
     */
    public String getImage() {
        return image;
    }
}
