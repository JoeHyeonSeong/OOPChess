public class Bishop extends LinearMovePiece
{

	public Bishop(Team team, Vector2 position, ChessPanel panel)
	{
		super(team, position, panel);
		myCode="♗";
		dir=new Vector2[] {new Vector2(1,1),new Vector2(1,-1),new Vector2(-1,-1),new Vector2(-1,-1)};
	}

}
