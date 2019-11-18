import java.util.ArrayList;

public class Pawn extends Piece {
    // Field
    private boolean isMoved = false;
    private boolean isJumped = false;
    private boolean isPromoted = false;
    private int counter = 0;

    // Constructor
    public Pawn(String type, boolean isWhite, Position position) {
        super(type, isWhite, position);
    }

    @Override
    protected boolean move(Position newPosition, Piece[][] board, int counter) {
        if (isValidMove(newPosition, board)) {
            if (!this.isMoved) this.isMoved = true;
            if (Math.abs(position.getRow() - newPosition.getRow()) == 2) {
                this.isJumped = true;
                this.counter = counter;
            }
            this.position = newPosition;
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Position> getValidMoveList(Piece[][] board) {
        ArrayList<Position> validPositions = new ArrayList<>();
        int row = this.position.getRow(), col = this.position.getCol(), i;

        if (this.isWhite) i = -1;
        else i = 1;

        // normal 1 step
        if (isInRange(row + i, col) && board[row + i][col] == null)
            validPositions.add(new Position(row + i, col));


        // first 2 step
        if (!isMoved && board[row + i][col] == null && board[row + i * 2][col] == null)
            validPositions.add(new Position(row + i * 2, col));

        if (isInRange(row + i, col + i)) {
            // diagonal left
            if (board[row + i][col + i] != null && board[row + i][col + i].isWhite != this.isWhite) {
                validPositions.add(new Position(row + i, col + i));
            }
            // En Passant - left
            if (board[row][col + i] != null
                    && board[row][col + i].getType() == "Pawn"
                    && board[row][col + i].isWhite != this.isWhite
                    && ((Pawn) board[row][col + i]).isJumped) {
                validPositions.add(new Position(row + i, col + i));
            }
        }

        if (isInRange(row + i, col - i)) {
            // diagonal right
            if (board[row + i][col - i] != null && board[row + i][col - i].isWhite != this.isWhite) {
                validPositions.add(new Position(row + i, col - i));
            }
            // En Passant - right
            if (board[row][col - i] != null
                    && board[row][col - i] instanceof Pawn
                    && board[row][col - i].isWhite != this.isWhite
                    && ((Pawn) board[row][col - i]).isJumped) {
                validPositions.add(new Position(row + i, col - i));
            }
        }

        return validPositions;
    }

    public boolean setPromotion() {
        this.isPromoted = true;
        return true;
    }

    public void setJumped(int c) {
        if (this.counter + 1 == c)
            this.isJumped = false;
    }

    public boolean getPromotion() {
        return this.isPromoted;
    }

}
