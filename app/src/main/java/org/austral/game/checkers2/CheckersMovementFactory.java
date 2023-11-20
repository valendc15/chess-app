package org.austral.game.checkers2;

import org.austral.game.chess.MovementValidatorWithCollision;
import org.austral.game.commons.CompositeAndValidator;
import org.austral.game.commons.CompositeOrValidator;
import org.austral.game.commons.DiagonalPathGenerator;
import org.austral.game.commons.MovementValidatorWithoutCollision;

public class CheckersMovementFactory {

    public static CannotEatValidator cannotEatValidator = new CannotEatValidator();
    public static ForcedToEatValidator forcedToEatValidator = new ForcedToEatValidator();
    public static LastMovementWasEatValidator lastMovementWasEatValidator = new LastMovementWasEatValidator();
    public static LastMovementWasntEatValidator lastMovementWasntEatValidator = new LastMovementWasntEatValidator();
    public static CheckersEatingValidator checkersEatingValidator = new CheckersEatingValidator();
    public static QueenEatingValidator queenEatingValidator= new QueenEatingValidator();

    public static CompositeOrValidator createNormalWhitePieceMovements(){
        DiagonalPathGenerator checkersDiagonalNormalMovement = new DiagonalPathGenerator(1, true, false );
        DiagonalPathGenerator checkersDiagonalEatingMovement = new DiagonalPathGenerator(2, true, false);
        DiagonalPathGenerator checkersChainEatingMovement = new DiagonalPathGenerator(2, true, true);
        MovementValidatorWithoutCollision checkersEatingMovementValidator = new MovementValidatorWithoutCollision(checkersDiagonalEatingMovement);
        MovementValidatorWithoutCollision checkersNormalMovementValidator = new MovementValidatorWithoutCollision(checkersDiagonalNormalMovement);
        MovementValidatorWithoutCollision checkersChainEatingMovementValidator = new MovementValidatorWithoutCollision(checkersChainEatingMovement);
        CompositeAndValidator normalMovement = new CompositeAndValidator(forcedToEatValidator,cannotEatValidator, lastMovementWasntEatValidator, checkersNormalMovementValidator);
        CompositeAndValidator eatingMovement = new CompositeAndValidator(lastMovementWasntEatValidator, checkersEatingMovementValidator, checkersEatingValidator);
        CompositeAndValidator chainEatingMovement = new CompositeAndValidator(lastMovementWasEatValidator, checkersChainEatingMovementValidator, checkersEatingValidator);
        return new CompositeOrValidator(chainEatingMovement, eatingMovement, normalMovement);
    }

    public static CompositeOrValidator createNormalBlackPieceMovements(){
        DiagonalPathGenerator checkersDiagonalNormalMovement = new DiagonalPathGenerator(1, false, true );
        DiagonalPathGenerator checkersDiagonalEatingMovement = new DiagonalPathGenerator(2, false,true  );
        DiagonalPathGenerator checkersChainEatingMovement = new DiagonalPathGenerator(2, true, true);
        MovementValidatorWithoutCollision checkersEatingMovementValidator = new MovementValidatorWithoutCollision(checkersDiagonalEatingMovement);
        MovementValidatorWithoutCollision checkersNormalMovementValidator = new MovementValidatorWithoutCollision(checkersDiagonalNormalMovement);
        MovementValidatorWithoutCollision checkersChainEatingMovementValidator = new MovementValidatorWithoutCollision(checkersChainEatingMovement);
        CompositeAndValidator normalMovement = new CompositeAndValidator(forcedToEatValidator,cannotEatValidator, lastMovementWasntEatValidator, checkersNormalMovementValidator);
        CompositeAndValidator eatingMovement = new CompositeAndValidator(lastMovementWasntEatValidator, checkersEatingMovementValidator, checkersEatingValidator);
        CompositeAndValidator chainEatingMovement = new CompositeAndValidator(lastMovementWasEatValidator, checkersChainEatingMovementValidator, checkersEatingValidator);
        return new CompositeOrValidator(chainEatingMovement, eatingMovement, normalMovement);
    }

    public static CompositeOrValidator createQueenPieceMovements(){
        DiagonalPathGenerator checkersDiagonalNormalMovement = new DiagonalPathGenerator(0, true, true );
        DiagonalPathGenerator checkersDiagonalEatingMovement = new DiagonalPathGenerator(0, true,true  );
        DiagonalPathGenerator checkersChainEatingMovement = new DiagonalPathGenerator(0, true, true);
        MovementValidatorWithoutCollision checkersEatingMovementValidator = new MovementValidatorWithoutCollision(checkersDiagonalEatingMovement);
        MovementValidatorWithCollision checkersNormalMovementValidator = new MovementValidatorWithCollision(checkersDiagonalNormalMovement);
        MovementValidatorWithoutCollision checkersChainEatingMovementValidator = new MovementValidatorWithoutCollision(checkersChainEatingMovement);;
        CompositeAndValidator normalMovement = new CompositeAndValidator(forcedToEatValidator,cannotEatValidator, lastMovementWasntEatValidator, checkersNormalMovementValidator);
        CompositeAndValidator eatingMovement = new CompositeAndValidator(lastMovementWasntEatValidator, checkersEatingMovementValidator, queenEatingValidator);
        CompositeAndValidator chainEatingMovement = new CompositeAndValidator(lastMovementWasEatValidator, checkersChainEatingMovementValidator, queenEatingValidator);
        return new CompositeOrValidator(chainEatingMovement, eatingMovement, normalMovement);
    }


}
