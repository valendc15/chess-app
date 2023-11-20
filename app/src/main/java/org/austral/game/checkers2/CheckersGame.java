package org.austral.game.checkers2;

import org.austral.game.commons.*;

public class CheckersGame implements Game {

    private Board board;
    public Player whitePlayer;
    public Player blackPlayer;

    public WinningRules[] winingRules;

    public ChekersMovementManager movementManager;

    private TurnManager turnManager;
    private boolean isOver;

    public CheckersGame(Board board, Piece[] pieces, Validator movementRules, WinningRules[] winingRules){
        whitePlayer = new Player(Color.WHITE);
        blackPlayer = new Player(Color.BLACK);
        isOver = false;
        this.movementManager=new ChekersMovementManager(movementRules, false);
        this.board=initializeBoardWithPieces(pieces, board);
        this.winingRules=winingRules;
        this.turnManager=new TurnManager(whitePlayer);
    }

    public Player getCurrentPlayer(){
        return turnManager.getCurrentPlayer();
    }

    public CheckersGame(Board board, Player whitePlayer, Player blackPlayer, Validator movementRules, WinningRules[] winingRules, TurnManager turnManager){
        this.board = board;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        isOver = false;
        this.movementManager=new ChekersMovementManager(movementRules, false);
        this.winingRules=winingRules;
        this.turnManager=turnManager;
    }

    private Board initializeBoardWithPieces(Piece[] pieces, Board board) {
        Board newBoard = board;
        for (Piece piece : pieces) {
            newBoard = newBoard.placePiece(piece, piece.getOrigin()); // Place the piece on the new board
        }
        return newBoard;
    }


    public Board getBoard() {
        return board;
    }

    public CheckersGame setBoard(Board board) {
        return new CheckersGame(board, whitePlayer, blackPlayer, movementManager.getMovementRules(), winingRules, turnManager);
    }
    public boolean isOver(){
        for (WinningRules rule : winingRules) {
            if (appliesToAllPlayers(rule)) {
                isOver = true;
                return true;
            }
        }
        return false;
    }

    private boolean appliesToAllPlayers(WinningRules rule){
        return rule.validateRule(board, whitePlayer) || rule.validateRule(board, blackPlayer);
    }


    private boolean isWhiteAndCanEatAgain(Movement movement) {
        CanEatAgainValidator canEatAgainValidator = new CanEatAgainValidator(movementManager.getMovementRules());
        return isWhiteTurn() && canEatAgainValidator.validate(movement, movementManager.manageMovement(whitePlayer, board, movement), whitePlayer);
    }

    private boolean isBlackAndCanEatAgain(Movement movement) {
        CanEatAgainValidator canEatAgainValidator = new CanEatAgainValidator(movementManager.getMovementRules());
        return isBlackTurn() && canEatAgainValidator.validate(movement, movementManager.manageMovement(blackPlayer, board, movement), blackPlayer);
    }

    private boolean isBlackTurn() {
        return turnManager.getCurrentPlayer().getColor() == Color.BLACK;
    }

    private boolean isWhiteTurn() {
        return turnManager.getCurrentPlayer().getColor() == Color.WHITE;
    }
    public CheckersGame move(Movement movement) {
        CanEatAgainValidator canEatAgainValidator = new CanEatAgainValidator(movementManager.getMovementRules());
        movementManager = movementManager.isChain() ? movementManager.withChain() : new ChekersMovementManager(movementManager.getMovementRules(), false);
        if (isWhiteAndCanEatAgain(movement)) {
            return new CheckersGame(movementManager.manageMovement(whitePlayer, board, movement), whitePlayer, blackPlayer, movementManager.getMovementRules(), winingRules, new TurnManager(whitePlayer));
        } else if (isBlackAndCanEatAgain(movement)) {
            return new CheckersGame(movementManager.manageMovement(blackPlayer, board, movement), whitePlayer, blackPlayer, movementManager.getMovementRules(), winingRules, new TurnManager(blackPlayer));
        } else if (isWhiteTurn()) {
            return new CheckersGame(movementManager.manageMovement(whitePlayer, board, movement), whitePlayer, blackPlayer, movementManager.getMovementRules(), winingRules, new TurnManager(blackPlayer));
        } else {
            return new CheckersGame(movementManager.manageMovement(blackPlayer, board, movement), whitePlayer, blackPlayer, movementManager.getMovementRules(), winingRules, new TurnManager(whitePlayer));
        }
    }

    public TurnManager getTurnManager() {
        return turnManager;
    }
}
