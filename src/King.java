import java.util.ArrayList;

public class King extends Piece {
    private boolean moved = false;

    public King(String type, boolean isWhite, Position position) {
        super(type, isWhite, position);
    }

    @Override
    protected boolean move(Position newPosition, Piece[][] board, int counter) {
        boolean result = super.move(newPosition, board, counter);
        if (result && !moved) moved = true;
        return result;
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

        if (!moved) validPositions.addAll(getCastling(board));

        while (validPositions.contains(null)) validPositions.remove(null);
        board[this.position.getRow()][this.position.getCol()] = king;

        return validPositions;
    }

    private ArrayList<Position> getCastling(Piece[][] board) {
        ArrayList<Position> castlingPositions = new ArrayList<>();
        if (this.isWhite) {
            if (board[7][0] instanceof Rook && !((Rook) board[7][0]).getMoved() && board[7][1] == null && board[7][2] == null && board[7][3] == null)
                castlingPositions.add(getValidWay(board, 0, -2));
            if (board[7][7] instanceof Rook && !((Rook) board[7][7]).getMoved() && board[7][6] == null && board[7][5] == null)
                castlingPositions.add(getValidWay(board, 0, +2));
        } else {
            if (board[0][0] instanceof Rook && !((Rook) board[0][0]).getMoved() && board[0][1] == null & board[0][2] == null && board[0][3] == null)
                castlingPositions.add(getValidWay(board, 0, -2));
            if (board[0][7] instanceof Rook && !((Rook) board[0][7]).getMoved() && board[0][6] == null && board[0][5] == null)
                castlingPositions.add(getValidWay(board, 0, +2));
        }
        return castlingPositions;
    }

    private Position getValidWay(Piece[][] board, int rowMove, int colMove) {
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
