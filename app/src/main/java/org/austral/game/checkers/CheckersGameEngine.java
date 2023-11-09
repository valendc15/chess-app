package org.austral.game.checkers;
import edu.austral.dissis.chess.gui.*;
import org.austral.game.checkers.CheckersGame;
import org.austral.game.checkers.CheckersGameCreator;
import org.austral.game.checkers.CheckersValidMovementsCreator;
import org.austral.game.commons.Adapter;
import org.austral.game.commons.PromotionManager;

import java.util.Stack;

public class CheckersGameEngine implements GameEngine {
    private final CheckersGame game;
    private Stack<CheckersGame> previousGames = new Stack<>();
    public CheckersGameEngine(){
        this.game= CheckersGameCreator.createGame();
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
        currentGame=currentGame.setBoard(PromotionManager.applyPromotion(currentGame.getBoard(), CheckersValidMovementsCreator.createQueenPieceMovements()));
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