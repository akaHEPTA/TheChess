import java.util.ArrayList;

public class Pawn extends Piece {

    private boolean isFirstMoveTwo;

    // Constructor
    public Pawn(String type, boolean isWhite, Position position) {
        super(type, isWhite, position);
        this.isFirstMoveTwo = false;
    }

    @Override
    protected boolean move(Position newPosition, Piece[][] board) {
        boolean result = false;

        // It should check super's validity (grid range) && Pawn's validity
        if (super.isValidMove(newPosition, board) && isValidMove(newPosition, board)) {

            if (isWhite) {
                if (this.position.getRow() == 6 && newPosition.getRow() == 4) isFirstMoveTwo = true;
            } else {
                if (this.position.getRow() == 1 && newPosition.getRow() == 3) isFirstMoveTwo = true;
            }

            this.position = newPosition;

            result = true;
        }
        return result;
    }

    @Override
    public ArrayList<Position> getValidMoveList(Piece[][] board) {
        ArrayList<Position> validPositions = new ArrayList<>();
        int rowPosition = this.position.getRow(), colPosition = this.position.getCol();

        // find out i that abs(rowPositon - i) equals to 1. (0 <= i <= 7)
        // col, row is converted.
        //   0 <= row <= 7
        //   0 <= col <= 7

        if (isWhite) {
            for (int i = 0; i <= 7; i++) {
                if (rowPosition - i == 1) {

                    // forward 1 move
                    if (board[i][colPosition] == null) validPositions.add(new Position(i, colPosition));

                    // forward 2 moves
                    // If this is a first move, add more one position.
                    if (rowPosition == 6)
                        if (board[i - 1][colPosition] == null)
                            validPositions.add(new Position(i - 1, colPosition));

                    // If opponent piece exists to diagonal direction.
                    if (colPosition + 1 <= 7 && board[i][colPosition + 1] != null)
                        validPositions.add(new Position(i, colPosition + 1));
                    if (colPosition - 1 >= 0 && board[i][colPosition - 1] != null)
                        validPositions.add(new Position(i, colPosition - 1));

                    // If it is capable to en passant, add more one position
                    // ToDo check if just after Pawn moves two steps.
                    if (canEnPssant(rowPosition, colPosition, board)) {
                        if (board[rowPosition][colPosition + 1] instanceof Pawn)
                            validPositions.add(new Position(i, colPosition + 1));
                        if (board[rowPosition][colPosition - 1] instanceof Pawn)
                            validPositions.add(new Position(i, colPosition - 1));
                    }
                }
            }
        } else {
            for (int i = 0; i <= 7; i++) {
                if (rowPosition - i == -1) {

                    // forward 1 move
                    if (board[i][colPosition] == null) validPositions.add(new Position(i, colPosition));

                    // forward 2 moves
                    // If this is a first move, add more one position.
                    if (rowPosition == 1)
                        if (board[i + 1][colPosition] == null)
                            validPositions.add(new Position(i + 1, colPosition));

                    // If opponent piece exists to diagonal direction.
                    if (colPosition + 1 <= 7 && board[i][colPosition + 1] != null)
                        validPositions.add(new Position(i, colPosition + 1));
                    if (colPosition - 1 >= 0 && board[i][colPosition - 1] != null)
                        validPositions.add(new Position(i, colPosition - 1));

                    // If it is capable to en passant, add more one position
                    // ToDo check if just after Pawn moves two steps.
                    if (canEnPssant(rowPosition, colPosition, board)) {
                        if (board[rowPosition][colPosition + 1] instanceof Pawn)
                            validPositions.add(new Position(i, colPosition + 1));
                        if (board[rowPosition][colPosition - 1] instanceof Pawn)
                            validPositions.add(new Position(i, colPosition - 1));
                    }
                }
            }
        }
        return validPositions;
    }

    public boolean promote(Position newPosition, Piece[][] board) {
        if (isWhite) {
            return newPosition.getRow() == 0;
        } else {
            return newPosition.getRow() == 7;
        }
    }

    private boolean canEnPssant(int rowPosition, int colPosition, Piece[][] board) {

        Pawn rightPawn = null;
        Pawn leftPawn = null;
        // Not both ends
        if (colPosition <= 6 && colPosition >= 1) {
            if (board[rowPosition][colPosition + 1] == null
                    && board[rowPosition][colPosition - 1] == null) return false;
            if (!(board[rowPosition][colPosition + 1] instanceof Pawn)
                    && !(board[rowPosition][colPosition - 1] instanceof Pawn)) return false;
            rightPawn = (Pawn) board[rowPosition][colPosition + 1];
            leftPawn = (Pawn) board[rowPosition][colPosition - 1];
        }
        // Left end
        if (colPosition == 0) {
            if (board[rowPosition][colPosition + 1] == null) return false;
            if (!(board[rowPosition][colPosition + 1] instanceof Pawn)) return false;
            rightPawn = (Pawn) board[rowPosition][colPosition + 1];
        }

        // Right end
        if (colPosition == 7) {
            if (board[rowPosition][colPosition - 1] == null) return false;
            if (!(board[rowPosition][colPosition - 1] instanceof Pawn)) return false;
            rightPawn = (Pawn) board[rowPosition][colPosition - 1];
        }

        if (isWhite) {
            return (rowPosition == 3
                    && (rightPawn != null && rightPawn.isFirstMoveTwo
                    || leftPawn != null && leftPawn.isFirstMoveTwo));
        } else {
            return rowPosition == 4
                    && (rightPawn != null && rightPawn.isFirstMoveTwo
                    || leftPawn != null && leftPawn.isFirstMoveTwo);
        }
    }

    @Override
    public String toString() {
        return null;
    }

    protected void promotion() {
    }
}

/*
required to implement

- moves only one step forward
- captures only a front diagonal step
- gets promotion when it reached at the end (special rule)
- En passant capture (special rule)

 */
