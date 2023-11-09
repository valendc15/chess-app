package org.austral.game.chess;
import org.austral.game.commons.Board;
import org.austral.game.commons.Color;
import org.austral.game.commons.GetResult;
import org.austral.game.commons.Movement;
import org.austral.game.commons.Player;
import org.austral.game.commons.Position;
import org.austral.game.commons.Piece;

public class CheckValidator {

    public static boolean isCheck(Board board, Color color) {
        GetResult<Position, Boolean> kingPosition = board.findKingPosition(color);

        if (kingPosition.getErrorValue()) {
            return false;
        }

        for (int i = 0; i < board.getSquares().length; i++) {
            if (isAttackingKing(board, color, kingPosition, board.getSquares()[i].getPosition())) {
                return true;
            }
        }

        return false;
    }

    private static boolean isAttackingKing(Board board, Color color, GetResult<Position, Boolean> kingPosition, Position attackerPosition) {
        GetResult<Piece, Boolean> attackerPieceResult = board.getSquares(attackerPosition).getPiece();

        if (attackerPieceResult.getSuccesValue().isPresent() && attackerPieceResult.getSuccesValue().get().getColor() != color) {
            Movement movement = new Movement(attackerPosition, kingPosition.getSuccesValue().get());

            if (attackerPieceResult.getSuccesValue().get().isLegalMove(movement, board, new Player(color))) {
                return true;
            }
        }

        return false;
    }
}

