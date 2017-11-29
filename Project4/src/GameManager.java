import java.awt.Color;

public class GameManager
{

	public enum Team
	{
		Black, White
	}

	private enum Phase
	{
		pieceSelectPhase, posSelectPhase,end
	}
	
	public static GameManager instance;// �̱���

	private Team currentTurn;// ���� ������ ������
	
	private Phase currentPhase;

	private ChessGUI myGUI;

	private ChessPanel chessPanel;

	private Chesspiece currentSelectedPiece;
	
	public GameManager()
	{
		if (instance == null)
		{
			instance = this;
		}
	}
	
	private void setNewGame()
	{
		chessPanel=new ChessPanel();
		myGUI=new ChessGUI(chessPanel);
		currentTurn=Team.White;
		currentPhase=Phase.pieceSelectPhase;
	}
	
	private void turnChange()//���� ������ �Ѿ
	{
		if(currentTurn==Team.Black) currentTurn=Team.White;
		else currentTurn=Team.Black;
		myGUI.turnChange();
		currentPhase=Phase.pieceSelectPhase;
	}
	
	public void Restart()//���������
	{
		setNewGame();
	}
	
	public void cellSelected(Vector2 pos)
	{
		switch(currentPhase)
		{
		case pieceSelectPhase:
			selectPiece(pos);
			break;
		case posSelectPhase:
			selectPos(pos);
			break;
		}
	}
	
	private void selectPiece(Vector2 pos)
	{
		currentSelectedPiece= chessPanel.getChesspiece(pos);
		if(currentSelectedPiece!=null&&
				currentSelectedPiece.getTeam()==currentTurn)
		{
			myGUI.setCellColor(currentSelectedPiece.MovablePos(),Color.RED);
			currentPhase=Phase.posSelectPhase;
		}
	}
	
	private void selectPos(Vector2 pos)
	{
		Vector2 originalPos=currentSelectedPiece.pos();
		if(chessPanel.move(currentSelectedPiece, pos))
		{
			turnChange();
			myGUI.setCellText(pos,currentSelectedPiece.code());
			myGUI.setCellText(originalPos, "");
		}
	}
	
	public void check()
	{
		myGUI.notice("check");
	}
	
	public void checkmate()
	{
		myGUI.notice("Check Mate!"+currentTurn.toString()+" Win!");
		myGUI.showRetryButton();
	}
}
