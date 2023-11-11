package org.austral.game.connection;

import edu.austral.dissis.chess.gui.GameEventListener;
import edu.austral.ingsis.clientserver.ClientConnectionListener;

public class ClientListenerImp implements GameEventListener {

    private ClientController clientController;

    public ClientListenerImp(ClientController clientController) {
        this.clientController = clientController;
    }

    @Override
    public void handleMove(edu.austral.dissis.chess.gui.Move move) {
        clientController.handleMove(move);
    }

}
