package org.austral.game.chess;

import org.austral.game.commons.*;

public class ThreeTimesCheck implements WinningRules {

    int count = 0;

    public boolean validateRule(Board board, Player player) {
        if (CheckValidator.isCheck(board, player.getColor())) {
            count++;
        }
        return count == 3;
    }
}
