import java.util.ArrayList;

public class Rook extends Piece {

    public Rook(String type, boolean isWhite, Position position) {
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

- move horizontally or vertically
- Castling (Special rule)

 */