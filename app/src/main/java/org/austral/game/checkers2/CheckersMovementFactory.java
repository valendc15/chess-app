package org.austral.game.checkers2;

import org.austral.game.commons.CompositeAndValidator;
import org.austral.game.commons.CompositeOrValidator;
import org.austral.game.commons.DiagonalPathGenerator;
import org.austral.game.commons.MovementValidatorWithoutCollision;

public class CheckersMovementFactory {

    public static CompositeOrValidator createNormalWhitePieceMovements(){
        DiagonalPathGenerator checkersDiagonalNormalMovement = new DiagonalPathGenerator(1, true, false );
        DiagonalPathGenerator checkersDiagonalEatingMovement = new DiagonalPathGenerator(2, true, false);
        DiagonalPathGenerator checkersChainEatingMovement = new DiagonalPathGenerator(2, true, true);
        MovementValidatorWithoutCollision checkersEatingMovementValidator = new MovementValidatorWithoutCollision(checkersDiagonalEatingMovement);
        MovementValidatorWithoutCollision checkersNormalMovementValidator = new MovementValidatorWithoutCollision(checkersDiagonalNormalMovement);
        MovementValidatorWithoutCollision checkersChainEatingMovementValidator = new MovementValidatorWithoutCollision(checkersChainEatingMovement);
        CannotEatValidator cannotEatValidator = new CannotEatValidator();
        LastMovementWasEatValidator lastMovementWasEatValidator = new LastMovementWasEatValidator();
        LastMovementWasntEatValidator lastMovementWasntEatValidator = new LastMovementWasntEatValidator();
        CheckersEatingValidator checkersEatingValidator = new CheckersEatingValidator();
        CompositeAndValidator normalMovement = new CompositeAndValidator(cannotEatValidator, lastMovementWasntEatValidator, checkersNormalMovementValidator);
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
        CannotEatValidator cannotEatValidator = new CannotEatValidator();
        CheckersEatingValidator checkersEatingValidator = new CheckersEatingValidator();
        LastMovementWasEatValidator lastMovementWasEatValidator = new LastMovementWasEatValidator();
        LastMovementWasntEatValidator lastMovementWasntEatValidator = new LastMovementWasntEatValidator();
        CompositeAndValidator normalMovement = new CompositeAndValidator(cannotEatValidator, lastMovementWasntEatValidator, checkersNormalMovementValidator);
        CompositeAndValidator eatingMovement = new CompositeAndValidator(lastMovementWasntEatValidator, checkersEatingMovementValidator, checkersEatingValidator);
        CompositeAndValidator chainEatingMovement = new CompositeAndValidator(lastMovementWasEatValidator, checkersChainEatingMovementValidator, checkersEatingValidator);
        return new CompositeOrValidator(chainEatingMovement, eatingMovement, normalMovement);
    }

    public static CompositeOrValidator createQueenPieceMovements(){
        DiagonalPathGenerator checkersDiagonalNormalMovement = new DiagonalPathGenerator(1, true, true );
        DiagonalPathGenerator checkersDiagonalEatingMovement = new DiagonalPathGenerator(2, true,true  );
        DiagonalPathGenerator checkersChainEatingMovement = new DiagonalPathGenerator(2, true, true);
        MovementValidatorWithoutCollision checkersEatingMovementValidator = new MovementValidatorWithoutCollision(checkersDiagonalEatingMovement);
        MovementValidatorWithoutCollision checkersNormalMovementValidator = new MovementValidatorWithoutCollision(checkersDiagonalNormalMovement);
        MovementValidatorWithoutCollision checkersChainEatingMovementValidator = new MovementValidatorWithoutCollision(checkersChainEatingMovement);
        CannotEatValidator cannotEatValidator = new CannotEatValidator();
        CheckersEatingValidator checkersEatingValidator = new CheckersEatingValidator();
        LastMovementWasEatValidator lastMovementWasEatValidator = new LastMovementWasEatValidator();
        LastMovementWasntEatValidator lastMovementWasntEatValidator = new LastMovementWasntEatValidator();
        CompositeAndValidator normalMovement = new CompositeAndValidator(cannotEatValidator, lastMovementWasntEatValidator, checkersNormalMovementValidator);
        CompositeAndValidator eatingMovement = new CompositeAndValidator(lastMovementWasntEatValidator, checkersEatingMovementValidator, checkersEatingValidator);
        CompositeAndValidator chainEatingMovement = new CompositeAndValidator(lastMovementWasEatValidator, checkersChainEatingMovementValidator, checkersEatingValidator);
        return new CompositeOrValidator(chainEatingMovement, eatingMovement, normalMovement);
    }


}
