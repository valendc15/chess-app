package org.austral.game.commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Board {
    private int width;
    private int height;
    private Square[] squares;

    private MovementHistory movementHistory;

    public Board(int width, int height, Square[] squares, MovementHistory movementHistory) {
        this.width = width;
        this.height = height;
        this.squares = squares;
        this.movementHistory= movementHistory;
    }

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.squares = buildBoard();
        this.movementHistory=new MovementHistory(new ArrayList<Board>(), new ArrayList<Movement>());
    }

    public Board setMovementHistory(MovementHistory movementHistory) {
        return new Board(width, height, squares, movementHistory);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    private Square[] buildBoard(){
        Square[] squares = new Square[width * height];
        for (int i = 0; i < squares.length; i++) {
            squares[i] = new Square(new Position(i % width, i / width), null);
        }
        return squares;
    }

    public Square getSquares(Position position){
        if (isValidPosition(position)){
        return squares[position.getPositionY() * width + position.getPositionX()];}
        else{
            throw new RuntimeException("org.austral.game.commons.Square does not exist");
        }
    }

    public boolean isValidPosition(Position position){
        return position.getPositionX() >= 0 && position.getPositionX() < width && position.getPositionY() >= 0 && position.getPositionY() < height;
    }

    public Board placePiece (Piece piece, Position position){
        if (isValidPosition(position)) {
            Square[] newSquares = squares.clone();
            newSquares[position.getPositionY() * width + position.getPositionX()] = new Square(position, piece.move()); // Place the piece at the desired position
            return new Board(width, height, newSquares, movementHistory);
        }
        return this;
    }

    public Board removePiece(Position position) {
        if (isValidPosition(position)) {
            Square[] newSquares = squares.clone();
            newSquares[position.getPositionY() * width + position.getPositionX()] = new Square(position, null); // Remove the piece at the desired position
            return new Board(width, height, newSquares, movementHistory);
        }
        return this;
    }

    public boolean checkIfPathIsEmpty(Position[] path) {
        for (int i = 1; i < path.length - 1; i++) {
            Position position = path[i];
            if (getSquares(position).hasPiece()) {
                return false;
            }
        }
        return true;
    }

    public Board movePiece(Movement movement) {
        Board previousBoard = this;
        Position from = movement.getFrom();
        Position to = movement.getTo();

        Piece piece = getSquares(from).getPiece().getSuccesValue().get();

        Board newboard = removePiece(from);
        newboard = newboard.placePiece(piece, to);
        newboard= newboard.setMovementHistory(movementHistory.addMovement(previousBoard,movement));
        return newboard;
    }

    public boolean isValidPath(Position[] path) {
        if (path == null) {
            return false;
        }

        for (Position position : path) {
            if (!isValidPosition(position)) {
                return false;
            }
        }

        return true;
    }
    public GetResult<Position, Boolean> findKingPosition(Color color){
        for (int i = 0; i < squares.length; i++) {
            if (squares[i].getPiece().getSuccesValue().isPresent()){
                if (squares[i].getPiece().getSuccesValue().get().getColor() == color && squares[i].getPiece().getSuccesValue().get().isCheckable()){
                    return new GetResult<>(Optional.of(squares[i].getPosition()), false);
                }
            }
        }
        return new GetResult<>(Optional.empty(), true);
    }

    public boolean isCheck(Color color){
        GetResult<Position, Boolean> kingPosition = findKingPosition(color);
        if (kingPosition.getErrorValue()){
            return false;
        }
        for (int i = 0; i < squares.length; i++) {
            if (squares[i].getPiece().getSuccesValue().isPresent()){
                if (squares[i].getPiece().getSuccesValue().get().getColor() != color){
                    if (squares[i].getPiece().getSuccesValue().get().isLegalMove(new Movement(squares[i].getPosition(), kingPosition.getSuccesValue().get()), this, new Player(color))){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public List<Piece> getPieces(Color color){
        List<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < squares.length; i++) {
            if (squares[i].getPiece().getSuccesValue().isPresent()){
                if (squares[i].getPiece().getSuccesValue().get().getColor() == color){
                    pieces.add(squares[i].getPiece().getSuccesValue().get());
                }
            }
        }
        return pieces;
    }

    public boolean hasPiece(Position position){
        return getSquares(position).hasPiece();
    }

    public GetResult<Piece, Boolean> getPiece(Position position){
        return getSquares(position).getPiece();
    }

    public GetResult<Position, Boolean> findPiece(Piece piece){
        for (int i = 0; i < squares.length; i++) {
            if (squares[i].getPiece().getSuccesValue().isPresent()){
                if (squares[i].getPiece().getSuccesValue().get().equals(piece)){
                    return new GetResult<>(Optional.of(squares[i].getPosition()), false);
                }
            }
        }
        return new GetResult<>(Optional.empty(), true);
    }

    public Square[] getSquares() {
        return squares;
    }

    public Piece getPiecefromPosition(Position position){
        return getSquares(position).getPiece().getSuccesValue().get();
    }

    public MovementHistory getMovementHistory() {
        return movementHistory;
    }
}
