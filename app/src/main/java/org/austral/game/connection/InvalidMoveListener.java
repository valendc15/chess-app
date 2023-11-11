package org.austral.game.connection;

import edu.austral.dissis.chess.gui.InvalidMove;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;

public class InvalidMoveListener implements MessageListener<InvalidMove> {

    private ClientController clientController;

    public InvalidMoveListener(ClientController clientController) {
        this.clientController = clientController;
    }

    @Override
    public void handleMessage(Message<InvalidMove> message) {
        clientController.handleInvalidMove(message.getPayload());
    }
}
