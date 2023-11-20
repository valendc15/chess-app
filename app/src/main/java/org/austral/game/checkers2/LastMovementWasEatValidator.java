package org.austral.game.checkers2;

import org.austral.game.commons.*;

public class LastMovementWasEatValidator implements Validator{

    public boolean validate(Movement movement, Board board, Player player){
        Color opposingColor=player.getColor() == Color.WHITE ? Color.BLACK : Color.WHITE;
        Board lastBoard=board.getMovementHistory().getLastBoard();
        if (lastBoardIsntNull(lastBoard)) {
              int numberOfPreviousPieces = lastBoard.getPieces(opposingColor).size();
              int numberOfCurrentPieces = board.getPieces(opposingColor).size();
              return numberOfPreviousPieces!=numberOfCurrentPieces;
        }
        return false;
    }

    private boolean lastBoardIsntNull(Board lastBoard){
        return lastBoard != null;
    }
}
