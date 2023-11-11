package org.austral.game.connection;

import com.fasterxml.jackson.core.type.TypeReference;
import edu.austral.dissis.chess.gui.*;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.Server;
import edu.austral.ingsis.clientserver.ServerBuilder;

public class ServerController implements ConnectionController {
    private final Server server;
    private final GameEngine gameEngine;
    private final GameView gameView;

    public ServerController(GameEngine gameEngine, GameView gameView, ServerBuilder serverBuilder) {
        this.gameEngine = gameEngine;
        this.gameView = gameView;
        addStatesToGameView(new GameEventListenerImp(this));
        this.server = buildServer(serverBuilder);
        server.start();
    }

    public void start() {
        server.start();
    }

    public void stop() {
        server.stop();
    }

    public void handleMove(Move move) {
        MoveResult moveResult = gameEngine.applyMove(move);
        gameView.handleMoveResult(moveResult);
        broadcast(moveResult);
    }

    public void broadcast(MoveResult moveResult) {
        if (moveResult instanceof NewGameState) {
            server.broadcast(new Message<>("newGameState", moveResult));
        } else if (moveResult instanceof GameOver) {
            server.broadcast(new Message<>("gameOver", moveResult));
        } else if (moveResult instanceof InvalidMove) {
            server.broadcast(new Message<>("invalidMove", moveResult));
        }
    }

    public void handleNewGame() {
        InitialState initialState = gameEngine.init();
        gameView.handleInitialState(initialState);
        server.broadcast(new Message<>("initialState", initialState));
    }

    private Server buildServer(ServerBuilder builder) {
        return builder
                .withPort(8080)
                .withConnectionListener(new ServerConnectionListenerImp(this))
                .addMessageListener("move", new TypeReference<>() {
                }, new ServerMessageListener(new GameEventListenerImp(this)))
                .build();
    }

    private void addStatesToGameView(GameEventListenerImp gameListener) {
        gameView.addListener(gameListener);
        gameView.handleInitialState(gameEngine.init());
    }
}
