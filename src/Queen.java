import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(String type, boolean isWhite, Position position) {
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

        // It should check super's validity (grid range) && Queen's validity
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
        for (int i = 1; i < 8; i++) {
            if (rowPosition - i >= 0 && rowPosition - i < 8)
                if (board[rowPosition - i][colPosition] == null
                        || board[rowPosition - i][colPosition].isWhite != this.isWhite) {
                    validPositions.add(new Position(rowPosition - i, colPosition));
                }
        }

        // Right forward diagonal
        for (int i = 1; i < 8; i++) {
            if (rowPosition - i >= 0 && rowPosition - i < 8 && colPosition + i >= 0 && colPosition + i < 8)
                if (board[rowPosition - i][colPosition + i] == null
                        || board[rowPosition - i][colPosition + i].isWhite != this.isWhite) {
                    validPositions.add(new Position(rowPosition - i, colPosition + i));
                }
        }

        // Right
        for (int i = 1; i < 8; i++) {
            if (colPosition + i >= 0 && colPosition + i < 8)
                if (board[rowPosition][colPosition + i] == null
                        || board[rowPosition][colPosition + i].isWhite != this.isWhite) {
                    validPositions.add(new Position(rowPosition, colPosition + i));
                }
        }

        // Right back diagonal
        for (int i = 1; i < 8; i++) {
            if (rowPosition + i >= 0 && rowPosition + i < 8 && colPosition + i >= 0 && colPosition + i < 8)
                if (board[rowPosition + i][colPosition + i] == null
                        || board[rowPosition + i][colPosition + i].isWhite != this.isWhite) {
                    validPositions.add(new Position(rowPosition + i, colPosition + i));
                }
        }

        // Back
        for (int i = 1; i < 8; i++) {
            if (rowPosition + i >= 0 && rowPosition + i < 8)
                if (board[rowPosition + i][colPosition] == null
                        || board[rowPosition + i][colPosition].isWhite != this.isWhite) {
                    validPositions.add(new Position(rowPosition + i, colPosition));
                }
        }
        // Left back diagonal
        for (int i = 1; i < 8; i++) {
            if (rowPosition + i >= 0 && rowPosition + i < 8 && colPosition - i >= 0 && colPosition - i < 8)
                if (board[rowPosition + i][colPosition - i] == null
                        || board[rowPosition + i][colPosition - i].isWhite != this.isWhite) {
                    validPositions.add(new Position(rowPosition + i, colPosition - i));
                }
        }

        // Left
        for (int i = 1; i < 8; i++) {
            if (colPosition - i >= 0 && colPosition - i < 8)
                if (board[rowPosition][colPosition - i] == null
                        || board[rowPosition][colPosition - i].isWhite != this.isWhite) {
                    validPositions.add(new Position(rowPosition, colPosition - i));
                }
        }

        // Left forward diagonal
        for (int i = 1; i < 8; i++) {
            if (rowPosition - i >= 0 && rowPosition - i < 8 && colPosition - i >= 0 && colPosition - i < 8)
                if (board[rowPosition - i][colPosition - i] == null
                        || board[rowPosition - i][colPosition - i].isWhite != this.isWhite) {
                    validPositions.add(new Position(rowPosition - i, colPosition - i));
                }
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

- moves rook + bishop

 */