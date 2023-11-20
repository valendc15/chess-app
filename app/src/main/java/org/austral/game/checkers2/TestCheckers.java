package org.austral.game.checkers2;
import org.austral.game.commons.*;
import org.junit.Assert;
import org.junit.Test;

public class TestCheckers {


    private CheckersGame createGameWithEspecificBoard(Board board){
        HasPieceValidator hasPieceValidator = new HasPieceValidator();
        InsideBoundsValidator insideBoundsValidator = new InsideBoundsValidator();
        IsPlayersPieceValidator isPlayersPieceValidator = new IsPlayersPieceValidator();
        CollisionValidator collisionValidator = new CollisionValidator();
        CompositeAndValidator movementRules = new CompositeAndValidator(hasPieceValidator, insideBoundsValidator, isPlayersPieceValidator, collisionValidator);
        return new CheckersGame(board,new Piece[]{},movementRules,new WinningRules[]{new AllPiecesEaten()});
    }
    @Test
    public void testNormalPieceMovementsEating(){
        Board board = new Board(8,8);
        Piece blackPawn = new Piece(Color.BLACK, new Position(2, 6), CheckersMovementFactory.createNormalBlackPieceMovements(), "Pawn",1);
        Piece whitePawn = new Piece(Color.WHITE, new Position(1, 5), CheckersMovementFactory.createNormalWhitePieceMovements(), "Pawn",2);
        board = board.placePiece(blackPawn, blackPawn.getOrigin());
        board = board.placePiece(whitePawn, whitePawn.getOrigin());
        CheckersGame game = createGameWithEspecificBoard(board);
        game=game.move(new Movement(new Position(1,5),new Position(3,7)));
        Assert.assertEquals("Pawn", game.getBoard().getSquares(new Position(3, 7)).getPiece().getSuccesValue().get().getPieceName());
    }

    @Test
    public void testNormalPieceMovementsNormal(){
        Board board = new Board(8,8);
        Piece whitePawn = new Piece(Color.WHITE, new Position(1, 5), CheckersMovementFactory.createNormalWhitePieceMovements(), "Pawn",2);
        board = board.placePiece(whitePawn, whitePawn.getOrigin());
        CheckersGame game = createGameWithEspecificBoard(board);
        game=game.move(new Movement(new Position(1,5),new Position(2,6)));
        Assert.assertEquals("Pawn", game.getBoard().getSquares(new Position(2, 6)).getPiece().getSuccesValue().get().getPieceName());
    }

    @Test
    public void forcedToEat(){
        Board board = new Board(8,8);
        Piece blackPawn = new Piece(Color.BLACK, new Position(2, 6), CheckersMovementFactory.createNormalBlackPieceMovements(), "Pawn",1);
        Piece whitePawn = new Piece(Color.WHITE, new Position(1, 5), CheckersMovementFactory.createNormalWhitePieceMovements(), "Pawn",2);
        board = board.placePiece(blackPawn, blackPawn.getOrigin());
        board = board.placePiece(whitePawn, whitePawn.getOrigin());
        ForcedToEatValidator forcedToEatValidator = new ForcedToEatValidator();
        Assert.assertFalse(forcedToEatValidator.validate(new Movement(new Position(1, 5), new Position(3, 7)), board, new Player(Color.WHITE)));
    }

    @Test
    public void allPiecesEaten(){
        Board board = new Board(8,8);
        Piece blackPawn = new Piece(Color.BLACK, new Position(2, 6), CheckersMovementFactory.createNormalBlackPieceMovements(), "Pawn",1);
        Piece whitePawn = new Piece(Color.WHITE, new Position(1, 5), CheckersMovementFactory.createNormalWhitePieceMovements(), "Pawn",2);
        board = board.placePiece(blackPawn, blackPawn.getOrigin());
        board = board.placePiece(whitePawn, whitePawn.getOrigin());
        Game game=createGameWithEspecificBoard(board);
        Assert.assertFalse(new AllPiecesEaten().validateRule(board, new Player(Color.WHITE)));
        game=game.move(new Movement(new Position(1, 5), new Position(3, 7)));
        Assert.assertTrue(new AllPiecesEaten().validateRule(game.getBoard(), new Player(Color.WHITE)));
    }
}
