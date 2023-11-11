package org.austral.game.connection;

import edu.austral.ingsis.clientserver.ServerConnectionListener;

public class ServerConnectionListenerImp implements ServerConnectionListener {

    ServerController serverController;

    public ServerConnectionListenerImp(ServerController serverController) {
        this.serverController = serverController;
    }
    @Override
    public void handleClientConnection(String clientId) {
        serverController.handleNewGame();
    }

    @Override
    public void handleClientConnectionClosed(String clientId) {

    }
}
