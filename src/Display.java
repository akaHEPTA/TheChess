public class Display {
    // Constants
    /**
     * first index : 0 for white / 1 for black
     * second index : 0 for pawn / 1 for kight / 2 for bishop / 3 for rook / 4 for queen / 5 for king
     */
    private static final char[][] SIMBOLS = {{'♙', '♘', '♗', '♖', '♕', '♔'}, {'♟', '♞', '♝', '♜', '♛', '♚'}};

    // Constructor
    public Display() {
        System.out.println("[!] Display module online");
    }

    /**
     * Convert and print the board data to character that can be displayed on console.
     *
     * @param board is Piece bi-dimension array that contains piece objects.
     */
    public void printBoard(Piece[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != null && board[i][j].isWhite) {
                    switch (board[i][j].type) {
                        case "Pawn":
                            System.out.print(SIMBOLS[0][0] + "  ");
                            break;
                        case "Knight":
                            System.out.print(SIMBOLS[0][1] + "  ");
                            break;
                        case "Bishop":
                            System.out.print(SIMBOLS[0][2] + "  ");
                            break;
                        case "Rook":
                            System.out.print(SIMBOLS[0][3] + "  ");
                            break;
                        case "Queen":
                            System.out.print(SIMBOLS[0][4] + "  ");
                            break;
                        case "King":
                            System.out.print(SIMBOLS[0][5] + "  ");
                            break;
                    }
                } else if (board[i][j] != null && !board[i][j].isWhite) {
                    switch (board[i][j].type) {
                        case "Pawn":
                            System.out.print(SIMBOLS[1][0] + "  ");
                            break;
                        case "Knight":
                            System.out.print(SIMBOLS[1][1] + "  ");
                            break;
                        case "Bishop":
                            System.out.print(SIMBOLS[1][2] + "  ");
                            break;
                        case "Rook":
                            System.out.print(SIMBOLS[1][3] + "  ");
                            break;
                        case "Queen":
                            System.out.print(SIMBOLS[1][4] + "  ");
                            break;
                        case "King":
                            System.out.print(SIMBOLS[1][5] + "  ");
                            break;
                    }
                } else {
                    System.out.print("∙  ");
                }
            }
            System.out.println(" " + (8 - i));
        }
        System.out.println("\na  b  c  d  e  f  g  h");
        System.out.println();
    }

    /**
     * The console scripts for noticing who's turn, and to get user input
     */
    public void printTurn(boolean isWhiteTurn) {
        System.out.println((isWhiteTurn ? "White" : "Black") + " to move");
        System.out.print("Enter UCI (type 'help' for help): ");
    }

    /**
     * The console scripts for the "help" command
     */
    public void printHelp() {
        System.out.println("* type 'help' for help");
        System.out.println("* type 'board' to see the board again");
        System.out.println("* type 'resign' to resign");
        System.out.println("* type 'moves' to list all possible moves");
        System.out.println("* type a square (e.g. b1, e2) to list possible moves for that square");
        System.out.println("* type UCI (e.g. b1c3, e7e8q) to make a move");
        System.out.println();
    }

    public void printResign() {
        /* not finished */
    }

    public void printMove() {
        /* not finished */
    }

    public void printSquare() {
        /* not finished */
    }

    public void printUCI() {
    }

    public void printNewLine(){
        System.out.println();
    }
}


/*
private static final char WHITE_KING = '♔';
private static final char WHITE_QUEEN = '♕';
private static final char WHITE_ROOK = '♖';
private static final char WHITE_BISHOP = '♗';
private static final char WHITE_KNIGHT = '♘';
private static final char WHITE_PAWN = '♙';
private static final char BLACK_KING = '♚';
private static final char BLACK_QUEEN = '♛';
private static final char BLACK_ROOK = '♜';
private static final char BLACK_BISHOP = '♝';
private static final char BLACK_KNIGHT = '♞';
private static final char BLACK_PAWN = '♟';
 */