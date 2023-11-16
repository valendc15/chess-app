package org.austral.game.chess;

import org.austral.game.commons.*;
import org.junit.Assert;
import org.junit.Test;

public class Tests{
    @Test
    public void testPawnMovement(){
        Board board = new Board(8,8);
        CheckMate checkMate=new CheckMate();
        VerticalPathGenerator verticalPathGeneratorpawn = new VerticalPathGenerator(1, true, false);
        VerticalPathGenerator verticalDoblePathGenerator= new VerticalPathGenerator(2,true,false);
        DiagonalPathGenerator diagonalPathGeneratorPawn = new DiagonalPathGenerator(1,true, false);
        EatingValidator eatingValidator=new EatingValidator();
        NotEatingValidator notEatingValidator=new NotEatingValidator();
        HasPieceValidator hasPieceValidator=new HasPieceValidator();
        InsideBoundsValidator insideBoundsValidator=new InsideBoundsValidator();
        IsPlayersPieceValidator isPlayersPieceValidator=new IsPlayersPieceValidator();
        NoChekValidator noChekValidator=new NoChekValidator();
        InitialMovementValidator initialMovementValidator=new InitialMovementValidator();
        MovementValidatorWithCollision movementValidatorWithCollisionPawn=new MovementValidatorWithCollision(verticalPathGeneratorpawn);
        MovementValidatorWithCollision movementValidatorWithCollisionPawnDoble=new MovementValidatorWithCollision(verticalDoblePathGenerator);
        MovementValidatorWithCollision movementValidatorWithCollisionPawnDiagonal=new MovementValidatorWithCollision(diagonalPathGeneratorPawn);
        CompositeAndValidator movementRules = new CompositeAndValidator(insideBoundsValidator,hasPieceValidator,isPlayersPieceValidator,noChekValidator);
        CompositeAndValidator initialMovementPawn=new CompositeAndValidator(movementValidatorWithCollisionPawn,notEatingValidator);
        CompositeAndValidator initialMovementPawnDoble=new CompositeAndValidator(initialMovementValidator,movementValidatorWithCollisionPawnDoble,notEatingValidator);
        CompositeAndValidator eatingMovementPawn=new CompositeAndValidator(movementValidatorWithCollisionPawnDiagonal,eatingValidator);
        CompositeOrValidator pawnMovements=new CompositeOrValidator(initialMovementPawn,initialMovementPawnDoble,eatingMovementPawn);
        Piece blackPawn = new Piece(Color.BLACK, new Position(2, 6), pawnMovements, "Pawn",1);
        Piece whitePawn = new Piece(Color.WHITE, new Position(1, 2), pawnMovements, "Pawn",2);
        Game game = new Game(board, new Piece[]{whitePawn,blackPawn}, movementRules,new CheckMate[]{checkMate});
        game=game.whiteMakeMove(new Movement(new Position(1,2),new Position(1,4)));
        game=game.whiteMakeMove(new Movement(new Position(1,4),new Position(1,5)));
        game=game.whiteMakeMove(new Movement(new Position(1,5),new Position(2,6)));
        Assert.assertTrue(game.getBoard().getSquares(new Position(2,6)).getPiece().getSuccesValue().get().getPieceName().equals("Pawn"));
    }

