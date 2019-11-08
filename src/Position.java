import java.util.Objects;

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

    @Override
    public String toString() {
        return Character.toString(97 + col) + (8 - row);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }

}
