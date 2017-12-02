import java.util.ArrayList;

public class LinearMovePiece extends Chesspiece
{
	public LinearMovePiece(Team team, Vector2 position, ChessPanel panel)
	{
		super(team, position, panel);
	}

	protected Vector2[] dir;

	@Override
	public Vector2[] MovablePos()
	{
		ArrayList<Vector2> resultList = new ArrayList<Vector2>();
		for (int i = 0; i < dir.length; i++)
		{
			int cnt = 1;
			while (true)
			{
				Vector2 nextPos = myPos.add(dir[i].mul(cnt));
				if (ChessPanel.canGo(nextPos))
				{
					Chesspiece nextPosPiece = panel.getChesspiece(nextPos);
					if (nextPosPiece == null)
					{
						resultList.add(nextPos);
					}
					else
					{
						if (nextPosPiece.getTeam() != myTeam)
						{
							resultList.add(nextPos);
							break;
						}
						break;
					}
				} else
				{
					break;
				}
				cnt++;
			}
		}
		Vector2[] result=new Vector2[resultList.size()];
		result= (Vector2[]) resultList.toArray(result);
		return result;
	}

}
