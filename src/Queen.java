public class Queen extends Piece {

    public Queen(String type, boolean color) {
        this.type = type;
        this.color = color;
        this.position = null; // this.START_POSITION[type];
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



}

/*
required to implement

- moves rook + bishop

 */