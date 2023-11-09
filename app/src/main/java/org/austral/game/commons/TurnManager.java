package org.austral.game.commons;

import org.austral.game.commons.Player;

public class TurnManager {
    private Player currentPlayer;

    public TurnManager(Player player) {
        this.currentPlayer = player;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }


}
