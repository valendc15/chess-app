package org.austral.game.chess;

import org.austral.game.commons.MovementValidatorWithoutCollision;
import org.austral.game.commons.*;

public class ChessMovementFactory {

    public static CompositeOrValidator createWhitePawnMovements(){
        InitialMovementValidator initialMovementValidator=new InitialMovementValidator();
        EatingValidator eatingValidator=new EatingValidator();
        NotEatingValidator notEatingValidator=new NotEatingValidator();
        VerticalPathGenerator verticalPathGeneratorpawn = new VerticalPathGenerator(1, true, false);
        VerticalPathGenerator verticalDoblePathGenerator= new VerticalPathGenerator(2,true,false);
        DiagonalPathGenerator diagonalPathGeneratorPawn = new DiagonalPathGenerator(1, true, false);
        MovementValidatorWithCollision movementValidatorWithCollisionPawn=new MovementValidatorWithCollision(verticalPathGeneratorpawn);
        MovementValidatorWithCollision movementValidatorWithCollisionPawnDoble=new MovementValidatorWithCollision(verticalDoblePathGenerator);
        MovementValidatorWithCollision movementValidatorWithCollisionPawnDiagonal=new MovementValidatorWithCollision(diagonalPathGeneratorPawn);
        CompositeAndValidator initialMovementPawn=new CompositeAndValidator(movementValidatorWithCollisionPawn,notEatingValidator);
        CompositeAndValidator initialMovementPawnDoble=new CompositeAndValidator(initialMovementValidator,movementValidatorWithCollisionPawnDoble,notEatingValidator);
        CompositeAndValidator eatingMovementPawn=new CompositeAndValidator(movementValidatorWithCollisionPawnDiagonal,eatingValidator);
        CompositeOrValidator pawnMovements=new CompositeOrValidator(initialMovementPawn,initialMovementPawnDoble,eatingMovementPawn);
        return pawnMovements;
    }

    public static CompositeOrValidator createBlackPawnMovements(){
        InitialMovementValidator initialMovementValidator=new InitialMovementValidator();
        EatingValidator eatingValidator=new EatingValidator();
        NotEatingValidator notEatingValidator=new NotEatingValidator();
        VerticalPathGenerator verticalPathGeneratorpawn = new VerticalPathGenerator(1, false, true);
        VerticalPathGenerator verticalDoblePathGenerator= new VerticalPathGenerator(2,false,true);
        DiagonalPathGenerator diagonalPathGeneratorPawn = new DiagonalPathGenerator(1, false, true);
        MovementValidatorWithCollision movementValidatorWithCollisionPawn=new MovementValidatorWithCollision(verticalPathGeneratorpawn);
        MovementValidatorWithCollision movementValidatorWithCollisionPawnDoble=new MovementValidatorWithCollision(verticalDoblePathGenerator);
        MovementValidatorWithCollision movementValidatorWithCollisionPawnDiagonal=new MovementValidatorWithCollision(diagonalPathGeneratorPawn);
        CompositeAndValidator initialMovementPawn=new CompositeAndValidator(movementValidatorWithCollisionPawn,notEatingValidator);
        CompositeAndValidator initialMovementPawnDoble=new CompositeAndValidator(initialMovementValidator,movementValidatorWithCollisionPawnDoble,notEatingValidator);
        CompositeAndValidator eatingMovementPawn=new CompositeAndValidator(movementValidatorWithCollisionPawnDiagonal,eatingValidator);
        CompositeOrValidator pawnMovements=new CompositeOrValidator(initialMovementPawn,initialMovementPawnDoble,eatingMovementPawn);
        return pawnMovements;
    }

