import java.util.ArrayList;

public class RelativeMovePiece extends Chesspiece
{
	protected Vector2[] relativeMovablePos;

	public RelativeMovePiece(Team team, Vector2 position, ChessPanel panel)
	{
		super(team, position, panel);
	}

	@Override
	public Vector2[] MovablePos()
	{
		ArrayList<Vector2> resultList= new ArrayList<Vector2>();
		for(int i=0;i<relativeMovablePos.length;i++)
		{
			Vector2 nextPos=myPos.add(relativeMovablePos[i]);
		if(ChessPanel.canGo(nextPos))
		{
			Chesspiece nextPosPiece=panel.getChesspiece(nextPos);
			if(nextPosPiece==null)
			{
				resultList.add(nextPos);
			}
			else if(nextPosPiece.getTeam()!=myTeam)
			{
				resultList.add(nextPos);
			}
		}

		}
		Vector2[] result=new Vector2[resultList.size()];
		result=(Vector2 [])resultList.toArray(result);
		return result;
	}

}
