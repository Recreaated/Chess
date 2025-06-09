public class Pawn extends Piece{

    private boolean[][] movesPossible;
    private boolean hasMoved = false;

    public Pawn (String name){
        super(name);
    }
    @Override
    public void updateMovesPossible(int r, int c){
        movesPossible = new boolean[8][8];
        if(super.getName().substring(0,1).equalsIgnoreCase("w")){
            //White Pawn//
            if (c > 0) {
                if (MainWindow.chessBoard[r - 1][c - 1].getName() != null) {
                    movesPossible[r - 1][c - 1] = true;
                }
            }
            if(MainWindow.chessBoard[r -1][c].getName() == null){
                movesPossible[r -1][c] = true;
            }
            if(!hasMoved){
                if(MainWindow.chessBoard[r -2][c].getName() == null){
                    movesPossible[r -2][c] = true;
                }
            }
            if (c < 7) {
                if (MainWindow.chessBoard[r - 1][c + 1].getName() != null) {
                    movesPossible[r - 1][c + 1] = true;
                }
            }
        } else {
            //Black pawn//
            if (c > 0) {
                if (MainWindow.chessBoard[r + 1][c - 1].getName() != null) {
                    movesPossible[r + 1][c - 1] = true;
                }
            }
            if(MainWindow.chessBoard[r +1][c].getName() == null){
                movesPossible[r +1][c] = true;
            }
            if(!hasMoved){
                if(MainWindow.chessBoard[r +2][c].getName() == null){
                    movesPossible[r +2][c] = true;
                }
            }
            if (c < 7) {
                if (MainWindow.chessBoard[r + 1][c + 1].getName() != null) {
                    movesPossible[r + 1][c + 1] = true;
                }
            }
        }
        //return movesPossible;
    }
    @Override
    public int[] possibleChecks(){
        int[] output = {0,0};
        return output;
    }
    public boolean[][] getMovesPossible() {
        return movesPossible;
    }

    private void print(){
        for(boolean[] r : movesPossible){
            System.out.print("[");
            for(boolean c : r){
                System.out.print(((c)?"x":"o")+ " ");
            }
            System.out.println("]");
        }
    }
}
