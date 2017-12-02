import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChessGUI extends JFrame
{
	JPanel jpanel = new JPanel();
	JButton[][] chessSquares = new JButton[8][8];
	
	JPanel glass = (JPanel) super.getGlassPane();
	JButton restart = new JButton("Restart?");
	
	JLabel message = new JLabel();
	
	public ChessGUI(ChessPanel panel)
	{
	}
	
	public ChessGUI() {
		super("Chess Simulator");
		setLocation(200, 400);
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		jpanel.setLayout(new GridLayout(8,8));
		for(int i = 0; i < chessSquares.length; i++) {
			for(int j = 0; j< chessSquares[i].length; j++) {
				JButton square = new JButton();
				square.setMargin(new Insets(0, 0, 0, 0));
				
				chessSquares[i][j] = square;
				//chessSquares[i][j].setText(String.valueOf(i) + "," + String.valueOf(j));
				
				final Integer x = new Integer(i);
				final Integer y = new Integer(j);
				
				chessSquares[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GameManager.instance.cellSelected(new Vector2(x, y));
					}
				});
				
				jpanel.add(chessSquares[i][j]);
			}
		}
		backgroundRepaint();
		add(jpanel);
		
	}

	public void backgroundRepaint()
	{
		for(int i=0;i<chessSquares.length;i++)
		{
			for(int j=0;j<chessSquares.length;j++)
			{
				if((i % 2 == 1) && (j % 2 == 1) || (i % 2 == 0) && (j % 2 == 0)) {
					chessSquares[i][j].setBackground(new Color(0.741f, 0.588f, 0.372f));
				} else {
					chessSquares[i][j].setBackground(new Color(0.345f, 0.231f,0.168f));
				}
			}
		}
	}
	
	public void turnChange(Team toteam)//다음턴일때 해야 할 행동 판 돌리기, Black's Turn!이라고 말하기
	{
		if(toteam == Team.Black) {
			for(int i = 0; i < chessSquares.length; i++) {
				for(int j = 0; j< chessSquares[i].length; j++) {
					jpanel.remove(chessSquares[i][j]);
				}
			}
			
			notice("Team BLACK's Turn!");

			for(int i = chessSquares.length - 1; i >= 0 ; i--) {
				for(int j = chessSquares[i].length - 1; j >= 0; j--) {
					jpanel.add(chessSquares[i][j]);
					
				}
			}
			jpanel.repaint();
		} else {
			for(int i = chessSquares.length - 1; i >= 0 ; i--) {
				for(int j = chessSquares[i].length - 1; j >= 0; j--) {
					jpanel.remove(chessSquares[i][j]);
				}
			}
			
			notice("Team WHITE's Turn!");
			
			for(int i = 0; i < chessSquares.length; i++) {
				for(int j = 0; j< chessSquares[i].length; j++) {
					jpanel.add(chessSquares[i][j]);
					
				}
			}
			jpanel.repaint();
		}
	}
	
	public void setCellColor(Vector2[] buttonIndexes,Color a)//buttonIndexes에 해당하는 곳들 색 바꿔줌
	{
		for(int i = 0; i< buttonIndexes.length; i++) {
			chessSquares[buttonIndexes[i].X()][buttonIndexes[i].Y()].setBackground(a);
		}
	}
	
	public void setCellText(Vector2 index, String arg,Color col)
	{
		JButton b=chessSquares[index.X()][index.Y()];
		b.setText(arg);
		b.setFont(b.getFont().deriveFont(60.0f));
		b.setForeground(col);;
	}
	
	public void notice(String args)//화면에 args내용 표시 ex)Black's Turn!
	{
		message.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 35));
		message.setForeground(Color.RED);
		Timer messageTimer = new Timer();
		TimerTask timertask = new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				message.setText("");
			}
		};
		
		message.setText(args);
		glass.add(message);
		glass.setVisible(true);
		super.repaint();
		
		messageTimer.schedule(timertask, 3000);
	}
	
	public void showRetryButton()//재시작버튼을 보여줌
	{
		restart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GameManager.instance.start();				
			}
		});
		glass.add(restart);
		glass.setVisible(true);
		super.repaint();
		
	}
}
