import java.awt.*;

public class Tile {
    private Button b;
    private Piece piece;
    private int xCord;
    private int yCord;

    public Tile(String name, int xCord, int yCord, Piece piece){
        b = new Button(name);
        this.xCord = xCord;
        this.yCord = yCord;
        this.piece = piece;
    }

    public void updatePosMoves(){
        //System.out.println(xCord + "," + yCord);
        piece.updateMovesPossible(xCord,yCord);
    }

    public boolean[][] posMoves(){
        return piece.getMovesPossible();
    }

    public String getName(){
        return piece.getName();
    }
    public Button getButton(){
        return b;
    }
    public Piece getPiece(){return piece;}

    public int getX(){
        return xCord;
    }

    public int getY(){
        return yCord;
    }
    public void setPiece(Piece piece){
        this.piece = piece;
    }
    public boolean[][] getMoves(){
        return piece.getMovesPossible();
    }

    public String toString(){
        return piece.getName();
    }
}
