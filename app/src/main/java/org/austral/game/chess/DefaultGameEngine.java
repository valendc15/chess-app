package org.austral.game.chess;

import edu.austral.dissis.chess.gui.*;
import org.austral.game.commons.Adapter;

import java.util.Stack;

public class DefaultGameEngine implements GameEngine {
    private final Game game;
    private Stack<Game> previousGames = new Stack<>();
    public DefaultGameEngine(){
        this.game= new ChessGameFactory().createGame();
        previousGames.push(game);
    }

    public MoveResult applyMove(Move movement){
        Game previousGame=previousGames.peek();
        Game currentGame=previousGame.move(Adapter.convertMove(movement));
        if(previousGame.getBoard()==currentGame.getBoard()){
            return new InvalidMove("Invalid org.austral.game.commons.Movement");
        }
        if(currentGame.isOver()){
            return new GameOver(Adapter.convertPlayerColor(previousGame.getCurrentPlayer().getColor()));
        }
        //currentGame=currentGame.setBoard(PromotionManager.applyPromotion(currentGame.getBoard(), PieceValidMovementsCreator.createQueenMovements()));
        previousGames.push(currentGame);
        return new NewGameState(Adapter.getAllPieces(currentGame.getBoard()),Adapter.convertPlayerColor(currentGame.getCurrentPlayer().getColor()));
    }

    public InitialState init() {
        return createInitialState();
    }

    private InitialState createInitialState() {
        return new InitialState(Adapter.getBoardSize(game.getBoard()),
                Adapter.getAllPieces(game.getBoard()),
                Adapter.getCurrentTurn(game.getTurnManager()));
    }


}