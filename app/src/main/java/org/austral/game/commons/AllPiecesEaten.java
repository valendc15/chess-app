package org.austral.game.commons;

import org.austral.game.commons.Board;
import org.austral.game.commons.Color;
import org.austral.game.commons.Player;
import org.austral.game.commons.WinningRules;

public class AllPiecesEaten implements WinningRules {

    @Override
    public boolean validateRule(Board board, Player player) {
        Color opposingColor=player.getColor() == Color.WHITE ? Color.BLACK : Color.WHITE;
        boolean allPiecesEaten = board.getPieces(opposingColor).isEmpty();
        return allPiecesEaten;
    }
}
