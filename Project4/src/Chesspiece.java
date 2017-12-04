
public abstract class Chesspiece
{
	protected Team myTeam;// �궡媛� �늻援ы��씤吏�

	public Team getTeam()
	{
		return myTeam;
	}

	protected String myCode;// �굹�쓽 肄붾뱶

	public String code()
	{
		return myCode;
	}

	protected Vector2 myPos;// �궡 �쁽�옱 �쐞移�

	public Vector2 pos()
	{
		return myPos;
	}

	protected ChessPanel panel;

	public Chesspiece(Team team, Vector2 position, ChessPanel panel)
	{
		myTeam = team;
		myPos = position;
		this.panel = panel;
	}

	public abstract Vector2[] MovablePos();

	public boolean move(Vector2 position)
	{
		boolean validPos=false;
		Vector2[] movablePos=this.MovablePos();
		//媛덉닔�엳�뒗 怨녹씤吏� 泥댄겕
		for(int i=0;i<movablePos.length;i++)
		{
			if(movablePos[i].equals(position))
			{
				validPos=true;
				break;
			}
		}
		
		if (validPos)
		{
			Chesspiece nextPosPiece=panel.getChesspiece(position);
			if(nextPosPiece!=null&&nextPosPiece.getTeam()!=myTeam)
			{
				panel.delete(nextPosPiece);
			}
			myPos = position;
			checkCheck();
		}
		return validPos;
	}

	public void destroy()
	{
	}
	

	public boolean canGo(Vector2 pos)
	{
		Vector2[] nextPoses=MovablePos();
		for(int i=0;i<nextPoses.length;i++)
		{
			if(nextPoses[i].equals(pos)) return true;
		}
		return false;
	}
	
	private void checkCheck()//piece媛� �떎�쓬�뿉 媛� �닔 �엳�뒗 �쐞移섏뿉 �쟻 �궧 �엳�뒗吏� �뙋�떒
	{
		Vector2[] nextPoses=MovablePos();
		for(int i=0;i<nextPoses.length;i++)
		{
			Chesspiece nextPiece=panel.getChesspiece(nextPoses[i]);
			if(nextPiece!=null&&nextPiece.getTeam()!=myTeam&&nextPiece instanceof King)
			{
				if(panel.checkmate((King)nextPiece))
				{
					GameManager.instance.gameOver();
				}
				else
				{
					GameManager.instance.check();
				}
				
			}
		}
	}
}
