
public abstract class Chesspiece
{
	protected Team myTeam;// ���� ����������

	public Team getTeam()
	{
		return myTeam;
	}

	protected String myCode;// ���� �ڵ�

	public String code()
	{
		return myCode;
	}

	protected Vector2 myPos;// �� ���� ��ġ

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
		//�����ִ� ������ üũ
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
	
	public boolean canGo(Vector2 pos)
	{
		Vector2[] nextPoses=MovablePos();
		for(int i=0;i<nextPoses.length;i++)
		{
			if(nextPoses.equals(pos)) return true;
		}
		return false;
	}
	
	private void checkCheck()//piece�� ������ �� �� �ִ� ��ġ�� �� ŷ �ִ��� �Ǵ�
	{
		Vector2[] nextPoses=MovablePos();
		for(int i=0;i<nextPoses.length;i++)
		{
			Chesspiece nextPiece=panel.getChesspiece(nextPoses[i]);
			if(nextPiece!=null&&nextPiece.getTeam()!=myTeam&&nextPiece instanceof King)
			{
				if(panel.checkmate((King)nextPiece))
				{
					GameManager.instance.checkmate();
				}
				else
				{
					GameManager.instance.check();
				}
				
			}
		}
	}
}
