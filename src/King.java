import java.util.ArrayList;

public class King extends Piece {

    public King(String type, boolean isWhite, Position position) {
        super(type, isWhite, position);
    }

    /**
     * @param newPosition is the position that this piece will move
     * @param board is present board data
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
     * @param newPosition is the position that this piece will move
     * @param board is present board data
     * @return is boolean type value that move command is valid or not
     */
    @Override
    protected boolean isValidMove(Position newPosition, Piece[][] board) {
        boolean result = false;
        ArrayList<Position> validMoveList = getValidMoveList(board);
        for (Position position : validMoveList)
            if (position.equals(newPosition)) result = true;
        return result;
    }

    /**
     * @param board is present board data
     * @return is ArrayList that contains valid moves
     */
    @Override
    public ArrayList<Position> getValidMoveList(Piece[][] board) {
        ArrayList<Position> validPositions = new ArrayList<>();
        int rowPosition = this.position.getRow(), colPosition = this.position.getCol();

        // Forward
        if (rowPosition - 1 >= 0 && rowPosition - 1 < 8)
            if (board[rowPosition - 1][colPosition] == null
                    || board[rowPosition - 1][colPosition].isWhite != this.isWhite) {
                validPositions.add(new Position(rowPosition - 1, colPosition));
            }

        // Right forward diagonal
            if (rowPosition - 1 >= 0 && rowPosition - 1 < 8 && colPosition + 1 >= 0 && colPosition +1 < 8)
                if (board[rowPosition - 1][colPosition + 1] == null
                        || board[rowPosition - 1][colPosition + 1].isWhite != this.isWhite) {
                    validPositions.add(new Position(rowPosition - 1, colPosition +1));
                }

        // Right
            if (colPosition + 1 >= 0 && colPosition + 1 < 8)
               if (board[rowPosition][colPosition + 1] == null
                        || board[rowPosition][colPosition + 1].isWhite != this.isWhite) {
                    validPositions.add(new Position(rowPosition, colPosition +1));
                }

        // Right back diagonal
            if (rowPosition + 1 >= 0 && rowPosition + 1 < 8 && colPosition +1 >= 0 && colPosition +1 < 8)
                if (board[rowPosition + 1][colPosition +1] == null
                        || board[rowPosition +1][colPosition +1].isWhite != this.isWhite) {
                    validPositions.add(new Position(rowPosition +1, colPosition +1));
                }

        // Back
            if (rowPosition +1 >= 0 && rowPosition +1 < 8)
                if (board[rowPosition +1][colPosition] == null
                        || board[rowPosition +1][colPosition].isWhite != this.isWhite) {
                    validPositions.add(new Position(rowPosition +1, colPosition));
                }

        // Left back diagonal
            if (rowPosition +1 >= 0 && rowPosition +1 < 8 && colPosition -1 >= 0 && colPosition -1 < 8)
                if (board[rowPosition +1][colPosition -1] == null
                        || board[rowPosition +1][colPosition -1].isWhite != this.isWhite) {
                    validPositions.add(new Position(rowPosition +1, colPosition -1));
                }

        // Left
            if (colPosition -1 >= 0 && colPosition -1 < 8)
                if (board[rowPosition][colPosition -1] == null
                        || board[rowPosition][colPosition -1].isWhite != this.isWhite) {
                    validPositions.add(new Position(rowPosition, colPosition -1));
                }

        // Left forward diagonal

            if (rowPosition -1 >= 0 && rowPosition -1 < 8 && colPosition -1 >= 0 && colPosition -1 < 8)
                if (board[rowPosition -1][colPosition -1] == null
                        || board[rowPosition -1][colPosition -1].isWhite != this.isWhite) {
                    validPositions.add(new Position(rowPosition -1, colPosition -1));
                }

        return validPositions;
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