public class Pawn extends Piece{

    private boolean[][] movesPossible;
    private int timesMoved = 0;
    private boolean leftChance = true;
    private boolean rightChance = true;


    public Pawn (String name){
        super(name);
    }
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
                    if (MainWindow.chessBoard[r - 2][c].getName() == null) {
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
//                System.out.println(MainWindow.chessBoard[r][c + 1].getName() != null);
//                System.out.println(MainWindow.chessBoard[r][c - 1].getName() != null);
//                System.out.print(r);
//                System.out.println("check for En passant: W");
                if (c != 7 && MainWindow.chessBoard[r][c + 1].getName() != null) {
//                    System.out.println("En passant(Right) check1");
                    if (MainWindow.chessBoard[r][c + 1].getName().contains("Pawn")) {
//                        System.out.println("En passant(Right) check2");
                        if (!MainWindow.chessBoard[r][c + 1].getName().startsWith(super.getName().substring(0, 1))) {
                            if(MainWindow.chessBoard[r][c + 1].getPiece().getTimesMoved() == 1 && rightChance) {
                                rightChance = false;
                                movesPossible[r - 1][c + 1] = true;
//                                System.out.println("En passant(Right)");
                            }
                        }
                    }
                }
                if ((c != 0) && (MainWindow.chessBoard[r][c - 1].getName() != null)) {
//                    System.out.println("En passant(Left) check1");
                    if (MainWindow.chessBoard[r][c - 1].getName().contains("Pawn")) {
//                        System.out.println("En passant(Left) check2");
                        if (!MainWindow.chessBoard[r][c - 1].getName().startsWith(super.getName().substring(0, 1))) {
//                            System.out.println("Pawn moved: " + MainWindow.chessBoard[r][c - 1].getPiece().getTimesMoved());
//                            System.out.println("Piece in Question: " + MainWindow.chessBoard[r][c - 1].getPiece().getName());

                            if(MainWindow.chessBoard[r][c - 1].getPiece().getTimesMoved() == 1 && leftChance) {
                                leftChance = false;
                                movesPossible[r - 1][c - 1] = true;
//                                System.out.println("En passant(Left)");
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
                    if (MainWindow.chessBoard[r + 2][c].getName() == null) {
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
//                System.out.println(r);
//                System.out.println(c);
//                System.out.print(r);
//                System.out.println("check for En passant: B");
                if (c != 7 && MainWindow.chessBoard[r][c + 1].getName() != null ) {
//                    System.out.println("En passant(Right) check1");
                    if (MainWindow.chessBoard[r][c + 1].getName().contains("Pawn")) {
//                        System.out.println("En passant(Right) check2");
                        if (!MainWindow.chessBoard[r][c + 1].getName().startsWith(super.getName().substring(0, 1))) {
                            if(MainWindow.chessBoard[r][c + 1].getPiece().getTimesMoved() == 1 && rightChance) {
                                rightChance = false;
                                movesPossible[r + 1][c + 1] = true;
//                                System.out.println("En passant(Right)");
                            }
                        }
                    }
                }
                if (c != 0 && MainWindow.chessBoard[r][c - 1].getName() != null) {
//                    System.out.println("En passant(Left) check1");
                    if (MainWindow.chessBoard[r][c - 1].getName().contains("Pawn")) {
//                        System.out.println("En passant(Left) check2");
                        if (!MainWindow.chessBoard[r][c - 1].getName().startsWith(super.getName().substring(0, 1))) {
                            if(MainWindow.chessBoard[r][c - 1].getPiece().getTimesMoved() == 1 && leftChance) {
                                leftChance = false;
                                movesPossible[r + 1][c - 1] = true;
//                                System.out.println("En passant(Left)");
                            }
                        }
                    }
                }
            }
        }
        //return movesPossible;
    }
    public boolean[][] getMovesPossible() {
        return movesPossible;
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
                System.out.print(((c)?"x":"o")+ " ");
            }
            System.out.println("]");
        }
    }
}