    public static CompositeOrValidator createRookMovements(){
        InitialMovementValidator initialMovementValidator = new InitialMovementValidator();
        EatingValidator eatingValidator=new EatingValidator();
        NotEatingValidator notEatingValidator=new NotEatingValidator();
        HorizontalPathGenerator horizontalPathGenerator = new HorizontalPathGenerator(0);
        VerticalPathGenerator verticalPathGenerator = new VerticalPathGenerator(0, true, true);
        MovementValidatorWithCollision movementValidatorWithCollisionRookHorizontal=new MovementValidatorWithCollision(horizontalPathGenerator);
        MovementValidatorWithCollision movementValidatorWithCollisionRookVertical=new MovementValidatorWithCollision(verticalPathGenerator);
        CompositeAndValidator eatingMovementRookHorizontal=new CompositeAndValidator(movementValidatorWithCollisionRookHorizontal,eatingValidator);
        CompositeAndValidator eatingMovementRookVertical=new CompositeAndValidator(movementValidatorWithCollisionRookVertical,eatingValidator);
        CompositeAndValidator notEatingMovementRookHorizontal=new CompositeAndValidator(movementValidatorWithCollisionRookHorizontal,notEatingValidator);
        CompositeAndValidator notEatingMovementRookVertical=new CompositeAndValidator(movementValidatorWithCollisionRookVertical,notEatingValidator);
        CompositeOrValidator rookMovements=new CompositeOrValidator(eatingMovementRookHorizontal,notEatingMovementRookHorizontal,eatingMovementRookVertical,notEatingMovementRookVertical);
        return rookMovements;
    }
    public static CompositeOrValidator createBishopMovements(){
        EatingValidator eatingValidator=new EatingValidator();
        NotEatingValidator notEatingValidator=new NotEatingValidator();
        DiagonalPathGenerator diagonalPathGenerator = new DiagonalPathGenerator(0, true, true);
        MovementValidatorWithCollision movementValidatorWithCollisionBishop=new MovementValidatorWithCollision(diagonalPathGenerator);
        CompositeAndValidator eatingMovementBishop=new CompositeAndValidator(movementValidatorWithCollisionBishop,eatingValidator);
        CompositeAndValidator notEatingMovementBishop=new CompositeAndValidator(movementValidatorWithCollisionBishop,notEatingValidator);
        CompositeOrValidator bishopMovements=new CompositeOrValidator(eatingMovementBishop,notEatingMovementBishop);
        return bishopMovements;
    }

    public static CompositeOrValidator createKnightMovements(){
        EatingValidator eatingValidator=new EatingValidator();
        NotEatingValidator notEatingValidator=new NotEatingValidator();
        LPathGenerator knightPathGenerator = new LPathGenerator();
        MovementValidatorWithCollision movementValidatorWithCollisionKnight=new MovementValidatorWithCollision(knightPathGenerator);
        CompositeAndValidator eatingMovementKnight=new CompositeAndValidator(movementValidatorWithCollisionKnight,eatingValidator);
        CompositeAndValidator notEatingMovementKnight=new CompositeAndValidator(movementValidatorWithCollisionKnight,notEatingValidator);
        CompositeOrValidator knightMovements=new CompositeOrValidator(eatingMovementKnight,notEatingMovementKnight);
        return knightMovements;
    }

    public static CompositeOrValidator createQueenMovements(){
        EatingValidator eatingValidator=new EatingValidator();
        NotEatingValidator notEatingValidator=new NotEatingValidator();
        DiagonalPathGenerator diagonalPathGeneratorQueen = new DiagonalPathGenerator(0, true, true);
        HorizontalPathGenerator horizontalPathGeneratorQueen = new HorizontalPathGenerator(0);
        VerticalPathGenerator verticalPathGeneratorQueen = new VerticalPathGenerator(0, true, true);
        MovementValidatorWithCollision movementValidatorWithCollisionQueen=new MovementValidatorWithCollision(diagonalPathGeneratorQueen);
        MovementValidatorWithCollision movementValidatorWithCollisionQueenHorizontal=new MovementValidatorWithCollision(horizontalPathGeneratorQueen);
        MovementValidatorWithCollision movementValidatorWithCollisionQueenVertical=new MovementValidatorWithCollision(verticalPathGeneratorQueen);
        CompositeAndValidator eatingMovementQueen=new CompositeAndValidator(movementValidatorWithCollisionQueen,eatingValidator);
        CompositeAndValidator eatingMovementQueenHorizontal=new CompositeAndValidator(movementValidatorWithCollisionQueenHorizontal,eatingValidator);
        CompositeAndValidator eatingMovementQueenVertical=new CompositeAndValidator(movementValidatorWithCollisionQueenVertical,eatingValidator);
        CompositeAndValidator notEatingMovementQueen=new CompositeAndValidator(movementValidatorWithCollisionQueen,notEatingValidator);
        CompositeAndValidator notEatingMovementQueenHorizontal=new CompositeAndValidator(movementValidatorWithCollisionQueenHorizontal,notEatingValidator);
        CompositeAndValidator notEatingMovementQueenVertical=new CompositeAndValidator(movementValidatorWithCollisionQueenVertical,notEatingValidator);
        CompositeOrValidator queenMovements=new CompositeOrValidator(eatingMovementQueen,notEatingMovementQueen,eatingMovementQueenHorizontal,notEatingMovementQueenHorizontal,eatingMovementQueenVertical,notEatingMovementQueenVertical);
        return queenMovements;
    }

