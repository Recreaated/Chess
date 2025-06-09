import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainWindow {
    public static Tile[][] chessBoard;
    public static boolean canPromote = false;
    public static Tile Promotable = null;
    private JFrame window;
    private JPanel MainPanel;
    private final Piece[][] startingLayout = {{new Rook("B.Rook"),new Knight("B.Knight"),new Bishop("B.Bishop"),new Queen("B.Queen"),new King("B.King"),new Bishop("B.Bishop"),new Knight("B.Knight"),new Rook("B.Rook")},
            {new Pawn("B.Pawn"),new Pawn("B.Pawn"),new Pawn("B.Pawn"),new Pawn("B.Pawn"),new Pawn("B.Pawn"),new Pawn("B.Pawn"),new Pawn("B.Pawn"),new Pawn("B.Pawn")},
            {new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece()},
            {new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece()},
            {new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece()},
            {new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece()},
            {new Pawn("W.Pawn"),new Pawn("W.Pawn"),new Pawn("W.Pawn"),new Pawn("W.Pawn"),new Pawn("W.Pawn"),new Pawn("W.Pawn"),new Pawn("W.Pawn"),new Pawn("W.Pawn")},
            {new Rook("W.Rook"),new Knight("W.Knight"),new Bishop("W.Bishop"),new Queen("W.Queen"),new King("W.King"),new Bishop("W.Bishop"),new Knight("W.Knight"),new Rook("W.Rook")}};


    public MainWindow(){
        innit();
    }//end of constructor

    private void innit(){
        window = new JFrame();
        window.setTitle("Chess");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(1000,900);
        window.setLocationRelativeTo(null);

        //topLevelPanel = new JPanel();
        MainPanel = new JPanel();

        MainPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        MainPanel.setBounds(0,0,800,800);
        //topLevelPanel.add(MainPanel);
        //topLevelPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        window.add(MainPanel);



        MainPanel.setBackground(Color.WHITE);
        char[] horLtr = {'A','B','C','D','E','F','G','H'};
        chessBoard = new Tile[8][8];
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                String label = ""+ horLtr[col] + (8-row);
                String pieceName = startingLayout[row][col].getName();
                chessBoard[row][col] =new Tile(pieceName,row,col, startingLayout[row][col]);
                if (((row + col) % 2 == 0)) {
                    chessBoard[row][col].getButton().setBackground(new Color(225,225,225));
                } else {
                    chessBoard[row][col].getButton().setBackground(new Color(0,148,56));
                }
                chessBoard[row][col].getButton().setPreferredSize((new Dimension(100,100)));
                //Interface usage here "ActionEvent -> ..." lambda
                final Tile tile = chessBoard[row][col];
                chessBoard[row][col].getButton().addActionListener((ActionEvent e) -> {

//                    System.out.print(label + " button was pressed.");
                    GameMechanics.updateLastTile(tile);
                });
                MainPanel.add(chessBoard[row][col].getButton());
            }
        }
//        MainPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
//        topLevelPanel.add(MainPanel);

        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(Color.black);
        //infoPanel.setBounds(new Rectangle(800,0,500,800));
        JLabel temp = new JLabel("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
        // temp.setEditable(false);
        //temp.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        infoPanel.add(temp);

        JButton promoteToRook = new JButton("Promote to Rook");
        promoteToRook.addActionListener((ActionEvent e) -> {
            if(canPromote){
                GameMechanics.promotePawn(new Rook(GameMechanics.getPreviousTurnName() + ".Rook"));
            }
        });
        promoteToRook.setLayout(new FlowLayout(FlowLayout.RIGHT));
        infoPanel.add(promoteToRook);

        JButton promoteToKnight = new JButton("Promote to knight");
        promoteToKnight.addActionListener((ActionEvent e) -> {
            if(canPromote){
                GameMechanics.promotePawn(new Knight(GameMechanics.getPreviousTurnName() + ".Knight"));
            }
        });
        promoteToKnight.setLayout(new FlowLayout(FlowLayout.RIGHT));
        infoPanel.add(promoteToKnight);

        JButton promoteToBishop = new JButton("Promote to Bishop");
        promoteToBishop.addActionListener((ActionEvent e) -> {
            if(canPromote){
                GameMechanics.promotePawn(new Bishop(GameMechanics.getPreviousTurnName() + ".Bishop"));
            }
        });
        promoteToBishop.setLayout(new FlowLayout(FlowLayout.RIGHT));
        infoPanel.add(promoteToBishop);

        JButton promoteToQueen = new JButton("Promote to Queen");
        promoteToQueen.addActionListener((ActionEvent e) -> {
            if(canPromote){
                GameMechanics.promotePawn(new Queen(GameMechanics.getPreviousTurnName() + ".Queen"));
            }
        });
        promoteToQueen.setLayout(new FlowLayout(FlowLayout.RIGHT));
        infoPanel.add(promoteToQueen);


        infoPanel.setBounds(800,0,500,800);
        infoPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        window.add(infoPanel);



//        window.setResizable(false);
        window.setLayout(null);
        window.setVisible(true);
    }//end of initializer method


}
