package org.austral.game.checkers;

import org.austral.game.commons.*;

import java.util.List;

public class LastMovementWasEatValidator implements Validator{

    public boolean validate(Movement movement, Board board, Player player){
        Color opposingColor=player.getColor() == Color.WHITE ? Color.BLACK : Color.WHITE;
        Board lastBoard=board.getMovementHistory().getLastBoard();
        if (lastBoard != null) {
              int numberOfPreviousPieces = lastBoard.getPieces(opposingColor).size();
              int numberOfCurrentPieces = board.getPieces(opposingColor).size();
              return numberOfPreviousPieces!=numberOfCurrentPieces;
        }
        return false;
    }
}
