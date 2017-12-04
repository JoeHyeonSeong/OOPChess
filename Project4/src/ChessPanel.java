import java.util.ArrayList;

public class ChessPanel
{
	private ArrayList<Chesspiece> myPanel = new ArrayList<Chesspiece>();
	public final static int panelLen = 8;

	public Chesspiece getChesspiece(Vector2 pos)
	{
		for (int i = 0; i < myPanel.size(); i++)
		{
			if (myPanel.get(i).pos().equals(pos))
			{
				return myPanel.get(i);
			}
		}
		return null;
	}

	public void setInitialPlace()
	{
		ArrayList<Chesspiece> tempList = new ArrayList<Chesspiece>();
		tempList.add(new Rook(Team.Black, new Vector2(0, 0), this));
		tempList.add(new Knight(Team.Black, new Vector2(0, 1), this));
		tempList.add(new Bishop(Team.Black, new Vector2(0, 2), this));
		tempList.add(new Queen(Team.Black, new Vector2(0, 3), this));
		tempList.add(new King(Team.Black, new Vector2(0, 4), this));
		tempList.add(new Bishop(Team.Black, new Vector2(0, 5), this));
		tempList.add(new Knight(Team.Black, new Vector2(0, 6), this));
		tempList.add(new Rook(Team.Black, new Vector2(0, 7), this));

		tempList.add(new Rook(Team.White, new Vector2(7, 0), this));
		tempList.add(new Knight(Team.White, new Vector2(7, 1), this));
		tempList.add(new Bishop(Team.White, new Vector2(7, 2), this));
		tempList.add(new Queen(Team.White, new Vector2(7, 3), this));
		tempList.add(new King(Team.White, new Vector2(7, 4), this));
		tempList.add(new Bishop(Team.White, new Vector2(7, 5), this));
		tempList.add(new Knight(Team.White, new Vector2(7, 6), this));
		tempList.add(new Rook(Team.White, new Vector2(7, 7), this));

		for (int i = 0; i < panelLen; i++)
		{
			tempList.add(new Pawn(Team.Black, new Vector2(1, i), this));
		}
		for (int i = 0; i < panelLen; i++)
		{
			tempList.add(new Pawn(Team.White, new Vector2(6, i), this));
		}
		myPanel = tempList;
	}

	public void delete(Chesspiece piece)
	{
		piece.destroy();
		myPanel.remove(piece);
	}

	public static boolean canGo(Vector2 pos)
	{
		return (pos.X() < panelLen && pos.X() >= 0 && pos.Y() < panelLen && pos.Y() >= 0);
	}

	public boolean checkmate(King king)
	{
		Vector2[] nextposes = king.MovablePos();
		System.out.println(nextposes.length);
		for (int i = 0; i < nextposes.length; i++)
		{
			System.out.println(nextposes[i].toString());
			boolean hunterExist = false;// 날 죽일수 있는 애
			for (int j = 0; j < myPanel.size(); j++)
			{
				Chesspiece temp = myPanel.get(j);
				System.out.println(temp.getClass().getName()+temp.canGo(nextposes[i])+temp.pos());
				if (temp.canGo(nextposes[i]) && temp.getTeam() != king.getTeam())// 죽일수있음
				{
					
					hunterExist = true;
				}

			}
			if (!hunterExist)
			{
				return false;
			}
		}
		System.out.print("pass");
		for (int i = 0; i < myPanel.size(); i++)
		{
			Chesspiece enermy = myPanel.get(i);
			if (enermy.getTeam() != king.getTeam() && enermy.canGo(king.pos()))
			{
				for (int j = 0; j < myPanel.size(); j++)
				{
					Chesspiece enermyCatcher = myPanel.get(j);
					if (enermyCatcher.canGo(enermy.pos()))
					{
						return false;
					}
				}
			}

		}
		return true;
	}
}
