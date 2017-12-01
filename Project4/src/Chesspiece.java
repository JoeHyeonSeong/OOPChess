
public abstract class Chesspiece
{
	protected Team myTeam;// 내가 누구팀인지

	public Team getTeam()
	{
		return myTeam;
	}

	protected String myCode;// 나의 코드

	public String code()
	{
		return myCode;
	}

	protected Vector2 myPos;// 내 현재 위치

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
		//갈수있는 곳인지 체크
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
			myPos = myPos.add(position);
			return true;
		}
		return false;
	}
}
