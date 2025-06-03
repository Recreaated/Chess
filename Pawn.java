public class Piece {
    String name;
    public Piece(String name){
        this.name = name;
    }
    public Piece(){
        this.name = null;
    }
    public String getName(){
        return name;
    }

    public void movesAvaliable(){}
    public int[] possibleChecks(){
        int[] output = {-1,-1};
        return output;
    }
}
