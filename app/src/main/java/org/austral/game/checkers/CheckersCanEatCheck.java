package org.austral.game.checkers;
import org.austral.game.commons.*;

public class CheckersCanEatCheck {

    public static boolean checkForEating(Movement movement, Board board, PathResult pathResult) {
        if (!isPathValid(pathResult)) {
            return false;
        }

        Position inBetweenPosition = movement.getMiddlePositionIfDiagonalByTwo();
        if (inBetweenPosition == null) {
            return false;
        }

        Piece myPiece = getMyPiece(board, movement.getFrom());

        if (myPiece != null && isPossibleEnemyPiece(board, inBetweenPosition, myPiece)) {
            return true;
        }

        return false;
    }

    private static boolean isPathValid(PathResult pathResult) {
        return pathResult.pathExists();
    }

    private static Piece getMyPiece(Board board, Position from) {
        GetResult<Piece, Boolean> myPieceResult = board.getPiece(from);
        if (myPieceResult.getSuccesValue().isPresent()) {
            return myPieceResult.getSuccesValue().get();
        }
        return null;
    }

    private static boolean isPossibleEnemyPiece(Board board, Position position, Piece myPiece) {
        GetResult<Piece, Boolean> possibleEnemyPieceResult = board.getPiece(position);
        if (possibleEnemyPieceResult.getSuccesValue().isPresent()) {
            Piece possibleEnemyPiece = possibleEnemyPieceResult.getSuccesValue().get();
            return possibleEnemyPiece.getColor() != myPiece.getColor();
        }
        return false;
    }


    public static boolean isPossibleToEatCheck(Movement movement, Board board) {
        GetResult<Piece, Boolean> myPieceResult = board.getPiece(movement.getTo());
        PossibleMovesCalculator possibleMovesCalculator = new PossibleMovesCalculator();

        if (myPieceResult.getSuccesValue().isPresent()) {
            Movement[] possibleMovements = possibleMovesCalculator.calculatePossibleMoves(board, movement.getTo());

            if (hasPossibleEatingMove(board, possibleMovements)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasPossibleEatingMove(Board board, Movement[] possibleMovements) {
        for (Movement possibleMovement : possibleMovements) {
            DiagonalPathGenerator pathGenerator = new DiagonalPathGenerator(2, true, true);
            PathResult diagonalPath = pathGenerator.generatePath(possibleMovement);

            if (checkForEating(possibleMovement, board, diagonalPath)) {
                return true;
            }
        }
        return false;
    }
}
