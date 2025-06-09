import java.util.ArrayList;

public class GameMechanics{
    private static String[] turn= {"W","B"};
    private static int turnIndex = 0;
    private static Tile lastTile = null;
    private static ArrayList<Tile> kingInCheck = new ArrayList<Tile>();
    private static boolean noPossibleMoves = false;

    public static void updateLastTile(Tile t) {

        if(!noPossibleMoves) {
            System.out.println("Check 0 ");
            if (turnIndex == 0 && lastTile == null) {
                updateBoard();
            }
            System.out.println(" Contains : " + t.getPiece().getName());
            //print();
            //print();
            System.out.println(turn[turnIndex % 2]);
            if (lastTile == null && t.getName() != null) {
                System.out.println("check0.5");
                if (t.getName().substring(0, 1).equalsIgnoreCase(turn[turnIndex % 2])) {
                    lastTile = t;
                    System.out.println("check1");
                }
            } else if (t.getName() != null && lastTile.getName()!= null && lastTile.getName().substring(0, 1).equalsIgnoreCase(t.getName().substring(0, 1))) {
                System.out.println("check1.5");
                lastTile = t;
                System.out.println("check2");
            } else if (lastTile != null && lastTile.getName()!= null && lastTile.getName().substring(0, 1).equalsIgnoreCase(turn[turnIndex % 2])) {
                System.out.println("check2.5");
                if (lastTile.getMoves()[t.getX()][t.getY()]) {
                    swap(t);
                    turnIndex++;
                    System.out.println("piece moved");
                    if(!noPossibleMoves && kingInCheck.size()>0) {
                        System.out.println("checking if there is any possible moves");
                        noPossibleMoves = hasAPossibleMove(kingInCheck.getFirst().getPiece().getAttackingPeices());
                    }

                }
            }
        }
    }
    public static int getTurnIndex(){
        return turnIndex;
    }
    //Perm Swap
    private static void swap(Tile t){
        String tempTLabel = t.getName();
        String tempLastLabel = lastTile.getName();
        Piece tempTPiece = t.getPiece();
        Piece tempLastPiece = lastTile.getPiece();

        t.getButton().setLabel(lastTile.getName());
        t.setPiece(lastTile.getPiece());
        lastTile.setPiece(new Piece());
        lastTile.getButton().setLabel("");

        updateBoard();
        System.out.println("successfully updated board");
        if(!kingInCheck.isEmpty()){
            //System.out.println(kingInCheck.getPiece().getAttackingPeices());
            System.out.println("kingInCheck is not null");
            //System.out.println("kingInCheck name : " + kingInCheck.getName() + ". turn : " + turn[turnIndex % 2]);
            if(kingInCheck.size() > 1 ||kingInCheck.getFirst().getName().substring(0,1).equalsIgnoreCase(turn[turnIndex % 2])){
                System.out.println("Swaping tiles...");
                t.getButton().setLabel(tempTLabel);
                t.setPiece(tempTPiece);
                lastTile.setPiece(tempLastPiece);
                lastTile.getButton().setLabel(tempLastLabel);
                turnIndex--;
            }

        } else {
            lastTile = null;
        }
    }

    //Temp Swap
    private static boolean swap(Tile t1, Tile t2){
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

        if(kingInCheck == null){
            //if(kingInCheck.getName().substring(0,1).equalsIgnoreCase(turn[turnIndex % 2])){
                output = true;
            //}

        }
        System.out.println("Swaping tiles...");
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
        }
        kingInCheck.clear();
        Tile king1 = null;
        Tile king2 = null;
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
        System.out.println("successfully kingInCheck");
        kingInCheck.add(t);
    }

    private static boolean hasAPossibleMove(ArrayList<Tile> attackingPieces){
        boolean output = true;
//        ArrayList<int[]> temp = new ArrayList<int[]>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //System.out.println(kingInCheck.getFirst().getMoves()[i][j]);
                if (kingInCheck.getFirst().getMoves()[i][j] && !swap(MainWindow.chessBoard[i][j], kingInCheck.getFirst())) {
                    System.out.println("pos Check 1");
                    output = false;
                    //temp.add(new int[] {i,j});
                }
            }
        }
        if(output && attackingPieces.size() == 1) {
            for(Tile[] r : MainWindow.chessBoard){
                for(Tile c : r){
                    if(c.getName() != null && c.getName().substring(0,1).equalsIgnoreCase(turn[turnIndex%2])){
                        for(int row = 0; row < 8; row++) {
                            for (int col = 0; col < 8; col++) {
                                if((c.getMoves()[row][col] && attackingPieces.get(0).getMoves()[row][col])
                                        || (c.getMoves()[row][col]
                                            && (row == attackingPieces.get(0).getX() && col == attackingPieces.get(0).getY()))){
                                    if(!swap(kingInCheck.getFirst(),c)){
                                        System.out.println("pos Check 2");
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
        }
        System.out.println("no Possible move? :"+ output);
//        System.out.println(temp);
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
