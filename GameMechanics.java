import java.util.ArrayList;

public class GameMechanics{
    private static String[] turn= {"W","B"};
    private static int turnIndex = 0;
    private static Tile lastTile = null;
    private static ArrayList<Tile> kingInCheck = new ArrayList<Tile>();
    private static boolean noPossibleMoves = false;
//    private static boolean staleMateCheck = false;
    private static boolean isInStalemate = false;
    private static Tile king1 = null;
    private static Tile king2 = null;


    public static void promotePawn(Piece p){
        MainWindow.Promotable.setPiece(p);
        MainWindow.Promotable.getButton().setLabel(p.getName());
        MainWindow.Promotable = null;
        MainWindow.canPromote = false;
        updateBoard();
    }

    public static void updateLastTile(Tile t) {

        if(!noPossibleMoves && !MainWindow.canPromote && !isInStalemate) {
//            System.out.println("Check 0 ");
            if (turnIndex == 0 && lastTile == null) {
                updateBoard();
            }
//            System.out.println(" Contains : " + t.getPiece().getName());
            //print();
            //print();
            System.out.println(turn[turnIndex % 2]);
            if (lastTile == null && t.getName() != null) {
//                System.out.println("check0.5");
                if (t.getName().substring(0, 1).equalsIgnoreCase(turn[turnIndex % 2])) {
                    lastTile = t;
//                    System.out.println("check1");
                }
            } else if (t.getName() != null && lastTile.getName() != null &&lastTile.getName().substring(0, 1).equalsIgnoreCase(t.getName().substring(0, 1))) {
//                System.out.println("check1.5 : " + lastTile.getName().substring(0, 1) + " " + t.getName().substring(0, 1));
                lastTile = t;
//                System.out.println("check2");
            } else if (lastTile != null && lastTile.getName().substring(0, 1).equalsIgnoreCase(turn[turnIndex % 2])) {
//                System.out.println("check2.5");
                if (lastTile.getMoves()[t.getX()][t.getY()]) {
//                    System.out.println("check3");
                    swap(t);
                    if(turnIndex % 2 == 0){
                        for(Tile c :MainWindow.chessBoard[0]){
                            if(c.getName()!= null && c.getName().contains("Pawn")){
                                MainWindow.canPromote = true;
                                MainWindow.Promotable = c;

                            }
                        }
                    } else {
                        for(Tile c :MainWindow.chessBoard[7]){
                            if(c.getName()!= null && c.getName().contains("Pawn")){
                                MainWindow.canPromote = true;
                                MainWindow.Promotable = c;
                            }
                        }
                    }
                    turnIndex++;
                    MainWindow.infoLabel.setText("Current Side to move piece : " + turn[turnIndex % 2]);
                    isInStalemate = isInStalemate();
//                    System.out.println("piece moved");
                    if(!noPossibleMoves && kingInCheck.size()>0) {
//                        System.out.println("checking if there is any possible moves");
                        noPossibleMoves = hasAPossibleMove();
                    }

                }
            }
        }
    }
    public static int getTurnIndex(){
        return turnIndex;
    }
    //public static String getTurnName(){ return turn[turnIndex % 2]; }
    public static String getPreviousTurnName(){ return turn[((turnIndex-1) % 2)]; }