    @Test
    public void testKnightMovements(){
        Board board = new Board(8,8);
        CheckMate checkMate=new CheckMate();
        LPathGenerator lPathGenerator = new LPathGenerator(2, 1,1,2 );
        EatingValidator eatingValidator=new EatingValidator();
        NotEatingValidator notEatingValidator=new NotEatingValidator();
        HasPieceValidator hasPieceValidator=new HasPieceValidator();
        InsideBoundsValidator insideBoundsValidator=new InsideBoundsValidator();
        IsPlayersPieceValidator isPlayersPieceValidator=new IsPlayersPieceValidator();
        NoChekValidator noChekValidator=new NoChekValidator();
        MovementValidatorWithoutCollision movementValidatorWithoutCollisionKnight=new MovementValidatorWithoutCollision(lPathGenerator);
        CompositeAndValidator movementRules = new CompositeAndValidator(insideBoundsValidator,hasPieceValidator,isPlayersPieceValidator,noChekValidator);
        CompositeAndValidator eatingMovementKnight=new CompositeAndValidator(movementValidatorWithoutCollisionKnight,eatingValidator);
        CompositeAndValidator notEatingMovementKnight=new CompositeAndValidator(movementValidatorWithoutCollisionKnight,notEatingValidator);
        CompositeOrValidator knightMovements=new CompositeOrValidator(eatingMovementKnight,notEatingMovementKnight);
        Piece blackKnight = new Piece(Color.BLACK, new Position(2, 6), knightMovements, "Knight",1);
        Piece whiteKnight = new Piece(Color.WHITE, new Position(1, 2), knightMovements, "Knight",2);
        Game game = new Game(board, new Piece[]{whiteKnight,blackKnight}, movementRules, new CheckMate[]{checkMate});
        game=game.whiteMakeMove(new Movement(new Position(1,2),new Position(2,4)));
        game=game.whiteMakeMove(new Movement(new Position(2,4),new Position(4,5)));
        game=game.whiteMakeMove(new Movement(new Position(4,5),new Position(6,6)));
        Assert.assertTrue(game.getBoard().getSquares(new Position(6,6)).getPiece().getSuccesValue().get().getPieceName().equals("Knight"));
    }

