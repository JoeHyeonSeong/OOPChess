import java.awt.Color;

public class GameManager
{
	public static void main(String[] args)
	{
		GameManager myGame=new GameManager();
		myGame.start();
	}

	private enum Phase
	{
		pieceSelectPhase, posSelectPhase, end
	}

	public static GameManager instance;// 싱글톤

	private Team currentTurn;// 현재 누구의 턴인지

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


	private void turnChange()// 다음 턴으로 넘어감
	{
		if (currentTurn == Team.Black)
			currentTurn = Team.White;
		else
			currentTurn = Team.Black;
		myGUI.turnChange();
		currentPhase = Phase.pieceSelectPhase;
	}

	public void start()// 시작해줌
	{
		chessPanel = new ChessPanel();
		chessPanel.setInitialPlace();
		myGUI = new ChessGUI();
		for(int i=0;i<ChessPanel.panelLen;i++)
		{
			for(int j=0;j<ChessPanel.panelLen;j++)
			{
				Vector2 currentPos= new Vector2(i,j);
				Chesspiece tempPiece= chessPanel.getChesspiece(currentPos);
				if(tempPiece!=null)
				{
					myGUI.setCellText(currentPos,tempPiece.myCode);
				}
				else
				{
					myGUI.setCellText(currentPos,"");
				}
			}
		}
		currentTurn = Team.White;
		currentPhase = Phase.pieceSelectPhase;
	}

	public void cellSelected(Vector2 pos)
	{
		System.out.println(currentPhase.toString()+pos.toString());
		switch (currentPhase)
		{
		case pieceSelectPhase:
			selectPiece(pos);
			break;
		case posSelectPhase:
			selectPos(pos);
			break;
			default:
				break;
		}
	}

	private void selectPiece(Vector2 pos)
	{
		currentSelectedPiece = chessPanel.getChesspiece(pos);
		if (currentSelectedPiece != null && currentSelectedPiece.getTeam() == currentTurn)
		{
			myGUI.setCellColor(currentSelectedPiece.MovablePos(), Color.RED);
			currentPhase = Phase.posSelectPhase;
		}
	}

	private void selectPos(Vector2 pos)
	{
		Vector2 originalPos = currentSelectedPiece.pos();
		if (currentSelectedPiece.move(pos))
		{
			turnChange();
			myGUI.setCellText(pos, currentSelectedPiece.code());
			myGUI.setCellText(originalPos, "");
		}
		else
		{
			currentPhase=Phase.pieceSelectPhase;
		}
		myGUI.backgroundRepaint();
	}

	public void check()
	{
		myGUI.notice("check");
	}

	public void checkmate()
	{
		myGUI.notice("Check Mate!" + currentTurn.toString() + " Win!");
		myGUI.showRetryButton();
	}

}
