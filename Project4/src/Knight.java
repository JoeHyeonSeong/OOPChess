public class Knight extends RelativeMovePiece
{

	public Knight(Team team, Vector2 position,ChessPanel panel)
	{
		super(team, position,panel);
		myCode="♘";
		this.relativeMovablePos=new Vector2[]
				{
						new Vector2(1,2),
						new Vector2(-1,2),
						new Vector2(1,-2),
						new Vector2(-1,-2),
						new Vector2(2,1),
						new Vector2(-2,1),
						new Vector2(2,-1),
						new Vector2(-2,-1),
				};
	}

}
