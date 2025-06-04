//import com.sun.tools.javac.Main;

import javax.swing.*;
//import javax.swing.pla.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

public class MainWindow {


    private final Piece[][] startingLayout = {{new Rook("Rook"),new Knight("Knight"),new Bishop("Bishop"),new Queen("Queen"),new King("King"),new Bishop("Bishop"),new Knight("Knight"),new Rook("Rook")},
        {new Pawn("Pawn"),new Pawn("Pawn"),new Pawn("Pawn"),new Pawn("Pawn"),new Pawn("Pawn"),new Pawn("Pawn"),new Pawn("Pawn"),new Pawn("Pawn")},
        {new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece()},
        {new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece()},
        {new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece()},
        {new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece(),new Piece()},
        {new Pawn("Pawn"),new Pawn("Pawn"),new Pawn("Pawn"),new Pawn("Pawn"),new Pawn("Pawn"),new Pawn("Pawn"),new Pawn("Pawn"),new Pawn("Pawn")},
        {new Rook("Rook"),new Knight("Knight"),new Bishop("Bishop"),new Queen("Queen"),new King("King"),new Bishop("Bishop"),new Knight("Knight"),new Rook("Rook")}};
    public MainWindow(){
        innit();
    }//end of constructor

    private void innit(){
        JFrame window = new JFrame();
        window.setTitle("Chess");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setResizable(false);
        window.setSize(850,850);
        window.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        window.add(panel, BorderLayout.CENTER);

/*
        panel.setBackground(new Color(200,200,200));
        char[] horLtr = {'A','B','C','D','E','F','G','H'};
        Tile[][] chessBoard = new Tile[8][8];
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                String label = ""+ horLtr[row] + (col+1);
                String pieceName = startingLayout[row][col].getName();
                chessBoard[row][col] =new Tile(pieceName,row,col, startingLayout[row][col]);
                chessBoard[row][col].getButton().setLocation(row*100+50,col*100+50);
                if (((row + col) % 2 == 0)) {
                    chessBoard[row][col].getButton().setBackground(Color.white);
                } else {
                    chessBoard[row][col].getButton().setBackground(new Color(0,183,43));
                }
                chessBoard[row][col].getButton().setPreferredSize((new Dimension(100,100)));
                //Interface usage here "ActionEvent -> ..." lambda
                chessBoard[row][col].getButton().addActionListener((ActionEvent e) -> {
                    System.out.println(label + " button was pressed. Contains :" + pieceName);
                });
                panel.add(chessBoard[row][col].getButton());
            }

        }
**/
        window.setVisible(true);
    }//end of initializer method
}
