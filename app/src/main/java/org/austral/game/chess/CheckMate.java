package org.austral.game.chess;

import java.util.List;
import org.austral.game.commons.Board;
import org.austral.game.commons.Color;
import org.austral.game.commons.GetResult;
import org.austral.game.commons.Movement;
import org.austral.game.commons.Piece;
import org.austral.game.commons.Player;
import org.austral.game.commons.WinningRules;
import org.austral.game.commons.PossibleMovesCalculator;
import org.austral.game.commons.Position;

public class CheckMate implements WinningRules {
    PossibleMovesCalculator possibleMovesCalculator = new PossibleMovesCalculator();

    public boolean validateRule(Board board, Player player) {
        Color color = player.getColor();
        // If the king is not in check, it's not checkmate.
        if (!board.isCheck(color)) {
            return false;
        }
        GetResult<Position, Boolean> kingPositionResult = board.findKingPosition(color);
        // If there is no king, this should never happen, so the game automatically ends.
        if (kingPositionResult.getErrorValue()) {
            return true;
        }

        List<Piece> pieces=board.getPieces(color);
        for (Piece piece : pieces) {
            if(!board.findPiece(piece).getErrorValue()) {
                Movement[] possibleMoves = possibleMovesCalculator.calculatePossibleMoves(board, board.findPiece(piece).getSuccesValue().get());
                for (Movement move : possibleMoves) {
                    Board updatedBoard = board.movePiece(move);
                    if (!updatedBoard.isCheck(color)) {
                        return false;
                    }
                }
            }
        }

        return true;
}}



