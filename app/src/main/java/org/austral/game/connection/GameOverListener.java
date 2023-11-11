package org.austral.game.connection;

import edu.austral.dissis.chess.gui.GameOver;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;

public class GameOverListener implements MessageListener<GameOver> {

        private ClientController clientController;

        public GameOverListener(ClientController clientController) {
            this.clientController = clientController;
        }

        public void handleMessage(Message<GameOver> message) {
            clientController.handleGameOver(message.getPayload());
        }
}
