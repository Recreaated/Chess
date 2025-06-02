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

    public Button getButton(){
        return b;
    }

    public int getX(){
        return xCord;
    }

    public int getY(){
        return yCord;
    }


}
