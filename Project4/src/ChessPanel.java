import java.util.ArrayList;

public class ChessPanel
{
	private Chesspiece[] myPanel=new Chesspiece[32];
	public final static int panelLen=8;
	
	private boolean checkCheck(Chesspiece piece)//piece가 다음에 갈 수 있는 위치에 적 킹 있는지 판단
	{
		return false;
	}
	
	public Chesspiece getChesspiece(Vector2 pos)
	{
		for(int i=0;i<myPanel.length;i++)
		{
			if(myPanel[i].pos().equals(pos))
			{
				return myPanel[i];
			}
		}
		return null;
	}
	
	public void setInitialPlace()
	{
		ArrayList<Chesspiece> tempList= new ArrayList<Chesspiece>();
		tempList.add(new Rook(Team.Black,new Vector2(0,0),this));
		tempList.add(new Knight(Team.Black,new Vector2(0,1),this));
		tempList.add(new Bishop(Team.Black,new Vector2(0,2),this));
		tempList.add(new Queen(Team.Black,new Vector2(0,3),this));
		tempList.add(new King(Team.Black,new Vector2(0,4),this));
		tempList.add(new Bishop(Team.Black,new Vector2(0,5),this));
		tempList.add(new Knight(Team.Black,new Vector2(0,6),this));
		tempList.add(new Rook(Team.Black,new Vector2(0,7),this));
		
		tempList.add(new Rook(Team.White,new Vector2(7,0),this));
		tempList.add(new Knight(Team.White,new Vector2(7,1),this));
		tempList.add(new Bishop(Team.White,new Vector2(7,2),this));
		tempList.add(new Queen(Team.White,new Vector2(7,3),this));
		tempList.add(new King(Team.White,new Vector2(7,4),this));
		tempList.add(new Bishop(Team.White,new Vector2(7,5),this));
		tempList.add(new Knight(Team.White,new Vector2(7,6),this));
		tempList.add(new Rook(Team.White,new Vector2(7,7),this));
		
		for(int i=0;i<panelLen;i++)
		{
			tempList.add(new Pawn(Team.Black,new Vector2(1,i),this));
		}
		for(int i=0;i<panelLen;i++)
		{
			tempList.add(new Pawn(Team.White,new Vector2(6,i),this));
		}
		myPanel=(Chesspiece[])tempList.toArray(myPanel);
	}
	
public static boolean canGo(Vector2 pos)
{
	return (pos.X()<panelLen&&pos.X()>0&&pos.Y()<panelLen&&pos.Y()>0);
}
}
