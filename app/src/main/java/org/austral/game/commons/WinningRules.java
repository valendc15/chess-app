package org.austral.game.commons;

import org.austral.game.commons.Board;
import org.austral.game.commons.Player;

public interface WinningRules {
    public boolean validateRule(Board board, Player player);
}
