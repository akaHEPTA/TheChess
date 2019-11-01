import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(String type, boolean isWhite, Position position) {
        super(type, isWhite, position);
    }

    @Override
    protected boolean move(Position newPosition) {
        boolean result = false;

        // It should check super's validity (grid range) && Queen's validity
        if (super.isValidMove(newPosition) && isValidMove(newPosition)) {
            this.position = newPosition;
            result = true;
        }

        return result;
    }

    @Override
    protected boolean isValidMove(Position newPosition) {
        boolean result = false;

        /* QUEEN'S MOVING RANGE CHECK CODE
        * if valid -> true
        * if invalid -> false;
        *
        * */

        return result;
    }

    @Override
    public ArrayList<Position> getValidMoveList() {
        return null;
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