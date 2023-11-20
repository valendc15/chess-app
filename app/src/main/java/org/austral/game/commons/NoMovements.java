package org.austral.game.commons;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class NoMovements implements WinningRules {

    public boolean validateRule(Board board, Player player) {
       List<Piece> pieces = board.getPieces(player.getColor()==Color.BLACK?Color.WHITE:Color.BLACK);
       PossibleMovesCalculator possibleMoves = new PossibleMovesCalculator();
         for (Piece piece : pieces) {
              if (findPossibleMove(board, piece, possibleMoves)) {
                return false;
              }
         }
            return true;
    }

    public boolean findPossibleMove(Board board, Piece piece, PossibleMovesCalculator possibleMoves) {
        GetResult<Position, Boolean> position = board.findPiece(piece);
        Optional<Position> positionOptional =position.getSuccesValue();
        return positionOptional.filter(value -> possibleMoves.calculatePossibleMoves(board, value).length > 0).isPresent();
    }
}