    //Perm Swap
    private static void swap(Tile t){
        String tempTLabel = t.getName();
        String tempLastLabel = lastTile.getName();
        Piece tempTPiece = t.getPiece();
        Piece tempLastPiece = lastTile.getPiece();

        if(t.getY() !=lastTile.getY() && t.getName() == null && lastTile.getName().contains("Pawn")){
            MainWindow.chessBoard[lastTile.getX()][t.getY()].setPiece(new Piece());
            MainWindow.chessBoard[lastTile.getX()][t.getY()].getButton().setLabel("");
        }
        if(lastTile.getName().contains("King")){
            if(Math.abs(lastTile.getY() - t.getY()) > 1){
                boolean longside = lastTile.getY() - t.getY() >0;
                if(longside) {
                    MainWindow.chessBoard[lastTile.getX()][3].setPiece(MainWindow.chessBoard[lastTile.getX()][0].getPiece());
                    MainWindow.chessBoard[lastTile.getX()][3].getButton().setLabel(MainWindow.chessBoard[lastTile.getX()][0].getName());
                    MainWindow.chessBoard[lastTile.getX()][0].setPiece(new Piece());
                    MainWindow.chessBoard[lastTile.getX()][0].getButton().setLabel("");
                } else {
                    MainWindow.chessBoard[lastTile.getX()][5].setPiece(MainWindow.chessBoard[lastTile.getX()][7].getPiece());
                    MainWindow.chessBoard[lastTile.getX()][5].getButton().setLabel(MainWindow.chessBoard[lastTile.getX()][7].getName());
                    MainWindow.chessBoard[lastTile.getX()][7].setPiece(new Piece());
                    MainWindow.chessBoard[lastTile.getX()][7].getButton().setLabel("");
                }
            }
        }
        tempLastPiece.addToTotalMoves(1);

        t.getButton().setLabel(lastTile.getName());
        t.setPiece(lastTile.getPiece());
        lastTile.setPiece(new Piece());
        lastTile.getButton().setLabel("");

        updateBoard();
//        System.out.println("successfully updated board");
        if(!kingInCheck.isEmpty() && !kingInCheck.getFirst().getName().substring(0,1).equalsIgnoreCase(turn[turnIndex % 2])){
            //System.out.println(kingInCheck.getPiece().getAttackingPeices());
//            System.out.println("kingInCheck is not null");
            //System.out.println("kingInCheck name : " + kingInCheck.getName() + ". turn : " + turn[turnIndex % 2]);
            if(kingInCheck.size() > 1 ||kingInCheck.getFirst().getName().substring(0,1).equalsIgnoreCase(turn[turnIndex % 2])){
//                System.out.println("Swaping tiles...");
//                System.out.println("Cannot Swap tiles");
                t.getButton().setLabel(tempTLabel);
                t.setPiece(tempTPiece);
                lastTile.setPiece(tempLastPiece);
                lastTile.getButton().setLabel(tempLastLabel);
                tempLastPiece.addToTotalMoves(-1);

                turnIndex--;
            }

        } else {
//            System.out.println("reseting LastTile1");
            lastTile = null;

        }
    }

    //Temp Swap
    public static boolean swap(Tile t1, Tile t2){
        boolean output = false;
        String tempTLabel = t1.getName();
        String tempLastLabel = t2.getName();
        Piece tempTPiece = t1.getPiece();
        Piece tempLastPiece = t2.getPiece();

        t1.getButton().setLabel(t2.getName());
        t1.setPiece(t2.getPiece());
        t2.setPiece(new Piece());
        t2.getButton().setLabel("");
        updateBoard();

        if(kingInCheck.isEmpty()){
            //if(kingInCheck.getName().substring(0,1).equalsIgnoreCase(turn[turnIndex % 2])){
            output = true;
            //}

        }
//        System.out.println("Temp swaping tiles...");
        t1.getButton().setLabel(tempTLabel);
        t1.setPiece(tempTPiece);
        t2.setPiece(tempLastPiece);
        t2.getButton().setLabel(tempLastLabel);
        updateBoard();

        return output;
    }

    private static void updateBoard(){

        if (!kingInCheck.isEmpty()) {
            for(Tile t : kingInCheck) {
                t.getPiece().resetAttackingPiece();
            }
            kingInCheck.clear();
        }

        for(Tile[] r : MainWindow.chessBoard){
            for(Tile c : r){
                if(c.getName() != null && c.getName().contains("King")) {
                    if(king1 == null){
                        king1 = c;
                    } else {
                        king2 = c;
                    }

                } else {
                    c.updatePosMoves();
                }
            }
        }
        if (king1 != null && king2 != null) {

            king1.updatePosMoves();
            king2.updatePosMoves();

        }

    }

