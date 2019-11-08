import java.io.*;

public class Game {
    // Fields
    private Piece[][] myBoard;
    private Display myDisplay;
    private InputCollector myInput;
    private MyFileWrite myFW;
    private boolean finish = false;
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
        try {
            myFW = new MyFileWrite();
        } catch (IOException e) {
            myDisplay.printFileWriteError(e);
        }
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

//        for (int i = 0; i < 8; i++) {
//            myBoard[1][i] = new Pawn("Pawn", false, new Position(1, i));
//            myBoard[6][i] = new Pawn("Pawn", true, new Position(6, i));
//        }
    }

    /**
     * Main method that this program runs
     */
    public void run() {
        while (!finish) {
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
                case "square":
                    switchSquare();
                    break;
                default:
                    switchUCI(input);
            }
        }
    }

    /**
     * show the 'help' list
     */
    private void switchHelp() {
        myDisplay.printHelp();
    }

    /**
     * show the 'board' again
     */
    private void switchBoard() {
        myDisplay.printBoard(myBoard);
    }

    /**
     * finish the game and score the players
     * show the result of this game
     */
    private void switchResign() {
        /* not finished

        Game over command

        1. check the turn and make score data
        2. finish = true;
        ...
        add more

        */
        finish = true;
        myDisplay.printResign(isWhiteTurn);
    }

    /**
     * MOVED TO SUB-CASE OF DEFAULT CASE
     */
    private void switchMove() {
        /* show valid move list - not finished */
        //myDisplay.printMove();
    }

    /**
     * List all of the valid moves in the square
     */
    private void switchSquare() {
        /* show valid move list of specific square - not finished */
        myDisplay.printSquare();
    }

    /**
     * If the input is not help/board/resign/square, this block will be executed
     *
     * @param input is command of player (String)
     */
    private void switchUCI(String input) {
        /* not finished

        1-1. If it's correct UCI command
            -> Interpret UCI command to Piece & Position

        1-2. If it's incorrect UCI command
            -> Show the message and just finish this cycle
            -> "BUT DO NOT TRIGGER TO CHANGE PLAYER"
            -> This same player will input command again

        2. (after 1-1) Call move method of the selected Piece
            -> move() method return true after the moving if the position is available
            -> If the position is unavailable, it doesn't move and just return false

        3-1. If it's moved (correct Position case)
            -> Trigger to next turn
            -> isWhiteTurn = !isWhiteTurn
            -> isTurnChanged = True

            ------> Extra challenge feature:
            If you want to save the play log as a text file, make another class that controls file write
            In this step(correctly moved) you can take a String to save...

        3-2. If it doesn't moved (incorrect Position case)
            -> Show the message and just finish this cycle
            -> "BUT DO NOT TRIGGER TO CHANGE PLAYER"
            -> This same player will input command again

        ----------

        check length >
        if it's 2 -> show the whole possible move list
                4 -> try move
                5 -> move + special order (promotion)

        else: throw away
        */

        // remove all whitespaces from the input
        input = input.replace(" ", "");

        switch (input.length()) {
            case 2:
                /*
                1. check validity (piece) "CHECK THE TURN OF COLOR"
                2-1. true -> call the piece's getValidMoveList
                2-2. false -> show message & skip the other steps
                 */
                Position piece = convertUCI(input);
                myDisplay.printMove(myBoard[piece.getRow()][piece.getCol()].getValidMoveList(myBoard));
                break;
            case 4:
                /*
                1. check validity (piece + new position) "CHECK THE TURN OF COLOR"
                2-1: true -> call the piece's move method
                2-2: false -> show message & skip the other steps
                 */
                Position piece2 = convertUCI(input.substring(0, 2));
                Position newPosition = convertUCI(input.substring(2, 4));
                boolean moveOk = myBoard[piece2.getRow()][piece2.getCol()].move(newPosition, myBoard);
                myBoard[newPosition.getRow()][newPosition.getCol()] = myBoard[piece2.getRow()][piece2.getCol()];
                myBoard[piece2.getRow()][piece2.getCol()] = null;
                myDisplay.printUCI(moveOk);

                break;
            case 5:
                /*
                1. check validity (piece + new position + promotion)
                2-1: true -> call the piece's move method and promotion
                */
                break;
            default:
                /* show invalid command message and finish the cycle to get it again */
                break;
        }

//        /* test code */
//        if (input.equals("d1d5")) {
//            if (myBoard[7][3].move(new Position(3, 3), myBoard)) {
//                myBoard[3][3] = myBoard[7][3];
//                myBoard[7][3] = null;
//            }
//            System.out.println(myBoard[7][3].getValidMoveList(myBoard));
//        }
//
//        System.out.println("OK");

    }

    /**
     * interpret UCI command to use
     */
    private Position convertUCI(String input) {
        /* not finished yet - try / catch */
        int col = input.charAt(0) - 97;
        int row = 8 - Integer.parseInt(
                input.substring(1, 2));

        return new Position(row, col);
    }


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