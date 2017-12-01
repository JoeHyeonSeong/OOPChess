import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChessGUI extends JFrame
{
	JPanel jpanel = new JPanel();
	JLabel message = new JLabel();
	JButton[][] chessSquares = new JButton[8][8];
	
	public ChessGUI(ChessPanel panel)
	{
	}
	
	public ChessGUI() {
		super("ChessMaster");
		setLocation(200, 400);
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		jpanel.setLayout(new GridLayout(8,8));
		for(int i = 0; i < chessSquares.length; i++) {
			for(int j = 0; j< chessSquares[i].length; j++) {
				JButton square = new JButton();
				square.setMargin(new Insets(0, 0, 0, 0));
				if((i % 2 == 1) && (j % 2 == 1) || (i % 2 == 0) && (j % 2 == 0)) {
					square.setBackground(Color.WHITE);
				} else {
					square.setBackground(Color.BLACK);
				}
				chessSquares[i][j] = square;
				chessSquares[i][j].setText(String.valueOf(i) + "," + String.valueOf(j));
				jpanel.add(chessSquares[i][j]);
			}
		}
		add(jpanel);
	}

	public void turnChange()//다음턴일때 해야 할 행동 판 돌리기, Black's Turn!이라고 말하기
	{
		for(int i = 0; i < chessSquares.length; i++) {
			for(int j = 0; j< chessSquares[i].length; j++) {
				jpanel.remove(chessSquares[i][j]);
			}
		}
		
		notice("Next Players Turn!");
		
		for(int i = 0; i < chessSquares.length; i++) {
			for(int j = 0; j< chessSquares[i].length; j++) {
				jpanel.add(chessSquares[j][i]);
				jpanel.repaint();
			}
		}
	}

	public void setCellColor(Vector2[] buttonIndexes,Color a)//buttonIndexes에 해당하는 곳들 색 바꿔줌
	{
		for(int i = 0; i< buttonIndexes.length; i++) {
			chessSquares[buttonIndexes[i].X()][buttonIndexes[i].Y()].setBackground(Color.MAGENTA);
		}
		
	}
	
	public void setCellText(Vector2 index, String arg)
	{
		chessSquares[index.X()][index.Y()].setText(arg);
	}
	
	public void notice(String args)//화면에 args내용 표시 ex)Black's Turn!
	{
		
	}
	
	public void showRetryButton()//재시작버튼을 보여줌
	{
		
	}
	
	public static void main(String[] args) {
		new ChessGUI();
	}
}