    public static void setKingInCheck(Tile t){
//        System.out.println("successfully kingInCheck");
        kingInCheck.add(t);
    }

    private static boolean hasAPossibleMove(){
        boolean output = true;
//        ArrayList<int[]> temp = new ArrayList<int[]>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //System.out.println(kingInCheck.getFirst().getMoves()[i][j]);
//                System.out.println(swap(MainWindow.chessBoard[i][j], kingInCheck.getFirst()));
                if (kingInCheck.getFirst().getMoves()[i][j] && swap(MainWindow.chessBoard[i][j], kingInCheck.getFirst())) {
//                    System.out.println("pos Check 1");
//                    System.out.println("Move Mossible to: " + MainWindow.chessBoard[i][j].getX() + ", " + MainWindow.chessBoard[i][j].getY());
                    output = false;
                    //temp.add(new int[] {i,j});
                }
            }
        }
//        System.out.println(output);
//        System.out.println(kingInCheck.getFirst().getPiece().getAttackingPeices().size());
//        for (int i = 0; i < kingInCheck.getFirst().getPiece().getAttackingPeices().size()-1; i ++) {
//            System.out.print(kingInCheck.getFirst().getPiece().getAttackingPeices().get(i).equals(kingInCheck.getFirst().getPiece().getAttackingPeices().get(i+1)));
//        }
//        System.out.println("");
        if(output && kingInCheck.getFirst().getPiece().getAttackingPeices().size() == 1) {
//            System.out.println("pos Check 1.5");
            for(Tile[] r : MainWindow.chessBoard){
                for(Tile c : r){
                    if(c.getName() != null && c.getName().substring(0,1).equalsIgnoreCase(turn[turnIndex%2])){
                        for(int row = 0; row < 8; row++) {
                            for (int col = 0; col < 8; col++) {
                                if((c.getMoves()[row][col] && kingInCheck.getFirst().getPiece().getAttackingPeices().get(0).getMoves()[row][col])
                                        || (c.getMoves()[row][col]
                                        && (row == kingInCheck.getFirst().getPiece().getAttackingPeices().get(0).getX() && col == kingInCheck.getFirst().getPiece().getAttackingPeices().get(0).getY()))){
                                    if(swap(MainWindow.chessBoard[row][col],c)){
//                                        System.out.println("pos Check 2");
//                                        System.out.print("This piece can block check" + c.getName());
                                        output = false;
//                                        temp.add(new int[] {row,col});
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if(output){
            System.out.println("Checkmate!");
            MainWindow.infoLabel.setText("Checkmate! " + turn[((turnIndex-1)%2)] + " Wins!");
        }
//        System.out.println("no Possible move? :"+ output);
//        System.out.println(temp);
//        System.out.println("reseting LastTile2");
        lastTile = null;
        return output;
    }

    private static boolean isInStalemate(){
        boolean output = true;
        for(Tile[] r : MainWindow.chessBoard) {
            for (Tile c : r) {
                if(c.getName() != null && c.getName().substring(0,1).equalsIgnoreCase(turn[turnIndex%2])){
                    for(int row = 0; row < 8; row++) {
                        for (int col = 0; col < 8; col++) {
                            if((c.getMoves()[row][col])){
                                king1.getPiece().addToTotalMoves(100);
                                king2.getPiece().addToTotalMoves(100);
                                 if(swap(c,MainWindow.chessBoard[row][col])){
                                     output = false;
                                 }
                                king1.getPiece().addToTotalMoves(-100);
                                king2.getPiece().addToTotalMoves(-100);
                            }
                        }
                    }
                }
            }
        }

        if(output){
            MainWindow.infoLabel.setText("Stalemate. No one wins");
        }
        return output;
    }

    private static void print(){
        System.out.print("[");
        for(Tile[] r : MainWindow.chessBoard){
            for(Tile c : r){
                System.out.print(((c==null)?"null": c) +" ");
            }
            System.out.println("]");
        }
    }
}
