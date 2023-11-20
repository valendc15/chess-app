package org.austral.game.checkers2;
import org.austral.game.commons.*;

public class QueenEatingValidator implements Validator {

    @Override
    public boolean validate(Movement movement, Board board, Player player) {
        DiagonalPathGenerator diagonalPathGenerator = new DiagonalPathGenerator(0, true, true);
        PathResult diagonalPath = diagonalPathGenerator.generatePath(movement);

        if (diagonalPath.pathExists()) {
            return checkQueenEatingConditions(diagonalPath, board, player);
        }

        return false;
    }

    public Position getMiddlePosition(Movement movement){
        DiagonalPathGenerator diagonalPathGenerator = new DiagonalPathGenerator(0, true, true);
        PathResult diagonalPath = diagonalPathGenerator.generatePath(movement);
        Position[] path = diagonalPath.getPath();
        return path[path.length - 2];
    }

    private boolean checkQueenEatingConditions(PathResult diagonalPath, Board board, Player player) {
        Position[] path = diagonalPath.getPath();

        if (path.length < 3) {
            return false; // At least three positions are required for eating (start, middle, end)
        }

        Position start = path[0];
        Position end = path[path.length - 1];
        Position middle = path[path.length - 2];

        return isEnemyPiece(board, middle, player) && noPieceInBetween(board, path);
    }

    private boolean isEnemyPiece(Board board, Position middle, Player player) {
        GetResult<Piece, Boolean> piece = board.getPiece(middle);
        if (piece.getSuccesValue().isPresent()){
            return piece.getSuccesValue().get().getColor() != player.getColor();
        }
        return false;
    }

    private boolean noPieceInBetween(Board board, Position[] path) {
        for (int i = 1; i < path.length - 2; i++) {
            GetResult<Piece, Boolean> piece = board.getPiece(path[i]);
            if (piece.getSuccesValue().isPresent()) {
                return false;
            }
        }
        return true;
    }
}
