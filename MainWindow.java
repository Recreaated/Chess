//import com.sun.tools.javac.Main;

import com.sun.tools.javac.Main;

import javax.swing.*;
//import javax.swing.pla.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

public class MainWindow {
    private Tile[][] chessBoard;
    private JFrame window;
    private JPanel MainPanel;
    private final Piece[][] startingLayout = {{new Rook("B.Rook"),new Knight("B.Knight"),new Bishop("B.ishop"),new Queen("B.Queen"),new King("B.King"),new Bishop("B.Bishop"),new Knight("B.Knight"),new Rook("B.Rook")},
        {new Pawn("B.Pawn"),new Pawn("B.Pawn"),new Pawn("B.Pawn"),new Pawn("B.Pawn"),new Pawn("B.Pawn"),new Pawn("B.Pawn"),new Pawn("B.Pawn"),new Pawn("B.Pawn")},
        {new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece()},
        {new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece()},
        {new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece()},
        {new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece()},
        {new Pawn("W.Pawn"),new Pawn("W.Pawn"),new Pawn("W.Pawn"),new Pawn("W.Pawn"),new Pawn("W.Pawn"),new Pawn("W.Pawn"),new Pawn("W.Pawn"),new Pawn("W.Pawn")},
        {new Rook("W.Rook"),new Knight("W.Knight"),new Bishop("W.Bishop"),new Queen("W.Queen"),new King("W.King"),new Bishop("W.Bishop"),new Knight("W.Knight"),new Rook("W.Rook")}};

    public JPanel[] chessRows = new JPanel[8];

    public MainWindow(){
        innit();
    }//end of constructor

    private void innit(){
        window = new JFrame();
        window.setTitle("Chess");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(1000,900);
        window.setLocationRelativeTo(null);

        MainPanel = new JPanel();


        


        MainPanel.setBackground(Color.WHITE);
        char[] horLtr = {'A','B','C','D','E','F','G','H'};
        chessBoard = new Tile[8][8];
        for(int row = 0; row < 8; row++) {
            chessRows[row] = new JPanel();
            for(int col = 0; col < 8; col++) {
                String label = ""+ horLtr[row] + (col+1);
                String pieceName = startingLayout[row][col].getName();
                chessBoard[row][col] =new Tile(pieceName,row,col, startingLayout[row][col]);
                if (((row + col) % 2 == 0)) {
                    chessBoard[row][col].getButton().setBackground(new Color(225,225,225));
                } else {
                    chessBoard[row][col].getButton().setBackground(new Color(0,148,56));
                }
                chessBoard[row][col].getButton().setPreferredSize((new Dimension(100,100)));
                //Interface usage here "ActionEvent -> ..." lambda
                chessBoard[row][col].getButton().addActionListener((ActionEvent e) -> {
                    System.out.println(label + " button was pressed. Contains :" + pieceName);
                });
                chessRows[row].add(chessBoard[row][col].getButton());
            }
            chessRows[row].setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
            MainPanel.add(chessRows[row]);
        }
/*
        JPanel infoPanel = new JPanel();
        JButton temp = new JButton(new String("This is a test of this formated field\nthis should work in theory\nbut no one knows"));
                //new JLabel(new String("This is a test of this formated field\nthis should work in theory\nbut no one knows"));
//        temp.setMinimumSize(new Dimension(50,800));
//        temp.setMaximumSize(new Dimension(-1,800));
        temp.setPreferredSize(new Dimension(-1,800));
        temp.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        infoPanel.add(temp);

        infoPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        MainPanel.add(infoPanel);
*/
        MainPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        window.add(MainPanel, BorderLayout.CENTER);
        window.setSize(1000,900);
//        window.setResizable(false);
        window.setVisible(true);
    }//end of initializer method
}
