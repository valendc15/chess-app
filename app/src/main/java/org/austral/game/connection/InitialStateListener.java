package org.austral.game.connection;

import edu.austral.dissis.chess.gui.InitialState;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;

public class InitialStateListener implements MessageListener<InitialState> {

        private ClientController clientController;

        public InitialStateListener(ClientController clientController) {
            this.clientController = clientController;
        }


        public void handleMessage(Message<InitialState> message) {
            clientController.handleInitialState(message.getPayload());
        }
}
