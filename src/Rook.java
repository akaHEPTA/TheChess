public class Rook extends Piece {

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

- move horizontally or vertically
- Castling (Special rule)

 */