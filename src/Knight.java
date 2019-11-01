import java.util.ArrayList;

public class Knight extends Piece {

    public Knight(String type, boolean isWhite, Position position) {
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

- moves like L
- can pass through the pieces in L (jump)

 */