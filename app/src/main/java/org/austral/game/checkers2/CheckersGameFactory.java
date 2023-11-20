package org.austral.game.checkers2;


import org.austral.game.commons.*;

import java.util.ArrayList;
import java.util.List;

public class CheckersGameFactory implements GameCreator {
    CompositeOrValidator whitePieceMovements = CheckersMovementFactory.createNormalWhitePieceMovements();
    CompositeOrValidator blackPieceMovements = CheckersMovementFactory.createNormalBlackPieceMovements();

    public CheckersGame  createGame() {
        Board board = new Board(8, 8);
        CompositeAndValidator movementRules = createMovemenRules();
        List<Piece> pieces = new ArrayList<>();
        initializeWhitePieces(pieces);
        initializeBlackPieces(pieces);
        WinningRules[] winningRules = { new AllPiecesEaten()};
        return new CheckersGame(board, pieces.toArray(new Piece[0]), movementRules, winningRules);
    }

    private CompositeAndValidator createMovemenRules(){
        HasPieceValidator hasPieceValidator = new HasPieceValidator();
        InsideBoundsValidator insideBoundsValidator = new InsideBoundsValidator();
        IsPlayersPieceValidator isPlayersPieceValidator = new IsPlayersPieceValidator();
        CollisionValidator collisionValidator = new CollisionValidator();
        return new CompositeAndValidator(hasPieceValidator, insideBoundsValidator, isPlayersPieceValidator, collisionValidator);
    }

    private void initializeWhitePieces(List<Piece> allPieces) {
        for (int i = 1; i <= 7; i+=2) {
            allPieces.add(new Piece(Color.WHITE, new Position(i, 0), whitePieceMovements, "pawn", i));
        }
        for (int i = 0; i <= 6; i+=2) {
            allPieces.add(new Piece(Color.WHITE, new Position(i, 1), whitePieceMovements, "pawn", i+8));
        }

        for (int i = 1; i <= 7; i+=2) {
            allPieces.add(new Piece(Color.WHITE, new Position(i, 2), whitePieceMovements, "pawn", i+16));
        }
    }



    private void initializeBlackPieces(List<Piece> allPieces) {
        for (int i = 0; i <= 6; i+=2) {
            allPieces.add(new Piece(Color.BLACK, new Position(i, 5), blackPieceMovements, "pawn", i+25));
        }
        for (int i = 1; i <= 7; i+=2) {
            allPieces.add(new Piece(Color.BLACK, new Position(i, 6), blackPieceMovements, "pawn", i+33));
        }
        for (int i = 0; i <= 6; i+=2) {
            allPieces.add(new Piece(Color.BLACK, new Position(i, 7), blackPieceMovements, "pawn", i+41));
        }
    }







}
