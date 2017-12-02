
public class King extends RelativeMovePiece
{

	public King(Team team, Vector2 position, ChessPanel panel)
	{
		super(team, position, panel);
		myCode="♔";
		this.relativeMovablePos=new Vector2[] {
				new Vector2(1,1),new Vector2(1,-1),new Vector2(-1,-1),new Vector2(-1,-1),
				new Vector2(0,1),new Vector2(1,0),new Vector2(0,-1),new Vector2(-1,0)
		};
	}


}
