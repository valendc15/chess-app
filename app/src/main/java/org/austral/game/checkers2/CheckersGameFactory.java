package org.austral.game.checkers2;


import org.austral.game.checkers.CollisionValidator;
import org.austral.game.commons.*;

public class CheckersGameFactory implements GameCreator {

    public CheckersGame  createGame() {
        Board board = new Board(8, 8);
        HasPieceValidator hasPieceValidator = new HasPieceValidator();
        InsideBoundsValidator insideBoundsValidator = new InsideBoundsValidator();
        IsPlayersPieceValidator isPlayersPieceValidator = new IsPlayersPieceValidator();
        org.austral.game.checkers.CollisionValidator collisionValidator = new CollisionValidator();
        CompositeAndValidator movementRules = new CompositeAndValidator(hasPieceValidator, insideBoundsValidator, isPlayersPieceValidator, collisionValidator);
        CompositeOrValidator whitePieceMovements = CheckersMovementFactory.createNormalWhitePieceMovements();
        CompositeOrValidator blackPieceMovements = CheckersMovementFactory.createNormalBlackPieceMovements();
        Piece whitepiece1=new Piece(Color.WHITE, new Position(1, 0), whitePieceMovements, "pawn", 1);
        Piece whitepiece2=new Piece(Color.WHITE, new Position(3, 0), whitePieceMovements, "pawn", 2);
        Piece whitepiece3=new Piece(Color.WHITE, new Position(5, 0), whitePieceMovements, "pawn", 3);
        Piece whitepiece4=new Piece(Color.WHITE, new Position(7, 0), whitePieceMovements, "pawn", 4);
        Piece whitepiece5=new Piece(Color.WHITE, new Position(0, 1), whitePieceMovements, "pawn", 5);
        Piece whitepiece6=new Piece(Color.WHITE, new Position(2, 1), whitePieceMovements, "pawn", 6);
        Piece whitepiece7=new Piece(Color.WHITE, new Position(4, 1), whitePieceMovements, "pawn", 7);
        Piece whitepiece8=new Piece(Color.WHITE, new Position(6, 1), whitePieceMovements, "pawn", 8);
        Piece whitepiece9=new Piece(Color.WHITE, new Position(1, 2), whitePieceMovements, "pawn", 9);
        Piece whitepiece10=new Piece(Color.WHITE, new Position(3, 2), whitePieceMovements, "pawn", 10);
        Piece whitepiece11=new Piece(Color.WHITE, new Position(5, 2), whitePieceMovements, "pawn", 11);
        Piece whitepiece12=new Piece(Color.WHITE, new Position(7, 2), whitePieceMovements, "pawn", 12);
        Piece blackpiece1=new Piece(Color.BLACK, new Position(0, 5), blackPieceMovements, "pawn", 13);
        Piece blackpiece2=new Piece(Color.BLACK, new Position(2, 5), blackPieceMovements, "pawn", 14);
        Piece blackpiece3=new Piece(Color.BLACK, new Position(4, 5), blackPieceMovements, "pawn", 15);
        Piece blackpiece4=new Piece(Color.BLACK, new Position(6, 5), blackPieceMovements, "pawn", 16);
        Piece blackpiece5=new Piece(Color.BLACK, new Position(1, 6), blackPieceMovements, "pawn", 17);
        Piece blackpiece6=new Piece(Color.BLACK, new Position(3, 6), blackPieceMovements, "pawn", 18);
        Piece blackpiece7=new Piece(Color.BLACK, new Position(5, 6), blackPieceMovements, "pawn", 19);
        Piece blackpiece8=new Piece(Color.BLACK, new Position(7, 6), blackPieceMovements, "pawn", 20);
        Piece blackpiece9=new Piece(Color.BLACK, new Position(0, 7), blackPieceMovements, "pawn", 21);
        Piece blackpiece10=new Piece(Color.BLACK, new Position(2, 7), blackPieceMovements, "pawn", 22);
        Piece blackpiece11=new Piece(Color.BLACK, new Position(4, 7), blackPieceMovements, "pawn", 23);
        Piece blackpiece12=new Piece(Color.BLACK, new Position(6, 7), blackPieceMovements, "pawn", 24);
        Piece[] allPieces={ whitepiece1, whitepiece2, whitepiece3, whitepiece4, whitepiece5, whitepiece6, whitepiece7, whitepiece8, whitepiece9, whitepiece10, whitepiece11, whitepiece12, blackpiece1, blackpiece2, blackpiece3, blackpiece4, blackpiece5, blackpiece6, blackpiece7, blackpiece8, blackpiece9, blackpiece10, blackpiece11, blackpiece12};
        WinningRules[] winningRules = { new AllPiecesEaten() };
        return new CheckersGame(board, allPieces, movementRules, winningRules);
    }
}