    public static CompositeOrValidator createKingMovements(){
        EatingValidator eatingValidator=new EatingValidator();
        NotEatingValidator notEatingValidator=new NotEatingValidator();
        DiagonalPathGenerator diagonalPathGeneratorKing = new DiagonalPathGenerator(1, true, true);
        HorizontalPathGenerator horizontalPathGeneratorKing = new HorizontalPathGenerator(1);
        VerticalPathGenerator verticalPathGeneratorKing = new VerticalPathGenerator(1, true, true);
        MovementValidatorWithCollision movementValidatorWithCollisionKing=new MovementValidatorWithCollision(diagonalPathGeneratorKing);
        MovementValidatorWithCollision movementValidatorWithCollisionKingHorizontal=new MovementValidatorWithCollision(horizontalPathGeneratorKing);
        MovementValidatorWithCollision movementValidatorWithCollisionKingVertical=new MovementValidatorWithCollision(verticalPathGeneratorKing);
        CompositeAndValidator eatingMovementKing=new CompositeAndValidator(movementValidatorWithCollisionKing,eatingValidator);
        CompositeAndValidator eatingMovementKingHorizontal=new CompositeAndValidator(movementValidatorWithCollisionKingHorizontal,eatingValidator);
        CompositeAndValidator eatingMovementKingVertical=new CompositeAndValidator(movementValidatorWithCollisionKingVertical,eatingValidator);
        CompositeAndValidator notEatingMovementKing=new CompositeAndValidator(movementValidatorWithCollisionKing,notEatingValidator);
        CompositeAndValidator notEatingMovementKingHorizontal=new CompositeAndValidator(movementValidatorWithCollisionKingHorizontal,notEatingValidator);
        CompositeAndValidator notEatingMovementKingVertical=new CompositeAndValidator(movementValidatorWithCollisionKingVertical,notEatingValidator);
        CompositeOrValidator kingMovements=new CompositeOrValidator(eatingMovementKing,notEatingMovementKing,eatingMovementKingHorizontal,notEatingMovementKingHorizontal,eatingMovementKingVertical,notEatingMovementKingVertical);
        return kingMovements;
    }

    public static CompositeOrValidator queenWithoutCollision(){
        EatingValidator eatingValidator= new EatingValidator();
        NotEatingValidator notEatingValidator = new NotEatingValidator();
        DiagonalPathGenerator diagonalPathGeneratorQueen = new DiagonalPathGenerator(0, true, true);
        HorizontalPathGenerator horizontalPathGeneratorQueen = new HorizontalPathGenerator(0);
        VerticalPathGenerator verticalPathGeneratorQueen = new VerticalPathGenerator(0, true, true);
        MovementValidatorWithoutCollision diagonalWithoutCollistion=new MovementValidatorWithoutCollision(diagonalPathGeneratorQueen);
        MovementValidatorWithoutCollision horizontalWithoutCollisio=new MovementValidatorWithoutCollision(horizontalPathGeneratorQueen);
        MovementValidatorWithoutCollision verticalWithoutCollisio=new MovementValidatorWithoutCollision(verticalPathGeneratorQueen);
        CompositeAndValidator eatingMovementQueen=new CompositeAndValidator(diagonalWithoutCollistion,eatingValidator);
        CompositeAndValidator eatingMovementQueenHorizontal=new CompositeAndValidator(horizontalWithoutCollisio,eatingValidator);
        CompositeAndValidator eatingMovementQueenVertical=new CompositeAndValidator(verticalWithoutCollisio,eatingValidator);
        CompositeAndValidator notEatingMovementQueen=new CompositeAndValidator(diagonalWithoutCollistion,notEatingValidator);
        CompositeAndValidator notEatingMovementQueenHorizontal=new CompositeAndValidator(horizontalWithoutCollisio,notEatingValidator);
        CompositeAndValidator notEatingMovementQueenVertical=new CompositeAndValidator(verticalWithoutCollisio,notEatingValidator);
        CompositeOrValidator queenMovements=new CompositeOrValidator(eatingMovementQueen,notEatingMovementQueen,eatingMovementQueenHorizontal,notEatingMovementQueenHorizontal,eatingMovementQueenVertical,notEatingMovementQueenVertical);
        return queenMovements;

    }

}
