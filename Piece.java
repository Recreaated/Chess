import java.util.ArrayList;

public class Piece {
    private String name;
    private boolean [][] movesPossible = new boolean[][] {{false}};
    public Piece(String name){
        this.name = name;
    }
    public Piece(){
        this.name = null;
    }
    public String getName(){
        return name;
    }

    public ArrayList<Tile> getAttackingPeices() {
        return new ArrayList<Tile>();
    }
    public int getTimesMoved(){
        return Integer.MIN_VALUE;
    }

    public void updateMovesPossible(int r, int c){}
    public void addToTotalMoves(int i){}
    public void setTempGhostPiece(boolean b){}
    public boolean getTempGhostPiece(){return false;}

    public void resetAttackingPiece(){}
    public boolean[][] getMovesPossible() {
        return movesPossible;
    }

    public int[] possibleChecks(){
        int[] output = {-1,-1};
        return output;
    }
}
