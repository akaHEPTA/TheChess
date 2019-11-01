import java.util.ArrayList;

public class King extends Piece {

    public King(String type, boolean isWhite, Position position) {
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

- moves a square
- cant move to a check-mate position
- if King has no space to move, game over
- king cant be captured
- castling (special rule)
 */