package org.austral.game.commons;

import edu.austral.dissis.chess.gui.*;

import java.util.Stack;

public class CustomGameEngine implements GameEngine {
    private Game game;
    private Stack<Game> previousGames = new Stack<>();
    public CustomGameEngine(GameCreator gameCreator){
        this.game= gameCreator.createGame();
        previousGames.push(game);
    }

    public MoveResult applyMove(Move movement){
        Game previousGame=previousGames.peek();
        Game currentGame=previousGame.move(Adapter.convertMove(movement));
        if(previousGame.getBoard()==currentGame.getBoard()){
            return new InvalidMove("Invalid Movement");
        }
        if(currentGame.isOver()){
            return new GameOver(Adapter.convertPlayerColor(previousGame.getCurrentPlayer().getColor()));
        }
        previousGames.push(currentGame);
        return new NewGameState(Adapter.getAllPieces(currentGame.getBoard()),Adapter.convertPlayerColor(currentGame.getCurrentPlayer().getColor()));
    }

    public InitialState init() {
        return createInitialState();
    }

    private InitialState createInitialState() {
        return new InitialState(Adapter.getBoardSize(previousGames.peek().getBoard()),
                Adapter.getAllPieces(previousGames.peek().getBoard()),
                Adapter.getCurrentTurn(previousGames.peek().getTurnManager()));
    }
}
