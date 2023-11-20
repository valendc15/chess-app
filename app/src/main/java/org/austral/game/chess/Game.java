package org.austral.game.chess;

import org.austral.game.commons.*;

public class Game implements org.austral.game.commons.Game {
    private Board board;
    public Player whitePlayer;
    public Player blackPlayer;

    public WinningRules[] winingRules;

    public MovementManager movementManager;

    private TurnManager turnManager;
    private boolean isOver;

    public Game(Board board, Piece[] pieces, Validator movementRules, WinningRules[] winingRules, Color firstPlayerColor){
        whitePlayer = new Player(Color.WHITE);
        blackPlayer = new Player(Color.BLACK);
        isOver = false;
        this.movementManager=new MovementManager(movementRules);
        this.board=initializeBoardWithPieces(pieces, board);
        this.winingRules=winingRules;
        if(firstPlayerColor==Color.BLACK){
            turnManager=new TurnManager(blackPlayer);
        }
        else{
            turnManager=new TurnManager(whitePlayer);
        }
    }

    public Player getCurrentPlayer(){
        return turnManager.getCurrentPlayer();
    }

    public Game (Board board, Player whitePlayer, Player blackPlayer, Validator movementRules, WinningRules[] winingRules, TurnManager turnManager){
        this.board = board;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        isOver = false;
        this.movementManager=new MovementManager(movementRules);
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

    public Game setBoard(Board board) {
        return new Game(board, whitePlayer, blackPlayer, movementManager.getMovementRules(), winingRules, turnManager);
    }

    public Game whiteMakeMove(Movement movement){
        return new Game(movementManager.manageMovement(whitePlayer, board, movement), whitePlayer, blackPlayer, movementManager.getMovementRules(), winingRules,new TurnManager(blackPlayer));
    }

    public Game blackMakeMove(Movement movement){
        return new Game(movementManager.manageMovement(blackPlayer, board, movement), whitePlayer, blackPlayer, movementManager.getMovementRules(), winingRules, new TurnManager(whitePlayer));
    }

    public boolean isOver(){
        for (WinningRules rule : winingRules) {
            if (rule.validateRule(board, whitePlayer) || rule.validateRule(board, blackPlayer)){
                isOver = true;
                return true;
            }
        }
        return false;
    }

    public Board callMovementManager(Movement movement, Player player){
        return movementManager.manageMovement(player, board, movement);
    }

    public Game move(Movement movement){
        if(turnManager.getCurrentPlayer().getColor()==Color.BLACK){
            return new Game(callMovementManager(movement, turnManager.getCurrentPlayer()), whitePlayer, blackPlayer, movementManager.getMovementRules(), winingRules, new TurnManager(whitePlayer));
        }
        else{
            return new Game(callMovementManager(movement, turnManager.getCurrentPlayer()), whitePlayer, blackPlayer, movementManager.getMovementRules(), winingRules, new TurnManager(blackPlayer));
        }
    }


    public TurnManager getTurnManager() {
        return turnManager;
    }
}
