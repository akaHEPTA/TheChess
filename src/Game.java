public class Game {
    // Fields
    private Piece[][] myBoard;
    private Display myDisplay;
    private InputCollector myInput;
    private boolean finish = true;
    private boolean isWhiteTurn = true;
    private boolean isTurnChanged = true;

    // Constructor
    public Game() {
        setObject();
        createPieces();
        System.out.println("[!] Game start\n");
    }

    /**
     * set the game objects before start
     */
    private void setObject() {
        myBoard = new Piece[8][8];
        myDisplay = new Display();
        myInput = new InputCollector();
    }

    /**
     * Create all the pieces on the board
     */
    private void createPieces() {
        myBoard[0][0] = new Rook("Rook", false, new Position(0, 0));
        myBoard[0][1] = new Knight("Knight", false, new Position(0, 1));
        myBoard[0][2] = new Bishop("Bishop", false, new Position(0, 2));
        myBoard[0][3] = new Queen("Queen", false, new Position(0, 3));
        myBoard[0][4] = new King("King", false, new Position(0, 4));
        myBoard[0][5] = new Bishop("Bishop", false, new Position(0, 5));
        myBoard[0][6] = new Knight("Knight", false, new Position(0, 6));
        myBoard[0][7] = new Rook("Rook", false, new Position(0, 7));

        myBoard[7][0] = new Rook("Rook", true, new Position(7, 0));
        myBoard[7][1] = new Knight("Knight", true, new Position(7, 1));
        myBoard[7][2] = new Bishop("Bishop", true, new Position(7, 2));
        myBoard[7][3] = new Queen("Queen", true, new Position(7, 3));
        myBoard[7][4] = new King("King", true, new Position(7, 4));
        myBoard[7][5] = new Bishop("Bishop", true, new Position(7, 5));
        myBoard[7][6] = new Knight("Knight", true, new Position(7, 6));
        myBoard[7][7] = new Rook("Rook", true, new Position(7, 7));

        for (int i = 0; i < 8; i++) {
            myBoard[1][i] = new Pawn("Pawn", false, new Position(1, i));
            myBoard[6][i] = new Pawn("Pawn", true, new Position(6, i));
        }
    }

    /**
     * Main method that this program runs
     */
    public void run() {
        while (finish) {
            if (isTurnChanged) {
                myDisplay.printBoard(myBoard);
                isTurnChanged = false;
            }
            myDisplay.printTurn(isWhiteTurn);

            String input = myInput.getLine().toLowerCase();
            myDisplay.printNewLine();
            switch (input) {
                case "help":
                    switchHelp();
                    break;
                case "board":
                    switchBoard();
                    break;
                case "resign":
                    switchResign();
                    break;
                case "move":
                    switchMove();
                    break;
                case "square":
                    switchSquare();
                    break;
                default:
                    switchUCI(input);
            }
        }
    }

    private void switchHelp() {
        myDisplay.printHelp();
    }

    private void switchBoard() {
        myDisplay.printBoard(myBoard);
    }

    private void switchResign() {
        /* game over command - not finished */
        myDisplay.printResign();
    }

    private void switchMove() {
        /* show valid move list - not finished */
        myDisplay.printMove();
    }

    private void switchSquare() {
        /* show valid move list of specific square - not finished */
        myDisplay.printSquare();
    }

    private void switchUCI(String input) {
        /* not finished

        1-1. if it's correct UCI command
            -> interpret UCI command to Piece & Position
        1-2. if it's incorrect UCI command
            -> show the message and just finish this cycle
            -> "BUT DO NOT TRIGGER TO CHANGE PLAYER"
            -> this player will input another command

        2. (after 1-1) call move method of the Piece

        3-1. if it's valid position -> move & trigger to next turn
        3-2. if it's invalid position -> do
        */
        while (true) {


            break;
        }
        myDisplay.printUCI();
    }

    /**
     * interpret UCI command to use
     */
    private void convertUCI() {

    }

    /*
    public static void main(String[] args) {
        Display myDisplay = new Display();
        boolean isWhiteTurn = true;
        myDisplay.printTurn(isWhiteTurn);
        isWhiteTurn = false;
        myDisplay.printTurn(isWhiteTurn);
    }
    */
}
/*
00 01 02 03 04 05 06 07  0  8
10 11 12 13 14 15 16 17  1  7
20 21 22 23 24 25 26 27  2  6
30 31 32 33 34 35 36 37  3  5
40 41 42 43 44 45 46 47  4  4
50 51 52 53 54 55 56 57  5  3
60 61 62 63 64 65 66 67  6  2
70 71 72 73 74 75 76 77  7  1

0  1  2  3  4  5  6  7
a  b  c  d  e  f  g  h
*/