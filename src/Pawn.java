import java.util.ArrayList;

public class Pawn extends Piece {
    // Constructor
    public Pawn(String type, boolean isWhite, Position position) {
        super(type, isWhite, position);
    }


    @Override
    protected boolean move(Position destination) {
        return false;
    }

    @Override
    protected boolean isValidMove(Position newPosition) {
        return false;
    }

    @Override
    public ArrayList<Position> getValidMoveList() {
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