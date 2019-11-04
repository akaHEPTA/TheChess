import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(String type, boolean isWhite, Position position) {
        super(type, isWhite, position);
    }

    @Override
    protected boolean move(Position newPosition, Piece[][] board, boolean isWhiteTurn) {
        boolean result = false;

        // It should check super's validity (grid range) && Queen's validity
        if (super.isValidMove(newPosition) && isValidMove(newPosition, board, isWhiteTurn)) {
            this.position = newPosition;
            result = true;
        }

        return result;
    }

    // @Override
    protected boolean isValidMove(Position newPosition, Piece[][] board, boolean isWhiteTurn) {
        boolean result = false;
        ArrayList<Position> validPositions = new ArrayList<>();
        int rowPosition = this.position.getRow(), colPosition = this.position.getCol();

        if (isWhiteTurn && isWhite) {

            for (int i = 0; i < 8; i++) {
                // Forward
                if (board[rowPosition + i][colPosition] == null) {
                    validPositions.add(new Position(rowPosition + i, colPosition));
                } else if (board[rowPosition + i][colPosition].isWhite != this.isWhite) {
                    validPositions.add(new Position(rowPosition + i, colPosition));
                }

                // Right
                if (board[rowPosition][colPosition + i] == null) {
                    validPositions.add(new Position(rowPosition, colPosition + i));
                } else if (board[rowPosition][colPosition].isWhite != this.isWhite) {
                    validPositions.add(new Position(rowPosition + i, colPosition));
                }

            }
        }

        for (Position position : validPositions)
            if (position.getRow() == newPosition.getCol() && position.getCol() == newPosition.getCol()) result = true;

        return result;
    }

    @Override
    public ArrayList<Position> getValidMoveList() {
        return null;
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