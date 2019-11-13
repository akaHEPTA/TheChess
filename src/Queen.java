import java.util.ArrayList;

public class Queen extends Piece {

  public Queen(String type, boolean isWhite, Position position) {
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
        // It should check super's validity (grid range) && Queen's validity
        if (isValidMove(newPosition, board)) {
            this.position = newPosition;
            result = true;
        }
        return result;
    }
    return result;
  }

    /**
     * @param newPosition is the position that this piece will move
     * @param board       is present board data
     * @return is boolean type value that move command is valid or not
     */
//    @Override
//    protected boolean isValidMove(Position newPosition, Piece[][] board) {
//        boolean result = false;
//        ArrayList<Position> validMoveList = getValidMoveList(board);
//        for (Position position : validMoveList)
//            if (position.equals(newPosition)) result = true;
//        return result;
//    }

    /**
     * @param board is present board data
     * @return is ArrayList that contains valid moves
     */
    @Override
    protected ArrayList<Position> getValidMoveList(Piece[][] board) {
        ArrayList<Position> validPositions = new ArrayList<>();

        validPositions.addAll(getValidWay(board, -1, 0));
        validPositions.addAll(getValidWay(board, -1, +1));
        validPositions.addAll(getValidWay(board, 0, +1));
        validPositions.addAll(getValidWay(board, 1, +1));
        validPositions.addAll(getValidWay(board, 1, 0));
        validPositions.addAll(getValidWay(board, 1, -1));
        validPositions.addAll(getValidWay(board, 0, -1));
        validPositions.addAll(getValidWay(board, -1, -1));

        return validPositions;
    }

    protected ArrayList<Position> getValidWay(Piece[][] board, int rowMove, int colMove) {
        ArrayList<Position> validPositions = new ArrayList<>();

        boolean isBlocked = false;

        for (int i = 1; i < 8; i++) {
            int rowPosition = this.position.getRow() + i * rowMove;
            int colPosition = this.position.getCol() + i * colMove;

            if (isInRange(rowPosition, colPosition) && !isBlocked) {
                if (board[rowPosition][colPosition] == null)
                    validPositions.add(new Position(rowPosition, colPosition));
                else if (board[rowPosition][colPosition].isWhite != this.isWhite) {
                    validPositions.add(new Position(rowPosition, colPosition));
                    isBlocked = true;
                } else
                    isBlocked = true;
            }
        }
    }

        return validPositions;
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
