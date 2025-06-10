import java.util.ArrayList;

public class King extends Piece{
    private ArrayList<Tile> attackingPeices= new ArrayList<Tile>();
    private int timesMoved = 0;
    private boolean[][] movesPossible;
    private boolean[][] oppositePieceChecks = new boolean[8][8];
    private boolean tempGhostPiece = false;


    public King (String name){
        super(name);
    }
    @Override
    public void updateMovesPossible(int r, int c){
        attackingPeices.clear();
        movesPossible = new boolean[8][8];
        if(GameMechanics.getTurnIndex() >= 2) {
            oppositePieceChecks = updateOpposite(r,c);
        }
        //Left
        if(c != 0){
            if(r != 0){
                if (MainWindow.chessBoard[r - 1][c - 1].getName() == null||
                        !MainWindow.chessBoard[r - 1][c - 1].getName().startsWith(super.getName().substring(0,1))) {
                    if(!oppositePieceChecks[r - 1][c - 1]) {
                        movesPossible[r - 1][c - 1] = true;
                    }
                }
            }
            if (MainWindow.chessBoard[r][c - 1].getName() == null||
                    !MainWindow.chessBoard[r][c - 1].getName().startsWith(super.getName().substring(0,1))) {
                if(!oppositePieceChecks[r][c - 1]) {
                    movesPossible[r][c - 1] = true;
                }
            }
            if(r != 7){
                if (MainWindow.chessBoard[r + 1][c - 1].getName() == null||
                        !MainWindow.chessBoard[r + 1][c - 1].getName().startsWith(super.getName().substring(0,1))) {
                    if(!oppositePieceChecks[r + 1][c - 1]) {
                        movesPossible[r + 1][c - 1] = true;
                    }
                }
            }
        }
        //Up and Down
        if(r != 0) {
            if (MainWindow.chessBoard[r - 1][c].getName() == null ||
                    !MainWindow.chessBoard[r - 1][c].getName().startsWith(super.getName().substring(0, 1))) {
                if(!oppositePieceChecks[r - 1][c]) {
                    movesPossible[r - 1][c] = true;
                }
            }
        }
        if(r != 7){
            if (MainWindow.chessBoard[r + 1][c].getName() == null||
                    !MainWindow.chessBoard[r + 1][c].getName().startsWith(super.getName().substring(0,1))) {
                if(!oppositePieceChecks[r + 1][c]) {
                    movesPossible[r + 1][c] = true;
                }
            }
        }

        //Right
        if(c != 7){
            if(r != 0){
                if (MainWindow.chessBoard[r - 1][c + 1].getName() == null||
                        !MainWindow.chessBoard[r - 1][c + 1].getName().startsWith(super.getName().substring(0,1))) {
                    if(!oppositePieceChecks[r - 1][c + 1]) {
                        movesPossible[r - 1][c + 1] = true;
                    }
                }
            }
            if (MainWindow.chessBoard[r][c + 1].getName() == null||
                    !MainWindow.chessBoard[r][c + 1].getName().startsWith(super.getName().substring(0,1))) {
                if(!oppositePieceChecks[r][c + 1]) {
                    movesPossible[r][c + 1] = true;
                }
            }
            if(r != 7){
                if (MainWindow.chessBoard[r + 1][c + 1].getName() == null||
                        !MainWindow.chessBoard[r + 1][c + 1].getName().startsWith(super.getName().substring(0,1))) {
                    if(!oppositePieceChecks[r + 1][c + 1]) {
                        movesPossible[r + 1][c + 1] = true;
                    }
                }
            }
        }
//        print();
        //Special Case - Castling

        if(!tempGhostPiece&&attackingPeices.isEmpty() && timesMoved == 0){
//            System.out.println("Check if castle is possible: " + super.getName().substring(0,1));
            //Long Castle
            if(c >= 4 && MainWindow.chessBoard[r][c - 1].getName() == null && movesPossible[r][c-1]){
//                System.out.println("LongCastle Check1");
                tempGhostPiece = true;
                if(MainWindow.chessBoard[r][c - 2].getName() == null && GameMechanics.swap(MainWindow.chessBoard[r][c - 2],MainWindow.chessBoard[r][c])){
//                    System.out.println("LongCastle Check2");
                    if(MainWindow.chessBoard[r][c - 3].getName() == null){
//                        System.out.println("LongCastle Check3");
                        if(MainWindow.chessBoard[r][c - 4].getName().equalsIgnoreCase("W.Rook") && MainWindow.chessBoard[r][c - 3].getPiece().getTimesMoved() == 0){
//                            System.out.println(super.getName() + " can LongCastle");
                            movesPossible[r][c - 2] = true;
                        }
                    }
                }
            }
            tempGhostPiece = false;
            //short Castle
            if(c <= 4 && MainWindow.chessBoard[r][c + 1].getName() == null && movesPossible[r][c+1]){
//                System.out.println("ShortCastle Check1");
                tempGhostPiece = true;
                if(MainWindow.chessBoard[r][c + 2].getName() == null && GameMechanics.swap(MainWindow.chessBoard[r][c + 2],MainWindow.chessBoard[r][c])){
//                    System.out.println("ShortCastle Check2");
                    if(MainWindow.chessBoard[r][c + 3].getName() != null && MainWindow.chessBoard[r][c + 3].getName().equalsIgnoreCase("W.Rook") && MainWindow.chessBoard[r][c + 3].getPiece().getTimesMoved() == 0){
//                        System.out.println(super.getName() + " can ShortCastle");
                        movesPossible[r][c + 2] = true;
                    }
                }
            }
            tempGhostPiece = false;
        }
        if(!attackingPeices.isEmpty()){
//            System.out.println("updating kingincheck");
            if(attackingPeices.size()>=2){
                trimDupes();
            }
//            System.out.println("In King class: " + attackingPeices.size());
            GameMechanics.setKingInCheck(MainWindow.chessBoard[r][c]);
        }
    }

