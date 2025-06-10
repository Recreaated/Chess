import java.awt.*;

//this is the tile Class
//this holds vital information in relation to a specific tile such as
//what piece is on this tile, access to the button, and its coordinates
public class Tile {
    private final Button b;
    private Piece piece;
    private final int xCord;
    private final int yCord;

    public Tile(String name, int xCord, int yCord, Piece piece){
        b = new Button(name);
        this.xCord = xCord;
        this.yCord = yCord;
        this.piece = piece;
    }

    //updates the possible moves of what ever piece is on this tile
    public void updatePosMoves(){
        piece.updateMovesPossible(xCord,yCord);
    }

    //getter Methods
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
    public boolean[][] getMoves(){
        return piece.getMovesPossible();
    }

    //setter Methods
    public int getY(){
        return yCord;
    }
    public void setPiece(Piece piece){
        this.piece = piece;
    }

    //basic toString
    public String toString(){
        return piece.getName();
    }
}
