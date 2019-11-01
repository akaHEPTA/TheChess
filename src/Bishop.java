import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(String type, boolean isWhite, Position position) {
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

}

/*
required to implement

- move diagonally

 */