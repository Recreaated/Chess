public class Bishop extends Piece{
    private boolean[][] movesPossible;
    public Bishop (String name){
        super(name);
    }

    //updates the moves possible for the Bishop Pieces
    @Override
    public void updateMovesPossible(int r, int c){
        movesPossible = new boolean[8][8];

        //Up-Right//
        boolean temp = true;
        for (int row = r - 1, col = c + 1; row > 0 && col < 8 && temp; row--, col++) {
            if(MainWindow.chessBoard[row][col].getName() == null){
                movesPossible[row][col] = true;
            } else {
                if(!MainWindow.chessBoard[row][col].getName().startsWith(super.getName().substring(0,1))){
                    movesPossible[row][col] = true;
                }
                temp = false;
            }
        }
        //Up-Left//
        temp = true;
        for (int row = r - 1, col = c - 1; row >= 0 && col >= 0 && temp; row--, col--) {
            if(MainWindow.chessBoard[row][col].getName() == null){
                movesPossible[row][col] = true;
            } else {
                if(!MainWindow.chessBoard[row][col].getName().startsWith(super.getName().substring(0,1))){
                    movesPossible[row][col] = true;
                }
                temp = false;
            }
        }
        //Down-Right//
        temp = true;
        for (int row = r + 1, col = c + 1; row < 8 && col < 8 && temp; row++, col++) {
            if(MainWindow.chessBoard[row][col].getName() == null){
                movesPossible[row][col] = true;
            } else {
                if(!MainWindow.chessBoard[row][col].getName().startsWith(super.getName().substring(0,1))){
                    movesPossible[row][col] = true;
                }
                temp = false;
            }
        }

        //Down-Left//
        temp = true;
        for (int row = r + 1, col = c - 1; row < 8 && col >= 0 && temp; row++, col--) {
            if(MainWindow.chessBoard[row][col].getName() == null){
                movesPossible[row][col] = true;
            } else {
                if(!MainWindow.chessBoard[row][col].getName().startsWith(super.getName().substring(0,1))){
                    movesPossible[row][col] = true;
                }
                temp = false;
            }
        }
    }

    //returns the moves possible
    public boolean[][] getMovesPossible() {
        return movesPossible;
    }
}
