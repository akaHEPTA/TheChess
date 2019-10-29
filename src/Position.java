public class Position {
    // Fields
    private int row;
    private int col;

    // Constructors
    private Position() {
    }

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // Accessor
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    // Mutator
    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
