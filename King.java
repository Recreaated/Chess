import java.util.ArrayList;

public class King extends Piece{
    private ArrayList<Tile> attackingPieces = new ArrayList<Tile>();
    private int timesMoved = 0;
    private boolean[][] movesPossible;
    private boolean[][] oppositePieceChecks = new boolean[8][8];
    private boolean tempGhostPiece = false;


    public King (String name){
        super(name);
    }

    //updates the moves possible for the King Pieces
    //will not let the king be put in danger on a possible move
    //Also accounts for special cases
    @Override
    public void updateMovesPossible(int r, int c){
        attackingPieces.clear();
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
        //Special Case - Castling

        if(!tempGhostPiece&& attackingPieces.isEmpty() && timesMoved == 0){
            //Long Castle
            if(c >= 4 && MainWindow.chessBoard[r][c - 1].getName() == null && movesPossible[r][c-1]){
                tempGhostPiece = true;
                if(MainWindow.chessBoard[r][c - 2].getName() == null && GameMechanics.swap(MainWindow.chessBoard[r][c - 2],MainWindow.chessBoard[r][c])){
                    if(MainWindow.chessBoard[r][c - 3].getName() == null){
                        if(MainWindow.chessBoard[r][c - 4].getName().equalsIgnoreCase("W.Rook") && MainWindow.chessBoard[r][c - 3].getPiece().getTimesMoved() == 0){
                            movesPossible[r][c - 2] = true;
                        }
                    }
                }
            }
            tempGhostPiece = false;
            //short Castle
            if(c <= 4 && MainWindow.chessBoard[r][c + 1].getName() == null && movesPossible[r][c+1]){
                tempGhostPiece = true;
                if(MainWindow.chessBoard[r][c + 2].getName() == null && GameMechanics.swap(MainWindow.chessBoard[r][c + 2],MainWindow.chessBoard[r][c])){
                    if(MainWindow.chessBoard[r][c + 3].getName() != null && MainWindow.chessBoard[r][c + 3].getName().equalsIgnoreCase("W.Rook") && MainWindow.chessBoard[r][c + 3].getPiece().getTimesMoved() == 0){
                        movesPossible[r][c + 2] = true;
                    }
                }
            }
            tempGhostPiece = false;
        }
        if(!attackingPieces.isEmpty()){
            if(attackingPieces.size()>=2){
                trimDupes();
            }
            GameMechanics.setKingInCheck(MainWindow.chessBoard[r][c]);
        }
    }

    //returns the moves possible with this piece
    public boolean[][] getMovesPossible() {
        return movesPossible;
    }

    //this returns the spaces that the opposite side pieces can attack on
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
                        attackingPieces.add(c);
                    }
                    if(c.getName().contains("Pawn") && c.getX() != 0 && c.getX() != 7) {
                        for (boolean[] ro : temp) {
                            for (boolean co : ro) {
                                if(!super.getName().substring(0,1).equalsIgnoreCase("w")) {
                                    //White Pawn//
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
        return output;
    }

    //returns the pieces that are currently attacking this king
    @Override
    public ArrayList<Tile> getAttackingPieces() {
        return attackingPieces;
    }
    //resets the ArrayList of pieces that are currently attacking this king
    @Override
    public void resetAttackingPiece(){
        attackingPieces.clear();
    }

    //trims duplicates in the case that for some reason there are two or more instances of the same object.
    public void trimDupes(){
        for (int i = 0; i < attackingPieces.size()-1; i++) {
            for(int j = i+1; j < attackingPieces.size(); j++){
                if(attackingPieces.get(i).equals(attackingPieces.get(j))){
                    attackingPieces.remove(j);
                    j--;
                }
            }
        }
    }
    //updates the timesMove counter
    @Override
    public void addToTotalMoves(int i){
        timesMoved += i;
    }

    //returns the timesMoved counter
    @Override
    public int getTimesMoved(){
        return timesMoved;
    }
}
