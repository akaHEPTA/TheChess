public class King extends Piece {
    @Override
    protected boolean move(Position destination) {
        return false;
    }

    @Override
    protected boolean isValidMove(Position newPosition) {
        return false;
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