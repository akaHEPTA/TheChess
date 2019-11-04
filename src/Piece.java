import java.util.ArrayList;

public abstract class Piece {
    // Constants
    /**
     * protected position value array that child classes' constructor can use to set its starting position
     * will be filled with actual data...
     */
    protected final Position[] START_POSITION = null;

    // Fields
    /**
     * String type:
     * Type of the piece, this will be assigned at the initialization
     * ex) Queen, Rook...
     * <p>
     * boolean color:
     * White - true, black - false
     * <p>
     * Position position:
     * Position of the piece, this will be assigned at the initialization with the starting point data.
     *
     * @author Richard
     */
    protected String type;
    protected boolean isWhite;
    protected Position position;

    protected Piece(String type, boolean isWhite, Position position) {
        this.type = type;
        this.isWhite = isWhite;
        this.position = position;
    }

    // Methods

    /**
     * move the position of this piece - common method for the all pieces
     * this move method has no safety codes, so you must call isValidMove() for validity check
     * if other player's piece is occupying the destination, your piece will capture
     *
     * @param newPosition is the position that this piece will move
     */
    protected boolean move(Position newPosition, Piece[][] board, boolean isWhiteTurn){
        return true;
    }


    protected boolean move(Position newPosition){
        return true;
    }

    /**
     * check the destination point is available to move
     * this method checks only the grid safety(is this piece on the board),
     * so you should override and use this method inside of the pieces' isValidMove()
     *
     * @return true if it's valid, false in invalid
     */
    protected boolean isValidMove(Position newPosition) {
        boolean result = false;

        if (newPosition.getRow() <= 0 && newPosition.getRow() <= 7
                && newPosition.getCol() >= 0 && newPosition.getCol() <= 7)
            result = true;

        return result;
    }

    /**
     * Every piece should have their own overridden method that returns available move list
     *
     * @return ArrayList of available Position objects list
     */
    public abstract ArrayList<Position> getValidMoveList();

    @Override
    public abstract String toString();


    // Accessor

    /**
     * @return the piece's type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the piece's position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * No setter for this.type
     * -> after the piece object created, the type aren't gonna change (except Pawn)
     * -> set the type on the initialization (constructor)
     *
     * No setter for this.position
     * -> only move() method can change the position of the piece for the safety
     * -> do not try to set a piece's position manually from outside of the object
     *
     * @author Richard
     */

}

