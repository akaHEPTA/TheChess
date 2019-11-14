import java.util.ArrayList;

public class Pawn extends Piece {

  // = 0 -> false
  // > 0 -> true and the count that two move occurred.
  private int isFirstMoveTwo = 0;

  // Constructor
  public Pawn(String type, boolean isWhite, Position position) {
    super(type, isWhite, position);
  }

  // For Pawn
  protected boolean move(Position newPosition, Piece[][] board, int currentCount) {
    boolean result = false;

    // It should check super's validity (grid range) && Queen's validity
    if (isValidMove(newPosition, board, currentCount)) {
      this.position = newPosition;
      result = true;
    }
    return result;
  }

  // For Pawn
  protected boolean isValidMove(Position newPosition, Piece[][] board, int currentCounter) {
    boolean result = false;
    ArrayList<Position> validMoveList = getValidMoveList(board, currentCounter);
    for (Position position : validMoveList) if (position.equals(newPosition)) result = true;
    return result;
  }

  @Override
  protected boolean move(Position newPosition, Piece[][] board) {
    return true;
  }

  @Override
  public ArrayList<Position> getValidMoveList(Piece[][] board) {
    return new ArrayList<>();
  }

  public ArrayList<Position> getValidMoveList(Piece[][] board, int currentCounter) {
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
            if (board[i][colPosition] == null && board[i - 1][colPosition] == null)
              validPositions.add(new Position(i - 1, colPosition));

          // If opponent piece exists to diagonal direction.
          if (colPosition + 1 <= 7
              && board[i][colPosition + 1] != null
              && board[i][colPosition + 1].isWhite != isWhite)
            validPositions.add(new Position(i, colPosition + 1));
          if (colPosition - 1 >= 0
              && board[i][colPosition - 1] != null
              && board[i][colPosition - 1].isWhite != isWhite)
            validPositions.add(new Position(i, colPosition - 1));

          // Check if En Pssant: right piece
          if (colPosition < 7) {
            if (board[rowPosition][colPosition + 1] != null) {
              if (canEnPssant(board[rowPosition][colPosition + 1], currentCounter))
                validPositions.add(new Position(i, colPosition + 1));
            }
          }

          // Check if En Pssant: left piece
          if (colPosition > 0) {
            if (board[rowPosition][colPosition - 1] != null) {
              if (canEnPssant(board[rowPosition][colPosition - 1], currentCounter))
                validPositions.add(new Position(i, colPosition - 1));
            }
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
            if (board[i][colPosition] == null && board[i + 1][colPosition] == null)
              validPositions.add(new Position(i + 1, colPosition));

          // If opponent piece exists to diagonal direction.
          if (colPosition + 1 <= 7
              && board[i][colPosition + 1] != null
              && board[i][colPosition + 1].isWhite != isWhite)
            validPositions.add(new Position(i, colPosition + 1));
          if (colPosition - 1 >= 0
              && board[i][colPosition - 1] != null
              && board[i][colPosition - 1].isWhite != isWhite)
            validPositions.add(new Position(i, colPosition - 1));

          // right piece
          if (colPosition < 7) {
            if (board[rowPosition][colPosition + 1] != null) {
              if (canEnPssant(board[rowPosition][colPosition + 1], currentCounter))
                validPositions.add(new Position(i, colPosition + 1));
            }
          }

          // left piece
          if (colPosition > 0) {
            if (board[rowPosition][colPosition - 1] != null) {
              if (canEnPssant(board[rowPosition][colPosition - 1], currentCounter))
                validPositions.add(new Position(i, colPosition - 1));
            }
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

  private boolean canEnPssant(Piece p, int currentCounter) {
    if (!(p instanceof Pawn)) return false;
    Pawn pp = (Pawn) p;

    // Pawn didn't move two.
    if ((pp.isFirstMoveTwo == 0)) return false;

    return currentCounter - pp.isFirstMoveTwo == 1;
  }

  public void setIsFirstMoveTwo(int isFirstMoveTwo) {
    this.isFirstMoveTwo = isFirstMoveTwo;
  }

  @Override
  public String toString() {
    return null;
  }
}

/*
required to implement

- moves only one step forward
- captures only a front diagonal step
- gets promotion when it reached at the end (special rule)
- En passant capture (special rule)

 */
