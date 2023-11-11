package org.austral.game.connection;

import edu.austral.dissis.chess.gui.NewGameState;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;

public class NewStateListener implements MessageListener<NewGameState> {

        private ClientController clientController;

        public NewStateListener(ClientController connectionController) {
            this.clientController = connectionController;
        }

        public void handleMessage(Message<NewGameState> message) {
            clientController.handleNewGameState(message.getPayload());
        }
}
