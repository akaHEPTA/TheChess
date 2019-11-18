import java.util.ArrayList;

public class Rook extends Piece {

    public Rook(String type, boolean isWhite, Position position) {
        super(type, isWhite, position);
    }

    /**
     * @param board is present board data
     * @return is ArrayList that contains valid moves
     */
    @Override
    public ArrayList<Position> getValidMoveList(Piece[][] board) {
        ArrayList<Position> validPositions = new ArrayList<>();

        validPositions.addAll(getValidWay(board, -1, 0));
        validPositions.addAll(getValidWay(board, 0, +1));
        validPositions.addAll(getValidWay(board, 1, 0));
        validPositions.addAll(getValidWay(board, 0, -1));

        return validPositions;
    }

    protected ArrayList<Position> getValidWay(Piece[][] board, int rowMove, int colMove) {
        ArrayList<Position> validPositions = new ArrayList<>();

        boolean isBlocked = false;

        for (int i = 1; i < 8; i++) {
            int rowP = this.position.getRow() + i * rowMove, colP = this.position.getCol() + i * colMove;

            if (isInRange(rowP, colP) && !isBlocked) {
                if (board[rowP][colP] == null) validPositions.add(new Position(rowP, colP));
                else if (board[rowP][colP].isWhite != this.isWhite) {
                    validPositions.add(new Position(rowP, colP));
                    isBlocked = true;
                } else isBlocked = true;
            }
        }

        return validPositions;
    }

    public boolean castling(Piece[][] board) {
        return false;

    }

}

/*
required to implement

- move horizontally or vertically
- Castling (Special rule)

 */