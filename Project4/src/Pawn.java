

public class Pawn extends Chesspiece
{

	public Pawn(Team team, Vector2 position, ChessPanel panel)
	{
		super(team, position,panel);
		myCode="♙";
	}

	@Override
	public Vector2[] MovablePos()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
