import java.util.ArrayList;

//This is the piece mother class.
//because the Tile class includes many different pieces this is needed in order to access specific methods that are only
//related to certain pieces.
public class Piece {
    private String name;
    private boolean [][] movesPossible = new boolean[][] {{false}};
    //basic Constructor
    public Piece(String name){
        this.name = name;
    }

    //default constructor
    public Piece(){
        this.name = null;
    }
    public String getName(){
        return name;
    }

    public ArrayList<Tile> getAttackingPieces() {
        return new ArrayList<Tile>();
    }
    public int getTimesMoved(){
        return Integer.MIN_VALUE;
    }

    public void updateMovesPossible(int r, int c){}
    public void addToTotalMoves(int i){}

    public void resetAttackingPiece(){}
    public boolean[][] getMovesPossible() {
        return movesPossible;
    }
}
