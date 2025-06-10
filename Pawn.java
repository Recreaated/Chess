public class Pawn extends Piece{

    private boolean[][] movesPossible;
    private int timesMoved = 0;
    private boolean leftChance = true;
    private boolean rightChance = true;


    public Pawn (String name){
        super(name);
    }

    //updates the moves possible for the pawn Pieces, including special cases
    @Override
    public void updateMovesPossible(int r, int c){
        movesPossible = new boolean[8][8];
        if(super.getName().substring(0,1).equalsIgnoreCase("w")){
            //White Pawn//
            if( r >= 1) {
                if (c > 0) {
                    if (MainWindow.chessBoard[r - 1][c - 1].getName() != null) {
                        movesPossible[r - 1][c - 1] = true;
                    }
                }
                if (MainWindow.chessBoard[r - 1][c].getName() == null) {
                    movesPossible[r - 1][c] = true;
                }
                if (timesMoved == 0) {
                    if (movesPossible[r - 1][c] && MainWindow.chessBoard[r - 2][c].getName() == null) {
                        movesPossible[r - 2][c] = true;
                    }
                }
                if (c < 7) {
                    if (MainWindow.chessBoard[r - 1][c + 1].getName() != null) {
                        movesPossible[r - 1][c + 1] = true;
                    }
                }
            }
            //En Passant Special Case
            if(r == 3 ){
                if (c != 7 && MainWindow.chessBoard[r][c + 1].getName() != null) {
                    if (MainWindow.chessBoard[r][c + 1].getName().contains("Pawn")) {
                        if (!MainWindow.chessBoard[r][c + 1].getName().startsWith(super.getName().substring(0, 1))) {
                            if(MainWindow.chessBoard[r][c + 1].getPiece().getTimesMoved() == 1 && rightChance) {
                                rightChance = false;
                                movesPossible[r - 1][c + 1] = true;
                            }
                        }
                    }
                }
                if ((c != 0) && (MainWindow.chessBoard[r][c - 1].getName() != null)) {
                    if (MainWindow.chessBoard[r][c - 1].getName().contains("Pawn")) {
                        if (!MainWindow.chessBoard[r][c - 1].getName().startsWith(super.getName().substring(0, 1))) {
                            if(MainWindow.chessBoard[r][c - 1].getPiece().getTimesMoved() == 1 && leftChance) {
                                leftChance = false;
                                movesPossible[r - 1][c - 1] = true;
                            }
                        }
                    }
                }
            }
        } else {
            //Black pawn//
            if(r <= 6) {
                if (c > 0) {
                    if (MainWindow.chessBoard[r + 1][c - 1].getName() != null) {
                        movesPossible[r + 1][c - 1] = true;
                    }
                }
                if (MainWindow.chessBoard[r + 1][c].getName() == null) {
                    movesPossible[r + 1][c] = true;
                }
                if (timesMoved == 0) {
                    if (movesPossible[r + 1][c] && MainWindow.chessBoard[r + 2][c].getName() == null) {
                        movesPossible[r + 2][c] = true;
                    }
                }
                if (c < 7) {
                    if (MainWindow.chessBoard[r + 1][c + 1].getName() != null) {
                        movesPossible[r + 1][c + 1] = true;
                    }
                }
            }
            //En Passant Special Case
            if(r == 4){
                if (c != 7 && MainWindow.chessBoard[r][c + 1].getName() != null ) {
                    if (MainWindow.chessBoard[r][c + 1].getName().contains("Pawn")) {
                        if (!MainWindow.chessBoard[r][c + 1].getName().startsWith(super.getName().substring(0, 1))) {
                            if(MainWindow.chessBoard[r][c + 1].getPiece().getTimesMoved() == 1 && rightChance) {
                                rightChance = false;
                                movesPossible[r + 1][c + 1] = true;
                            }
                        }
                    }
                }
                if (c != 0 && MainWindow.chessBoard[r][c - 1].getName() != null) {
                    if (MainWindow.chessBoard[r][c - 1].getName().contains("Pawn")) {
                        if (!MainWindow.chessBoard[r][c - 1].getName().startsWith(super.getName().substring(0, 1))) {
                            if(MainWindow.chessBoard[r][c - 1].getPiece().getTimesMoved() == 1 && leftChance) {
                                leftChance = false;
                                movesPossible[r + 1][c - 1] = true;
                            }
                        }
                    }
                }
            }
        }
    } // end of update Method

    //returns the possible moves this piece can make
    public boolean[][] getMovesPossible() {
        return movesPossible;
    }

    //updates the timesMoved int
    @Override
    public void addToTotalMoves(int i){
        timesMoved += i;
    }

    //returns the certain amount of moves that this piece has made
    @Override
    public int getTimesMoved(){
        return timesMoved;
    }
}
