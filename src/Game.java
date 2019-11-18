import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {
    // Fields
    private Piece[][] myBoard;
    private Display myDisplay;
    private InputCollector myInput;
//    private MyFileWrite myFW;

    private boolean finish = false;
    private boolean isWhiteTurn = true;
    private boolean isTurnChanged = true;

    private int counter = 1;

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
//        try {
//            myFW = new MyFileWrite();
//        } catch (IOException e) {
//            myDisplay.printFileWriteError(e);
//        }
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
                case "moves":
                    switchMoves();
                    break;
                case "resign":
                    switchResign();
                    break;
                default:
                    switchUCI(input);
            }
            check();
            myDisplay.printNewLine();
        }
    }

    private boolean check() {
        Piece white = null;
        Piece black = null;

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (myBoard[i][j] instanceof King) {
                    if (myBoard[i][j].isWhite) white = myBoard[i][j];
                    if (!myBoard[i][j].isWhite) black = myBoard[i][j];
                }

        if (white == null || black == null) {
            finish = true;
//            myFW.endRecord();
            myDisplay.printGameEnd(isWhiteTurn);
        } else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Piece temp = myBoard[i][j];
                    if (temp != null) {
                        if (temp.getValidMoveList(myBoard).contains(white.position)) {
                            if (white.getValidMoveList(myBoard).isEmpty()) {
                                finish = true;
//                                myFW.endRecord();
                                myDisplay.printCheckmate();
                            } else {
                                myDisplay.printCheck(myBoard[i][j]);
                            }
                        } else if (temp.getValidMoveList(myBoard).contains(black.position)) {
                            if (black.getValidMoveList(myBoard).isEmpty()) {
                                finish = true;
//                                myFW.endRecord();
                                myDisplay.printCheckmate();
                            } else myDisplay.printCheck(myBoard[i][j]);
                        }

                    }
                }
            }
        }
        return false;
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
     * show all the possible moves of team's pieces
     */
    private void switchMoves() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                //Piece piece
                if (myBoard[i][j] != null && myBoard[i][j].isWhite == isWhiteTurn) {
                    ArrayList<Position> moves = myBoard[i][j].getValidMoveList(myBoard);
                    if (!moves.isEmpty())
                        myDisplay.printMoves(myBoard[i][j], moves);
                }
            }
        myDisplay.printNewLine();
    }

    /**
     * finish the game and score the players show the result of this game
     */
    private void switchResign() {
//        myFW.endRecord();
        finish = true;
        myDisplay.printResign(isWhiteTurn);
    }

    /**
     * If the input is not help/board/resign/square, this block will be executed
     *
     * @param input is command of player (String)
     */
    private void switchUCI(String input) {
        // remove all whitespaces from the input
        input = input.replace(" ", "");
        switch (input.length()) {
            case 2:
                switchGetMoveList(input);
                break;
            case 4:
                switchMove(input);
                break;
            case 5:
                if (input.contains(",")) switchSquare(input);
                else switchPromotion(input);
                break;
            default:
                myDisplay.printPatternUnmatch();
        }
    }

    private void switchGetMoveList(String input) {
        if (patternMatch(input, 0)) {
            Position piece = convertUCI(input);
            int row = piece.getRow(), col = piece.getCol();
            if (isInRange(row, col)) { // board range check
                Piece temp = myBoard[row][col];
                if (temp != null) // null check
                    if (temp.isWhite == isWhiteTurn) // color check
                        myDisplay.printMoves(temp, myBoard[row][col].getValidMoveList(myBoard));
                    else myDisplay.printMovesFail(2);
                else myDisplay.printMovesFail(1);
            } else myDisplay.printMovesFail(0);
        } else myDisplay.printPatternUnmatch();
    }

    private boolean switchMove(String input) {
        if (patternMatch(input, 1)) {
            Position pos = convertUCI(input.substring(0, 2));
            Position newPos = convertUCI(input.substring(2, 4));
            Piece temp = myBoard[pos.getRow()][pos.getCol()];

            if (temp != null) { // null check
                if (temp.isWhite == isWhiteTurn) { // color check
                    if (temp instanceof Pawn && (newPos.getRow() == 0 || newPos.getRow() == 7)) {
                        if (!((Pawn) temp).getPromotion()) {
                            myDisplay.printPromotionRequired();
                            return false;
                        }
                    }
                    if (temp.move(newPos, myBoard, counter)) {
                        if (temp instanceof Pawn) {
                            if (isEnPassant(pos, newPos))
                                if (temp.isWhite) myBoard[newPos.getRow() + 1][newPos.getCol()] = null;
                                else myBoard[newPos.getRow() - 1][newPos.getCol()] = null;
                        } else if (temp instanceof King) {
                            if (isCastling(pos, newPos)) {
                                if (newPos.equals(new Position(0, 2))) {
                                    ((Rook) myBoard[0][0]).setPosition(new Position(0, 3));
                                    myBoard[0][3] = myBoard[0][0];
                                    myBoard[0][0] = null;
                                } else if (newPos.equals(new Position(0, 6))) {
                                    ((Rook) myBoard[0][0]).setPosition(new Position(0, 5));
                                    myBoard[0][5] = myBoard[0][7];
                                    myBoard[0][7] = null;
                                } else if (newPos.equals(new Position(7, 2))) {
                                    ((Rook) myBoard[7][0]).setPosition((new Position(7, 3)));
                                    myBoard[7][3] = myBoard[7][0];
                                    myBoard[7][0] = null;
                                } else if (newPos.equals(new Position(7, 6))) {
                                    ((Rook) myBoard[7][7]).setPosition(new Position(7, 5));
                                    myBoard[7][5] = myBoard[7][7];
                                    myBoard[7][7] = null;
                                }
                            }
                        }
                        myBoard[newPos.getRow()][newPos.getCol()] = myBoard[pos.getRow()][pos.getCol()];
                        myBoard[pos.getRow()][pos.getCol()] = null;
//                        myFW.recordMove(input);
                        myDisplay.printMove(pos, newPos);
                        trigger();
                        return true;
                    } else myDisplay.printMoveFail(0);
                } else myDisplay.printMoveFail(2); // team not matched
            } else myDisplay.printMoveFail(1); // piece is not exist (null)
        } else myDisplay.printPatternUnmatch();
        return false;
    }

    private boolean isEnPassant(Position oldPos, Position newPos) {
        int r = Math.abs(oldPos.getRow() - newPos.getRow()), c = Math.abs(oldPos.getCol() - newPos.getCol());
        return r == 1 && c == 1;
    }

    private boolean isCastling(Position oldPos, Position newPos) {
        return Math.abs(oldPos.getCol() - newPos.getCol()) > 1;
    }

    /**
     * List all of the valid moves in the square
     */
    private void switchSquare(String input) {
        if (patternMatch(input, 2)) {
            boolean printed = false;
            Position p1 = convertUCI(input.substring(0, 2)), p2 = convertUCI(input.substring(3, 5));
            int minRow = Math.min(p1.getRow(), p2.getRow()), maxRow = Math.max(p1.getRow(), p2.getRow()),
                    minCol = Math.min(p1.getCol(), p2.getCol()), maxCol = Math.max(p1.getCol(), p2.getCol());
            for (int i = maxRow; i >= minRow; i--)
                for (int j = minCol; j <= maxCol; j++) {
                    Piece p = myBoard[i][j];
                    if (p != null) // null check
                        if (p.isWhite == isWhiteTurn) { // color check
                            ArrayList<Position> moves = p.getValidMoveList(myBoard);
                            if (!moves.isEmpty())
                                myDisplay.printMoves(p, moves);
                            if (!printed) printed = true;
                        }
                }
            myDisplay.printNewLine();
        } else myDisplay.printPatternUnmatch();
    }

    private void switchPromotion(String input) {
        if (patternMatch(input, 3)) {
            Position pos = convertUCI(input.substring(0, 2)), newPos = convertUCI(input.substring(2, 4));
            Piece temp = myBoard[pos.getRow()][pos.getCol()];
            if (temp instanceof Pawn && ((Pawn) temp).setPromotion() && switchMove(input.substring(0, 4))) {
                myBoard[newPos.getRow()][newPos.getCol()] = createPromotedPiece(input.substring(4, 5), temp.getPosition());
            }
        } else myDisplay.printPatternUnmatch();
    }

    /**
     * ASCII code used (a = 97) String -> Position
     */
    private Position convertUCI(String input) {
        int row = 8 - Integer.parseInt(input.substring(1, 2)), col = input.charAt(0) - 97;
        return new Position(row, col);
    }

    private String convertUCI(Position position) {
        char row = (char) (8 - position.getRow()), col = (char) (position.getCol() + 97);
        return Character.toString(col) + row;
    }

    private Piece createPromotedPiece(String input, Position dest) {
        Piece promotedPiece;
        switch (input) {
            case "b":
                promotedPiece = new Bishop("Bishop", !isWhiteTurn, dest);
                break;
            case "k":
                promotedPiece = new Knight("Knight", !isWhiteTurn, dest);
                break;
            case "q":
                promotedPiece = new Queen("Queen", !isWhiteTurn, dest);
                break;
            case "r":
                promotedPiece = new Rook("Rook", !isWhiteTurn, dest);
                break;
            default:
                promotedPiece = null;
        }
        return promotedPiece;
    }

    private boolean patternMatch(String input, int code) {
        Pattern pattern;

        switch (code) {
            case 0:
                pattern = Pattern.compile("^[a-h][1-8]$");
                break;
            case 1:
                pattern = Pattern.compile("^[a-h][1-8][a-h][1-8]$");
                break;
            case 2:
                pattern = Pattern.compile("^[a-h][1-8],[a-h][1-8]$");
                break;
            case 3:
                pattern = Pattern.compile("^[a-h][27][a-h][18][bkqr]$");
                break;
            default:
                return false;
        }

        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    private void trigger() {
        setPawnData();
        isWhiteTurn = !isWhiteTurn;
        isTurnChanged = true;
        counter++;
    }

    private void setPawnData() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece temp = myBoard[i][j];
                if (temp instanceof Pawn)
                    ((Pawn) temp).setJumped(counter);
            }
        }
    }

    private boolean isInRange(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

}

