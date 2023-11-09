package org.austral.game.checkers;

import org.austral.game.commons.CompositeOrValidator;
import org.austral.game.commons.DiagonalPathGenerator;

public class CheckersValidMovementsCreator {

    public static CompositeOrValidator createNormalWhitePieceMovements(){
        DiagonalPathGenerator checkersDiagonalNormalMovement = new DiagonalPathGenerator(1, true, false );
        DiagonalPathGenerator checkersDiagonalEatingMovement = new DiagonalPathGenerator(2, true, false);
        DiagonalPathGenerator checkersChainEatingMovement = new DiagonalPathGenerator(2, true, true);
        CheckersEatingMovementValidator checkersEatingMovementValidator = new CheckersEatingMovementValidator(checkersDiagonalEatingMovement);
        CheckersNotEatingMovementValidator checkersNormalMovementValidator = new CheckersNotEatingMovementValidator(checkersDiagonalNormalMovement);
        CheckersEatingChainMovementValidator checkersChainEatingMovementValidator = new CheckersEatingChainMovementValidator(checkersChainEatingMovement);
        return new CompositeOrValidator(checkersChainEatingMovementValidator, checkersEatingMovementValidator, checkersNormalMovementValidator);
    }

    public static CompositeOrValidator createNormalBlackPieceMovements(){
        DiagonalPathGenerator checkersDiagonalNormalMovement = new DiagonalPathGenerator(1, false, true );
        DiagonalPathGenerator checkersDiagonalEatingMovement = new DiagonalPathGenerator(2, false,true  );
        DiagonalPathGenerator checkersChainEatingMovement = new DiagonalPathGenerator(2, true, true);
        CheckersEatingMovementValidator checkersEatingMovementValidator = new CheckersEatingMovementValidator(checkersDiagonalEatingMovement);
        CheckersNotEatingMovementValidator checkersNormalMovementValidator = new CheckersNotEatingMovementValidator(checkersDiagonalNormalMovement);
        CheckersEatingChainMovementValidator checkersChainEatingMovementValidator = new CheckersEatingChainMovementValidator(checkersChainEatingMovement);
        return new CompositeOrValidator(checkersChainEatingMovementValidator, checkersEatingMovementValidator, checkersNormalMovementValidator);
    }

    public static CompositeOrValidator createQueenPieceMovements(){
        DiagonalPathGenerator checkersDiagonalPathGenerator = new DiagonalPathGenerator(2,true,true);
        DiagonalPathGenerator checkerNormalPathGenerator= new DiagonalPathGenerator(1, true, true);
        DiagonalPathGenerator checkersChainEatingPathGenerator = new DiagonalPathGenerator(2, true, true);
        CheckersEatingMovementValidator checkersEatingMovementValidator = new CheckersEatingMovementValidator(checkersDiagonalPathGenerator);
        CheckersNotEatingMovementValidator checkersNormalMovementValidator = new CheckersNotEatingMovementValidator(checkerNormalPathGenerator);
        CheckersEatingChainMovementValidator checkersChainEatingMovementValidator = new CheckersEatingChainMovementValidator(checkersChainEatingPathGenerator);
        return new CompositeOrValidator(checkersChainEatingMovementValidator, checkersEatingMovementValidator, checkersNormalMovementValidator);
    }


}
