package org.austral.game.commons;

import org.austral.game.commons.Board;
import org.austral.game.commons.Movement;
import org.austral.game.commons.Player;

public interface Validator {

    public boolean validate(Movement movement, Board board, Player player);
}
