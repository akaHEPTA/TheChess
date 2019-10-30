public class Pawn extends Piece {

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

- moves only one step forward
- captures only a front diagonal step
- gets promotion when it reached at the end (special rule)
- En passant capture (special rule)

 */