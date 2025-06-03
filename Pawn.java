public class Pawn extends Piece{
    public Pawn (String name){
        super(name);
    }
    @Override
    public void movesAvaliable(){

    }
    @Override
    public int[] possibleChecks(){
        int[] output = {0,0};
        return output;
    }
}
