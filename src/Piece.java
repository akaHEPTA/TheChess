import java.util.ArrayList;

public abstract class Piece {
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

    public Piece(String type, boolean isWhite, Position position) {
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
     * @param board       is present board status to check
     */
    protected boolean move(Position newPosition, Piece[][] board, int counter) {
        boolean result = false;
        if (this.isValidMove(newPosition, board)) {
            this.position = newPosition;
            result = true;
        }
        return result;
    }

    /**
     * check the destination point is available to move
     * this method checks only the grid safety(is this piece on the board),
     * so you should override and use this method inside of the pieces' isValidMove()
     *
     * @return true if it's valid, false in invalid
     */
    protected boolean isValidMove(Position newPosition, Piece[][] board) {
        boolean result = false;
        ArrayList<Position> validMoveList = getValidMoveList(board);
        for (Position position : validMoveList) if (position.equals(newPosition)) result = true;
        return result;
    }

    /**
     * Every piece should have their own overridden method that returns available move list
     *
     * @return ArrayList of available Position objects list
     */
    protected abstract ArrayList<Position> getValidMoveList(Piece[][] board);

    protected boolean isInRange(int row, int col) {
        if (row >= 0 && row < 8 && col >= 0 && col < 8) return true;
        return false;
    }

    @Override
    public String toString() {
        return this.position + ", " + this.type;
    }

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
     * No setter for this.type -> after the piece object created, the type aren't gonna change (except
     * Pawn) -> set the type on the initialization (constructor)
     *
     * <p>No setter for this.position -> only move() method can change the position of the piece for
     * the safety -> do not try to set a piece's position manually from outside of the object
     *
     * @author Richard
     */
}
