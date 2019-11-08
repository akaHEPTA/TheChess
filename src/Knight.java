import java.util.ArrayList;

public class Knight extends Piece {

    public Knight(String type, boolean isWhite, Position position) {
        super(type, isWhite, position);
    }

    @Override
    protected boolean move(Position newPosition, Piece[][] board) {
        boolean result = false;
        // It should check super's validity (grid range) && Knight's validity
        if (super.isValidMove(newPosition, board) && isValidMove(newPosition, board)) {
            this.position = newPosition;
            result = true;
        }
        return result;
    }

    @Override
    protected boolean isValidMove(Position newPosition, Piece[][] board) {
        boolean result = false;
        ArrayList<Position> validMoveList = getValidMoveList(board);
        for (Position position : validMoveList)
            if (position.equals(newPosition)) result = true;
        return result;
    }

    @Override
    public ArrayList<Position> getValidMoveList(Piece[][] board) {
        ArrayList<Position> validPositions = new ArrayList<>();
        int rowPosition = this.position.getRow();
        int colPosition = this.position.getCol();

        // 1 o'clock position
        if (rowPosition + 2 >= 0 && rowPosition + 2 < 8 && colPosition + 1 >= 0 && colPosition + 1 < 8) {
            if (board[rowPosition + 2][colPosition + 1] == null
                    || board[rowPosition + 2][colPosition + 1].isWhite != this.isWhite) {
                validPositions.add(new Position(rowPosition + 2, colPosition + 1));
            }
        }
        // 2 o'clock position
        if (rowPosition + 1 >= 0 && rowPosition + 1 < 8 && colPosition + 2 >= 0 && colPosition + 2 < 8) {
            if (board[rowPosition + 1][colPosition + 2] == null
                    || board[rowPosition + 1][colPosition + 2].isWhite != this.isWhite) {
                validPositions.add(new Position(rowPosition + 1, colPosition + 2));
            }
        }
        // 4 o'clock position
        if (rowPosition - 1 >= 0 && rowPosition - 1 < 8 && colPosition + 2 >= 0 && colPosition + 2 < 8) {
            if (board[rowPosition - 1][colPosition + 2] == null
                    || board[rowPosition - 1][colPosition + 2].isWhite != this.isWhite) {
                validPositions.add(new Position(rowPosition - 1, colPosition + 2));
            }
        }
        // 5 o'clock position
        if (rowPosition - 2 >= 0 && rowPosition - 2 < 8 && colPosition + 1 >= 0 && colPosition + 1 < 8) {
            if (board[rowPosition - 2][colPosition + 1] == null
                    || board[rowPosition - 2][colPosition + 1].isWhite != this.isWhite) {
                validPositions.add(new Position(rowPosition - 2, colPosition + 1));
            }
        }
        // 7 o'clock position
        if (rowPosition - 2 >= 0 && rowPosition - 2 < 8 && colPosition - 1 >= 0 && colPosition - 1 < 8) {
            if (board[rowPosition - 2][colPosition - 1] == null
                    || board[rowPosition - 2][colPosition - 1].isWhite != this.isWhite) {
                validPositions.add(new Position(rowPosition - 2, colPosition - 1));
            }
        }
        // 8 o'clock position
        if (rowPosition - 1 >= 0 && rowPosition - 1 < 8 && colPosition - 2 >= 0 && colPosition - 2 < 8) {
            if (board[rowPosition - 1][colPosition - 2] == null
                    || board[rowPosition - 1][colPosition - 2].isWhite != this.isWhite) {
                validPositions.add(new Position(rowPosition - 1, colPosition - 2));
            }
        }
        // 10 o'clock position
        if (rowPosition + 1 >= 0 && rowPosition + 1 < 8 && colPosition - 2 >= 0 && colPosition - 2 < 8) {
            if (board[rowPosition + 1][colPosition - 2] == null
                    || board[rowPosition + 1][colPosition - 2].isWhite != this.isWhite) {
                validPositions.add(new Position(rowPosition + 1, colPosition - 2));
            }
        }
        // 11 o'clock position
        if (rowPosition - 1 >= 0 && rowPosition - 1 < 8 && colPosition + 2 >= 0 && colPosition + 2 < 8) {
            if (board[rowPosition - 1][colPosition + 2] == null
                    || board[rowPosition - 1][colPosition + 2].isWhite != this.isWhite) {
                validPositions.add(new Position(rowPosition - 1, colPosition + 2));
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

- moves like L
- can pass through the pieces in L (jump)

 */