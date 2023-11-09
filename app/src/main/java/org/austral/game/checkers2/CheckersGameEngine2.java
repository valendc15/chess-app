package org.austral.game.checkers2;
import edu.austral.dissis.chess.gui.*;
import org.austral.game.commons.Adapter;
import org.austral.game.commons.PromotionManager;

import java.util.Stack;

public class CheckersGameEngine2 implements GameEngine {
    private final org.austral.game.checkers2.CheckersGame game;
    private Stack<CheckersGame> previousGames = new Stack<>();
    public CheckersGameEngine2(){
        this.game= new CheckersGameFactory().createGame();
        previousGames.push(game);
    }

    public MoveResult applyMove(Move movement){
        CheckersGame previousGame=previousGames.peek();
        CheckersGame currentGame=previousGame.move(Adapter.convertMove(movement));
        if(previousGame.getBoard()==currentGame.getBoard()){
            return new InvalidMove("Invalid org.austral.game.commons.Movement");
        }
        if(currentGame.isOver()){
            return new GameOver(Adapter.convertPlayerColor(previousGame.getCurrentPlayer().getColor()));
        }
        currentGame=currentGame.setBoard(PromotionManager.applyPromotion(currentGame.getBoard(), CheckersMovementFactory.createQueenPieceMovements()));
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