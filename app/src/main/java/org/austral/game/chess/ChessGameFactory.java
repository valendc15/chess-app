package org.austral.game.chess;

import org.austral.game.commons.*;

public class ChessGameFactory implements GameCreator{

    public Game createGame(){
        Board board = new Board(8, 8);
        CheckMate checkMate = new CheckMate();
        EatingValidator eatingValidator=new EatingValidator();
        NotEatingValidator notEatingValidator=new NotEatingValidator();
        HasPieceValidator hasPieceValidator=new HasPieceValidator();
        InsideBoundsValidator insideBoundsValidator=new InsideBoundsValidator();
        IsPlayersPieceValidator isPlayersPieceValidator=new IsPlayersPieceValidator();
        NoChekValidator noChekValidator=new NoChekValidator();
        CompositeOrValidator whitePawnMovements = ChessMovementFactory.createWhitePawnMovements();
        CompositeOrValidator blackPawnMovements = ChessMovementFactory.createBlackPawnMovements();
        CompositeOrValidator rookMovements = ChessMovementFactory.createRookMovements();
        CompositeOrValidator knightMovements = ChessMovementFactory.createKnightMovements();
        CompositeOrValidator bishopMovements = ChessMovementFactory.createBishopMovements();
        CompositeOrValidator queenMovements = ChessMovementFactory.createQueenMovements();
        CompositeOrValidator kingMovements = ChessMovementFactory.createKingMovements();
        Piece whitePawn1 = new Piece(Color.WHITE, new Position(0, 1), whitePawnMovements, "pawn", 32);
        Piece whitePawn2 = new Piece(Color.WHITE, new Position(1, 1), whitePawnMovements, "pawn", 1);
        Piece whitePawn3 = new Piece(Color.WHITE, new Position(2, 1), whitePawnMovements, "pawn", 2);
        Piece whitePawn4 = new Piece(Color.WHITE, new Position(3, 1), whitePawnMovements, "pawn", 3);
        Piece whitePawn5 = new Piece(Color.WHITE, new Position(4, 1), whitePawnMovements, "pawn", 4);
        Piece whitePawn6 = new Piece(Color.WHITE, new Position(5, 1), whitePawnMovements, "pawn", 5);
        Piece whitePawn7 = new Piece(Color.WHITE, new Position(6, 1), whitePawnMovements, "pawn", 6);
        Piece whitePawn8 = new Piece(Color.WHITE, new Position(7, 1), whitePawnMovements, "pawn", 7);
        Piece whiteRook1 = new Piece(Color.WHITE, new Position(0, 0), rookMovements, "rook", 8);
        Piece whiteRook2 = new Piece(Color.WHITE, new Position(7, 0), rookMovements, "rook", 9);
        Piece whiteKnight1 = new Piece(Color.WHITE, new Position(1, 0), knightMovements, "knight", 10);
        Piece whiteKnight2 = new Piece(Color.WHITE, new Position(6, 0), knightMovements, "knight", 11);
        Piece whiteBishop1 = new Piece(Color.WHITE, new Position(2, 0), bishopMovements, "bishop", 12);
        Piece whiteBishop2 = new Piece(Color.WHITE, new Position(5, 0), bishopMovements, "bishop", 13);
        Piece whiteQueen = new Piece(Color.WHITE, new Position(3, 0), queenMovements, "queen", 14);
        Piece whiteKing = new Piece(Color.WHITE, new Position(4, 0), kingMovements, "king", true,15);
        Piece blackPawn1 = new Piece(Color.BLACK, new Position(0, 6), blackPawnMovements, "pawn", 16);
        Piece blackPawn2 = new Piece(Color.BLACK, new Position(1, 6), blackPawnMovements, "pawn", 17);
        Piece blackPawn3 = new Piece(Color.BLACK, new Position(2, 6), blackPawnMovements, "pawn", 18);
        Piece blackPawn4 = new Piece(Color.BLACK, new Position(3, 6), blackPawnMovements, "pawn", 19);
        Piece blackPawn5 = new Piece(Color.BLACK, new Position(4, 6), blackPawnMovements, "pawn", 20);
        Piece blackPawn6 = new Piece(Color.BLACK, new Position(5, 6), blackPawnMovements, "pawn", 21);
        Piece blackPawn7 = new Piece(Color.BLACK, new Position(6, 6), blackPawnMovements, "pawn", 22);
        Piece blackPawn8 = new Piece(Color.BLACK, new Position(7, 6), blackPawnMovements, "pawn", 23);
        Piece blackRook1 = new Piece(Color.BLACK, new Position(0, 7), rookMovements, "rook", 24);
        Piece blackRook2 = new Piece(Color.BLACK, new Position(7, 7), rookMovements, "rook", 25);
        Piece blackKnight1 = new Piece(Color.BLACK, new Position(1, 7), knightMovements, "knight", 26);
        Piece blackKnight2 = new Piece(Color.BLACK, new Position(6, 7), knightMovements, "knight", 27);
        Piece blackBishop1 = new Piece(Color.BLACK, new Position(2, 7), bishopMovements, "bishop", 28);
        Piece blackBishop2 = new Piece(Color.BLACK, new Position(5, 7), bishopMovements, "bishop", 29);
        Piece blackQueen = new Piece(Color.BLACK, new Position(3, 7), queenMovements, "queen", 30);
        Piece blackKing = new Piece(Color.BLACK, new Position(4, 7), kingMovements, "king",true, 31);
        Piece[] allPieces={whitePawn1,whitePawn2,whitePawn3,whitePawn4,whitePawn5,whitePawn6,whitePawn7,whitePawn8,whiteRook1,whiteRook2,whiteKnight1,whiteKnight2,whiteBishop1,whiteBishop2,whiteQueen,whiteKing,blackPawn1,blackPawn2,blackPawn3,blackPawn4,blackPawn5,blackPawn6,blackPawn7,blackPawn8,blackRook1,blackRook2,blackKnight1,blackKnight2,blackBishop1,blackBishop2,blackQueen,blackKing};
        CompositeAndValidator movementRules = new CompositeAndValidator(insideBoundsValidator,hasPieceValidator,isPlayersPieceValidator,noChekValidator);
        return new Game(board,allPieces, movementRules,new CheckMate[]{checkMate});
    }
}
