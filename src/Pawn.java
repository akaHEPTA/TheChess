import java.util.ArrayList;

public class Pawn extends Piece {
    // Constructor
    public Pawn(String type, boolean isWhite, Position position) {
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

    protected void promotion(){

    }
}

/*
required to implement

- moves only one step forward
- captures only a front diagonal step
- gets promotion when it reached at the end (special rule)
- En passant capture (special rule)

 */