    @Test
    public void testNoChekMate(){

        Board board = new Board(8, 8);
        CheckMate checkMate = new CheckMate();
        EatingValidator eatingValidator=new EatingValidator();
        NotEatingValidator notEatingValidator=new NotEatingValidator();
        HasPieceValidator hasPieceValidator=new HasPieceValidator();
        InsideBoundsValidator insideBoundsValidator=new InsideBoundsValidator();
        IsPlayersPieceValidator isPlayersPieceValidator=new IsPlayersPieceValidator();
        NoChekValidator noChekValidator=new NoChekValidator();
        DiagonalPathGenerator diagonalPathGeneratorQueen = new DiagonalPathGenerator(0, true, true);
        HorizontalPathGenerator horizontalPathGeneratorQueen = new HorizontalPathGenerator(0);
        VerticalPathGenerator verticalPathGeneratorQueen = new VerticalPathGenerator(0, true, true);
        DiagonalPathGenerator diagonalPathGeneratorKing = new DiagonalPathGenerator(1, true, true);
        HorizontalPathGenerator horizontalPathGeneratorKing = new HorizontalPathGenerator(1);
        VerticalPathGenerator vking = new VerticalPathGenerator(1,true,true);
        CompositeAndValidator movementRules = new CompositeAndValidator(insideBoundsValidator,hasPieceValidator,isPlayersPieceValidator,noChekValidator);
        MovementValidatorWithCollision movementValidatorWithCollisionQueen=new MovementValidatorWithCollision(diagonalPathGeneratorQueen);
        MovementValidatorWithCollision movementValidatorWithCollisionQueenHorizontal=new MovementValidatorWithCollision(horizontalPathGeneratorQueen);
        MovementValidatorWithCollision movementValidatorWithCollisionQueenVertical=new MovementValidatorWithCollision(verticalPathGeneratorQueen);
        MovementValidatorWithCollision movementValidatorWithCollisionKing=new MovementValidatorWithCollision(diagonalPathGeneratorKing);
        MovementValidatorWithCollision movementValidatorWithCollisionKingHorizontal=new MovementValidatorWithCollision(horizontalPathGeneratorKing);
        MovementValidatorWithCollision moo=new MovementValidatorWithCollision(vking);
        CompositeAndValidator eatingMovementQueen=new CompositeAndValidator(movementValidatorWithCollisionQueen,eatingValidator);
        CompositeAndValidator eatingMovementQueenHorizontal=new CompositeAndValidator(movementValidatorWithCollisionQueenHorizontal,eatingValidator);
        CompositeAndValidator eatingMovementQueenVertical=new CompositeAndValidator(movementValidatorWithCollisionQueenVertical,eatingValidator);
        CompositeAndValidator eatingMovementKing=new CompositeAndValidator(movementValidatorWithCollisionKing,eatingValidator);
        CompositeAndValidator eatingMovementKingHorizontal=new CompositeAndValidator(movementValidatorWithCollisionKingHorizontal,eatingValidator);
        CompositeAndValidator eatingMovementKingVertical=new CompositeAndValidator(moo,eatingValidator);
        CompositeAndValidator notEatingMovementQueen=new CompositeAndValidator(movementValidatorWithCollisionQueen,notEatingValidator);
        CompositeAndValidator notEatingMovementQueenHorizontal=new CompositeAndValidator(movementValidatorWithCollisionQueenHorizontal,notEatingValidator);
        CompositeAndValidator notEatingMovementQueenVertical=new CompositeAndValidator(movementValidatorWithCollisionQueenVertical,notEatingValidator);
        CompositeAndValidator notEatingMovementKing=new CompositeAndValidator(movementValidatorWithCollisionKing,notEatingValidator);
        CompositeAndValidator notEatingMovementKingHorizontal=new CompositeAndValidator(movementValidatorWithCollisionKingHorizontal,notEatingValidator);
        CompositeAndValidator notEatingMovementKingVertical=new CompositeAndValidator(moo,notEatingValidator);
        CompositeOrValidator queenMovements=new CompositeOrValidator(eatingMovementQueen,notEatingMovementQueen,eatingMovementQueenHorizontal,notEatingMovementQueenHorizontal,eatingMovementQueenVertical,notEatingMovementQueenVertical);
        CompositeOrValidator kingMovements=new CompositeOrValidator(eatingMovementKing,notEatingMovementKing,eatingMovementKingHorizontal,notEatingMovementKingHorizontal,eatingMovementKingVertical,notEatingMovementKingVertical);
        Piece blackQueen = new Piece(Color.BLACK, new Position(4, 1), queenMovements, "Queen",1);
        Piece whiteKing = new Piece(Color.WHITE, new Position(4, 0), kingMovements, "King",true,2);
        Piece whiteQueen=new Piece(Color.WHITE,new Position(4,7),queenMovements,"Queen",3);
        Game game = new Game(board, new Piece[]{whiteKing,blackQueen,whiteQueen}, movementRules,new CheckMate[]{checkMate});
        Assert.assertFalse(game.isOver());
    }

