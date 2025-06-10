import java.util.ArrayList;

//this is the GameMechanics class
//This controls the basic game mechanics such as
//pawn promotion, moevement of pieces, checks, checkmates, and stalemates
public class GameMechanics{
    private static String[] turn= {"W","B"};
    private static int turnIndex = 0;
    private static Tile lastTile = null;
    private static ArrayList<Tile> kingInCheck = new ArrayList<Tile>();
    private static boolean noPossibleMoves = false;
    private static boolean isInStalemate = false;
    private static Tile king1 = null;
    private static Tile king2 = null;

    //This promotes a pawn that is on the last rank
    public static void promotePawn(Piece p){
        MainWindow.Promotable.setPiece(p);
        MainWindow.Promotable.getButton().setLabel(p.getName());
        MainWindow.Promotable = null;
        MainWindow.canPromote = false;
        updateBoard();
    }

    //this updates the tile so it knows which piece is being moved and where
    public static void updateLastTile(Tile t) {

        if(!noPossibleMoves && !MainWindow.canPromote && !isInStalemate) {
            if (turnIndex == 0 && lastTile == null) {
                updateBoard();
            }
            System.out.println(turn[turnIndex % 2]);
            if (lastTile == null && t.getName() != null) {
                if (t.getName().substring(0, 1).equalsIgnoreCase(turn[turnIndex % 2])) {
                    lastTile = t;
                }
            } else if (t.getName() != null && lastTile.getName() != null &&lastTile.getName().substring(0, 1).equalsIgnoreCase(t.getName().substring(0, 1))) {
                lastTile = t;
            } else if (lastTile != null && lastTile.getName().substring(0, 1).equalsIgnoreCase(turn[turnIndex % 2])) {
                if (lastTile.getMoves()[t.getX()][t.getY()]) {
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
                    MainWindow.infoLabel.setText("Current Side to move piece : " + turn[turnIndex % 2] + ((kingInCheck.size()>0) ? " You are in check" : ""));
                    isInStalemate = isInStalemate();
                    if(!noPossibleMoves && !kingInCheck.isEmpty()) {
                        noPossibleMoves = hasAPossibleMove();
                    }

                }
            }
        }
    }
    //gets the current turn index
    public static int getTurnIndex(){
        return turnIndex;
    }

    //gets the previous turn name ("W" or "B")
    public static String getPreviousTurnName(){ return turn[((turnIndex-1) % 2)]; }

    //Perm Swap
    //Permanently swaps two tiles
    //However in the case that this puts or leaves the king into check, it is undone
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
        if (!kingInCheck.isEmpty()) {
            if (kingInCheck.size() > 1 || kingInCheck.getFirst().getName().substring(0, 1).equalsIgnoreCase(turn[turnIndex % 2])) {
                t.getButton().setLabel(tempTLabel);
                t.setPiece(tempTPiece);
                lastTile.setPiece(tempLastPiece);
                lastTile.getButton().setLabel(tempLastLabel);
                tempLastPiece.addToTotalMoves(-1);

                turnIndex--;
            } else {
                lastTile = null;

            }
        } else {
            lastTile = null;

        }
    }

    //Temp Swap
    //Temporarily swaps two tiles,
    //This is done to make sure that a move will not result in check of the wrong king.
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
            output = true;
        }
        t1.getButton().setLabel(tempTLabel);
        t1.setPiece(tempTPiece);
        t2.setPiece(tempLastPiece);
        t2.getButton().setLabel(tempLastLabel);
        updateBoard();

        return output;
    }

    //updates each piece's possible moves
    //Kings are saved for last due to the fact that it relies on all other pieces to determine its moveset
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

    //adds onto a list that holds the specific king in check
    public static void setKingInCheck(Tile t){
        kingInCheck.add(t);
    }

    //checks if a side has any possible moves to avoid checkmate
    private static boolean hasAPossibleMove(){
        boolean output = true;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (kingInCheck.getFirst().getMoves()[i][j] && swap(MainWindow.chessBoard[i][j], kingInCheck.getFirst())) {
                    output = false;
                }
            }
        }
        if(output && kingInCheck.getFirst().getPiece().getAttackingPieces().size() == 1) {
            for(Tile[] r : MainWindow.chessBoard){
                for(Tile c : r){
                    if(c.getName() != null && c.getName().substring(0,1).equalsIgnoreCase(turn[turnIndex%2])){
                        for(int row = 0; row < 8; row++) {
                            for (int col = 0; col < 8; col++) {
                                if((c.getMoves()[row][col] && kingInCheck.getFirst().getPiece().getAttackingPieces().get(0).getMoves()[row][col])
                                        || (c.getMoves()[row][col]
                                        && (row == kingInCheck.getFirst().getPiece().getAttackingPieces().get(0).getX() && col == kingInCheck.getFirst().getPiece().getAttackingPieces().get(0).getY()))){
                                    if(swap(MainWindow.chessBoard[row][col],c)){
                                        output = false;
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
        lastTile = null;
        return output;
    }

    //checks if a side is in staleMate
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
}
