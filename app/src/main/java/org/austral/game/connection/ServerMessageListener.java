package org.austral.game.connection;

import edu.austral.dissis.chess.gui.Move;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import org.jetbrains.annotations.NotNull;

public class ServerMessageListener implements MessageListener<Move> {

    private GameEventListenerImp gameEventListenerImp;

    public ServerMessageListener(GameEventListenerImp gameEventListenerImp) {
        this.gameEventListenerImp = gameEventListenerImp;
    }

    @Override
    public void handleMessage(@NotNull Message<Move> message) {
        gameEventListenerImp.handleMove(message.getPayload());
    }
}
