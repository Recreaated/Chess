import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainWindow {
    public static JLabel infoLabel;
    public static Tile[][] chessBoard;
    public static boolean canPromote = false;
    public static Tile Promotable = null;
    private JFrame window;
    private JPanel mainPanel;
    private final Piece[][] startingLayout = {{new Rook("B.Rook"),new Knight("B.Knight"),new Bishop("B.Bishop"),new Queen("B.Queen"),new King("B.King"),new Bishop("B.Bishop"),new Knight("B.Knight"),new Rook("B.Rook")},
            {new Pawn("B.Pawn"),new Pawn("B.Pawn"),new Pawn("B.Pawn"),new Pawn("B.Pawn"),new Pawn("B.Pawn"),new Pawn("B.Pawn"),new Pawn("B.Pawn"),new Pawn("B.Pawn")},
            {new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece()},
            {new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece()},
            {new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece()},
            {new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece()},
            {new Pawn("W.Pawn"),new Pawn("W.Pawn"),new Pawn("W.Pawn"),new Pawn("W.Pawn"),new Pawn("W.Pawn"),new Pawn("W.Pawn"),new Pawn("W.Pawn"),new Pawn("W.Pawn")},
            {new Rook("W.Rook"),new Knight("W.Knight"),new Bishop("W.Bishop"),new Queen("W.Queen"),new King("W.King"),new Bishop("W.Bishop"),new Knight("W.Knight"),new Rook("W.Rook")}};


    public MainWindow(){
        init();
    }//end of constructor

    //initialization of MainWindow
    private void init(){
        //Creation of the Window
        window = new JFrame();
        window.setTitle("Chess");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(1000,900);
        window.setLocationRelativeTo(null);

        //creation of the mainPanel
        //hols the buttons needed for the board
        mainPanel = new JPanel();
        //this sets the layout so the buttons are correctly fitted
        mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        mainPanel.setBounds(0,0,800,800);

        //added this panel to the window
        window.add(mainPanel);

        //set the background to white
        mainPanel.setBackground(Color.WHITE);
        char[] horLtr = {'A','B','C','D','E','F','G','H'};

        //setting up the buttons to make the chess board
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
                //needed so that the event handler can access the tile it holds
                final Tile tile = chessBoard[row][col];
                //Interface usage here "ActionEvent -> ..." lambda
                chessBoard[row][col].getButton().addActionListener((ActionEvent e) -> {
                    GameMechanics.updateLastTile(tile);
                });
                mainPanel.add(chessBoard[row][col].getButton());
            }
        }

        //creation of the info panel
        //shows whose turn it is
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(Color.WHITE);
        JLabel temp = new JLabel("Current Side to move piece : W");
        infoLabel = temp;
        infoPanel.add(temp);

        //The buttons that control what a pawn will promote to
        JButton promoteToRook = new JButton("Promote to Rook");
        promoteToRook.addActionListener((ActionEvent e) -> {
            if(canPromote){
                GameMechanics.promotePawn(new Rook(GameMechanics.getPreviousTurnName() + ".Rook"));
            }
        });
        promoteToRook.setLayout(new FlowLayout(FlowLayout.RIGHT));
        promoteToRook.setPreferredSize((new Dimension(150,50)));
        infoPanel.add(promoteToRook);

        JButton promoteToKnight = new JButton("Promote to knight");
        promoteToKnight.addActionListener((ActionEvent e) -> {
            if(canPromote){
                GameMechanics.promotePawn(new Knight(GameMechanics.getPreviousTurnName() + ".Knight"));
            }
        });
        promoteToKnight.setLayout(new FlowLayout(FlowLayout.RIGHT));
        promoteToKnight.setPreferredSize((new Dimension(150,50)));

        infoPanel.add(promoteToKnight);

        JButton promoteToBishop = new JButton("Promote to Bishop");
        promoteToBishop.addActionListener((ActionEvent e) -> {
            if(canPromote){
                GameMechanics.promotePawn(new Bishop(GameMechanics.getPreviousTurnName() + ".Bishop"));
            }
        });
        promoteToBishop.setLayout(new FlowLayout(FlowLayout.RIGHT));
        promoteToBishop.setPreferredSize((new Dimension(150,50)));

        infoPanel.add(promoteToBishop);

        JButton promoteToQueen = new JButton("Promote to Queen");
        promoteToQueen.addActionListener((ActionEvent e) -> {
            if(canPromote){
                GameMechanics.promotePawn(new Queen(GameMechanics.getPreviousTurnName() + ".Queen"));
            }
        });
        promoteToQueen.setLayout(new FlowLayout(FlowLayout.RIGHT));
        promoteToQueen.setPreferredSize((new Dimension(150,50)));
        infoPanel.add(promoteToQueen);

        //setting the layout and a set size of this panel
        infoPanel.setBounds(800,0,300,800);
        infoPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));

        //added to the window
        window.add(infoPanel);


        //sets the window to be visible
        window.setLayout(null);
        window.setVisible(true);
    }//end of initializer method


}
