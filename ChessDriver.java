import javax.swing.SwingUtilities;

public class ChessDriver {

    public static void main(String[] args){
        //Interface usage here "() ->" lambda
        SwingUtilities.invokeLater(() ->{
            MainWindow main = new MainWindow();
        });
    }

}