    public boolean[][] getMovesPossible() {
        return movesPossible;
    }

    @Override
    public int[] possibleChecks(){
        int[] output = {0,0};
        return output;
    }
    public boolean[][] updateOpposite(int x, int y){
        boolean[][] output = new boolean[8][8];
        for(Tile[] r : MainWindow.chessBoard){
            for(Tile c : r) {
                boolean[][] temp = c.getMoves();
                if (c.getName() != null&&
                        !c.getName().startsWith(super.getName().substring(0, 1))) {
                    int row = 0;
                    int col = 0;
                    if(temp[x][y]){
                        System.out.println("in check");
                        attackingPeices.add(c);
                    }
                    if(c.getName().contains("Pawn") && c.getX() != 0 && c.getX() != 7) {
//                        System.out.println("Pawn check");
                        for (boolean[] ro : temp) {
                            for (boolean co : ro) {
                                if(!super.getName().substring(0,1).equalsIgnoreCase("w")) {
                                    //White Pawn//
//                                    System.out.println("W.Pawn check");
                                    if (c.getY() > 0) {
                                        if (MainWindow.chessBoard[c.getX() - 1][c.getY() - 1].getName() == null) {
                                            output[c.getX() - 1][c.getY() - 1] = true;
                                        }
                                    }
                                    if (c.getY() < 7) {
                                        if (MainWindow.chessBoard[c.getX() - 1][c.getY() + 1].getName() == null) {
                                            output[c.getX() - 1][c.getY() + 1] = true;
                                        }
                                    }
                                } else {
                                    //Black pawn//
//                                    System.out.println("B.Pawn check");
                                    if (c.getY() > 0) {
                                        if (MainWindow.chessBoard[c.getX() + 1][c.getY() - 1].getName() == null) {
                                            output[c.getX() + 1][c.getY() - 1] = true;
                                        }
                                    }
                                    if (c.getY() < 7) {
                                        if (MainWindow.chessBoard[c.getX() + 1][c.getY() + 1].getName() == null) {
                                            output[c.getX() + 1][c.getY() + 1] = true;
                                        }
                                    }
                                }

                                col++;
                            }
                            row++;
                            col = 0;
                        }
                    } else {

                        for (boolean[] ro : c.getMoves()) {
                            for (boolean co : ro) {

                                output[row][col] =  (output[row][col])? output[row][col]:co;
                                col++;
                            }
                            row++;
                            col = 0;
                        }
                    }
                }
            }
        }
/*
        System.out.println("Opposite side possible attacks:");
        for(boolean[] r : output){
            System.out.print("[");
            for(boolean c : r){
                System.out.print(((c)?"x":"o")+ " ");
            }
            System.out.println("]");
        }
        System.out.println("\n");

 */

        return output;
    }

    @Override
    public ArrayList<Tile> getAttackingPeices() {
        return attackingPeices;
    }
    @Override
    public void resetAttackingPiece(){
        attackingPeices.clear();
    }

    public void trimDupes(){
        for (int i = 0; i < attackingPeices.size()-1; i++) {
            for(int j = i+1; j < attackingPeices.size();j++){
                if(attackingPeices.get(i).equals(attackingPeices.get(j))){
                    attackingPeices.remove(j);
                }
            }
        }
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
        System.out.println("\n");
    }
}
