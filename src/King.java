import java.util.ArrayList;

public class King extends Piece {

    public King(String type, boolean isWhite, Position position) {
        super(type, isWhite, position);
    }

    /**
     * @param newPosition is the position that this piece will move
     * @param board       is present board data
     * @return is boolean type value that move command works or not
     */
    @Override
    protected boolean move(Position newPosition, Piece[][] board) {
        boolean result = false;

        // It should check super's validity (grid range) && King's validity
        if (super.isValidMove(newPosition, board) && isValidMove(newPosition, board)) {
            this.position = newPosition;
            result = true;
        }
        return result;
    }

    /**
     * @param board is present board data
     * @return is ArrayList that contains valid moves
     */
    @Override
    public ArrayList<Position> getValidMoveList(Piece[][] board) {
        ArrayList<Position> validPositions = new ArrayList<>();

        validPositions.add(getValidWay(board, -1, 0));
        validPositions.add(getValidWay(board, -1, +1));
        validPositions.add(getValidWay(board, 0, +1));
        validPositions.add(getValidWay(board, 1, +1));
        validPositions.add(getValidWay(board, 1, 0));
        validPositions.add(getValidWay(board, 1, -1));
        validPositions.add(getValidWay(board, 0, -1));
        validPositions.add(getValidWay(board, -1, -1));

        while (validPositions.contains(null))
            validPositions.remove(null);

        return validPositions;
    }

    protected Position getValidWay(Piece[][] board, int rowMove, int colMove) {
        int rowP = this.position.getRow() + rowMove, colP = this.position.getCol() + colMove;

        if (isInRange(rowP, colP) && (board[rowP][colP] == null || board[rowP][colP].isWhite != this.isWhite))
            return new Position(rowP, colP);
        else
            return null;
    }

    @Override
    public String toString() {
        return null;
    }
}

/*
required to implement

- moves a square
- cant move to a check-mate position
- if King has no space to move, game over
- king cant be captured
- castling (special rule)
 */