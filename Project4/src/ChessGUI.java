import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChessGUI extends JFrame
{
	JPanel jpanel = new JPanel();
	JButton[][] chessSquares = new JButton[8][8];
	
	JPanel glass = (JPanel) super.getGlassPane();
	JLabel message = new JLabel();
	JButton restart = new JButton("Restart?");
	
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
				chessSquares[i][j].setText(String.valueOf(i) + "," + String.valueOf(j));
				
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
	
	public void turnChange()//�������϶� �ؾ� �� �ൿ �� ������, Black's Turn!�̶�� ���ϱ�
	{
		for(int i = 0; i < chessSquares.length; i++) {
			for(int j = 0; j< chessSquares[i].length; j++) {
				jpanel.remove(chessSquares[i][j]);
			}
		}

		
		for(int i = 0; i < chessSquares.length; i++) {
			for(int j = 0; j< chessSquares[i].length; j++) {
				jpanel.add(chessSquares[j][i]);
				jpanel.repaint();
			}
		}
	}

	public void setCellColor(Vector2[] buttonIndexes,Color a)//buttonIndexes�� �ش��ϴ� ���� �� �ٲ���
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
	
	public void notice(String args)//ȭ�鿡 args���� ǥ�� ex)Black's Turn!
	{
		message.setText(args);
		glass.add(message);
		glass.setVisible(true);
		super.repaint();
	}
	
	public void showRetryButton()//����۹�ư�� ������
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
