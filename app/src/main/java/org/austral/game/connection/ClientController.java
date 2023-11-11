package org.austral.game.connection;

import com.fasterxml.jackson.core.type.TypeReference;
import edu.austral.dissis.chess.gui.GameView;
import edu.austral.dissis.chess.gui.*;
import edu.austral.ingsis.clientserver.Client;
import edu.austral.ingsis.clientserver.ClientBuilder;
import edu.austral.ingsis.clientserver.Message;
import javafx.application.Platform;

import java.net.InetSocketAddress;

public class ClientController implements ConnectionController {
        private Client client;
        private GameView gameView;

        private ClientBuilder builder;

        public ClientController( GameView gameView, ClientBuilder clientBuilder) {
            this.client = buildClient();
            this.gameView = gameView;
            this.builder=clientBuilder;
            start();
        }

        public void handleInitialState(InitialState initialState) {
            Platform.runLater(() -> gameView.handleInitialState(initialState));
        }

        public void handleNewGameState(NewGameState newGameState) {
            Platform.runLater(() -> gameView.handleMoveResult(newGameState));
        }

        public void handleGameOver(GameOver gameOver) {
            Platform.runLater(() -> gameView.handleMoveResult(gameOver));
        }

        public void handleInvalidMove(InvalidMove invalidMove) {
            Platform.runLater(() -> gameView.handleMoveResult(invalidMove));
        }

        public void handleMove(Move move) {
            client.send(new Message("move", move));
        }
        private Client buildClient() {
            InetSocketAddress socketAdress = new InetSocketAddress("localhost", 8080);
            return builder
                    .withAddress(socketAdress)
                    .addMessageListener("initialState", new TypeReference<>() {
                    }, new InitialStateListener(this))
                    .addMessageListener("newGameState", new TypeReference<>() {
                    }, new NewStateListener(this))
                    .addMessageListener("gameOver", new TypeReference<>() {
                    }, new GameOverListener(this))
                    .addMessageListener("invalidMove", new TypeReference<>() {
                    }, new InvalidMoveListener(this))
                    .build();

        }
        public void start() {
            gameView.addListener(new ClientListenerImp(this));
            client.connect();
        }
    }

