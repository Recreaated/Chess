public class Queen extends Piece{
    private boolean[][] movesPossible;

    public Queen(String name){
        super(name);
    }

    //updates the moves possible for the Queen Pieces
    //utilizes the same detection as the rook and bishop
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

        //Vertical down//
        boolean temp0 = true;
        for (int i = r +1; i < 8 && temp0; i++) {
            if(MainWindow.chessBoard[i][c].getName() == null){
                movesPossible[i][c] = true;
            } else {
                if(!MainWindow.chessBoard[i][c].getName().startsWith(super.getName().substring(0,1))){
                    movesPossible[i][c] = true;
                }
                temp0 = false;
            }
        }
        //Vertical up//
        boolean temp1 = true;
        for (int i = r -1; i >= 0  && temp1; i--) {
            if(MainWindow.chessBoard[i][c].getName() == null){
                movesPossible[i][c] = true;
            } else {
                temp1 = false;

                if(!MainWindow.chessBoard[i][c].getName().startsWith(super.getName().substring(0,1))){
                    movesPossible[i][c] = true;
                }
            }

        }
        //Horizontal Right//
        boolean temp2 = true;
        for (int i = c +1; i < 8 && temp2; i++) {
            if(MainWindow.chessBoard[r][i].getName() == null){
                movesPossible[r][i] = true;
            } else {
                if(!MainWindow.chessBoard[r][i].getName().startsWith(super.getName().substring(0,1))){
                    movesPossible[r][i] = true;
                }
                temp2 = false;
            }
        }

        //Horizontal Left//
        boolean temp3 = true;
        for (int i = c -1; i >= 0 && temp3; i--) {
            if(MainWindow.chessBoard[r][i].getName() == null){
                movesPossible[r][i] = true;
            } else {
                if(!MainWindow.chessBoard[r][i].getName().startsWith(super.getName().substring(0,1))){
                    movesPossible[r][i] = true;
                }
                temp3= false;
            }
        }
    }

    //returns the moves possible with this piece
    public boolean[][] getMovesPossible() {
        return movesPossible;
    }

}
