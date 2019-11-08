import java.util.ArrayList;

public class Knight extends Piece {

    public Knight(String type, boolean isWhite, Position position) {
        super(type, isWhite, position);
    }

    @Override
    protected boolean move(Position newPosition, Piece[][] board) {
        return false;
    }

    @Override
    protected boolean isValidMove(Position newPosition, Piece[][] board) {
        return false;
    }

    @Override
    public ArrayList<Position> getValidMoveList(Piece[][] Board) {
        return null;
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