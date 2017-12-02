import java.util.ArrayList;

public class Pawn extends Chesspiece
{
	private boolean firstmove = true;

	public Pawn(Team team, Vector2 position, ChessPanel panel)
	{
		super(team, position, panel);
		myCode = "♙";
	}

	@Override
	public Vector2[] MovablePos()
	{
		ArrayList<Vector2> resultList = new ArrayList<Vector2>();
		Vector2[] attackPos;
		Vector2 movePos;
		if (myTeam == Team.White)
		{
			attackPos = new Vector2[]
			{ new Vector2(-1, 1), new Vector2(-1, -1) };
			movePos = new Vector2(-1, 0);
		} else
		{
			attackPos = new Vector2[]
			{ new Vector2(1, 1), new Vector2(1, -1) };
			movePos = new Vector2(1, 0);
		}

		for (int i = 0; i < attackPos.length; i++)// 공격 체크
		{
			Vector2 nextPos = attackPos[i].add(myPos);
			if (ChessPanel.canGo(nextPos))
			{
				Chesspiece nextPosPiece = panel.getChesspiece(nextPos);
				if (nextPosPiece != null && nextPosPiece.getTeam() != myTeam)
				{
					resultList.add(nextPos);
				}
			}
		}

		if (resultList.isEmpty())//직진 체크
		{
			Vector2 nextPos = movePos.add(myPos);
			if (ChessPanel.canGo(nextPos) && panel.getChesspiece(nextPos) == null)
			{
				resultList.add(nextPos);
			}
			if(firstmove&&!resultList.isEmpty())
			{
				Vector2 nextPos2 = myPos.add(movePos.mul(2));
				if (ChessPanel.canGo(nextPos2) && panel.getChesspiece(nextPos2) == null)
				{
					resultList.add(nextPos2);
				}
			}
		}
		Vector2[] result=new Vector2[resultList.size()];
		result=(Vector2[]) resultList.toArray(result);
		return result;
	}

	public boolean move(Vector2 position)
	{
		boolean result = super.move(position);
		if (result)
		{
			firstmove = false;
		}
		return result;
	}
}
