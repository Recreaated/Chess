public class Rook extends Piece{
    private boolean[][] movesPossible;
    private int timesMoved = 0;

    public Rook (String name){
        super(name);
    }
    @Override
    public void updateMovesPossible(int r, int c){
        movesPossible = new boolean[8][8];
        boolean temp0 = true;
        //Vertical down//
//        System.out.println("Vertical down:");
        for (int i = r +1; i < 8 && temp0; i++) {
            if(MainWindow.chessBoard[i][c].getName() == null){
                movesPossible[i][c] = true;
            } else {
//                System.out.println("early exit at " + i +", " + y + " temp0 is " + temp0);
                if(!MainWindow.chessBoard[i][c].getName().startsWith(super.getName().substring(0,1))){
                    movesPossible[i][c] = true;
                }
                temp0 = false;
            }
//            System.out.println(i + " " + y);
        }
        //Vertical up//
//        System.out.println("Vertical up:");
        boolean temp1 = true;
        for (int i = r -1; i >= 0  && temp1; i--) {
            if(MainWindow.chessBoard[i][c].getName() == null){
                movesPossible[i][c] = true;
            } else {
                temp1 = false;
//                System.out.println("early exit at " + i +", " + y + " temp1 is " + temp1);

                if(!MainWindow.chessBoard[i][c].getName().startsWith(super.getName().substring(0,1))){
                    movesPossible[i][c] = true;
                }
            }
//            System.out.println(i + " " + y);

        }
        //Horizontal Right//
//        System.out.println("Horizontal Right:");
        boolean temp2 = true;
        for (int i = c +1; i < 8 && temp2; i++) {
            if(MainWindow.chessBoard[r][i].getName() == null){
                movesPossible[r][i] = true;
            } else {
//                System.out.println("early exit at " + x +", " + i + " temp2 is " + temp2);
                if(!MainWindow.chessBoard[r][i].getName().startsWith(super.getName().substring(0,1))){
                    movesPossible[r][i] = true;
                }
                temp2 = false;
            }
//            System.out.println(x + " " + i);
        }

        //Horizontal Left//
//        System.out.println("Horizontal Left:");
        boolean temp3 = true;
        for (int i = c -1; i >= 0 && temp3; i--) {
            if(MainWindow.chessBoard[r][i].getName() == null){
                movesPossible[r][i] = true;
            } else {
//                System.out.println("early exit at " + x +", " + i + " temp3 is " + temp3);
                if(!MainWindow.chessBoard[r][i].getName().startsWith(super.getName().substring(0,1))){
                    movesPossible[r][i] = true;
                }
                temp3= false;
            }
//            System.out.println(x + " " + i);
        }
//        print();
        //return movesPossible;
    }


    public boolean[][] getMovesPossible() {
        return movesPossible;
    }

    @Override
    public int[] possibleChecks(){
        int[] output = {0,0};
        return output;
    }

    @Override
    public void addToTotalMoves(int i){
        timesMoved += i;
    }

    @Override
    public int getTimesMoved(){
        return timesMoved;
    }

    private void print(){
        for(boolean[] r : movesPossible){
            System.out.print("[");
            for(boolean c : r){
                System.out.print(c);
            }
            System.out.println("]");
        }
    }
}