    @Test
    public void TestCheckMate(){
        Board board = new Board(8, 8);
        CheckMate checkMate = new CheckMate();
        EatingValidator eatingValidator=new EatingValidator();
        NotEatingValidator notEatingValidator=new NotEatingValidator();
        HasPieceValidator hasPieceValidator=new HasPieceValidator();
        InsideBoundsValidator insideBoundsValidator=new InsideBoundsValidator();
        IsPlayersPieceValidator isPlayersPieceValidator=new IsPlayersPieceValidator();
        NoChekValidator noChekValidator=new NoChekValidator();
        DiagonalPathGenerator diagonalPathGeneratorQueen = new DiagonalPathGenerator(0, true, true);
        HorizontalPathGenerator horizontalPathGeneratorQueen = new HorizontalPathGenerator(0);
        VerticalPathGenerator verticalPathGeneratorQueen = new VerticalPathGenerator(0, true, true);
        DiagonalPathGenerator diagonalPathGeneratorKing = new DiagonalPathGenerator(1, true, true);
        HorizontalPathGenerator horizontalPathGeneratorKing = new HorizontalPathGenerator(1);
        VerticalPathGenerator vking = new VerticalPathGenerator(1,true,true);
        CompositeAndValidator movementRules = new CompositeAndValidator(insideBoundsValidator,hasPieceValidator,isPlayersPieceValidator,noChekValidator);
        MovementValidatorWithCollision movementValidatorWithCollisionQueen=new MovementValidatorWithCollision(diagonalPathGeneratorQueen);
        MovementValidatorWithCollision movementValidatorWithCollisionQueenHorizontal=new MovementValidatorWithCollision(horizontalPathGeneratorQueen);MovementValidatorWithCollision movementValidatorWithCollisionQueenVertical=new MovementValidatorWithCollision(verticalPathGeneratorQueen);
        MovementValidatorWithCollision movementValidatorWithCollisionKing=new MovementValidatorWithCollision(diagonalPathGeneratorKing);
        MovementValidatorWithCollision movementValidatorWithCollisionKingHorizontal=new MovementValidatorWithCollision(horizontalPathGeneratorKing);
        MovementValidatorWithCollision moo=new MovementValidatorWithCollision(vking);
        CompositeAndValidator eatingMovementQueen=new CompositeAndValidator(movementValidatorWithCollisionQueen,eatingValidator);
        CompositeAndValidator eatingMovementQueenHorizontal=new CompositeAndValidator(movementValidatorWithCollisionQueenHorizontal,eatingValidator);CompositeAndValidator eatingMovementQueenVertical=new CompositeAndValidator(movementValidatorWithCollisionQueenVertical,eatingValidator);
        CompositeAndValidator eatingMovementKing=new CompositeAndValidator(movementValidatorWithCollisionKing,eatingValidator);
        CompositeAndValidator eatingMovementKingHorizontal=new CompositeAndValidator(movementValidatorWithCollisionKingHorizontal,eatingValidator);
        CompositeAndValidator eatingMovementKingVertical=new CompositeAndValidator(moo,eatingValidator);
        CompositeAndValidator notEatingMovementQueen=new CompositeAndValidator(movementValidatorWithCollisionQueen,notEatingValidator);
        CompositeAndValidator notEatingMovementQueenHorizontal=new CompositeAndValidator(movementValidatorWithCollisionQueenHorizontal,notEatingValidator);
        CompositeAndValidator notEatingMovementQueenVertical=new CompositeAndValidator(movementValidatorWithCollisionQueenVertical,notEatingValidator);
        CompositeAndValidator notEatingMovementKing=new CompositeAndValidator(movementValidatorWithCollisionKing,notEatingValidator);
        CompositeAndValidator notEatingMovementKingHorizontal=new CompositeAndValidator(movementValidatorWithCollisionKingHorizontal,notEatingValidator);
        CompositeAndValidator notEatingMovementKingVertical=new CompositeAndValidator(moo,notEatingValidator);
        CompositeOrValidator queenMovements=new CompositeOrValidator(eatingMovementQueen,notEatingMovementQueen,eatingMovementQueenHorizontal,notEatingMovementQueenHorizontal,eatingMovementQueenVertical,notEatingMovementQueenVertical);
        CompositeOrValidator kingMovements=new CompositeOrValidator(eatingMovementKing,notEatingMovementKing,eatingMovementKingHorizontal,notEatingMovementKingHorizontal,eatingMovementKingVertical,notEatingMovementKingVertical);
        Piece blackQueen = new Piece(Color.BLACK, new Position(4, 2), queenMovements, "Queen",1);
        Piece blackQueen3=new Piece(Color.BLACK,new Position(7,0),queenMovements,"Queen",2);
        Piece whiteKing = new Piece(Color.WHITE, new Position(4, 0), kingMovements, "King",true,3);
        Game game = new Game(board, new Piece[]{whiteKing,blackQueen,blackQueen3}, movementRules,new CheckMate[]{checkMate});
        Assert.assertTrue(game.isOver());
        }
}