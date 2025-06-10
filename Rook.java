public class Rook extends Piece{
    private boolean[][] movesPossible;
    private int timesMoved = 0;

    public Rook (String name){
        super(name);
    }

    //updates the moves possible for the rook Pieces
    @Override
    public void updateMovesPossible(int r, int c){
        movesPossible = new boolean[8][8];
        boolean temp0 = true;
        //Vertical down//
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


    //returns the moves possible
    public boolean[][] getMovesPossible() {
        return movesPossible;
    }

    //updates the timesMoved int
    @Override
    public void addToTotalMoves(int i){
        timesMoved += i;
    }

    //returns the times this piece was moved
    @Override
    public int getTimesMoved(){
        return timesMoved;
    }
}
