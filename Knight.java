public class Knight extends Piece{
    private boolean[][] movesPossible;

    public Knight (String name){
        super(name);
    }

    //updates the moves possible for the Knight Pieces
    @Override
    public void updateMovesPossible(int r, int c){
        movesPossible = new boolean[8][8];
        //down L
        if (r <= 5 && c >= 1) {
            if (MainWindow.chessBoard[r + 2][c - 1].getName() == null||
                    !MainWindow.chessBoard[r + 2][c - 1].getName().startsWith(super.getName().substring(0,1))) {
                movesPossible[r + 2][c - 1] = true;

            }
        }
        if (r <= 5 && c <= 6) {
            if (MainWindow.chessBoard[r + 2][c + 1].getName() == null ||
                    !MainWindow.chessBoard[r + 2][c + 1].getName().startsWith(super.getName().substring(0,1))){
                movesPossible[r + 2][c + 1] = true;

            }
        }
        //Up L
        if (r >= 2 && c >= 1) {
            if (MainWindow.chessBoard[r - 2][c - 1].getName() == null||
                    !MainWindow.chessBoard[r - 2][c - 1].getName().startsWith(super.getName().substring(0,1))) {
                movesPossible[r - 2][c - 1] = true;

            }
        }
        if (r >= 2 && c <= 6) {
            if (MainWindow.chessBoard[r - 2][c + 1].getName() == null||
                    !MainWindow.chessBoard[r - 2][c + 1].getName().startsWith(super.getName().substring(0,1))) {
                movesPossible[r - 2][c + 1] = true;

            }
        }
        ///////////////////////////////////////////////////
        //Left L
        if (r <= 6 && c >= 2) {
            if (MainWindow.chessBoard[r + 1][c - 2].getName() == null||
                    !MainWindow.chessBoard[r + 1][c - 2].getName().startsWith(super.getName().substring(0,1))) {
                movesPossible[r + 1][c - 2] = true;

            }
        }
        if (r >= 1 && c >= 2) {
            if (MainWindow.chessBoard[r - 1][c - 2].getName() == null||
                    !MainWindow.chessBoard[r - 1][c - 2].getName().startsWith(super.getName().substring(0,1))) {
                movesPossible[r - 1][c - 2] = true;

            }
        }
        //Right L
        if (r >= 1 && c <= 5) {
            if (MainWindow.chessBoard[r - 1][c + 2].getName() == null||
                    !MainWindow.chessBoard[r - 1][c + 2].getName().startsWith(super.getName().substring(0,1))) {
                movesPossible[r - 1][c + 2] = true;

            }
        }
        if (r <= 6 && c <= 5) {
            if (MainWindow.chessBoard[r + 1][c + 2].getName() == null||
                    !MainWindow.chessBoard[r + 1][c + 2].getName().startsWith(super.getName().substring(0,1))) {
                movesPossible[r + 1][c + 2] = true;

            }
        }
    }

    //returns the moves possible
    public boolean[][] getMovesPossible() {
        return movesPossible;
    }
}
