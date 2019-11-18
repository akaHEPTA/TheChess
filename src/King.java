import java.util.ArrayList;

public class King extends Piece {

    public King(String type, boolean isWhite, Position position) {
        super(type, isWhite, position);
    }

    /**
     * @param board is present board data
     * @return is ArrayList that contains valid moves
     */
    @Override
    public ArrayList<Position> getValidMoveList(Piece[][] board) {
        ArrayList<Position> validPositions = new ArrayList<>();

        Piece king = board[this.position.getRow()][this.position.getCol()];
        board[this.position.getRow()][this.position.getCol()] = null;

        validPositions.add(getValidWay(board, -1, 0));
        validPositions.add(getValidWay(board, -1, +1));
        validPositions.add(getValidWay(board, 0, +1));
        validPositions.add(getValidWay(board, 1, +1));
        validPositions.add(getValidWay(board, 1, 0));
        validPositions.add(getValidWay(board, 1, -1));
        validPositions.add(getValidWay(board, 0, -1));
        validPositions.add(getValidWay(board, -1, -1));

        while (validPositions.contains(null)) validPositions.remove(null);

        board[this.position.getRow()][this.position.getCol()] = king;

        return validPositions;
    }

    protected Position getValidWay(Piece[][] board, int rowMove, int colMove) {
        int rowP = this.position.getRow() + rowMove, colP = this.position.getCol() + colMove;

        if (isInRange(rowP, colP) && (board[rowP][colP] == null || board[rowP][colP].isWhite != this.isWhite)) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Piece temp = board[i][j];
                    Position newPos = new Position(rowP, colP);
                    if (temp != null && temp.isWhite != this.isWhite && temp.getValidMoveList(board).contains(newPos)) {
                        return null;
                    }
                }
            }
            return new Position(rowP, colP);
        }
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