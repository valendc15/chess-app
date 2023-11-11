package org.austral.game.connection;

import edu.austral.dissis.chess.gui.GameEventListener;
import edu.austral.dissis.chess.gui.Move;

public class GameEventListenerImp implements GameEventListener {

    private ConnectionController connectionController;

    public GameEventListenerImp(ConnectionController connectionController) {
        this.connectionController = connectionController;
    }
    @Override
    public void handleMove(Move move) {
        connectionController.handleMove(move);
    }

}

