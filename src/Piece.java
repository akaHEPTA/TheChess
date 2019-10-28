public abstract class Piece {
    // Fields
    private String name;
    private String type;
    protected Position position;

    // Methods

    /**
     * move the position of this piece
     * @param destination
     */
    protected abstract void move(Position destination);

    /**
     * check the destination point is available to move
     * @return
     */
    protected abstract boolean isValidMove(Position newPosition);

/*

methods list

1. move (actual move)
2. valid move checker
3.


 */





}
