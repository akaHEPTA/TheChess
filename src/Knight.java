public class Knight extends Piece {

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

- moves like L
- can pass through the pieces in L (jump)

 */